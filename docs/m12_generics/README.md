# Module 12: Generics

## Key Concepts

### Bounded Types
- `<T extends Number>`: upper bound.
- `<T super String>`: lower bound.

### Wildcards
- `<?>`: unbounded.
- `<? extends Number>`: upper bounded.
- `<? super String>`: lower bounded.

### PECS
- Producer Extends, Consumer Super.
- Use `extends` for reading, `super` for writing.

### Type Erasure
- Generics are compile-time only; erased at runtime.
- Cannot use `T` in `instanceof`.
- Cannot create arrays of generic types.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Bounded** | Restrict type parameter to subtypes/supertypes. |
| **Wildcards** | `?`, `? extends`, `? super`. |
| **PECS** | Producer (extends), Consumer (super). |
| **Type Erasure** | Generics removed at runtime. |

