# Mock 1 Answers: Easy - Java Fundamentals Basics

---

## Section 1: Conceptual Answers

### Q1: Primitives vs Wrapper Classes

**Model Answer**:

"Primitives (int, long, double, etc.) are simple values stored on the stack with no identity. Wrappers (Integer, Long, Double) are objects stored on the heap.

Use primitives for:
- Performance-critical code
- Arrays of simple values
- Most calculations

Use wrappers for:
- Collections (List<Integer>, not List<int>)
- Nullable values (Integer x = null;)
- When you need object methods

Example:
```java
int[] primitiveArray = {1, 2, 3};  // Efficient
List<Integer> list = new ArrayList<>();  // Need wrappers
list.add(null);  // OK with wrapper
```"

**Points**: 5/5 if you covered:
- [ ] Primitives are values, wrappers are objects
- [ ] Stack vs heap
- [ ] Collections example
- [ ] Null handling

---

### Q2: Integer Cache

**Model Answer**:

"Java caches Integer objects from -128 to 127 for performance. This is a common range.

```java
Integer a = 100;  // Cached
Integer b = 100;
a == b;  // true (same cached object)

Integer c = 200;  // NOT cached
Integer d = 200;
c == d;  // false (different objects)
```

Why? The Integer.valueOf() method uses a cache:
```java
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + 128];
    return new Integer(i);
}
```

Key point: Always use `.equals()` for value comparison, not `==`."

**Points**: 5/5 if you covered:
- [ ] Cache range -128 to 127
- [ ] valueOf() returns cached objects
- [ ] Use .equals() not ==
- [ ] Example showing difference

---

### Q3: Pass-by-Value

**Model Answer**:

"Java is strictly pass-by-value. Two scenarios:

**Primitives**: Value is copied
```java
void increment(int x) { x++; }
int a = 5;
increment(a);
// a is still 5, not 6
```

**Objects**: Reference is copied (not the object)
```java
void modifyPerson(Person p) {
    p.setName(\"Bob\");  // Affects caller's object ✓
    p = new Person(\"Charlie\");  // Doesn't affect caller's reference ✗
}

Person alice = new Person(\"Alice\");
modifyPerson(alice);
// alice.getName() = \"Bob\" (object modified via reference)
// alice instanceof Person (alice still exists, not replaced)
```

Key: You CAN modify the object via the reference, but you CANNOT replace the reference itself."

**Points**: 5/5 if you covered:
- [ ] Pass-by-value always
- [ ] Primitives copied
- [ ] References copied (not objects)
- [ ] Can modify object, not reference

---

### Q4: == vs equals() for Strings

**Model Answer**:

"`==` checks reference equality (memory address).
`.equals()` checks content equality (characters).

```java
String a = \"hello\";
String b = \"hello\";
a == b;  // true (both in string pool, same object)

String c = new String(\"hello\");
String d = new String(\"hello\");
c == d;  // false (different objects on heap)
c.equals(d);  // true (same content)

String e = new String(\"hello\");
String f = \"hello\";
e == f;  // false (e is heap, f is pool)
e.equals(f);  // true (same content)
```

**Rule**: Always use `.equals()` for string comparison."

**Points**: 5/5 if you covered:
- [ ] == checks reference
- [ ] equals() checks content
- [ ] String pool example
- [ ] Best practice

---

## Section 2: Coding Solution

### Complete Solution

```java
import java.util.*;

class Person {
    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        if (age < 0) throw new IllegalArgumentException("Age must be >= 0");
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person p = (Person) obj;
        return age == p.age && Objects.equals(name, p.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    
    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}

class PersonRegistry {
    private Set<Person> people = new HashSet<>();
    
    public void addPerson(Person p) {
        people.add(p);  // HashSet uses equals/hashCode
    }
    
    public boolean contains(Person p) {
        return people.contains(p);
    }
    
    public Set<Person> getAllPeople() {
        return new HashSet<>(people);  // Return copy
    }
    
    public Person findByName(String name) {
        return people.stream()
            .filter(p -> p.getName().equals(name))
            .findFirst()
            .orElse(null);
    }
    
    // Bonus
    public void removeByName(String name) {
        people.removeIf(p -> p.getName().equals(name));
    }
    
    public double getAverageAge() {
        return people.stream()
            .mapToInt(Person::getAge)
            .average()
            .orElse(0.0);
    }
}
```

