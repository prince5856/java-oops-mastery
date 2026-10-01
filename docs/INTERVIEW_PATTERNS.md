# Common Java/OOP Interview Patterns & Questions

This document covers patterns interviewers use to test your understanding. Master these, and you'll handle any variation.

---

## Pattern 1: "Explain X and Give an Example"

### Questions Using This Pattern:
- "Explain autoboxing and the Integer cache"
- "Explain pass-by-value in Java"
- "Explain method overloading vs overriding"

### Your Approach:
1. **Define**: 1 sentence, clear definition
2. **Explain Why**: Why does Java work this way? (performance, design)
3. **Code Example**: Show with code
4. **Gotcha**: What's the trap?
5. **Best Practice**: How to use it correctly

### Example Answer Format:
**Question**: "Explain autoboxing"

**Answer**:
- **Definition**: Autoboxing is automatic conversion from primitive (int) to wrapper (Integer).
- **Why**: Java treats primitives and objects differently. Autoboxing bridges the gap for collections, which only hold objects.
- **Code**: `Integer x = 5;` (autoboxing), `int y = new Integer(5);` (unboxing)
- **Gotcha**: Unboxing null throws NPE. `Integer x = null; int y = x;` → exception.
- **Best Practice**: Always null-check or use Optional. Prefer primitives in performance-critical code.

---

## Pattern 2: "Spot the Bug"

### Questions Using This Pattern:
- "What's wrong with this equals() implementation?"
- "This code has a memory leak. Where?"
- "Why does this HashMap lookup fail?"

### Your Approach:
1. **Read Code**: Line by line
2. **Identify Issue**: What's violated?
3. **Explain Impact**: How does this fail?
4. **Provide Fix**: Show corrected code
5. **Generalize**: When does this pattern cause problems?

### Common Bug Patterns:

#### Bug 1: Broken equals()
```java
// WRONG
@Override
public boolean equals(Object obj) {
    if (obj instanceof Person) {
        Person p = (Person) obj;
        return this.name == p.name;  // BUG: == on Strings!
    }
    return false;
}

// RIGHT
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Person p = (Person) obj;
    return Objects.equals(this.name, p.name);
}
```

**Interview Talk**: "The bug is using `==` for string comparison. Strings should use `.equals()`. Also missing null check and class check."

#### Bug 2: HashMap with Broken hashCode()
```java
// WRONG
static class User {
    String name;
    @Override
    public int hashCode() {
        return 1;  // BUG: all objects have same hash!
    }
}

// RIGHT
@Override
public int hashCode() {
    return Objects.hash(name, email);
}
```

**Interview Talk**: "All objects hash to 1, so HashMap degrades to linked list. O(1) becomes O(n). Must distribute hashes."

#### Bug 3: NullPointerException with Autoboxing
```java
// WRONG
public int getValue(Integer x) {
    return x + 10;  // BUG: unboxing null → NPE
}

// RIGHT
public int getValue(Integer x) {
    return x == null ? 0 : x + 10;
}
```

#### Bug 4: Inner Class Memory Leak
```java
// WRONG
class Outer {
    byte[] largeArray = new byte[1024*1024];
    public Inner createInner() {
        return new Inner();  // Inner holds implicit reference to Outer
    }
    class Inner { }  // Inner holds reference; prevents GC of Outer
}

// RIGHT
static class Inner { }  // Static: no implicit reference
```

#### Bug 5: Initialization Order Confusion
```java
// WRONG
class Parent {
    private int x = 10;
    Parent() { printX(); }  // Will print x, but Child's override not yet initialized
    void printX() { System.out.println("Parent: " + x); }
}
class Child extends Parent {
    private int x = 20;
    @Override
    void printX() { System.out.println("Child: " + x); }  // x is still 0!
}
new Child();  // Output: "Child: 0" (Child's x not yet initialized)

// RIGHT: Avoid calling overridable methods from constructors
```

---

## Pattern 3: "Design This"

