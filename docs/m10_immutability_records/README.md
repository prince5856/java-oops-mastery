# Module 10: Immutability and Records

## Key Concepts

### Immutable Class Design
- Make class final.
- All fields private and final.
- No setters.
- Defensive copying for mutable args.

### Records (Java 16+)
- Compact syntax for immutable data classes.
- Automatic equals(), hashCode(), toString(), constructor, getters.
- Cannot extend; can implement interfaces.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Immutable** | final class, final fields, no setters, defensive copy. |
| **Records** | Concise immutable data classes; auto-generated methods. |

