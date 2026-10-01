# Mock 7: Hard - Concurrency & Threading

**Duration**: 60 minutes | **Difficulty**: Hard | **Topics**: Thread-safety, Synchronization, AtomicInteger, ReentrantLock, Race Conditions

---

## Section 1: Conceptual Questions (10 min)

**Q1** (3 min): Explain race conditions. Why does `balance++` fail in multi-threaded contexts even with 4 instructions?

**Q2** (3 min): Compare `synchronized`, `AtomicInteger`, and `ReentrantLock`. When would you use each?

**Q3** (2 min): What's the difference between `synchronized` method and `synchronized(this)` block?

**Q4** (2 min): Explain `volatile`. When is it sufficient instead of `synchronized`?

---

## Section 2: Coding Problem (40 min)

### Problem: Thread-Safe BankAccount with 3 Implementations

**Requirements**:

1. Create an interface `Account`:
   - `void deposit(double amount)`
   - `void withdraw(double amount)`
   - `double getBalance()`

2. Implement 3 versions:

   **Version 1: SyncBankAccount** (using `synchronized` keyword)
   ```
   - synchronized methods
   - Simple and clear
   - Coarse-grained locking
   ```

   **Version 2: AtomicBankAccount** (using `AtomicLong`)
   ```
   - Use AtomicLong for balance
   - No explicit locking
   - Fine-grained, lock-free
   ```

   **Version 3: LockBankAccount** (using `ReentrantLock`)
   ```
   - Explicit ReentrantLock
   - try-finally pattern
   - Fine-grained control
   ```

3. Test each version with:
   - 10 threads
   - Each thread does 1000 deposits and 500 withdrawals
   - Expected final balance (calculable from operations)
   - Measure time taken

4. Test code:
```java
public void testBankAccount(Account account, int numThreads, int opsPerThread) {
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
        t.join();  // Wait for all threads
    }
    
    long elapsed = System.nanoTime() - start;
    System.out.println("Final balance: " + account.getBalance());
    System.out.println("Time: " + (elapsed / 1_000_000) + " ms");
}
```

**Bonus**: Add a `BankAccountWithReadLock` using `ReadWriteLock` for many readers, few writers.

---

## Section 3: Behavioral (10 min)

**Q5** (5 min): Discuss: If you use `synchronized(this)`, can a subclass break thread-safety? How?

**Q6** (5 min): When would you NOT use synchronization even in multi-threaded code? Give 2 examples.

---

## Rubric

| Section | Points |
|---------|--------|
| Conceptual (Q1-4) | 20 |
| Coding (3 implementations + test + correctness) | 60 |
| Behavioral (Q5-6) | 20 |
| **Total** | **100** |

**90+**: Excellent | **75-89**: Good | **<75**: Review Modules 13-15

---

See answers: `MOCK_07_ANSWERS.md`
