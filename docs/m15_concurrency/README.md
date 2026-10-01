# Module 15: Concurrency

## Key Concepts

### Threads and Synchronization
- `synchronized`: mutual exclusion.
- `volatile`: visibility guarantee.
- Happens-before relationships.

### Locks and Atomics
- ReentrantLock, Condition.
- AtomicInteger, AtomicReference.

### ExecutorService and CompletableFuture
- Thread pool management.
- Async computations.

### Deadlock
- Circular wait on locks.
- Prevent by ordering lock acquisition.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **synchronized** | Lock on object; mutual exclusion. |
| **volatile** | Visibility across threads. |
| **ReentrantLock** | Explicit lock; more flexible. |
| **Atomic** | Lock-free; CAS-based. |
| **ExecutorService** | Thread pool. |
| **CompletableFuture** | Async operations. |

