# Module 3: Encapsulation

## Key Concepts

### Access Modifiers
- `public`: accessible everywhere.
- `protected`: accessible in same package and subclasses.
- `package-private` (default): accessible in same package only.
- `private`: accessible only in the class.

### Invariants and Defensive Copying
- Invariants: constraints on field values (e.g., age must be >= 0).
- Encapsulation protects invariants via private fields and validated setters.
- Defensive copying: copy mutable arguments in constructor and return copies from getters (e.g., for lists).

### Package Design
- Keep related classes in the same package.
- Use access modifiers to expose only what's needed.
- Hide implementation details.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Access Modifiers** | public > protected > package-private > private. |
| **Invariants** | Encapsulate mutable fields; validate in setters. |
| **Defensive Copying** | Copy mutable args in constructor; return copies from getters. |

