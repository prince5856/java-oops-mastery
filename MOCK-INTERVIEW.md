# Mock Interview Rounds (60 Minutes Each)

Three complete mock interviews, each simulating a real 60-minute senior/SDE-2 technical interview.

**Format**: 
- Conceptual questions (10 min)
- Coding problem (40 min)
- Behavioral/Trade-off (10 min)

---

## Round 1: Java Fundamentals & Collections

**Duration**: 60 minutes  
**Topics**: Java basics, collections, threading basics

### Section 1: Conceptual Questions (10 minutes)

**Q1** (2 min): *Explain the Integer cache in Java. Why is it there?*

Expected answer: "-128 to 127 are cached for performance. Common use case optimization. Integer.valueOf() reuses cached objects. Use equals() for comparison, not ==."

**Q2** (2 min): *What's the difference between equals() and ==?*

Expected answer: "== checks reference identity (for objects) or value equality (for primitives). equals() is overridable and checks content. Must implement equals/hashCode together."

**Q3** (3 min): *How does HashMap work internally? What happens during resize?*

Expected answer: "Hash table with chaining. Hash code determines bucket index. On collision, chain objects. Resize when load factor (0.75) exceeded. Treeify when bucket size > 8 for O(log n) access."

**Q4** (3 min): *Explain pass-by-value semantics. Can you modify a collection passed to a method?*

Expected answer: "Java is strictly pass-by-value. References are copied, not the object. You can modify the object via the reference (e.g., list.add()), but reassigning the reference won't affect the caller."

### Section 2: Coding Problem (40 minutes)

**Problem**: Implement a **Thread-Safe Counter with AtomicInteger & ReentrantLock variants**

```java
/**
 * Requirements:
 * 1. Implement Counter using synchronized keyword
 * 2. Implement CounterAtomic using AtomicInteger
 * 3. Implement CounterLock using ReentrantLock
 * 4. All should be thread-safe
 * 5. Include increment(), decrement(), getValue()
 * 6. Bonus: Add a reset() that only works if count > 0
 * 
 * Test with concurrent threads:
 * - 10 threads, each increment 1000 times
 * - Final count should be 10000
 */
```

**Interviewer's Follow-ups**:
- "Which is fastest?" (AtomicInteger, no lock overhead)
- "When would you use ReentrantLock?" (When you need conditions, tryLock, etc.)
- "Can two synchronized methods run concurrently?" (No, synchronized on same object)

### Section 3: Behavioral/Trade-off (10 minutes)

**Q5** (5 min): *You have two code paths: one using HashMap (fast), one using TreeMap (sorted). Design constraint: must be both sorted AND fast. What do you do?*

Expected answer: "Depends on access patterns. If 90% reads, use LinkedHashMap for insertion order. If need range queries, TreeMap worth the log n. If both, maybe two structures or cache."

**Q6** (5 min): *A senior engineer says 'never use synchronized in production'. Do you agree?*

Expected answer: "No. synchronized is fine for simple cases (protects invariant, clear ownership). But for complex concurrency, ReentrantLock/ConcurrentHashMap better. Know trade-offs, don't blindly follow dogma."

---

## Round 2: OOP Design & Inheritance

**Duration**: 60 minutes  
**Topics**: Inheritance, polymorphism, design principles, immutability

### Section 1: Conceptual Questions (10 minutes)

**Q1** (2 min): *Explain the difference between overloading and overriding.*

Expected answer: "Overloading: same method name, different params, compile-time resolution, same class. Overriding: same signature, different class hierarchy, runtime resolution via dynamic dispatch."

**Q2** (2 min): *What's the purpose of the final keyword in different contexts?*

Expected answer: "final class: can't extend. final method: can't override. final field: immutable ref (set once). Note: final on reference doesn't make object immutable."

**Q3** (3 min): *Describe the initialization order when you create a subclass instance.*

Expected answer: "1. Static blocks of parent (once at class load). 2. Static blocks of child (once). 3. Instance vars of parent (defaults). 4. Instance blocks of parent. 5. Parent constructor body. 6. Instance vars of child. 7. Instance blocks of child. 8. Child constructor."

