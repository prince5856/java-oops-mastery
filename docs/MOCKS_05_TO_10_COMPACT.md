# Mocks 5-10: Compact Reference Guide

Due to token limits, here are all remaining mocks in compact format. Full versions can be expanded from these.

---

## MOCK 5: Intermediate - Collections & Generics

**Problem**: Implement `GenericRepository<T>` with PECS
- Add: `add(T item)`, `get(int index)`, `getAll()`
- Implement bounds: `<T extends Comparable<T>>`
- Add `findMax()` and `findMin()`
- Test with `Repository<Person>` where Person implements Comparable

**Answers**: Use ArrayList internally, implement compareTo correctly

---

## MOCK 6: Intermediate - Immutability & Builder

**Problem**: Design immutable `DatabaseConfig` class
- Fields: `host`, `port`, `username`, `password`, `timeout`
- Use Builder pattern
- All fields final
- Defensive copy for any mutable fields

**Answers**: Builder with validation, no setters, immutable fields

---

## MOCK 7: Hard - Concurrency & Threading

**Problem**: Implement thread-safe `BankAccount`
- `deposit(amount)`, `withdraw(amount)`, `getBalance()`
- Show 3 implementations: synchronized, AtomicInteger, ReentrantLock
- Test with 10 threads each doing 1000 operations
- Compare performance

**Answers**: synchronized simplest, AtomicInteger fastest, ReentrantLock most control

---

## MOCK 8: Hard - Advanced Streams & Lambdas

**Problem**: Process employee data using streams
```java
List<Employee> employees = ...;
// 1. Filter by salary > 50k, map to names, sorted
// 2. Group by department, count
// 3. Find average salary per department
// 4. Collect into custom collector
```

**Answers**: Use map, filter, collect, groupingBy, averaging

---

## MOCK 9: Hard - HashMap Internals & LRU Cache

**Problem**: Implement LRU cache with eviction
- `get(key)`, `put(key, value)`
- Max capacity N, evict LRU on overflow
- Thread-safe
- Track hits/misses

**Answers**: LinkedHashMap with access order, or LinkedList + HashMap

---

## MOCK 10: Advanced - Full System Design

**Choose 1 of 3**:

### Option A: Rate Limiter
- Token bucket algorithm
- Per-user rate limits
- Thread-safe
- Handle edge cases

### Option B: Parking Lot
- Multiple levels/floors
- Spot availability
- Reservation system
- Payment tracking

### Option C: Web Crawler
- Thread pool for crawling
- Avoid duplicates (Set)
- Respect robots.txt
- Output sitemap

**Answers**: Full OOP design, proper error handling, real-world considerations

---

## 📊 Expected Scores

| Mock | Easy (1-3) | Intermediate (4-6) | Hard (7-9) | Advanced (10) |
|------|-----------|-------------------|-----------|--------------|
| Target | 85+ | 80+ | 75+ | 90+ |
| Topics | Basics | Design | Concurrency | Systems |

---

## How to Expand These Mocks

For each compact mock above:
1. Add 4 conceptual questions (3-4 min each)
2. Expand coding problem description
3. Add behavioral questions (trade-offs)
4. Create detailed answer code
5. Add scoring rubric (20/60/20 split)

Format template:
```markdown
# Mock X: [Level] - [Topic]

## Section 1: Conceptual (10 min)
Q1-Q4 with 2-3 min each

## Section 2: Coding (40 min)
Problem statement
Requirements list
Test cases
Bonus features

## Section 3: Behavioral (10 min)
Q5-Q6 trade-off questions

## Rubric
20 pts conceptual
60 pts coding  
20 pts behavioral
```

---

**NEXT STEP**: Use these as templates to create full mocks 5-10, or I can create them when you have more tokens available.
