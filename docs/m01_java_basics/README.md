# Module 1: Java Basics

## Key Concepts

### Primitives vs Wrappers
- **Primitives**: `byte`, `short`, `int`, `long`, `float`, `double`, `boolean`, `char`. Live on stack, no identity, default values.
- **Wrappers**: `Byte`, `Short`, `Integer`, `Long`, `Float`, `Double`, `Boolean`, `Character`. Objects, can be null, identity equality.
- **Integer Cache**: `-128 to 127` cached; `new Integer(1) == Integer.valueOf(1)` is false, but `Integer.valueOf(1) == Integer.valueOf(1)` is true.

### Autoboxing Pitfalls
- Autoboxing can mask NPE: `Integer x = null; int y = x;` throws NPE at unboxing, not at assignment.
- Autoboxing in comparisons: `Integer a = 100; Integer b = 100;` → `a == b` is true (cached), but `a == new Integer(100)` is false.

### String Pool & Immutability
- Strings are immutable. String literals go to string pool; `new String("hello")` creates a heap object.
- `intern()` moves a string to the pool.
- String pool lives in heap (Java 7+), not PermGen.

### == vs equals()
- `==`: reference equality for objects, value equality for primitives.
- `equals()`: overridable; default is reference equality.
- Arrays: use `Arrays.equals()`, not `==`.

### StringBuilder
- Mutable string builder; use for concatenation in loops. `toString()` is final.
- Not thread-safe; use `StringBuffer` for concurrent access (rare).

### Pass-by-Value
- Java is pass-by-value for primitives and references.
- Reassigning a parameter doesn't affect the caller's variable.
- Modifying an object via a reference parameter DOES affect the caller's object.

### static / final
- `static`: class-level, shared by all instances, initialized once at class load time.
- `final` on class: no subclassing.
- `final` on method: no overriding.
- `final` on field: immutable after initialization (set once in constructor or initializer).
- `final` on primitive: const value; on object: const reference (not deep immutability).

### Varargs
- `void foo(int... args)` expands to array `int[]`.
- Calling `foo(1, 2, 3)` or `foo(new int[]{1, 2, 3})` are equivalent.
- Can only have one varargs param, must be last.
- Null varargs: `foo(null)` passes `null` array, not empty array.

### Operators
- **Instanceof**: `obj instanceof MyClass` is false if `obj` is null.
- **Ternary**: `a ? b : c` evaluates only one of b or c (short-circuit).
- **Shift**: `<<`, `>>` (sign-extend), `>>>` (zero-extend).

## Common Interview Pitfalls
1. Assume `==` and `equals()` are the same (they're not).
2. Forget that autoboxing can throw NPE.
3. Confuse string literals in the pool with new String() objects.
4. Assume pass-by-value means reassignment affects the caller.
5. Forget that varargs is syntactic sugar for arrays.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Primitives vs Wrappers** | Primitives on stack, no null; wrappers are objects, can be null. |
| **Integer Cache** | -128 to 127 cached; use valueOf(), not == for equality. |
| **String Immutability** | Strings cannot change; string pool is in heap (Java 7+). |
| **== vs equals()** | == for reference/value; equals() for content. |
| **Pass-by-Value** | Reassigning param doesn't affect caller; modifying object does. |
| **static vs final** | static is shared; final is immutable ref or const value. |
| **Varargs** | Syntax sugar for array; only one per method, must be last. |

