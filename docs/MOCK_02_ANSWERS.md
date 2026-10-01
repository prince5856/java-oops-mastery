# Mock 2 Answers: Easy - String & Collections Basics

## Section 2: Solution Code

```java
import java.util.*;

class StringCounter {
    private Map<String, Integer> frequencyMap = new HashMap<>();
    private int totalCount = 0;
    
    public void addWord(String word) {
        String normalized = word.toLowerCase()
            .replaceAll("[.,!?]", "");  // Remove punctuation
        if (normalized.isEmpty()) return;
        
        frequencyMap.put(normalized, frequencyMap.getOrDefault(normalized, 0) + 1);
        totalCount++;
    }
    
    public int getFrequency(String word) {
        String normalized = word.toLowerCase();
        return frequencyMap.getOrDefault(normalized, 0);
    }
    
    public String getTopWord() {
        return frequencyMap.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }
    
    public List<String> getAllWords() {
        return frequencyMap.keySet().stream()
            .sorted()
            .toList();
    }
    
    public int getTotalWordCount() {
        return totalCount;
    }
    
    // Bonus
    public Map<String, Integer> getFrequencyMap() {
        return new HashMap<>(frequencyMap);  // Return copy
    }
}
```

### Key Implementation Details

1. **HashMap**: Store word → frequency
2. **toLowerCase()**: Case-insensitive comparison
3. **replaceAll()**: Remove punctuation
4. **getOrDefault()**: Avoid null checks
5. **Stream API**: For sorting and max finding
6. **Return copy**: Don't expose internal state

**Points**: See rubric

---

## Behavioral Answers

### Q5: ConcurrentModificationException While Iterating

**Model Answer**:

"The problem: You can't modify a collection while iterating with for-each loop:
```java
List<String> list = new ArrayList<>(Arrays.asList(\"a\", \"b\", \"c\"));
for (String s : list) {
    if (s.equals(\"b\")) list.remove(s);  // ConcurrentModificationException!
}
```

**Fix 1: Use Iterator**
```java
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (it.next().equals(\"b\")) it.remove();  // Safe!
}
```

**Fix 2: Use removeIf() (Java 8+)**
```java
list.removeIf(s -> s.equals(\"b\"));  // Clean and safe
```

**Fix 3: Create copy first**
```java
for (String s : new ArrayList<>(list)) {
    if (s.equals(\"b\")) list.remove(s);  // OK
}
```

Why? The iterator maintains a modification count. When you call list.remove() outside iterator, it throws."

**Points**: 10/10 if you covered fixes and explanation

---

### Q6: HashMap vs TreeMap

**Model Answer**:

"**HashMap**:
- Time: O(1) average case for get/put
- Order: No guaranteed order
- Use: When you need fast access
- Memory: Less overhead

**TreeMap**:
- Time: O(log n) for get/put
- Order: Sorted by keys
- Use: When you need sorted keys or range queries
- Memory: More (maintains tree structure)

Trade-off:
```java
// Fast but unsorted
Map<String, Integer> freqMap = new HashMap<>();

// Sorted but slower
Map<String, Integer> freqMap = new TreeMap<>();

// Want sorted? Use:
Map<String, Integer> sortedMap = 
    new TreeMap<>(frequencyMap);  // Convert at end
```

Choose TreeMap only if you need sorted order or range queries (subMap(), headMap(), tailMap())."

**Points**: 10/10 if you covered timing, ordering, trade-off

---

## Summary

**Common Mistakes**:
- ❌ Modifying list during iteration
- ❌ Not using HashMap for O(1) lookup
- ❌ Exposing internal map without copy
- ❌ Case-sensitive comparison
- ❌ Not handling edge cases (empty list)

**Next**: Mock 3 or review if < 75
