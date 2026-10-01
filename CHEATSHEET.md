# Java + OOP Cheat Sheet (One-Page Last-Minute Review)

## Module 1: Java Basics
- **Primitives vs Wrappers**: Primitives on stack, wrappers are objects, can be null.
- **Integer Cache**: -128 to 127 cached; use valueOf(), not `==` for equality.
- **String Immutability**: Strings cannot change; string pool in heap (Java 7+).
- **Pass-by-Value**: Primitives copied, references copied (not deep).
- **static vs final**: static is shared; final is immutable ref or const value.
- **Varargs**: Syntax sugar for array; only one per method, must be last.

## Module 2: Classes & Objects
- **Constructors**: Initialize objects; can overload; called with `new`.
- **this()**: Calls another constructor; must be first statement.
- **Initialization Order**: Static (class load) → instance vars → instance blocks → constructor.

## Module 3: Encapsulation
- **Access Modifiers**: public > protected > package-private > private.
- **Invariants**: Encapsulate mutable fields; validate in setters.
- **Defensive Copying**: Copy mutable args in constructor; return copies from getters.

## Module 4: Inheritance
- **super()**: Calls parent constructor; must be first.
- **Overriding**: Dynamic dispatch; method resolved at runtime.
- **IS-A vs HAS-A**: Prefer composition over inheritance.

## Module 5: Polymorphism
- **Overloading**: Compile-time; same name, different params.
- **Overriding**: Runtime; same signature, dynamic dispatch.
- **Dynamic Dispatch**: Actual type determines method called.

## Module 6: Abstraction
- **Abstract Class**: Can have state; can't instantiate.
- **Interface**: Contract for behavior; can extend multiple (Java 8+ default methods).
- **Template Method**: Skeleton in abstract class; let subclasses override steps.

## Module 7: Object Class Contracts
- **equals()**: Reflexive, symmetric, transitive, consistent, null-safe.
- **hashCode()**: If `a.equals(b)`, then `a.hashCode() == b.hashCode()`.
- **Comparable**: Natural ordering (implement compareTo).
- **Comparator**: Custom ordering (separate object).

## Module 8: Object Relationships
- **Association**: Loose coupling; objects independent.
- **Aggregation**: "has-a"; independent lifetimes.
- **Composition**: "has-a"; dependent lifetimes; delete parent deletes child.

## Module 9: Nested Classes
- **Static Nested**: No implicit reference to outer.
- **Inner Class**: Has implicit `Outer.this` reference; memory leak risk.
- **Local/Anonymous**: Scoped to method; final vars accessible.

## Module 10: Immutability & Records
- **Immutable**: final class, final fields, no setters, defensive copy.
- **Records** (Java 16+): Concise immutable data classes; auto-generated methods.

## Module 11: Exception Handling
- **Checked**: Must declare or catch (IOException).
- **Unchecked**: RuntimeException; don't need to declare.
- **Chaining**: `throw new CustomException("msg", cause)` to preserve stack trace.
- **Try-with-resources**: Auto-closes; use for resources.

## Module 12: Generics
- **Bounded**: `<T extends Number>` (upper), `<T super String>` (lower).
- **Wildcards**: `?`, `? extends`, `? super`.
- **PECS**: Producer (extends), Consumer (super).
- **Type Erasure**: Generics removed at runtime; can't use `T` in instanceof.

## Module 13: Collections Internals
- **HashMap**: Hash table; O(1) average; resize when load factor > 0.75; treeify when bucket > 8.
- **TreeMap**: Sorted keys; O(log n).
- **LinkedHashMap**: Insertion/access order.
- **Fail-Fast**: Iterator throws ConcurrentModificationException if modified.
- **ConcurrentHashMap**: Thread-safe; segment locks.

## Module 14: Functional Java
- **Lambda**: `(params) -> body`; for functional interfaces.
- **Streams**: Lazy evaluation; chain operations.
- **Collectors**: groupingBy, toList, joining, etc.
- **Optional**: Avoid `isPresent() ? get() : default`; use wisely.

