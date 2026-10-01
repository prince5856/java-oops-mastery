# Module 16: JVM Memory Model

## Key Concepts

### Stack vs Heap
- **Stack**: primitives, references. LIFO. Thread-local.
- **Heap**: objects. Shared. GC-managed.

### Garbage Collection
- Mark-and-sweep, generational.
- Young, Old, Permanent/Metaspace generations.

### String Pool
- Heap-based (Java 7+).
- Intern moves to pool.

### Class Loading
- Bootstrap, Extension, Application classloaders.
- Lazy loading.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Stack** | Thread-local; primitives. |
| **Heap** | Shared; objects; GC-managed. |
| **GC** | Automatic memory reclaim. |
| **String Pool** | Heap-based interned strings. |

