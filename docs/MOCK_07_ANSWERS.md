# Mock 7 Answers: Concurrency & Threading

## Solution Code

```java
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

// Interface
interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}

// Version 1: Synchronized
public class SyncBankAccount implements Account {
    private double balance = 0;
    
    @Override
    public synchronized void deposit(double amount) {
        balance += amount;
    }
    
    @Override
    public synchronized void withdraw(double amount) {
        balance -= amount;
    }
    
    @Override
    public synchronized double getBalance() {
        return balance;
    }
}

// Version 2: AtomicLong
public class AtomicBankAccount implements Account {
    private AtomicLong balance = new AtomicLong(0);
    
    @Override
    public void deposit(double amount) {
        balance.addAndGet((long) amount);
    }
    
    @Override
    public void withdraw(double amount) {
        balance.addAndGet((long) -amount);
    }
    
    @Override
    public double getBalance() {
        return balance.get();
    }
}

// Version 3: ReentrantLock
public class LockBankAccount implements Account {
    private double balance = 0;
    private final ReentrantLock lock = new ReentrantLock();
    
    @Override
    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public void withdraw(double amount) {
        lock.lock();
        try {
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}

// Bonus: ReadWriteLock version
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccountWithReadLock implements Account {
    private double balance = 0;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    
    @Override
    public void deposit(double amount) {
        rwLock.writeLock().lock();
        try {
            balance += amount;
        } finally {
            rwLock.writeLock().unlock();
        }
    }
    
    @Override
    public void withdraw(double amount) {
        rwLock.writeLock().lock();
        try {
            balance -= amount;
        } finally {
            rwLock.writeLock().unlock();
        }
    }
    
    @Override
    public double getBalance() {
        rwLock.readLock().lock();  // Multiple threads can read simultaneously
        try {
            return balance;
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

// Test harness
public class ConcurrencyTest {
    public static void testBankAccount(Account account, int numThreads, int opsPerThread) 
            throws InterruptedException {
        long start = System.nanoTime();
        
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < opsPerThread; j++) {
                    account.deposit(10);
                    account.withdraw(5);
                }
            });
            threads[i].start();
        }
        
        for (Thread t : threads) {
            t.join();
        }
        
        long elapsed = System.nanoTime() - start;
        double expectedBalance = numThreads * opsPerThread * (10 - 5);
        System.out.println("Final balance: " + account.getBalance() + 
                         " (expected: " + expectedBalance + ")");
        System.out.println("Time: " + (elapsed / 1_000_000) + " ms");
    }
    
    public static void main(String[] args) throws InterruptedException {
        int numThreads = 10;
        int opsPerThread = 1000;
        
        System.out.println("=== SyncBankAccount ===");
        testBankAccount(new SyncBankAccount(), numThreads, opsPerThread);
        
        System.out.println("\n=== AtomicBankAccount ===");
        testBankAccount(new AtomicBankAccount(), numThreads, opsPerThread);
        
        System.out.println("\n=== LockBankAccount ===");
        testBankAccount(new LockBankAccount(), numThreads, opsPerThread);
        
        System.out.println("\n=== BankAccountWithReadLock ===");
        testBankAccount(new BankAccountWithReadLock(), numThreads, opsPerThread);
    }
}
```

## Q1: Race Conditions

**Why does `balance++` fail?**

Even though it looks like 1 instruction, it's actually 3:
1. **Load**: Read current balance into CPU register
2. **Increment**: Add 1 in register
3. **Store**: Write back to memory

With 2 threads both incrementing:
```
Thread 1: Load (10) → Increment (11) → Store (11)
Thread 2:    Load (10) → Increment (11) → Store (11)

Final: 11 (should be 12!) ← Lost update
```

Each thread reads the old value before the other writes, causing lost updates.

## Q2: Synchronized vs Atomic vs Lock

| Approach | Use Case | Pros | Cons |
|----------|----------|------|------|
| **synchronized** | Simple, coarse-grained locking | Easy, monitor-based, fair | Slower, coarse-grained, no timeouts |
| **AtomicInteger** | Simple atomic ops | Lock-free, faster, fine-grained | Only single fields, no compound ops |
| **ReentrantLock** | Complex logic, fine control | Timeouts, tryLock, conditions | More complex, manual unlock |

**When to use**:
- synchronized: Simple operations, good enough for most
- AtomicInteger: Counter, flag, simple state
- ReentrantLock: Timeouts needed, multiple conditions, complex logic

## Q3: Synchronized Method vs Block

```java
// Method: locks entire method
synchronized void deposit(double amt) {
    balance += amt;
}

// Block: locks specific code
void deposit(double amt) {
    synchronized(this) {
        balance += amt;
    }
}
```

**Difference**:
- Method: Locks entire duration, simpler but coarse-grained
- Block: Can hold lock only during critical section, more efficient
- Both use the same monitor (this)

## Q4: Volatile

`volatile` is sufficient when:
- **Single field updates** (no compound operations)
- **Reads vastly outnumber writes**
- **No dependencies between fields**

```java
// Good use of volatile
volatile boolean shutdown = false;
// One thread sets it, others read it
if (shutdown) return;

// Bad use of volatile
volatile int count;  // Doesn't prevent race on count++
```

**When NOT sufficient**:
- `count++` (needs atomicity, not just visibility)
- Dependent fields (use synchronized/lock instead)

## Q5: Synchronized(this) in Subclasses

**YES, a subclass can break thread-safety**:

```java
// Parent
public class Account {
    protected double balance = 0;
    
    public synchronized void deposit(double amt) {
        balance += amt;
    }
}

// Subclass - BREAKS thread-safety
public class BadAccount extends Account {
    @Override
    public void deposit(double amt) {  // NO synchronized!
        super.deposit(amt);  // Calls parent's sync version
        // But subclass can override and lose sync
    }
    
    public void evilTransfer(double amt) {  // No sync!
        balance += amt * 2;  // RACE CONDITION
    }
}
```

**Prevention**: Use `final` or encapsulation patterns.

## Q6: When NOT to Use Synchronization

1. **Immutable objects**: Once created, state never changes
   ```java
   final String name = "Alice";
   // No sync needed, can share safely
   ```

2. **Thread-confined data**: Only accessed by one thread
   ```java
   List<String> threadLocal = new ArrayList<>();
   // Only this thread touches it, no sync needed
   ```

3. **Stateless services**: No shared state
   ```java
   public String toUpperCase(String s) {
       return s.toUpperCase();  // No sync needed
   }
   ```

---

**Total Points**: 100  
**Expected Score**: 75+  
**Notes**: Performance may vary; AtomicInteger typically fastest, synchronized typically slowest for high contention.
