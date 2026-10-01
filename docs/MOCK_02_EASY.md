# Mock 2: Easy - String & Collections Basics

**Duration**: 60 minutes  
**Difficulty**: Junior / Entry-level  
**Topics**: String operations, ArrayList, HashMap basics, pass-by-value with collections

---

## Section 1: Conceptual Questions (10 minutes)

**Q1** (2 min): Explain the difference between ArrayList and LinkedList. When would you use each?

**Q2** (3 min): How does HashMap work? What happens when there's a hash collision?

**Q3** (3 min): Can you modify a collection while iterating over it? What exception might you get?

**Q4** (2 min): What's the string pool? Show an example where `a == b` is true for strings.

---

## Section 2: Coding Problem (40 minutes)

### Problem: Implement a Simple String Counter

**Requirements**:
1. Create a `StringCounter` class that:
   - Tracks frequency of each word in a text
   - Has method `addWord(String word)` to count words
   - Has method `getFrequency(String word)` → int (return 0 if not found)
   - Has method `getTopWord()` → String (most frequent word)
   - Has method `getAllWords()` → List<String> (all unique words, sorted alphabetically)
   - Has method `getTotalWordCount()` → int (total words added)

2. Case-insensitive: "Hello" and "hello" are the same

3. Ignore punctuation (remove . , ! ? from words)

4. **Test with**:
   ```java
   StringCounter counter = new StringCounter();
   counter.addWord("hello");
   counter.addWord("world");
   counter.addWord("hello");
   counter.addWord("world");
   counter.addWord("hello");
   
   counter.getFrequency("hello");        // 3
   counter.getFrequency("world");        // 2
   counter.getFrequency("unknown");      // 0
   counter.getTopWord();                 // "hello"
   counter.getTotalWordCount();          // 5
   counter.getAllWords();                // ["hello", "world"]
   ```

5. **Bonus**: Add `getFrequencyMap()` → Map<String, Integer> (return all frequencies)

---

## Section 3: Behavioral Questions (10 minutes)

**Q5** (5 min): Discuss the problem: "I'm iterating through a List and removing items. I get ConcurrentModificationException. How do I fix it?"

**Q6** (5 min): When should I use HashMap vs TreeMap? What's the trade-off?

---

## Grading Rubric

### Conceptual (20 pts)
- Q1: 5 pts (ArrayList faster access, LinkedList faster insert/delete)
- Q2: 5 pts (hash function, buckets, collision handling)
- Q3: 5 pts (ConcurrentModificationException with fail-fast)
- Q4: 5 pts (string pool example)

### Coding (60 pts)
- HashMap usage: 15 pts (stores word → frequency)
- addWord with case normalization: 10 pts
- getFrequency: 5 pts
- getTopWord (find max): 10 pts
- getAllWords sorted: 10 pts
- getTotalWordCount: 5 pts
- Bonus getFrequencyMap: 5 pts

### Behavioral (20 pts)
- Q5: 10 pts (Iterator.remove() or removeIf())
- Q6: 10 pts (HashMap O(1) vs TreeMap O(log n), sorted order)

**Total**: 100 points

- **90-100**: Interview ready
- **75-89**: Good collections understanding
- **60-74**: Needs HashMap/sorting practice
- **Below 60**: Review Module 13

---

See answers: `MOCK_02_ANSWERS.md`
