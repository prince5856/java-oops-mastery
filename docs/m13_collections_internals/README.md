# Module 13: Collections Internals

## Key Concepts

### HashMap Internals
- Hash table with collision resolution (chaining).
- Resize when load factor (default 0.75) exceeded.
- Treeify when bucket size > 8 (Java 8+).

### Fail-Fast vs Fail-Safe
- Fail-fast: throw ConcurrentModificationException if modified during iteration.
- Fail-safe: safe to modify during iteration (copy-on-write).

### TreeMap, LinkedHashMap, ConcurrentHashMap
- TreeMap: sorted keys.
- LinkedHashMap: insertion/access order.
- ConcurrentHashMap: thread-safe without full lock.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **HashMap** | Hash table; O(1) average. |
| **TreeMap** | Sorted; O(log n). |
| **LinkedHashMap** | Insertion order. |
| **Fail-Fast** | Iterator throws exception. |
| **ConcurrentHashMap** | Thread-safe; segment locks. |

