# Module 4: Inheritance

## Key Concepts

### super Keyword
- `super()` calls parent constructor; must be first statement.
- `super.method()` calls parent method (useful for override).
- Constructor chaining: child calls `super()`, parent calls `super()`, etc.

### Method Hiding vs Overriding
- Overriding: child replaces parent method (same signature, instance method). Resolved at runtime (dynamic dispatch).
- Hiding: child hides parent field or static method (compile-time resolution).

### final, IS-A vs HAS-A
- `final` class: cannot extend.
- `final` method: cannot override.
- IS-A: inheritance (Animal -> Dog is a Dog IS-A Animal).
- HAS-A: composition (Car HAS-A Engine).

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **super()** | Calls parent constructor; must be first. |
| **Overriding** | Dynamic dispatch; method resolved at runtime. |
| **Hiding** | Compile-time; static methods and fields. |
| **IS-A vs HAS-A** | Prefer composition over inheritance. |