### Test Output

```
registry.getAllPeople().size()     = 2  ✓
registry.contains(alice2)          = true  ✓
registry.findByName("Bob").getAge() = 25  ✓
registry.findByName("Charlie")      = null  ✓
registry.getAverageAge()           = 27.5  ✓
```

### Key Points in Solution

1. **Immutability**: name and age are final
2. **equals()**: Checks type, null, class, then fields
3. **hashCode()**: Uses Objects.hash() for consistency
4. **toString()**: Standard format
5. **HashSet**: Works because equals/hashCode implemented
6. **Stream operations**: Bonus features using modern Java

**Points**: See rubric above

---

## Section 3: Behavioral Answers

### Q5: Why equals() and hashCode() Together?

**Model Answer**:

"The equals/hashCode contract states: If two objects are equal (equals() returns true), they must have the same hashCode().

Why? HashSet and HashMap rely on this:
1. Add object → compute hashCode() → put in bucket
2. Check if exists → compute hashCode() → look in bucket
3. If hashCode() changes or is inconsistent with equals(), lookup fails!

Example of what breaks:
```java
class BrokenPerson {
    String name;
    // WRONG: equals() but no hashCode()
    @Override
    public boolean equals(Object o) {
        return ((BrokenPerson)o).name.equals(name);
    }
    // Missing hashCode()!
}

Set<BrokenPerson> set = new HashSet<>();
BrokenPerson alice = new BrokenPerson(\"Alice\");
set.add(alice);
set.add(new BrokenPerson(\"Alice\"));
set.size();  // 2 (expected 1!) WRONG
```

Solution: Implement both together."

**Points**: 10/10 if you covered:
- [ ] Contract: equal objects must have equal hashCode
- [ ] HashSet/HashMap impact
- [ ] Example showing breakage
- [ ] How to fix

---

### Q6: When to Use Integer Instead of int

**Model Answer**:

"Use Integer (wrapper) when:
1. Collections need objects
   ```java
   List<Integer> list = new ArrayList<>();
   List<int> list = ???  // COMPILE ERROR
   ```

2. Need nullable value
   ```java
   Integer result = findValue();
   if (result == null) { }  // Can be null
   
   int count = 0;  // Cannot be null
   ```

3. Need object methods
   ```java
   int x = Integer.parseInt(\"42\");
   int max = Integer.max(a, b);
   String s = Integer.toBinaryString(8);
   ```

4. API requires object
   ```java
   someMethod(Object o);  // Pass Integer, not int
   ```

Use int (primitive) when:
1. Performance matters
   ```java
   for (int i = 0; i < 1_000_000; i++) {  // Use int
       sum += i;
   }
   ```

2. Array of values
   ```java
   int[] array = new int[1000];  // More efficient than Integer[]
   ```

3. Direct calculations
   ```java
   int result = a + b;  // No need for object
   ```"

**Points**: 10/10 if you covered:
- [ ] Collections require objects
- [ ] Nullable values
- [ ] Object methods
- [ ] Performance considerations

---

## Summary

### What This Mock Tests
- ✅ Basic Java types (primitives vs wrappers)
- ✅ String and object equality
- ✅ Pass-by-value semantics
- ✅ equals/hashCode contract
- ✅ HashSet usage
- ✅ Null handling

### Common Mistakes to Avoid
- ❌ Using == for string comparison
- ❌ Implementing equals() without hashCode()
- ❌ Not validating input (age >= 0)
- ❌ Returning same set reference (not copy)
- ❌ NPE on null person

### Topics to Review If You Scored < 75
- [ ] Review Module 1: Java Basics
- [ ] Review Module 3: Encapsulation
- [ ] Review Module 7: Object Contracts
- [ ] Practice more HashSet/HashMap usage

---

**Next Steps**:
- Score: __/100
- If ≥ 85: Move to Mock 2
- If < 85: Review modules above, retake Mock 1

---

Generated: 2026-10-01
