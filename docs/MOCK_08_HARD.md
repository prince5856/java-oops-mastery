# Mock 8: Hard - Streams & Lambdas

**Duration**: 60 minutes | **Difficulty**: Hard | **Topics**: Stream API, Lambdas, Collectors, Functional Interfaces, Lazy Evaluation

---

## Section 1: Conceptual Questions (10 min)

**Q1** (3 min): Explain lazy evaluation in Streams. Why is `filter().map().collect()` different from loops?

**Q2** (3 min): What's the difference between intermediate and terminal operations? Give 3 examples of each.

**Q3** (2 min): Why does `Stream` throw IllegalStateException on reuse? Can you create reusable streams?

**Q4** (2 min): Explain function composition with `Function<T,R>`. How does `andThen()` and `compose()` work?

---

## Section 2: Coding Problem (40 min)

### Problem: Employee Stream Processing

**Requirements**:

1. Create an `Employee` class:
   - Fields: `id` (long), `name` (String), `department` (String), `salary` (double), `years` (int)
   - Implement `equals()`, `hashCode()`, `toString()`

2. Create sample data:
```java
List<Employee> employees = Arrays.asList(
    new Employee(1, "Alice", "Engineering", 120000, 5),
    new Employee(2, "Bob", "Sales", 80000, 3),
    new Employee(3, "Charlie", "Engineering", 110000, 4),
    new Employee(4, "Diana", "HR", 90000, 6),
    new Employee(5, "Eve", "Engineering", 105000, 2),
    new Employee(6, "Frank", "Sales", 75000, 1)
);
```

3. Solve these problems using Streams:

   **Problem 3a**: Filter employees with salary > 100k, map to names, sort alphabetically
   ```
   Expected output: [Alice, Charlie, Diana, Eve]
   ```

   **Problem 3b**: Group employees by department, show department → count
   ```
   Expected output:
   Engineering: 3
   Sales: 2
   HR: 1
   ```

   **Problem 3c**: Calculate average salary per department
   ```
   Expected output:
   Engineering: 111666.67
   Sales: 77500.0
   HR: 90000.0
   ```

   **Problem 3d**: Find top 3 earners by salary (name and salary)
   ```
   Expected output:
   Alice: 120000.0
   Charlie: 110000.0
   Eve: 105000.0
   ```

   **Problem 3e**: Collect employees into a Map<Department, List<Employee>>
   ```
   Use custom collector or Collectors.groupingBy()
   ```

4. Write reusable stream operations:
```java
// Example: Create a stream, process multiple ways
Stream<Employee> employeeStream = employees.stream();
// Can't reuse after terminal operation - show this understanding
```

**Bonus**: Create a custom collector that collects to a formatted string:
```java
employees.stream()
    .filter(e -> e.getSalary() > 100000)
    .collect(Collectors.joining(", ", "Employees: ", ""))
// Output: "Employees: Alice, Charlie, Diana, Eve"
```

---

## Section 3: Behavioral (10 min)

**Q5** (5 min): Discuss: Streams are slower than loops. Why use them? When would you NOT use streams?

**Q6** (5 min): Explain stateless vs stateful lambda functions. Why are stateful lambdas problematic in parallel streams?

---

## Rubric

| Section | Points |
|---------|--------|
| Conceptual (Q1-4) | 20 |
| Coding (3a-3e solutions + bonus) | 60 |
| Behavioral (Q5-6) | 20 |
| **Total** | **100** |

**90+**: Excellent | **75-89**: Good | **<75**: Review Module 17

---

See answers: `MOCK_08_ANSWERS.md`
