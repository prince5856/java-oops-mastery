# Module 9: Nested Classes

## Key Concepts

### Types of Nested Classes
- **Static nested class**: part of class, but static.
- **Inner class**: non-static; has access to outer instance.
- **Local class**: defined in method.
- **Anonymous class**: unnamed; often for listeners/callbacks.

### Memory Leaks
- Inner classes hold implicit reference to outer instance.
- Can prevent garbage collection.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Static Nested** | No implicit reference to outer. |
| **Inner Class** | Has implicit `Outer.this` reference. |
| **Local Class** | Scoped to method; final vars accessible. |
| **Anonymous** | One-time use; for interfaces/abstract. |

