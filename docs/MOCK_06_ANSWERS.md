# Mock 6 Answers: Immutability & Builder Pattern

## Solution Code

```java
import java.util.*;

public final class DatabaseConfig {
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final long timeout;
    private final int retryAttempts;
    private final Map<String, String> properties;
    
    // Private constructor - only Builder can call
    private DatabaseConfig(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.username = builder.username;
        this.password = builder.password;
        this.timeout = builder.timeout;
        this.retryAttempts = builder.retryAttempts;
        this.properties = Collections.unmodifiableMap(
            new HashMap<>(builder.properties)
        );
    }
    
    // Getters (no setters - immutable)
    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public long getTimeout() { return timeout; }
    public int getRetryAttempts() { return retryAttempts; }
    public Map<String, String> getProperties() {
        return properties;  // Already unmodifiable
    }
    
    @Override
    public String toString() {
        return "DatabaseConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", timeout=" + timeout +
                ", retryAttempts=" + retryAttempts +
                ", properties=" + properties +
                '}';
    }
    
    // Bonus: copy() method for modified-copy pattern
    public Builder copy() {
        return new Builder()
            .host(this.host)
            .port(this.port)
            .username(this.username)
            .password(this.password)
            .timeout(this.timeout)
            .retryAttempts(this.retryAttempts)
            .addAllProperties(this.properties);
    }
    
    // Builder inner class
    public static class Builder {
        private String host = "localhost";
        private int port = 5432;
        private String username = "user";
        private String password = "";
        private long timeout = 10_000;
        private int retryAttempts = 0;
        private Map<String, String> properties = new HashMap<>();
        
        public Builder host(String host) {
            this.host = Objects.requireNonNull(host, "host cannot be null");
            return this;
        }
        
        public Builder port(int port) {
            if (port < 1 || port > 65535) {
                throw new IllegalArgumentException("port must be 1-65535, got " + port);
            }
            this.port = port;
            return this;
        }
        
        public Builder username(String username) {
            this.username = Objects.requireNonNull(username, "username cannot be null");
            return this;
        }
        
        public Builder password(String password) {
            this.password = Objects.requireNonNull(password, "password cannot be null");
            return this;
        }
        
        public Builder timeout(long timeout) {
            if (timeout <= 0) {
                throw new IllegalArgumentException("timeout must be > 0, got " + timeout);
            }
            this.timeout = timeout;
            return this;
        }
        
        public Builder retryAttempts(int attempts) {
            if (attempts < 0) {
                throw new IllegalArgumentException("retryAttempts cannot be negative");
            }
            this.retryAttempts = attempts;
            return this;
        }
        
        public Builder addProperty(String key, String value) {
            this.properties.put(key, value);
            return this;
        }
        
        public Builder addAllProperties(Map<String, String> props) {
            this.properties.putAll(props);
            return this;
        }
        
        public DatabaseConfig build() {
            return new DatabaseConfig(this);
        }
    }
}

// Test code
public class Test {
    public static void main(String[] args) {
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
        
        // Test validation
        try {
            new DatabaseConfig.Builder().port(99999).build();
        } catch (IllegalArgumentException e) {
            System.out.println("Port validation works ✓");
        }
        
        // Test copy pattern
        DatabaseConfig config2 = config.copy()
            .port(3306)
            .build();
        System.out.println("\nCopied config with new port:");
        System.out.println(config2);
    }
}
```

## Q1: Five Immutability Requirements

1. **Make class `final`**: Prevents subclasses from adding mutable behavior
2. **Make all fields `private final`**: Prevents modification and external access
3. **No setters**: Eliminates methods that change state
4. **Defensive copying**: Copy mutable objects in constructor/getters
5. **Return unmodifiable collections**: Wrap returned collections/arrays

## Q2: Defensive Copying

**Example of bug without it**:
```java
// Bad - no defensive copy
public class ImmutableConfig {
    private final Map<String, String> props;
    
    public ImmutableConfig(Map<String, String> props) {
        this.props = props;  // BUG: direct reference
    }
    
    public Map<String, String> getProperties() {
        return props;  // BUG: caller can modify
    }
}

Map<String, String> myProps = new HashMap<>();
myProps.put("key", "value");
ImmutableConfig config = new ImmutableConfig(myProps);

myProps.put("key", "HACKED");  // Modifies config's internal state!
```

**Fix**: Copy in constructor and return unmodifiable wrapper

## Q3: Benefits vs Drawbacks

**Benefits**:
- Thread-safe (no synchronization needed)
- Predictable behavior (state never changes)
- Easy to reason about
- Safe to share/cache

**Drawbacks**:
- Memory overhead (copying)
- Performance (creating new objects for changes)
- More verbose (Builder pattern)

## Q4: Builder vs Constructor

**Constructor**:
```java
new Person("John", 30, "john@example.com")  // Hard to read with many params
```

**Builder**:
```java
new Person.Builder()
    .name("John")
    .age(30)
    .email("john@example.com")
    .build()  // Clear and maintainable
```

Use Builder when:
- More than 3-4 parameters
- Many optional parameters
- Want validation before construction
- Need immutability with complex setup

## Q5: Final Field Mutability

**NO**, a `final` List is NOT immutable:
```java
private final List<String> items = new ArrayList<>();

// Final prevents reassignment, but not modification
items.add("hack");  // Still works!
```

**Fix**: Return unmodifiable wrapper
```java
public List<String> getItems() {
    return Collections.unmodifiableList(items);
}
```

Or use immutable collection:
```java
private final List<String> items = List.of("a", "b");  // Java 9+
```

## Q6: Validation in Builder

**Why not validate in constructor?**
- Constructor might have state already modified
- Hard to give good error messages
- Builder catches errors before object creation

**Best practice**: Validate in Builder.build()
```java
public DatabaseConfig build() {
    if (port < 1 || port > 65535) {
        throw new IllegalArgumentException("Invalid port");
    }
    return new DatabaseConfig(this);
}
```

All validation happens before the immutable object is created, so it's always valid.

---

**Total Points**: 100  
**Expected Score**: 80+