## Module 15: Concurrency
- **synchronized**: Lock on object; mutual exclusion.
- **volatile**: Visibility across threads; no atomicity.
- **ReentrantLock**: Explicit lock; more flexible than synchronized.
- **Atomic**: Lock-free; CAS-based (AtomicInteger, etc).
- **ExecutorService**: Thread pool.
- **CompletableFuture**: Async operations.
- **Deadlock**: Circular wait on locks; prevent by ordering.

## Module 16: JVM Memory
- **Stack**: Thread-local; primitives, references; LIFO.
- **Heap**: Shared; objects; GC-managed.
- **String Pool**: Heap-based; intern moves to pool.
- **Class Loading**: Bootstrap, Extension, Application classloaders; lazy loading.

## Module 17: Modern Java
- **var** (Java 10+): Local variable type inference.
- **switch Expressions** (Java 14+): Pattern matching; expression form.
- **Text Blocks** (Java 15+): Multi-line strings.
- **Sealed Classes** (Java 17+): Restrict inheritance (`sealed class X permits Y, Z`).
- **Pattern Matching** (Java 16+): `instanceof` with pattern variables.

## Module 18: SOLID
- **SRP**: One responsibility per class.
- **OCP**: Open for extension, closed for modification.
- **LSP**: Subtypes substitutable for base types.
- **ISP**: Focused interfaces; don't force unused dependencies.
- **DIP**: Depend on abstractions, not concrete implementations.

## Module 19: Design Patterns
- **Singleton**: Single instance; thread-safe variants (eager, lazy, enum).
- **Builder**: Fluent construction; separate builder object.
- **Factory**: Abstract object creation; decouple client.
- **Strategy**: Encapsulate algorithms; interchange at runtime.
- **Observer**: Notify changes; event listeners.
- **Decorator**: Add behavior dynamically without inheritance.
- **State**: Behavior changes with state; separate state objects.
- **Template Method**: Algorithm skeleton in abstract class.

## Module 20: Capstone (Machine Coding)
- **Parking Lot**: Levels, spots, reservation, cancellation, payment. Use composition, encapsulation.
- **Rate Limiter**: Token bucket or sliding window. Thread-safe. Handle concurrent requests.
- **LRU Cache**: Eviction listener callbacks. Generics. Linked structures. O(1) get/put.

---

## Quick Lookup by Interview Question Type

### "Explain X"
- **String Pool**: Heap-based; literals interned; intern() moves to pool.
- **Pass-by-Value**: Primitives copied; references copied (not object).
- **Integer Cache**: -128 to 127; use valueOf(), not new Integer().
- **Generics**: Type erasure; can't use T in instanceof; PECS.
- **Synchronized**: Mutual exclusion; lock on object; not reentrant (use ReentrantLock).

### "Implement/Design X"
- **Immutable Class**: final class, final fields, no setters, defensive copy, equals/hashCode.
- **equals/hashCode**: Check type, null-safe, consistent, hash collision okay.
- **Singleton**: Enum best (thread-safe, serializable); else lazy init with lock.
- **LRU Cache**: LinkedHashMap or LinkedList + HashMap; notify on eviction.
- **Rate Limiter**: Token bucket; increment tokens per unit time; permit if tokens >= threshold.

### "What's the Trade-Off?"
- **Composition vs Inheritance**: Composition more flexible; inheritance tighter coupling.
- **Interface vs Abstract Class**: Interface for contract; abstract for shared code + state.
- **HashMap vs TreeMap**: HashMap faster (O(1)) but unsorted; TreeMap sorted but slower (O(log n)).
- **synchronized vs ReentrantLock**: synchronized simpler; ReentrantLock more control (conditions, tryLock).

### "Pitfalls"
- **Autoboxing NPE**: Integer x = null; int y = x; → NPE at unboxing.
- **Equals/HashCode**: Implement both; must be consistent.
- **Varargs null**: foo(null) passes null array, not empty array.
- **Initialization Order**: Instance vars before constructor; static before instance.
- **Inner Class Leak**: Inner class holds implicit reference to outer; can prevent GC.

---

**Pro Tip**: Memorize this cheat sheet. In an interview, you can reference "As I mentioned in my prep, X is Y because Z" if you get stuck.