**Q4** (3 min): *Why prefer composition over inheritance?*

Expected answer: "Flexibility: change composition at runtime. Inheritance is tighter (fragile base class). Avoids deep hierarchies. Single responsibility. Diamond problem."

### Section 2: Coding Problem (40 minutes)

**Problem**: Implement an **Immutable Person class with defensive copying**

```java
/**
 * Requirements:
 * 1. Person class with fields: name (String), age (int), hobbies (List<String>)
 * 2. Make it truly immutable:
 *    - final class, final fields
 *    - no setters
 *    - defensive copy in constructor (hobbies)
 *    - defensive copy in getter (hobbies)
 * 3. Implement equals(), hashCode(), toString()
 * 4. Implement Comparable (by age)
 * 5. Test that original list and returned list are independent
 * 
 * Bonus:
 * - Make it a Record (Java 16+)
 * - Implement a Builder pattern
 */
```

**Interviewer's Follow-ups**:
- "What happens if someone modifies the returned hobbies list?" (Shouldn't matter; we return a copy)
- "Is this a shallow or deep copy?" (Deep for lists; shallow for strings since they're immutable)
- "How would you serialize this?" (Serializable interface; hobbies becomes a copy)

### Section 3: Behavioral/Trade-off (10 minutes)

**Q5** (5 min): *Your team has a class hierarchy: Shape → Circle, Square. A junior dev added a setRadius() method. Is this good design?*

Expected answer: "No. Radius is only for Circle, not Square. Violates Liskov Substitution. Better: radius in Circle only. Or use composition: Shape contains Geometry (Circle/Square)."

**Q6** (5 min): *When would you use abstract class vs interface?*

Expected answer: "Abstract: when you want state or protected methods (Drawable with Color field). Interface: pure contract, no state (Drawable interface). Java 8+ blurs line with default methods."

---

## Round 3: System Design & Advanced Patterns

**Duration**: 60 minutes  
**Topics**: Generics, collections internals, functional programming, design patterns

### Section 1: Conceptual Questions (10 minutes)

**Q1** (2 min): *Explain type erasure in Java generics. What are the implications?*

Expected answer: "Generics info removed at compile-time. Can't use `T` in instanceof. Can't create array of generic type. But allows backward compatibility. Wildcards (? extends, ? super) still exist at runtime."

**Q2** (2 min): *What does PECS mean? Give an example.*

Expected answer: "Producer Extends, Consumer Super. `List<? extends Number>` for reading (producer). `List<? super Integer>` for writing (consumer). Maximizes flexibility."

**Q3** (3 min): *Why is the equals/hashCode contract important in HashMap?*

Expected answer: "If equals(b) is true, hashCode must be same. Else put a key, then lookup fails. Bucket chain relies on equals to find correct object. Collision (same hash, different objects) okay; equals distinguishes."

**Q4** (3 min): *Explain the difference between fail-fast and fail-safe iterators.*

Expected answer: "Fail-fast: throws ConcurrentModificationException if collection modified during iteration (ArrayList). Fail-safe: safe to modify; iterates on copy (CopyOnWriteArrayList). Trade-off: fail-safe has overhead."

### Section 2: Coding Problem (40 minutes)

**Problem**: Implement an **LRU Cache with Generics**

```java
/**
 * Requirements:
 * 1. Generic LRU cache: class LRUCache<K, V>
 * 2. Fixed capacity: cache(int capacity)
 * 3. get(K key) -> V or null
 * 4. put(K key, V value) -> void
 *    - If key exists, update and move to front
 *    - If new, add to front
 *    - If capacity exceeded, evict LRU (rear)
 * 5. Thread-safe (use ReentrantLock or synchronized)
 * 6. Bonus: callback on eviction (EvictionListener<K, V>)
 * 
 * Implement with LinkedHashMap or LinkedList + HashMap.
 * 
 * Test:
 * - LRUCache<String, Integer> cache = new LRUCache<>(2);
 * - cache.put("a", 1); cache.put("b", 2);
 * - cache.get("a"); // moves "a" to front
 * - cache.put("c", 3); // evicts "b"
 * - cache.get("b"); // null
 */
```

**Interviewer's Follow-ups**:
- "What's the time complexity?" (O(1) get/put with HashMap + LinkedList)
- "How would you optimize for write-heavy workloads?" (Segment locks, concurrent structures)
- "Can you explain the eviction callback pattern?" (Observer pattern; notify listeners)

### Section 3: Behavioral/Trade-off (10 minutes)

**Q5** (5 min): *A Singleton pattern can break with reflection and serialization. How do you defend?*

Expected answer: "Use enum Singleton (serialization-safe, reflection-safe). Or: make constructor private, implement readResolve() for serialization, throw exception in constructor for reflection."

**Q6** (5 min): *Design a rate limiter for an API. What algorithm do you use and why?*

Expected answer: "Token bucket: allows burst (good). Sliding window: precise but expensive memory. Token bucket better. Impl: keep track of tokens + last refill time. Refill = (now - lastRefill) / interval * rate."

---

## Grading Rubric

### Conceptual Questions (20 points per Round)
- **Correct**: 5 pts (complete answer, no errors)
- **Mostly Correct**: 3-4 pts (right idea, minor gaps)
- **Partially Correct**: 1-2 pts (some understanding)
- **Wrong**: 0 pts

### Coding Problem (60 points per Round)
- **Compiles & Runs**: 10 pts
- **Correct Logic**: 20 pts
- **Handles Edge Cases**: 15 pts
- **Clean Code/Naming**: 10 pts
- **Bonus/Explanation**: 5 pts

### Behavioral/Trade-off (20 points per Round)
- **Thoughtful Answer**: 10 pts (acknowledges trade-offs, not dogmatic)
- **Justification**: 10 pts (explains reasoning, cites examples)

### Overall Scoring
- **90-100**: Ready for SDE-2 offer
- **75-89**: Strong, minor gaps
- **60-74**: Good foundation, needs practice
- **Below 60**: Focus on weaker areas, practice more

---

## Tips for Practicing

1. **Time Yourself**: Use a timer for each section. 10 min for Q's, 40 min for code.
2. **Code Out Loud**: Explain your approach before coding.
3. **Test**: Write test cases for your code.
4. **Discuss Trade-offs**: Never say "there's only one way".
5. **Ask Clarifying Questions**: "Should this be thread-safe?", "What's the scale?".

---

## Answer Key (Brief)

### Round 1 Problem: Thread-Safe Counter
```java
// Synchronized
class Counter {
    private int count;
    synchronized void increment() { count++; }
    synchronized int getValue() { return count; }
}

// AtomicInteger (no lock)
class CounterAtomic {
    private AtomicInteger count = new AtomicInteger(0);
    void increment() { count.incrementAndGet(); }
    int getValue() { return count.get(); }
}

// ReentrantLock (explicit control)
class CounterLock {
    private int count;
    private final ReentrantLock lock = new ReentrantLock();
    void increment() {
        lock.lock();
        try { count++; } finally { lock.unlock(); }
    }
}
```

### Round 2 Problem: Immutable Person
```java
final class Person implements Comparable<Person> {
    private final String name;
    private final int age;
    private final List<String> hobbies;
    
    Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);  // Defensive copy
    }
    
    List<String> getHobbies() {
        return new ArrayList<>(hobbies);  // Return copy
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person p)) return false;
        return age == p.age && Objects.equals(name, p.name) 
            && Objects.equals(hobbies, p.hobbies);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age, hobbies);
    }
    
    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age, o.age);
    }
}
```

### Round 3 Problem: LRU Cache
```java
class LRUCache<K, V> {
    private final Map<K, V> map;
    private final LinkedHashMap<K, V> order;
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    
    LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.order = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }
    
    V get(K key) {
        lock.lock();
        try {
            return order.getOrDefault(key, null);
        } finally { lock.unlock(); }
    }
    
    void put(K key, V value) {
        lock.lock();
        try {
            map.put(key, value);
            order.put(key, value);
        } finally { lock.unlock(); }
    }
}
```

---

**Remember**: The goal isn't to be perfect, but to **communicate clearly**, handle **edge cases**, and show **design thinking**. Good luck!
