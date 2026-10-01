# Mock 5: Intermediate - Collections & Generics (PECS)

**Duration**: 60 minutes | **Difficulty**: Intermediate | **Topics**: Generics, PECS, Collections, Comparable, TreeSet

---

## Section 1: Conceptual Questions (10 min)

**Q1** (3 min): What is PECS? Explain "Producer Extends, Consumer Super" with concrete examples.

**Q2** (3 min): What's the difference between `List<? extends Number>` and `List<? super Number>`? When would you use each?

**Q3** (2 min): Why does `List<Integer>` not work where `List<Object>` is expected, even though Integer extends Object? (Type erasure/covariance)

**Q4** (2 min): Explain the difference between `Comparable` and `Comparator`. When should you implement each?

---

## Section 2: Coding Problem (40 min)

### Problem: Generic Repository with PECS

**Requirements**:

1. Create a generic `Repository<T>` class:
   - Must support `add(T item)`, `get(int index)`, `getAll()`
   - Implement with ArrayList internally
   - Type parameter: `<T extends Comparable<T>>`

2. Add these methods:
   - `findMax()` → returns the maximum element (using compareTo)
   - `findMin()` → returns the minimum element
   - `sort()` → sorts internal list
   - `addAll(Collection<? extends T> items)` → uses PECS (Producer Extends)

3. Create a `Person` class implementing `Comparable<Person>`:
   - Fields: `name` (String), `age` (int), `id` (long)
   - `equals()` and `hashCode()` based on id
   - `compareTo()` → compare by age, then by name
   - Implement `toString()` for readable output

4. Test code:
```java
Repository<Person> people = new Repository<>();
people.add(new Person("Alice", 30, 1L));
people.add(new Person("Bob", 25, 2L));
people.add(new Person("Charlie", 35, 3L));

System.out.println("Max: " + people.findMax());  // Expected: Charlie (age 35)
System.out.println("Min: " + people.findMin());  // Expected: Bob (age 25)

people.sort();
people.getAll().forEach(System.out::println);    // Sorted by age

List<Person> newPeople = new ArrayList<>();
newPeople.add(new Person("Diana", 28, 4L));
people.addAll(newPeople);                         // Uses PECS
```

**Bonus**: Add a `filter(Predicate<? super T> predicate)` method using wildcard generics.

---

## Section 3: Behavioral (10 min)

**Q5** (5 min): Discuss: If you have `Repository<Cat>` and `Repository<? extends Animal>`, can you add a Dog to the second repository? Why or why not?

**Q6** (5 min): When designing APIs with generics, how do you decide between using `T`, `? extends T`, or `? super T`? Give 2 examples.

---

## Rubric

| Section | Points |
|---------|--------|
| Conceptual (Q1-4) | 20 |
| Coding (Repository + Person + test output) | 60 |
| Behavioral (Q5-6) | 20 |
| **Total** | **100** |

**90+**: Excellent | **75-89**: Good | **<75**: Review Modules 7-8

---

See answers: `MOCK_05_ANSWERS.md`
