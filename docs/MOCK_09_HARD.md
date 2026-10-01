# Mock 9: Hard - HashMap Internals & LRU Cache

**Duration**: 60 minutes | **Difficulty**: Hard | **Topics**: HashMap, LinkedHashMap, LRU Cache, Eviction Policies, Access Order

---

## Section 1: Conceptual Questions (10 min)

**Q1** (3 min): Explain HashMap's internal structure. What happens on hash collision? Load factor?

**Q2** (3 min): What's the difference between `HashMap`, `LinkedHashMap`, and `TreeMap`? When use each?

**Q3** (2 min): How does LinkedHashMap track insertion/access order? Why is this useful for caches?

**Q4** (2 min): What's the difference between `LinkedHashMap(capacity, loadFactor, accessOrder=true)` vs `false`?

---

## Section 2: Coding Problem (40 min)

### Problem: Implement Thread-Safe LRU Cache

**Requirements**:

1. Create `LRUCache<K, V>` class:
   - Constructor: `LRUCache(int capacity)`
   - Methods:
     - `V get(K key)` - retrieve value, mark as recently used
     - `void put(K key, V value)` - add/update, evict LRU if full
     - `int size()` - current entries
     - `void clear()` - remove all
     - `Set<K> keys()` - all current keys

2. Implementation requirements:
   - **Max capacity**: Fixed at construction time
   - **LRU eviction**: Remove least recently used on overflow
   - **Thread-safe**: Handle concurrent access
   - **Metrics**: Track hits/misses

3. Implement using LinkedHashMap:
```java
public class LRUCache<K, V> {
    private final LinkedHashMap<K, V> cache;
    private final int capacity;
    private int hits = 0;
    private int misses = 0;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        // Create LinkedHashMap with access-order (LRU)
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                // Remove oldest when capacity exceeded
                return size() > capacity;
            }
        };
    }
    
    public synchronized V get(K key) {
        V value = cache.get(key);
        if (value != null) {
            hits++;
        } else {
            misses++;
        }
        return value;
    }
    
    public synchronized void put(K key, V value) {
        cache.put(key, value);
    }
    
    // ... other methods
}
```

4. Test code:
```java
LRUCache<String, String> cache = new LRUCache<>(3);

cache.put("key1", "value1");
cache.put("key2", "value2");
cache.put("key3", "value3");
System.out.println("Cache: " + cache.keys());  // [key1, key2, key3]

cache.get("key1");  // Marks key1 as recently used
System.out.println("After accessing key1: " + cache.keys());

cache.put("key4", "value4");  // Capacity exceeded, evict LRU (key2)
System.out.println("After adding key4: " + cache.keys());
// Expected: [key1, key3, key4]

System.out.println("Hit ratio: " + cache.getHitRatio());
```

5. Multi-threaded stress test:
   - 10 threads
   - Each puts 100 random keys
   - Each gets 200 random keys
   - Verify cache never exceeds capacity

**Bonus**: Implement with manual LinkedList + HashMap (without LinkedHashMap):
```java
// Maintain doubly-linked list for LRU order
private LinkedList<K> lruOrder = new LinkedList<>();
```

---

## Section 3: Behavioral (10 min)

**Q5** (5 min): Discuss: Why can't you use a regular HashMap as an LRU cache? What would happen?

**Q6** (5 min): In a distributed system with multiple servers, how would you maintain consistent LRU cache state?

---

## Rubric

| Section | Points |
|---------|--------|
| Conceptual (Q1-4) | 20 |
| Coding (LRU implementation + thread-safety + test) | 60 |
| Behavioral (Q5-6) | 20 |
| **Total** | **100** |

**90+**: Excellent | **75-89**: Good | **<75**: Review Modules 10-11

---

See answers: `MOCK_09_ANSWERS.md`
