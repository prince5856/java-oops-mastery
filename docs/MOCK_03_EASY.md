# Mock 3: Easy - OOP Principles Introduction

**Duration**: 60 minutes | **Difficulty**: Junior | **Topics**: Inheritance, basic polymorphism, constructors

---

## Section 1: Conceptual (10 min)

**Q1** (3 min): Explain inheritance. Show an example with `Animal` and `Dog`.

**Q2** (2 min): What's the difference between method overloading and method overriding?

**Q3** (3 min): When you create a `Dog` instance, what runs first: Parent constructor or Child constructor?

**Q4** (2 min): Can you override a static method? Why or why not?

---

## Section 2: Coding Problem (40 min)

### Problem: Build a Shape Hierarchy

**Requirements**:
1. Create abstract `Shape` class:
   - Abstract method `calculateArea()` → double
   - Abstract method `getName()` → String
   - Concrete method `printInfo()` that prints "Shape: [name], Area: [area]"

2. Implement `Circle` class:
   - Constructor(radius)
   - Implement calculateArea() using π * r²
   - Implement getName() → "Circle"

3. Implement `Rectangle` class:
   - Constructor(width, height)
   - Implement calculateArea() using width * height
   - Implement getName() → "Rectangle"

4. **Test**:
   ```java
   Shape circle = new Circle(5);
   circle.printInfo();  // "Shape: Circle, Area: 78.54"
   
   Shape rect = new Rectangle(4, 5);
   rect.printInfo();  // "Shape: Rectangle, Area: 20.0"
   ```

**Bonus**: Add `Triangle(base, height)` class

---

## Section 3: Behavioral (10 min)

**Q5** (5 min): Why use abstract classes instead of concrete classes?

**Q6** (5 min): Explain constructor chaining with `super()`.

---

## Rubric

### Conceptual (20 pts)
- Q1: 5 pts | Q2: 5 pts | Q3: 5 pts | Q4: 5 pts

### Coding (60 pts)
- Shape abstract class: 15 pts
- Circle implementation: 15 pts
- Rectangle implementation: 15 pts
- printInfo works correctly: 10 pts
- No compilation errors: 5 pts

### Behavioral (20 pts)
- Q5: 10 pts | Q6: 10 pts

**Total**: 100 pts | **90+**: Interview ready | **<75**: Review Modules 4-6

---

See: `MOCK_03_ANSWERS.md`
