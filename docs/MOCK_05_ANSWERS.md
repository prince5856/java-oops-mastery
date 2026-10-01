# Mock 5 Answers: Collections & Generics (PECS)

## Solution Code

```java
import java.util.*;

// Person class implementing Comparable
class Person implements Comparable<Person> {
    private String name;
    private int age;
    private long id;
    
    public Person(String name, int age, long id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }
    
    @Override
    public int compareTo(Person other) {
        // Compare by age first
        if (this.age != other.age) {
            return Integer.compare(this.age, other.age);
        }
        // If same age, compare by name
        return this.name.compareTo(other.name);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id;
    }
    
    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
    
    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d, id=%d}", name, age, id);
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public long getId() { return id; }
}

// Generic Repository with PECS
public class Repository<T extends Comparable<T>> {
    private List<T> items = new ArrayList<>();
    
    public void add(T item) {
        items.add(item);
    }
    
    public T get(int index) {
        return items.get(index);
    }
    
    public List<T> getAll() {
        return new ArrayList<>(items);  // Defensive copy
    }
    
    // PECS: Producer Extends
    public void addAll(Collection<? extends T> newItems) {
        items.addAll(newItems);
    }
    
    public T findMax() {
        if (items.isEmpty()) return null;
        T max = items.get(0);
        for (T item : items) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }
    
    public T findMin() {
        if (items.isEmpty()) return null;
        T min = items.get(0);
        for (T item : items) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return min;
    }
    
    public void sort() {
        Collections.sort(items);
    }
    
    // Bonus: filter using wildcard generics (Consumer Super)
    public List<T> filter(java.util.function.Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}

// Test code
public class Test {
    public static void main(String[] args) {
        Repository<Person> people = new Repository<>();
        people.add(new Person("Alice", 30, 1L));
        people.add(new Person("Bob", 25, 2L));
        people.add(new Person("Charlie", 35, 3L));
        
        System.out.println("Max: " + people.findMax());    // Charlie, age 35
        System.out.println("Min: " + people.findMin());    // Bob, age 25
        
        people.sort();
        System.out.println("\nAfter sort:");
        people.getAll().forEach(System.out::println);
        
        List<Person> newPeople = new ArrayList<>();
        newPeople.add(new Person("Diana", 28, 4L));
        people.addAll(newPeople);
        
        System.out.println("\nAfter addAll:");
        people.getAll().forEach(System.out::println);
        
        // Bonus: filter
        System.out.println("\nPeople over 28:");
        people.filter(p -> p.getAge() > 28).forEach(System.out::println);
    }
}
```

## Q1: PECS Explanation

**Producer Extends, Consumer Super**

- **Producer Extends**: `List<? extends Number>` - you can read/consume, can't write/produce
  - Use when you only GET items from the collection
  - Example: `addAll(Collection<? extends T> items)` - the source "produces" T items

- **Consumer Super**: `List<? super Number>` - you can write/produce, can't read as Number
  - Use when you only PUT items into the collection
  - Example: `Comparator<? super T> comp` - the comparator "consumes" T for comparison

**Key insight**: Wildcards restrict what you can do, making code type-safe.

## Q2: Extends vs Super

**`List<? extends Number>`**:
- Can read as Number
- Cannot write (don't know exact type)
- Use for: method inputs (reading data)

**`List<? super Number>`**:
- Can write Number
- Cannot read as Number (might be Object)
- Use for: method outputs (writing data)

## Q3: Type Erasure & Covariance

Java generics are **invariant**, not covariant:
- `List<Integer>` and `List<Object>` are different at compile-time
- At runtime, both are `List` (type erasure)
- Requires explicit wildcard: `List<? extends Object>` accepts `List<Integer>`

This is why PECS matters - wildcards make collections covariant/contravariant.

## Q4: Comparable vs Comparator

**Comparable** (`compareTo`):
- Defines "natural" ordering
- Single comparison method
- Use: `class Person implements Comparable<Person>`

**Comparator** (separate object):
- Defines custom ordering
- Can have multiple implementations
- Use: `Comparator<Person> byAge = (a, b) -> a.getAge() - b.getAge();`

## Q5: Repository Type Variance

**NO**, you cannot add a Dog to `Repository<? extends Animal>`.

- `Repository<Cat>` is type-safe (only Cats)
- `Repository<? extends Animal>` accepts any Cat subclass but is read-only
- You cannot write (add) because exact type unknown

**Why**: Prevents type corruption. If you could add Dog to `Repository<? extends Animal>` and it was actually `Repository<Cat>`, you'd have a Dog in a Cat repository.

## Q6: Generic Design Decisions

**Use `T`**:
```java
public T get(int index)  // Return exact type
public void add(T item)  // Accept exact type
```

**Use `? extends T`**:
```java
public void addAll(Collection<? extends T> items)  // Producer: reads items
// Can add List<Dog> to Repository<Animal>
```

**Use `? super T`**:
```java
public void sort(Comparator<? super T> comp)  // Consumer: uses for comparison
// Can use Comparator<Animal> for sorting List<Dog>
```

---

**Total Points**: 100  
**Expected Score**: 80+
