# Module 2: Classes, Objects, and Initialization

## Key Concepts

### Constructors and this()
- Constructors initialize objects; they're called when you use `new`.
- `this()` calls another constructor in the same class (constructor chaining).
- Must be the first statement in the constructor.
- Helps avoid code duplication in multiple constructors.

### Initialization Order (Critical for interviews!)
1. **Class loading** (once): static variables initialized, static blocks run.
2. **Object creation** (each `new`): instance variables initialized to default values, instance initialization blocks run, then constructor body runs.

**Order within a class:**
1. Static variables and blocks (in order, top to bottom) — once at class load.
2. Instance variables (initialized to defaults).
3. Instance initialization blocks (top to bottom).
4. Constructor body.

### Constructor Chaining
- `this(args)` calls another constructor; avoid infinite loops.
- Each constructor in the chain runs its body.
- The most specific constructor should call `this()` to avoid duplication.

### Static vs Instance Members
- **Static**: class-level, shared, initialized once at class load.
- **Instance**: per-object, created for each instance.
- Static context (static method) cannot access instance members without an instance.

## Common Interview Pitfalls
1. Forgetting initialization order; assuming instance vars are null before constructor.
2. Calling `this()` not as the first statement.
3. Infinite constructor chaining.
4. Accessing instance members from static methods.
5. Not understanding when static blocks run (class load, not object creation).

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Constructors** | Initialize objects; can be overloaded; called with `new`. |
| **this()** | Calls another constructor; must be first statement. |
| **Constructor Chaining** | Call `this()` to avoid duplication; each constructor body runs. |
| **Initialization Order** | Static (class load), then instance vars (defaults), then instance blocks, then constructor. |
| **Static Context** | Cannot access instance members; must use instance reference. |

