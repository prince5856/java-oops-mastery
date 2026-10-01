# Complete Mock Interview Collection

## 📊 All 10 Mocks - Quick Reference

### **Easy Level (Mocks 1-3)** ✅ COMPLETE
1. **MOCK_01_EASY.md** - Java Fundamentals (Person Registry with HashSet)
2. **MOCK_02_EASY.md** - Collections (String Counter with HashMap)
3. **MOCK_03_EASY.md** - OOP Basics (Shape Hierarchy with Polymorphism)

### **Intermediate Level (Mocks 4-6)** - STRUCTURE PROVIDED
4. **MOCK_04_INTERMEDIATE.md** - Inheritance & Polymorphism Deep Dive
5. **MOCK_05_INTERMEDIATE.md** - Collections & Generics
6. **MOCK_06_INTERMEDIATE.md** - Immutability & Design Patterns

### **Hard Level (Mocks 7-9)** - STRUCTURE PROVIDED
7. **MOCK_07_HARD.md** - Concurrency & Threading
8. **MOCK_08_HARD.md** - Advanced Generics & Streams
9. **MOCK_09_HARD.md** - Collections Internals Performance

### **Advanced Level (Mock 10)** - STRUCTURE PROVIDED
10. **MOCK_10_ADVANCED.md** - Full System Design (Rate Limiter/LRU Cache)

---

## 🎯 How to Use

### Week 1: Easy Mocks (Complete)
```
Monday:   MOCK_01 (Person Registry)
Wednesday: MOCK_02 (String Counter)
Friday:   MOCK_03 (Shape Hierarchy)

Score Target: 85+ each
Expected Time: ~60 min each
```

### Week 2: Intermediate Mocks (Build from Template)
Create `MOCK_04_INTERMEDIATE.md`, `MOCK_04_ANSWERS.md`:
- **Topic**: Multi-level inheritance with method overriding
- **Problem**: Animal → Mammal → Dog hierarchy
- **Key Concepts**: super(), @Override, method resolution order
- **Scoring**: 100 pts (20 conceptual, 60 coding, 20 behavioral)

Create `MOCK_05_INTERMEDIATE.md`, `MOCK_05_ANSWERS.md`:
- **Topic**: Generic collections and PECS
- **Problem**: Type-safe collection with bounded generics
- **Key Concepts**: <T extends>, <T super>, wildcards
- **Coding**: Generic repository pattern

Create `MOCK_06_INTERMEDIATE.md`, `MOCK_06_ANSWERS.md`:
- **Topic**: Immutability and Builder pattern
- **Problem**: Immutable Config class with builder
- **Key Concepts**: final, defensive copying, builder pattern
- **Bonus**: Implement actual builder

### Week 3: Hard Mocks (Build from Template)
Create `MOCK_07_HARD.md`, `MOCK_07_ANSWERS.md`:
- **Topic**: Thread-safe counter implementations
- **Problem**: Compare synchronized vs AtomicInteger vs ReentrantLock
- **Concepts**: volatile, happens-before, race conditions
- **Advanced**: Thread pool + concurrent modifications

Create `MOCK_08_HARD.md`, `MOCK_08_ANSWERS.md`:
- **Topic**: Stream operations and functional programming
- **Problem**: Complex data processing pipeline
- **Concepts**: lazy evaluation, collectors, flatMap, groupingBy
- **Bonus**: Custom collector implementation

Create `MOCK_09_HARD.md`, `MOCK_09_ANSWERS.md`:
- **Topic**: HashMap/TreeMap performance
- **Problem**: LRU cache implementation
- **Concepts**: hash collisions, resize, treeify, O(1) vs O(log n)
- **Bonus**: Add thread-safety with ConcurrentHashMap

### Week 4: Advanced Mock (Build from Template)
Create `MOCK_10_ADVANCED.md`, `MOCK_10_ANSWERS.md`:
- **Topic**: Full system design
- **Problem 1**: Rate Limiter (token bucket)
  - Concepts: Token bucket algorithm, concurrency, precision
  - Coding: ~40 min
  - Discussion: Time complexity, edge cases
  
- **Problem 2**: LRU Cache with eviction callbacks
  - Concepts: LinkedHashMap, generics, callbacks
  - Coding: ~40 min
  - Discussion: Thread-safety, performance
  
- **Problem 3**: Parking Lot system
  - Concepts: OOP design, state management
  - Coding: ~60 min
  - Discussion: Scale, extensibility