### Questions Using This Pattern:
- "Design an immutable class"
- "Design a thread-safe counter"
- "Design a rate limiter"

### Your Approach:
1. **Clarify Requirements**: Ask questions
2. **Sketch Design**: High-level structure
3. **Code Key Parts**: Implement critical methods
4. **Discuss Trade-offs**: Why this approach?
5. **Optimize**: What about thread-safety, performance?

### Example: Design an Immutable Class

**Question**: "Design an immutable Person class with a hobbies list"

**Answer**:
```java
final class Person {  // final to prevent extension
    private final String name;
    private final int age;
    private final List<String> hobbies;  // final
    
    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);  // Defensive copy on input
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<String> getHobbies() {
        return new ArrayList<>(hobbies);  // Defensive copy on output
    }
    
    @Override
    public boolean equals(Object o) { ... }
    
    @Override
    public int hashCode() { ... }
}
```

**Interview Talk**: "I made the class final to prevent subclassing. All fields final and private. I do defensive copying on input and output to ensure the list can't be modified externally. This ensures the object is truly immutable."

---

## Pattern 4: "Performance & Trade-offs"

### Questions Using This Pattern:
- "HashMap or TreeMap? Why?"
- "synchronized or ReentrantLock?"
- "ArrayList or LinkedList?"

### Your Approach:
1. **Compare Options**: Pro/con for each
2. **Give Metrics**: Time complexity, memory
3. **Ask Context**: "What's the workload? 90% reads or 50/50?"
4. **Recommend**: "I'd use X because Y, but if Z, then use A"
5. **Justify**: Show data or reasoning

### Example: HashMap vs TreeMap

**Question**: "We need a sorted map. HashMap or TreeMap?"

**Answer**:
| Aspect | HashMap | TreeMap |
|--------|---------|---------|
| Time: get/put | O(1) avg | O(log n) |
| Memory | Lower | Higher (tree structure) |
| Sorted | No | Yes |
| Range queries | No | Yes (subMap, headMap) |

**My Choice**: "Depends on your workload:
- **90% reads, 10% writes**: TreeMap (cost paid once)
- **50/50 mixed**: HashMap if no sort needed; TreeMap if sort needed
- **Need both sorted AND fast**: Hmm. Maybe stream and sort, or cache + refresh strategy.
- **Need sort + insert-order**: LinkedHashMap (insertion order)

I'd start with HashMap and add sorting only if profiling shows it's a bottleneck."

---

## Pattern 5: "Inheritance Trap"

### Questions Using This Pattern:
- "This class extends Animal. Is the design good?"
- "When would you use abstract vs interface?"
- "Why is this violating Liskov Substitution?"

### Your Approach:
1. **Read Design**: Understand the hierarchy
2. **Check Substitutability**: Can child replace parent?
3. **Check Responsibility**: Does child have extra concepts?
4. **Suggest Composition**: Would composition be better?

### Example: Bad Inheritance

**Code**:
```java
class Shape {
    void draw() { }
}
class Circle extends Shape {
    double radius;
}
class Square extends Shape {
    double side;
}
```

**Interview Talk**: "This violates Liskov Substitution. Circle and Square have different properties (radius vs side). Better design:
```java
interface Shape {
    void draw();
    double area();
}
class Circle implements Shape { }
class Square implements Shape { }
```
This way, clients don't need to know about radius/side."

---

## Pattern 6: "Thread-Safety"

### Questions Using This Pattern:
- "Is this thread-safe?"
- "What happens with multiple threads?"
- "How would you make this thread-safe?"

### Your Approach:
1. **Identify Shared State**: What's accessed by multiple threads?
2. **Identify Race Condition**: What can go wrong?
3. **Apply Synchronization**: synchronized, volatile, atomic, lock
4. **Consider Granularity**: Fine-grained vs coarse-grained locks
5. **Test**: What if 100 threads increment?

### Example: Thread-Safety

