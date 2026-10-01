# Module 14: Functional Java

## Key Concepts

### Lambdas and Functional Interfaces
- Single abstract method interface (SAM).
- Lambda syntax: `(params) -> body`.
- Method references: `::`

### Streams
- Lazy evaluation; operations not executed until terminal.
- Collectors for aggregation (groupingBy, joining, etc).
- FlatMap for flattening.

### Optional
- Don't overuse; use wisely.
- Avoid `isPresent() ? get() : default`.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Lambda** | Compact functional syntax. |
| **Functional Interface** | SAM; can use lambda. |
| **Streams** | Lazy; use for data transformations. |
| **Collectors** | groupingBy, toList, joining. |
| **Optional** | Avoid null; use wisely. |

