# Java/OOP Gotchas & Edge Cases

Master these edge cases and you'll handle any curveball in an interview.

---

## 1. Autoboxing & null Gotchas

### Gotcha 1.1: Unboxing null throws NPE
```java
Integer x = null;
int y = x;  // NullPointerException
// Fix: if (x != null) int y = x;
```

### Gotcha 1.2: Integer cache boundary
```java
Integer a = 127;    // Cached
Integer b = 127;
System.out.println(a == b);  // true

Integer c = 128;    // NOT cached
Integer d = 128;
System.out.println(c == d);  // false
```

### Gotcha 1.3: new Integer() vs valueOf()
```java
Integer a = new Integer(100);     // Always new object
Integer b = Integer.valueOf(100); // May be cached
System.out.println(a == b);       // false
System.out.println(a.equals(b));  // true
```

### Gotcha 1.4: Comparing with >
```java
Integer a = null;
if (a > 10) { }  // NPE! Not "false"
// Fix: if (a != null && a > 10) { }
```

### Gotcha 1.5: Collections and autoboxing
```java
List<Integer> list = new ArrayList<>();
list.add(1);
list.add(2);
list.remove(1);  // Removes index 1, not value 1!
System.out.println(list);  // [1], not [2]
// Fix: list.remove(Integer.valueOf(1));
```

---

## 2. String & Immutability Gotchas

### Gotcha 2.1: String pool and new String()
```java
String a = "hello";
String b = new String("hello");
System.out.println(a == b);       // false
System.out.println(a.equals(b));  // true
```

### Gotcha 2.2: Concatenation and pool
```java
String a = "hello" + "world";  // Literal; may be pooled
String b = new String("helloworld");
System.out.println(a == b);  // false
```

### Gotcha 2.3: intern() side effects
```java
String a = new String("test");
String b = a.intern();
String c = "test";
System.out.println(b == c);  // true (both in pool)
System.out.println(a == b);  // false (a still heap object)
// Warning: intern() can exhaust pool if used on arbitrary strings
```

### Gotcha 2.4: String in HashMap keys
```java
String key1 = new String("key");
String key2 = "key";
map.put(key1, "value1");
map.put(key2, "value2");  // Overwrites? Depends on equals/hashCode!
// String's equals uses .equals(), so it WILL overwrite
```

### Gotcha 2.5: Character array vs String
```java
char[] arr = {'h', 'e', 'l', 'l', 'o'};
String s = new String(arr);
arr[0] = 'H';  // Modify array
System.out.println(s);  // Still "hello" (String made copy)
```

---

## 3. equals & hashCode Gotchas

### Gotcha 3.1: equals() but forgot hashCode()
```java
class Person {
    String name;
    @Override
    public boolean equals(Object o) {
        if (o instanceof Person) return ((Person)o).name.equals(name);
        return false;
    }
    // WRONG: forgot hashCode()!
}

Person p1 = new Person("Alice");
Person p2 = new Person("Alice");
Set<Person> set = new HashSet<>();
set.add(p1);
set.add(p2);  // BOTH added! (not deduplicated)
System.out.println(set.size());  // 2 (expected 1)
```

### Gotcha 3.2: Mutable key in HashMap
```java
class Person {
    String name;
    @Override
    public int hashCode() { return name.hashCode(); }
}

Person p = new Person("Alice");
Map<Person, String> map = new HashMap<>();
map.put(p, "Engineer");
System.out.println(map.get(p));  // "Engineer"

p.name = "Bob";  // Mutate the key!
System.out.println(map.get(p));  // null! (hash changed)
```

### Gotcha 3.3: equals is not transitive under inheritance
```java
class Shape {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Shape)) return false;
        return true;  // All shapes equal (bad design, but possible)
    }
}

class Circle extends Shape { }
class Square extends Shape { }

Shape s1 = new Circle();
Shape s2 = new Square();
Shape s3 = s1;  // Same as s1

s1.equals(s2);  // true
s2.equals(s3);  // true
s1.equals(s3);  // true
// But: s1 instanceof Circle, s3 instanceof Circle → BOTH could break elsewhere
```

---

## 4. Inheritance & Initialization Gotchas

