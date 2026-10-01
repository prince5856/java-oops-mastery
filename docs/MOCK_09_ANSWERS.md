# Mock 9 Answers: HashMap Internals & LRU Cache

## Solution Code

```java
import java.util.*;

public class LRUCache<K, V> {
    private final LinkedHashMap<K, V> cache;
    private final int capacity;
    private int hits = 0;
    private int misses = 0;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        // LinkedHashMap with access-order (third param = true)
        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
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
    
    public synchronized int size() {
        return cache.size();
    }
    
    public synchronized void clear() {
        cache.clear();
        hits = 0;
        misses = 0;
    }
    
    public synchronized Set<K> keys() {
        return new LinkedHashSet<>(cache.keySet());
    }
    
    public synchronized double getHitRatio() {
        int total = hits + misses;
        return total == 0 ? 0 : (double) hits / total;
    }
    
    public synchronized String getMetrics() {
        return String.format("Hits: %d, Misses: %d, Hit Ratio: %.2f%%", 
            hits, misses, getHitRatio() * 100);
    }
}

// Test code
public class LRUCacheTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Basic LRU Cache Test ===");
        LRUCache<String, String> cache = new LRUCache<>(3);
        
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        System.out.println("Initial cache: " + cache.keys());  // [key1, key2, key3]
        
        cache.get("key1");  // Marks key1 as recently used
        System.out.println("After accessing key1: " + cache.keys());
        
        cache.put("key4", "value4");  // Evicts key2 (least recently used)
        System.out.println("After adding key4: " + cache.keys());
        // Expected: [key1, key3, key4]
        
        System.out.println("Metrics: " + cache.getMetrics());
        
        // Verify eviction
        if (cache.get("key2") == null) {
            System.out.println("✓ key2 was correctly evicted");
        }
        if (cache.get("key1") != null) {
            System.out.println("✓ key1 still in cache");
        }
        
        // Concurrent test
        System.out.println("\n=== Multi-threaded Test ===");
        testConcurrency();
    }
    
    public static void testConcurrency() throws InterruptedException {
        LRUCache<Integer, Integer> cache = new LRUCache<>(100);
        int numThreads = 10;
        int opsPerThread = 150;
        
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                Random random = new Random();
                for (int j = 0; j < opsPerThread; j++) {
                    int key = random.nextInt(200);  // Keys 0-199
                    if (random.nextDouble() < 0.7) {
                        cache.put(key, threadId * 1000 + j);
                    } else {
                        cache.get(key);
                    }
                }
            });
            threads[i].start();
        }
        
        for (Thread t : threads) {
            t.join();
        }
        
        System.out.println("Final cache size: " + cache.size() + " (max 100)");
        System.out.println("Metrics: " + cache.getMetrics());
    }
}

// Bonus: Manual LinkedList + HashMap implementation
public class LRUCacheManual<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private final int capacity;
    private final Map<K, Node<K, V>> map = new HashMap<>();
    private final Node<K, V> head = new Node<>(null, null);  // Dummy head
    private final Node<K, V> tail = new Node<>(null, null);  // Dummy tail
    private int hits = 0;
    private int misses = 0;
    
    public LRUCacheManual(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public synchronized V get(K key) {
        Node<K, V> node = map.get(key);
        if (node == null) {
            misses++;
            return null;
        }
        
        hits++;
        moveToHead(node);  // Mark as recently used
        return node.value;
    }
    
    public synchronized void put(K key, V value) {
        if (map.containsKey(key)) {
            removeNode(map.get(key));
        }
        
        Node<K, V> node = new Node<>(key, value);
        addToHead(node);
        map.put(key, node);
        
        if (map.size() > capacity) {
            removeTail();  // Remove LRU (at tail)
        }
    }
    
    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addToHead(node);
    }
    
    private void addToHead(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void removeTail() {
        Node<K, V> last = tail.prev;
        removeNode(last);
        map.remove(last.key);
    }
    
    public synchronized int size() {
        return map.size();
    }
}
```