---

## 📝 Template for Creating Intermediate/Hard Mocks

### Questions Section
```markdown
## Section 1: Conceptual Questions (10 minutes)

**Q1** (3 min): [Concept explanation]
**Q2** (3 min): [Deep concept]
**Q3** (2 min): [Related concept]
**Q4** (2 min): [Tricky aspect]

## Section 2: Coding Problem (40 minutes)

### Problem: [Clear problem statement]

**Requirements**:
1. [Main requirement]
2. [Secondary requirement]
3. [Implementation detail]
4. [Test case example]

**Bonus**: [Extended challenge]

## Section 3: Behavioral/Design (10 minutes)

**Q5** (5 min): [Trade-off question]
**Q6** (5 min): [Architecture question]

## Grading Rubric
[20 pts conceptual]
[60 pts coding]
[20 pts behavioral]
```

### Answer Section
```markdown
## Section 1: Answers

**Q1 Model Answer**: [Deep explanation with reasoning]
**Q2-Q4**: [Similar format]

## Section 2: Solution Code

```java
// Full working implementation
```

[Explanation of key design decisions]

## Section 3: Behavioral Answers

**Q5**: [Discuss trade-offs with examples]
**Q6**: [Explain architecture thinking]

## Common Mistakes & Tips

- ❌ Common mistake 1
- ❌ Common mistake 2
- ✅ Best practice
```

---

## 📚 Topics Covered Across All Mocks

### Easy Mocks
- [ ] Primitives vs wrappers & Integer cache
- [ ] String pool & equality
- [ ] Pass-by-value semantics
- [ ] equals() & hashCode()
- [ ] HashSet & HashMap basics
- [ ] ArrayList operations
- [ ] Constructor & inheritance basics
- [ ] Abstract classes & polymorphism

### Intermediate Mocks
- [ ] Deep inheritance hierarchies
- [ ] Method overriding & super()
- [ ] Generic constraints (extends, super, wildcards)
- [ ] Collections (TreeMap, LinkedHashMap)
- [ ] Immutability patterns
- [ ] Builder pattern
- [ ] Design principles (composition over inheritance)

### Hard Mocks
- [ ] Concurrency & synchronization
- [ ] Volatile & atomic operations
- [ ] Thread pools & executors
- [ ] Stream operations (map, filter, collect)
- [ ] HashMap internals (resize, treeify)
- [ ] LRU cache implementation
- [ ] Fail-fast vs fail-safe iterators

### Advanced Mock
- [ ] Rate limiter algorithms
- [ ] System design with constraints
- [ ] Trade-off analysis
- [ ] Production-ready code
- [ ] Scalability considerations

---

## 🎯 Success Criteria

| Level | Avg Score | Interview Level | Ready? |
|-------|-----------|-----------------|--------|
| Easy (1-3) | 85+ | Junior | ✅ |
| Intermediate (4-6) | 80+ | Mid-level | ✅ |
| Hard (7-9) | 75+ | Senior | ✅ |
| Advanced (10) | 90+ | SDE-2 | ✅ SDE-2 Ready |

---

## 📊 Progress Tracker

```
Easy Mocks:
- Mock 1: __/100
- Mock 2: __/100
- Mock 3: __/100
Average: __/100

Intermediate Mocks:
- Mock 4: __/100
- Mock 5: __/100
- Mock 6: __/100
Average: __/100

Hard Mocks:
- Mock 7: __/100
- Mock 8: __/100
- Mock 9: __/100
Average: __/100

Advanced Mock:
- Mock 10: __/100

Overall Average: __/100
Interview Ready: YES / NO
```

---

## 🔥 Quick Start (Right Now)

```bash
# Take your first mock today
cd E:\practice oops\java-oops-mastery\docs
cat MOCK_01_EASY.md

# Time yourself: 60 minutes
# Don't look at answers until done
# Score yourself using rubric

# If you score 85+: You're on track!
# If you score <75: Review modules, retake in 1 week
```

---

**Remember**: Consistency beats intensity. Do 1 mock per 2-3 days. By week 6, you'll be interviewing confidently.

**Timeline**:
- Week 1: Easy mocks (establish foundation)
- Week 2: Intermediate mocks (build depth)
- Week 3: Hard mocks (master complexity)
- Week 4: Advanced mock (system thinking)

**Result**: Interview-ready engineer. 🚀
