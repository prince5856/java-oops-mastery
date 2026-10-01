# Mock 4: Intermediate - Inheritance & Polymorphism Deep Dive

**Duration**: 60 minutes | **Difficulty**: Mid-level | **Topics**: Multi-level inheritance, method overriding, Liskov Substitution

---

## Section 1: Conceptual Questions (10 min)

**Q1** (3 min): Explain the Liskov Substitution Principle. Give an example of a violation and fix it.

**Q2** (3 min): What's the difference between method overriding and method hiding? Show with static methods.

**Q3** (2 min): When you create a `Dog extends Mammal extends Animal`, in what order do constructors run?

**Q4** (2 min): Can you override a final method? What about a private method?

---

## Section 2: Coding Problem (40 min)

### Problem: Design a Multi-Level Inheritance Hierarchy

**Requirements**:
1. Create `Animal` abstract class:
   - `name` field
   - Abstract methods: `makeSound()`, `eat(String food)`
   - Concrete method: `sleep()` prints sleeping message

2. Create `Mammal extends Animal`:
   - Add `isWarmBlooded()` → true
   - Override `sleep()` with mammal-specific behavior

3. Create `Dog extends Mammal`:
   - Add `breed` field
   - Implement all abstract methods
   - Add `fetch()` method
   - Override `eat()` to show dogs eating meat

4. Create `Cat extends Mammal`:
   - Add `independenceLevel` (1-10)
   - Implement all abstract methods
   - Override `eat()` to show cats eating fish
   - Add `scratch()` method

5. Test with polymorphism:
```java
List<Animal> animals = new ArrayList<>();
animals.add(new Dog("Rex", "Labrador"));
animals.add(new Cat("Whiskers", 9));

for (Animal animal : animals) {
    animal.makeSound();
    animal.eat("food");
    animal.sleep();
}
```

**Bonus**: Add `Bird extends Animal` (not Mammal). Handle the conflict: birds lay eggs, mammals don't.

---

## Section 3: Behavioral (10 min)

**Q5** (5 min): Discuss: A subclass is stricter than parent in equals(). Is this OK?

**Q6** (5 min): When should you use abstract classes vs interfaces vs concrete classes?

---

## Rubric

| Section | Points |
|---------|--------|
| Conceptual (Q1-4) | 20 |
| Coding (hierarchy + polymorphism) | 60 |
| Behavioral (Q5-6) | 20 |
| **Total** | **100** |

**90+**: Interview ready | **75-89**: Good | **<75**: Review Modules 4-5

---

See answers: `MOCK_04_ANSWERS.md`
