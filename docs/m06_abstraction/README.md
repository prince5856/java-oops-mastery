# Module 6: Abstraction

## Key Concepts

### Abstract Classes vs Interfaces
- **Abstract class**: can have abstract methods, concrete methods, state (fields).
- **Interface**: only abstract methods (until Java 8), no state.
- **Java 8+**: interfaces can have default methods, static methods, private methods.

### Template Method Pattern
- Define algorithm structure in abstract class, let subclasses override specific steps.

### Diamond Problem
- Multiple interfaces with same method signature.
- Java interfaces don't have state, so no true diamond problem like C++.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Abstract Class** | Use when you want state; can't instantiate. |
| **Interface** | Contract for behavior; can extend multiple. |
| **Default Methods** | Interface methods with implementation (Java 8+). |

