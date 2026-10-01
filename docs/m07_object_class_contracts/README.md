# Module 7: Object Class Contracts (equals, hashCode, toString)

## Key Concepts

### equals() Contract
- Reflexive: `a.equals(a)` is true.
- Symmetric: if `a.equals(b)`, then `b.equals(a)`.
- Transitive: if `a.equals(b)` and `b.equals(c)`, then `a.equals(c)`.
- Consistent: calling multiple times gives same result.
- Null-safe: `a.equals(null)` is false.

### hashCode() Contract
- If `a.equals(b)`, then `a.hashCode() == b.hashCode()`.
- HashCode can be same for unequal objects (collision).
- Called by HashMap, HashSet, etc.

### Comparable vs Comparator
- `Comparable`: natural ordering (implement compareTo).
- `Comparator`: custom ordering (separate object).

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **equals()** | Implement contract; check type first. |
| **hashCode()** | Consistent with equals(); used for hashing. |
| **toString()** | Override for debugging; return meaningful string. |
| **Comparable** | Natural ordering; implement in class. |
| **Comparator** | Custom ordering; separate class. |