## Q1: HashMap Internal Structure

**Bucket Array + Linked Lists**:
```
Index 0: [Entry1] → [Entry2] → [Entry3]
Index 1: [Entry4]
Index 2: [Entry5] → [Entry6]
...
```

**Hash Collision Resolution**: Chaining (linked list of entries)

**Load Factor** (default 0.75):
- When `size / capacity > 0.75`, HashMap resizes
- Doubles capacity to reduce collisions
- Trade-off: memory vs. performance

**Hash Collision Example**:
```java
HashMap<String, Integer> map = new HashMap<>();
map.put("AB", 1);  // Hash = 64
map.put("Ba", 2);  // Hash = 64 (collision!)
// Both stored in same bucket, chained together
```

## Q2: HashMap vs LinkedHashMap vs TreeMap

| Type | Order | Performance | Use Case |
|------|-------|-------------|----------|
| **HashMap** | None (random) | O(1) | General purpose |
| **LinkedHashMap** | Insertion/Access | O(1) | LRU Cache, FIFO |
| **TreeMap** | Sorted (compareTo) | O(log n) | Ordered traversal, range queries |

**When to use**:
- HashMap: Default choice, fastest
- LinkedHashMap: Need order (insertion or access)
- TreeMap: Need sorted order or range operations

## Q3: LinkedHashMap Access Order

```java
// Access-order LinkedHashMap
LinkedHashMap<K, V> cache = new LinkedHashMap<K, V>(16, 0.75f, true) {
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }
};

// Internally maintains doubly-linked list
// When you call get(), node moves to tail (most recent)
// Oldest node is always at head
```

**Use for LRU Cache**: Automatically tracks access order!

## Q4: Access Order Flag

```java
// Insertion-order (default, false)
LinkedHashMap<K, V> insertion = new LinkedHashMap<>(16, 0.75f, false);
insertion.put("a", 1);
insertion.put("b", 2);
insertion.get("a");  // Doesn't change order
// Iterates: a, b

// Access-order (true)
LinkedHashMap<K, V> access = new LinkedHashMap<>(16, 0.75f, true);
access.put("a", 1);
access.put("b", 2);
access.get("a");  // Moves "a" to tail
// Iterates: b, a
```

Use `true` for LRU cache (track both get and put as access).

## Q5: Why Not Use HashMap for LRU

**Problems**:
1. **No access order**: HashMap doesn't track last access time
2. **No eviction policy**: No way to know which entry to remove
3. **No automatic cleanup**: Must manually track and evict

```java
// This DOESN'T work as LRU
HashMap<K, V> map = new HashMap<>();
// When full, which entry to evict? No information!
```

**What would happen**:
- Cache fills up randomly
- Can't determine LRU entry (all appear equally old)
- Must add manual timestamp tracking (complex and slow)

**Solution**: LinkedHashMap with access-order provides automatic LRU

## Q6: Distributed LRU Cache Consistency

**Challenges**:
1. **Multiple servers**: Each has own cache, different states
2. **Access order changes**: Server A accesses key X, Server B doesn't know
3. **Eviction conflicts**: Server A evicts key, Server B wants it

**Solutions**:

1. **Cache-Aside Pattern** (most common):
   ```
   Client → Server A → Database
   Client → Server B → Database
   Each server caches independently, DB is source of truth
   ```

2. **Write-Through Pattern**:
   ```
   All writes go to Database first
   Then replicate to all cache servers
   Consistent but slower
   ```

3. **Use Central Cache Service**:
   ```
   Redis/Memcached as shared LRU cache
   All servers query same cache
   Consistent LRU across all
   ```

4. **Clock Synchronization + TTL**:
   ```
   Each server tracks access time (clock synced)
   Evict based on same timestamp logic
   Eventually consistent
   ```

**Best for SDE-2**: Understand trade-offs between consistency and performance.

---

**Total Points**: 100  
**Expected Score**: 75+
