# Module 5: Polymorphism

## Key Concepts

### Overloading vs Overriding
- **Overloading**: same method name, different parameters (same class, compile-time resolution).
- **Overriding**: same signature, different class hierarchy (runtime resolution via dynamic dispatch).

### Dynamic Dispatch
- Method resolved at runtime based on actual object type, not reference type.
- `Animal a = new Dog(); a.makeSound();` calls Dog's makeSound(), not Animal's.

### Covariant Returns
- Child can override with a narrower return type (subtype of parent's return type).
- Example: parent returns `Animal`, child returns `Dog` (which IS-A Animal).

### Overload Resolution
- Compiler chooses the most specific overload.
- Priority: exact match > widening > autoboxing > varargs.
- Null matches the most specific overload (not necessarily the expected one).

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Overloading** | Compile-time; same name, different params. |
| **Overriding** | Runtime; same signature, dynamic dispatch. |
| **Dynamic Dispatch** | Actual type determines method called. |
| **Covariant Returns** | Child can return subtype of parent's return. |

