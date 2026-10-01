# Mock 8 Answers: Streams & Lambdas

## Solution Code

```java
import java.util.*;
import java.util.stream.*;

class Employee {
    private long id;
    private String name;
    private String department;
    private double salary;
    private int years;
    
    public Employee(long id, String name, String department, double salary, int years) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.years = years;
    }
    
    public long getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getYears() { return years; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee emp = (Employee) o;
        return id == emp.id;
    }
    
    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
    
    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', dept='%s', salary=%.0f}", 
            id, name, department, salary);
    }
}

public class StreamsTest {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Alice", "Engineering", 120000, 5),
            new Employee(2, "Bob", "Sales", 80000, 3),
            new Employee(3, "Charlie", "Engineering", 110000, 4),
            new Employee(4, "Diana", "HR", 90000, 6),
            new Employee(5, "Eve", "Engineering", 105000, 2),
            new Employee(6, "Frank", "Sales", 75000, 1)
        );
        
        // Problem 3a: Filter salary > 100k, map to names, sort
        System.out.println("=== 3a: Employees with salary > 100k ===");
        List<String> highEarners = employees.stream()
            .filter(e -> e.getSalary() > 100000)
            .map(Employee::getName)
            .sorted()
            .collect(Collectors.toList());
        System.out.println(highEarners);
        // Output: [Alice, Charlie, Diana, Eve]
        
        // Problem 3b: Group by department, count
        System.out.println("\n=== 3b: Employee count by department ===");
        Map<String, Long> deptCount = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
            ));
        deptCount.forEach((dept, count) -> 
            System.out.println(dept + ": " + count)
        );
        // Output:
        // Engineering: 3
        // HR: 1
        // Sales: 2
        
        // Problem 3c: Average salary by department
        System.out.println("\n=== 3c: Average salary by department ===");
        Map<String, Double> avgSalary = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ));
        avgSalary.forEach((dept, avg) -> 
            System.out.printf("%s: %.2f%n", dept, avg)
        );
        // Output:
        // Engineering: 111666.67
        // HR: 90000.00
        // Sales: 77500.00
        
        // Problem 3d: Top 3 earners
        System.out.println("\n=== 3d: Top 3 earners ===");
        employees.stream()
            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
            .limit(3)
            .forEach(e -> System.out.printf("%s: %.0f%n", e.getName(), e.getSalary()));
        // Output:
        // Alice: 120000
        // Charlie: 110000
        // Eve: 105000
        
        // Problem 3e: Collect to Map<Department, List<Employee>>
        System.out.println("\n=== 3e: Employees grouped by department ===");
        Map<String, List<Employee>> byDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        byDept.forEach((dept, emps) -> {
            System.out.println(dept + ":");
            emps.forEach(e -> System.out.println("  " + e.getName()));
        });
        
        // Bonus: Custom string formatting
        System.out.println("\n=== Bonus: Formatted high earners ===");
        String result = employees.stream()
            .filter(e -> e.getSalary() > 100000)
            .map(Employee::getName)
            .sorted()
            .collect(Collectors.joining(", ", "Employees: ", ""));
        System.out.println(result);
        // Output: Employees: Alice, Charlie, Diana, Eve
    }
}
```

## Q1: Lazy Evaluation

**Streams use lazy evaluation**: intermediate operations don't execute until terminal operation.

```java
employees.stream()
    .filter(e -> {
        System.out.println("Filtering " + e.getName());
        return e.getSalary() > 100000;
    })
    .map(Employee::getName)
    .forEach(System.out::println);  // Terminal operation - TRIGGERS evaluation
```

**Why different from loops?**
- Loops execute eagerly (immediately)
- Streams can optimize (skip unnecessary work, parallelize)
- Example: filter().limit() in loop processes ALL items, but stream can stop early

```java
// Loop: processes all 6 employees
employees.stream().filter(e -> e.getSalary() > 100000).limit(2);

// Is optimized by streams to process only until 2 found
```

## Q2: Intermediate vs Terminal Operations

**Intermediate** (return Stream, lazy):
1. `filter(Predicate)` - keep matching elements
2. `map(Function)` - transform elements
3. `sorted(Comparator)` - order elements
4. `distinct()` - remove duplicates
5. `limit(long)` - take first N
6. `skip(long)` - skip first N

**Terminal** (consume stream, eager):
1. `collect(Collector)` - gather into collection
2. `forEach(Consumer)` - process each element
3. `reduce(BinaryOperator)` - combine to single value
4. `findFirst()`, `findAny()` - get one element
5. `count()` - count elements
6. `allMatch()`, `anyMatch()` - boolean result

## Q3: Stream Reusability

**Streams are single-use**:
```java
Stream<Integer> stream = Arrays.asList(1,2,3).stream();
stream.forEach(System.out::println);
stream.forEach(System.out::println);  // IllegalStateException!
```

**Creating reusable operations**:
```java
// Option 1: Create new stream each time
employees.stream().filter(e -> e.getSalary() > 100000).forEach(...);
employees.stream().filter(e -> e.getSalary() > 100000).map(...);

// Option 2: Use Supplier
Supplier<Stream<Employee>> streamSupplier = () -> 
    employees.stream().filter(e -> e.getSalary() > 100000);
streamSupplier.get().forEach(...);
streamSupplier.get().map(...);
```

## Q4: Function Composition

```java
Function<Integer, Integer> add2 = x -> x + 2;
Function<Integer, Integer> multiply3 = x -> x * 3;

// andThen: apply this, then next
Function<Integer, Integer> f1 = add2.andThen(multiply3);
f1.apply(5);  // ((5 + 2) * 3) = 21

// compose: apply next, then this
Function<Integer, Integer> f2 = add2.compose(multiply3);
f2.apply(5);  // (5 * 3) + 2 = 17
```

## Q5: Streams vs Loops Performance

**Why use Streams despite performance?**
1. **Readability**: Declarative (WHAT), not imperative (HOW)
   ```java
   // Loop (HOW)
   List<String> names = new ArrayList<>();
   for (Employee e : employees) {
       if (e.getSalary() > 100000) {
           names.add(e.getName());
       }
   }
   
   // Stream (WHAT)
   employees.stream()
       .filter(e -> e.getSalary() > 100000)
       .map(Employee::getName)
       .collect(toList());
   ```

2. **Composability**: Chain operations naturally
3. **Parallelization**: `.parallelStream()` for multi-core
4. **Optimization**: JVM can optimize (short-circuit, etc.)

**When NOT to use Streams**:
- Performance-critical tight loops
- Complex state management
- Debugging (exceptions are hard to trace)
- Simple operations (1-2 steps, use loops)

## Q6: Stateless vs Stateful Lambdas

**Stateless** (pure function):
```java
.map(e -> e.getSalary() * 1.1)  // No external state
```

**Stateful** (accesses external data):
```java
double totalSalary = 0;
employees.stream()
    .forEach(e -> totalSalary += e.getSalary());  // ERROR: effectively final
```

**Why problematic in parallel streams?**
```java
// Thread 1 updates totalSalary while Thread 2 reads it → race condition
employees.parallelStream()
    .forEach(e -> totalSalary += e.getSalary());  // WRONG
```

**Fix**: Use proper collectors
```java
double totalSalary = employees.stream()
    .collect(Collectors.summingDouble(Employee::getSalary));
```

---

**Total Points**: 100  
**Expected Score**: 75+
