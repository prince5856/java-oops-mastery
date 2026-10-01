# Module 1: Java Basics - Interview Questions

## 15 Verbal Interview Questions for 4-Year Backend Engineer

### Q1: Explain autoboxing and the Integer cache pitfall.

**Model Answer:**
Autoboxing is the automatic conversion between primitives and wrapper objects. When you use `Integer.valueOf()` or implicit autoboxing, integers in the range -128 to 127 are cached and reused. This means `Integer a = 100; Integer b = 100; a == b;` returns true because they're the same cached object. But outside the cache range, `Integer a = 200; Integer b = 200; a == b;` returns false because they're different objects. Always use `.equals()` for value comparison, not `==`, to avoid this pitfall.

Interviewer might probe: "Why is this range chosen?" (Common use case), "What about `new Integer()`?" (Always creates new objects, doesn't use cache)

---

### Q2: What's the difference between == and equals()?

**Model Answer:**
`==` checks reference equality (for objects) or value equality (for primitives). `equals()` is an instance method that can be overridden to define content equality. For example, two String objects with the same content return false with `==` but true with `equals()`. Best practice: use `==` only for null checks or when you specifically need identity, and use `equals()` for content comparison.

---

### Q3: Explain String immutability and the string pool.

**Model Answer:**
Strings are immutable once created. Any operation that appears to modify a string (like concatenation) actually creates a new String object. String literals are automatically interned and stored in the string pool (a global cache in the heap since Java 7), allowing memory reuse. For example, `String a = "hello"; String b = "hello";` points to the same object. But `new String("hello")` creates a separate heap object. You can use `intern()` to manually move a string to the pool.

Pitfall: calling `intern()` on arbitrary strings can exhaust the pool and cause memory leaks.

---

### Q4: Is Java pass-by-value or pass-by-reference?

**Model Answer:**
Java is strictly pass-by-value. For primitives, the value is copied. For objects, the reference (memory address) is copied, not the object itself. This means:
- Reassigning a parameter doesn't affect the caller's variable.
- Modifying an object's state via the reference parameter DOES affect the caller's object.

For example, reassigning the reference inside a method (`obj = new MyClass()`) doesn't affect the caller's reference, but calling `obj.setField(value)` does modify the caller's object.

---

### Q5: Explain the difference between static and instance members.

**Model Answer:**
Static members are class-level, shared by all instances and initialized once at class load time. Instance members are per-object, created for each instance. Static members are used for shared state or utility methods. Instance members hold object-specific state. Example: a static counter tracks total instances created (class-level), while each instance has a unique ID (instance-level).

Interviewer might probe: "Can you access an instance member from a static method?" (No, because static doesn't have `this`)

---

### Q6: What does final mean in different contexts?

**Model Answer:**
- `final` on a class: cannot be extended (subclassed).
- `final` on a method: cannot be overridden in subclasses.
- `final` on a field: immutable after initialization (can be set once in constructor or initializer). Once assigned, cannot be reassigned.

Important: a final reference is immutable, but the object it points to is not (not deep immutability). For example, `final List<Integer> list = new ArrayList<>();` allows `list.add(1)` but not `list = new ArrayList<>()`.

---

### Q7: How does varargs work?

**Model Answer:**
Varargs (`int... args`) is syntactic sugar for an array parameter (`int[] args`). `foo(1, 2, 3)` is equivalent to `foo(new int[]{1, 2, 3})`. Key points:
- Only one varargs parameter per method, must be last.
- Calling `foo(null)` passes a null array, not an empty array, which can cause NPE.

Interviewer might ask: "Can you have multiple varargs?" (No, only one, and it must be last)

---

### Q8: Explain the performance implications of string concatenation.

**Model Answer:**
String concatenation with `+` in a loop is inefficient because strings are immutable. Each `+` creates a new String object, resulting in O(n²) complexity for n concatenations. Use StringBuilder instead for O(n) complexity. Example:

```java
// Bad: O(n²)
String result = "";
for (int i = 0; i < n; i++) {
    result += i;  // Creates new String each time
}

// Good: O(n)
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append(i);
}
```

---

### Q9: What's the difference between StringBuilder and StringBuffer?

**Model Answer:**
Both are mutable string builders. `StringBuffer` is synchronized (thread-safe), while `StringBuilder` is not. In modern Java, use `StringBuilder` almost always because synchronization has overhead and is rarely needed (strings are immutable anyway, so thread-safety concerns are usually elsewhere). Use `StringBuffer` only if you're explicitly sharing the builder across threads, which is rare.

---

### Q10: Explain the instanceof operator and null behavior.

**Model Answer:**
`instanceof` checks if an object is an instance of a class or implements an interface. Key point: `if (obj instanceof MyClass)` returns false if `obj` is null, not an error. This makes it safe for null checks. Example:

```java
String str = null;
if (str instanceof String) {  // false, not NPE
    // ...
}
```

This is useful for safe type checking without null guards.

---

### Q11: What are the default values of fields in Java?

**Model Answer:**
- Numeric primitives (`int`, `long`, `double`, etc.): 0
- `boolean`: false
- `char`: '\u0000' (null character)
- Object references: null
- Static fields are initialized at class load, instance fields at object creation.

Note: local variables (on the stack) do NOT have default values; the compiler requires you to initialize them before use.

---

### Q12: Explain boxing and unboxing costs.

**Model Answer:**
Autoboxing/unboxing have overhead compared to primitives:
- Autoboxing creates wrapper objects (memory allocation).
- Unboxing extracts the value (null unboxing throws NPE).
- Wrapper objects are heavier than primitives (extra bytes for object headers).

In performance-critical loops, prefer primitives or use primitive collections (e.g., IntStream instead of List<Integer>).

---

### Q13: What's the difference between compile-time and runtime string concatenation?

**Model Answer:**
Compile-time concatenation (literals): `String a = "hello" + "world";` is optimized to `String a = "helloworld";` at compile time by the compiler.

Runtime concatenation (variables): `String a = x + y;` generates code that uses StringBuilder at runtime (or StringConcatFactory in Java 9+).

---

### Q14: Explain the ternary operator and short-circuiting.

**Model Answer:**
The ternary operator `(condition ? true_expr : false_expr)` short-circuits: only one of true_expr or false_expr is evaluated, never both. This is important for side effects.

Example:
```java
int x = 10;
int y = (x > 5) ? 20 : (x = 30);  // x = 20, y = 20 (x = 30 never executes)
```

---

### Q15: What's the difference between -128 to 127 and other cached ranges?

**Model Answer:**
Only integers from -128 to 127 are cached by default. This range was chosen because:
1. Common use case optimization (small integers are frequently used).
2. One byte's range: -128 to 127 fits in a signed byte.

You can cache more integers with `-XX:AutoBoxCacheMax=N`, but this is rarely needed. Always use `equals()` for integer comparison if you're not sure about caching.

---