### Gotcha 4.1: Initialization order with inheritance
```java
class Parent {
    int x = 10;
    Parent() { foo(); }
    void foo() { System.out.println(x); }
}

class Child extends Parent {
    int x = 20;
    @Override
    void foo() { System.out.println("Child: " + x); }
}

new Child();  // Output: "Child: 0" (NOT 20!)
// Child's x is 0 until Child() constructor runs
```

### Gotcha 4.2: Calling overridable methods from constructor
```java
class Parent {
    Parent() {
        initialize();  // BUG: calls Child's initialize()
    }
    void initialize() { System.out.println("Parent init"); }
}

class Child extends Parent {
    private List<String> items = new ArrayList<>();
    @Override
    void initialize() {
        items.add("item");  // NPE! items not yet initialized
    }
}

new Child();  // NPE on items
```

### Gotcha 4.3: super() and initialization
```java
class Parent {
    int x;
    Parent(int x) { this.x = x; }
}

class Child extends Parent {
    int y = 10;
    Child() {
        super(5);  // Parent's x = 5
        // At this point: x=5, y=10
    }
}
```

---

## 5. Collections Gotchas

### Gotcha 5.1: ConcurrentModificationException
```java
List<Integer> list = new ArrayList<>();
list.add(1); list.add(2); list.add(3);

for (Integer i : list) {
    if (i == 2) list.remove(i);  // ConcurrentModificationException!
}
// Fix: use list.removeIf() or Iterator.remove()
```

### Gotcha 5.2: HashMap during iteration
```java
Map<String, Integer> map = new HashMap<>();
map.put("a", 1); map.put("b", 2);

for (String key : map.keySet()) {
    if (key.equals("a")) map.remove(key);  // ConcurrentModificationException!
}
// Fix: Iterator.remove() or create temp list
```

### Gotcha 5.3: ArrayList.remove(int) vs remove(Object)
```java
List<Integer> list = new ArrayList<>();
list.add(1); list.add(2); list.add(3);

list.remove(1);  // Removes index 1 (value 2), not value 1!
System.out.println(list);  // [1, 3]
```

### Gotcha 5.4: TreeMap and null
```java
Map<String, Integer> map = new TreeMap<>();
map.put(null, 1);  // NullPointerException!
// TreeMap uses compareTo(); can't compare null
```

### Gotcha 5.5: HashSet with mutable objects
```java
Set<StringBuilder> set = new HashSet<>();
StringBuilder sb = new StringBuilder("hello");
set.add(sb);
sb.append(" world");  // Modify original

System.out.println(set.contains(sb));  // May return false!
// hashCode changed, HashSet can't find it
```

---

## 6. Generics Gotchas

### Gotcha 6.1: Type erasure
```java
List<String> list = new ArrayList<>();
list.add("hello");

List<Integer> list2 = (List<Integer>) (Object) list;  // Unchecked cast
Integer x = list2.get(0);  // ClassCastException at runtime!
```

### Gotcha 6.2: Cannot use T in instanceof
```java
<T> boolean isInstance(Object o, Class<T> clazz) {
    if (o instanceof T) { }  // COMPILE ERROR!
    // Fix: use clazz.isInstance(o)
    return clazz.isInstance(o);
}
```

### Gotcha 6.3: Cannot create new T[]
```java
<T> T[] createArray(int size) {
    return new T[size];  // COMPILE ERROR!
    // Fix: use Array.newInstance()
    return (T[]) new Object[size];  // Unsafe cast, but necessary
}
```

### Gotcha 6.4: Wildcard bounds confusion
```java
List<? extends Number> list1 = new ArrayList<Integer>();
list1.add(5);  // COMPILE ERROR! Can't add to ? extends (read-only)
Number n = list1.get(0);  // OK

List<? super Integer> list2 = new ArrayList<Number>();
Number n = list2.get(0);  // COMPILE ERROR! Type unknown
list2.add(5);  // OK (adding Integer, which is super Integer)
```

---

## 7. Concurrency Gotchas

### Gotcha 7.1: volatile doesn't mean atomic
```java
volatile int counter = 0;

// Thread 1
counter++;  // Not atomic! read, increment, write

// Thread 2
counter++;

// Result: may be 1 (lost update)
// Fix: use AtomicInteger
```