**Code**:
```java
class Counter {
    private int count = 0;  // WRONG: race condition
    void increment() { count++; }  // Not atomic
}
```

**Interview Talk**: "Race condition. Two threads can both read count=5, increment to 6, write 6. Expected 7, got 6. Fix:
```java
synchronized void increment() { count++; }
// OR
AtomicInteger count = new AtomicInteger(0);
void increment() { count.incrementAndGet(); }
```

synchronized is simple but locks entire method. AtomicInteger is lock-free and faster for simple ops."

---

## Pattern 7: "Gotcha Code"

### Questions Using This Pattern:
Interviewer shows tricky code and asks: "What's the output?"

### Common Gotchas:

#### Gotcha 1: Initialization Order
```java
class Parent {
    int x = 10;
    Parent() { foo(); }
    void foo() { System.out.println(x); }
}
class Child extends Parent {
    int x = 20;
    @Override
    void foo() { System.out.println("Child: " + x); }
}
new Child();  // Output: "Child: 0"  (NOT 20!)
```

**Why**: Child's int x not yet initialized when Parent constructor runs.

#### Gotcha 2: String Pool
```java
String a = "hello";
String b = new String("hello");
String c = "hello";
System.out.println(a == b);  // false (different objects)
System.out.println(a == c);  // true (same pool)
```

#### Gotcha 3: Ternary Short-Circuit
```java
int x = 10;
int y = (x > 5) ? (x = 20) : (x = 30);
System.out.println(x + " " + y);  // "20 20" (not "30 20")
```

#### Gotcha 4: Varargs Null
```java
void foo(int... arr) {
    System.out.println(arr.length);
}
foo(null);  // Prints 0? No, throws NPE (arr.length on null)
foo();      // Prints 0 (empty array)
```

#### Gotcha 5: Equals Not Transitive
```java
Integer a = new Integer(1);
Integer b = new Integer(1);
Integer c = Integer.valueOf(1);
System.out.println(a.equals(b));  // true (same content)
System.out.println(b.equals(c));  // true
// But a and c might not be same reference (depends on valueOf caching)
```

---

## Interview Checklist

Before every interview, ensure you can:

### Module 1-5 (Fundamentals)
- [ ] Explain Integer cache + autoboxing without notes
- [ ] Implement equals/hashCode from memory
- [ ] Explain pass-by-value with an example
- [ ] Spot a bug in inheritance code
- [ ] Design an immutable class

### Module 6-10 (Advanced OOP)
- [ ] Choose between abstract vs interface
- [ ] Implement composition correctly
- [ ] Explain static nested vs inner class
- [ ] Design a thread-safe counter
- [ ] Handle concurrent modifications

### Module 11-15 (Advanced)
- [ ] Explain HashMap resize + treeify
- [ ] Write a generic method with PECS
- [ ] Implement LRU cache in 10 minutes
- [ ] Spot concurrency bugs
- [ ] Explain fail-fast vs fail-safe

### Module 16-20 (Mastery)
- [ ] Design a parking lot system
- [ ] Implement a rate limiter
- [ ] Discuss SOLID violations and fixes
- [ ] Know trade-offs (sync vs lock, map implementations, etc)
- [ ] Explain JVM memory model

---

## Final Tips

1. **Always Ask Questions**: "Should this be thread-safe?", "What's the scale?"
2. **Think Out Loud**: Interviewer wants to see your process, not just answer
3. **Discuss Trade-offs**: "This approach is fast but uses memory. That one is slower but simpler."
4. **Code Carefully**: Test your code in your head before writing
5. **Handle Edge Cases**: null, empty arrays, negative numbers
6. **Use Objects.equals()**: Never `==` for objects (except null checks)
7. **Defensive Copy**: Mutable args and return values in constructors/getters
8. **Prefer Composition**: Over inheritance, when in doubt

---

**Practice**: Go through each pattern above. For each, write a 5-minute answer, then code it. That's how you master interviews.
