# Module 11: Exception Handling

## Key Concepts

### Checked vs Unchecked
- **Checked**: must be declared or caught (IOException, SQLException).
- **Unchecked**: RuntimeException subclasses (NPE, ArrayIndexOutOfBoundsException).

### Exception Chaining
- `throw new CustomException("msg", cause)` to preserve stack trace.

### Try-With-Resources
- Auto-closes resources implementing AutoCloseable.
- Cleaner than try-finally.

## Cheat Sheet

| Concept | Key Takeaway |
|---------|--------------|
| **Checked** | Must declare in throws; common in I/O. |
| **Unchecked** | Don't need to declare; programming errors. |
| **Chaining** | Preserve cause with constructor. |
| **Try-with-resources** | Auto-closes; use for resources. |