### Gotcha 7.2: synchronized on wrong object
```java
class Counter {
    private int count = 0;
    private final Object lock = new Object();
    
    public void increment() {
        synchronized (this) {  // Synchronizes on 'this'
            count++;
        }
    }
    
    public int getCount() {
        synchronized (lock) {  // Different lock!
            return count;  // Race condition!
        }
    }
}
```

### Gotcha 7.3: Deadlock with two locks
```java
Object lock1 = new Object();
Object lock2 = new Object();

// Thread A
synchronized (lock1) {
    Thread.sleep(100);
    synchronized (lock2) { }  // Waiting for lock2
}

// Thread B
synchronized (lock2) {
    synchronized (lock1) { }  // Waiting for lock1
}

// DEADLOCK!
```

### Gotcha 7.4: ReentrantLock must be unlocked
```java
ReentrantLock lock = new ReentrantLock();

try {
    lock.lock();
    // ... do work
} finally {
    lock.unlock();  // CRITICAL: unlock in finally
}

// If you forget unlock(), threads waiting on lock.lock() hang forever
```

---

## 8. Stream Gotchas

### Gotcha 8.1: Stream can't be reused
```java
Stream<Integer> stream = List.of(1, 2, 3).stream();
stream.forEach(System.out::println);
stream.forEach(System.out::println);  // IllegalStateException! Stream already used
```

### Gotcha 8.2: Lazy evaluation
```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
Stream<Integer> stream = list.stream()
    .peek(x -> System.out.println("peeking: " + x))
    .filter(x -> x > 2)
    .map(x -> x * 2);

// No output yet! Stream not evaluated
stream.forEach(System.out::println);  // NOW it evaluates
```

### Gotcha 8.3: flatMap with null
```java
List<List<Integer>> list = List.of(List.of(1, 2), null, List.of(3, 4));
list.stream()
    .flatMap(sublist -> sublist.stream())  // NPE on null!
    // Fix: filter out nulls first
    .forEach(System.out::println);
```

### Gotcha 8.4: Optional and NPE
```java
Optional<String> opt = Optional.of(null);  // NPE!
// Fix: Optional.ofNullable(value)
```

---

## 9. Equals & toString Gotchas

### Gotcha 9.1: toString() on null
```java
String[] arr = {null, "hello"};
System.out.println(Arrays.toString(arr));  // [null, hello] (works)
for (String s : arr) {
    System.out.println(s.toString());  // NPE on null!
}
```

### Gotcha 9.2: Comparing arrays with ==
```java
int[] a = {1, 2, 3};
int[] b = {1, 2, 3};
System.out.println(a == b);                  // false (different objects)
System.out.println(Arrays.equals(a, b));     // true
System.out.println(Arrays.deepEquals(...));  // For nested arrays
```

---

## 10. Final Keyword Gotchas

### Gotcha 10.1: final field can be modified via reflection
```java
final int x = 10;
Field field = this.getClass().getDeclaredField("x");
field.setAccessible(true);
field.setInt(this, 20);  // Changed final field!
System.out.println(x);   // Still 10 (JIT may have optimized)
```

### Gotcha 10.2: final doesn't make object immutable
```java
final List<String> list = new ArrayList<>();
list.add("hello");  // OK! list reference is final, not contents
```

---

## Checklist: Did You Know?

- [ ] Integer cache: -128 to 127
- [ ] String pool is in heap (Java 7+)
- [ ] pass-by-value: references are copied, not objects
- [ ] equals & hashCode must be consistent
- [ ] Mutable keys in HashMap cause bugs
- [ ] Child constructor runs after Parent constructor body? **No! Parent runs first.**
- [ ] Initialization order: static → instance vars → instance blocks → constructor
- [ ] Unboxing null = NullPointerException
- [ ] ConcurrentModificationException during iteration
- [ ] volatile ≠ atomic
- [ ] Synchronized on wrong object = race condition
- [ ] Stream can't be reused
- [ ] Optional.of(null) throws NPE (use ofNullable)
- [ ] final method can still be overridden in different way? **No, final prevents override.**
- [ ] Type erasure: generics removed at runtime

---

**Master these 50 gotchas, and you'll never be caught off-guard in an interview.**
