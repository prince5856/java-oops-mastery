# Mock 6: Intermediate - Immutability & Builder Pattern

**Duration**: 60 minutes | **Difficulty**: Intermediate | **Topics**: Immutability, Builder Pattern, Defensive Copying, Validation

---

## Section 1: Conceptual Questions (10 min)

**Q1** (3 min): What makes an object immutable in Java? List 5 requirements and explain each.

**Q2** (3 min): Why is defensive copying important? Show an example where it prevents bugs.

**Q3** (2 min): What are the benefits and drawbacks of immutable objects?

**Q4** (2 min): When would you use the Builder pattern vs constructor with many parameters?

---

## Section 2: Coding Problem (40 min)

### Problem: Design Immutable DatabaseConfig Class

**Requirements**:

1. Create an immutable `DatabaseConfig` class with:
   - Fields (all private final):
     - `host` (String)
     - `port` (int)
     - `username` (String)
     - `password` (String)
     - `timeout` (long)
     - `retryAttempts` (int)
     - `properties` (Map<String, String>)

2. Implement a nested `Builder` class:
   - All fields optional (sensible defaults)
   - Fluent API: `new Builder().host("localhost").port(5432).build()`
   - Validation: port 1-65535, timeout > 0, retryAttempts >= 0
   - Throw IllegalArgumentException on invalid values

3. Immutability features:
   - No setters
   - `getProperties()` returns unmodifiable Map (defensive copy)
   - Constructor is private (only Builder can create)
   - Implement `toString()` for debugging

4. Test code:
```java
DatabaseConfig config = new DatabaseConfig.Builder()
    .host("db.example.com")
    .port(5432)
    .username("admin")
    .password("secret123")
    .timeout(30_000)
    .retryAttempts(3)
    .addProperty("ssl", "true")
    .addProperty("pool_size", "10")
    .build();

System.out.println(config);

// Verify immutability
Map<String, String> props = config.getProperties();
try {
    props.put("new_key", "new_value");  // Should fail
} catch (UnsupportedOperationException e) {
    System.out.println("Properties are immutable ✓");
}
```

**Bonus**: Add a `copy()` method that returns a new Builder with current values, enabling "modified copy" pattern.

---

## Section 3: Behavioral (10 min)

**Q5** (5 min): Discuss: If you store a List as a final field in an immutable object, is it truly immutable? How do you fix it?

**Q6** (5 min): When designing an immutable class with many fields, how do you handle validation errors? Why not just throw in the constructor?

---

## Rubric

| Section | Points |
|---------|--------|
| Conceptual (Q1-4) | 20 |
| Coding (Builder + validation + immutability + test) | 60 |
| Behavioral (Q5-6) | 20 |
| **Total** | **100** |

**90+**: Excellent | **75-89**: Good | **<75**: Review Modules 9-10

---

See answers: `MOCK_06_ANSWERS.md`
