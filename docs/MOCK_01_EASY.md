# Mock 1: Easy - Java Fundamentals Basics

**Duration**: 60 minutes  
**Difficulty**: Junior / Entry-level  
**Topics**: Primitives, autoboxing, strings, pass-by-value, basic equals/hashCode

---

## Section 1: Conceptual Questions (10 minutes)

**Q1** (2 min): Explain the difference between primitive types and wrapper classes. Give an example of when you'd use each.

**Q2** (2 min): What is the Integer cache in Java? Why does `Integer a = 100; Integer b = 100; a == b;` return true?

**Q3** (3 min): Explain pass-by-value in Java. Can you modify an object passed to a method?

**Q4** (3 min): What's the difference between `==` and `.equals()` for strings?

---

## Section 2: Coding Problem (40 minutes)

### Problem: Implement a Simple Person Registry

**Requirements**:
1. Create a `Person` class with:
   - `name` (String, final)
   - `age` (int, final)
   - Constructor that validates age >= 0
   - Getters for name and age
   
2. Implement `equals()` and `hashCode()` correctly
   - Two persons are equal if they have same name and age
   
3. Implement `toString()` that returns `"Person{name='...', age=...}"`

4. Create a `PersonRegistry` class that:
   - Stores people in a Set (use HashSet)
   - Can add a person: `addPerson(Person p)`
   - Can check if person exists: `contains(Person p)` → boolean
   - Can get all people: `getAllPeople()` → Set<Person>
   - Can find person by name: `findByName(String name)` → Person or null

5. **Test with**:
   ```java
   PersonRegistry registry = new PersonRegistry();
   
   Person alice1 = new Person("Alice", 30);
   Person alice2 = new Person("Alice", 30);
   Person bob = new Person("Bob", 25);
   
   registry.addPerson(alice1);
   registry.addPerson(alice2);  // Should NOT add duplicate
   registry.addPerson(bob);
   
   System.out.println(registry.getAllPeople().size());  // Expected: 2
   System.out.println(registry.contains(alice2));       // Expected: true
   System.out.println(registry.findByName("Bob").getAge()); // Expected: 25
   System.out.println(registry.findByName("Charlie"));  // Expected: null
   ```

**Hints**:
- Use HashSet for storage
- equals() and hashCode() MUST be consistent
- Handle null safely

**Bonus** (if time):
- Add a `removeByName(String name)` method
- Add a `getAverageAge()` method

---

## Section 3: Behavioral Questions (10 minutes)

**Q5** (5 min): Why is it important to implement both equals() and hashCode() together?

**Q6** (5 min): In what scenarios would you use Integer instead of int?

---

## Grading Rubric

### Conceptual Questions (20 points)
- **Q1**: 5 pts (primitives on stack, wrappers are objects)
- **Q2**: 5 pts (cache range, reference equality)
- **Q3**: 5 pts (copy semantics, can modify object)
- **Q4**: 5 pts (reference vs content equality)

### Coding Problem (60 points)
- **Person class**: 10 pts (fields, constructor, getters)
- **equals()**: 15 pts (correct logic, null-safe, type check)
- **hashCode()**: 15 pts (consistent with equals)
- **toString()**: 5 pts (correct format)
- **PersonRegistry**: 15 pts (add, contains, getAll, findByName work correctly)
- **No NPE, compiles**: 5 pts

### Behavioral Questions (20 points)
- **Q5**: 10 pts (explains contract, HashSet impact)
- **Q6**: 10 pts (use cases, null handling, collections)

---

## Scoring

**Total**: 100 points

- **90-100**: Interview ready (junior level)
- **75-89**: Good fundamentals, minor gaps
- **60-74**: Needs more practice on equals/hashCode
- **Below 60**: Review Module 1-3 before retaking

---

## Answers Available

See: `MOCK_01_ANSWERS.md`

**DO NOT LOOK UNTIL YOU'VE COMPLETED THE MOCK**

Timer: ⏱️ 60 minutes. Start now!
