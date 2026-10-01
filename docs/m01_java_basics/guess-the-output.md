# Module 1: Java Basics - Guess the Output

## 10 Code Snippets (Easy to Tricky)

### Snippet 1 (Easy): Integer Cache
```java
Integer a = 50;
Integer b = 50;
System.out.println(a == b);
```

<details>
<summary>Output</summary>

```
true
```

Explanation: Both are autoboxed to Integer.valueOf(50), which returns the cached object in range -128 to 127.

</details>

---

### Snippet 2 (Easy): String Literals
```java
String x = "hi";
String y = "hi";
System.out.println(x == y);
```

<details>
<summary>Output</summary>

```
true
```

Explanation: Both string literals are the same object from the string pool.

</details>

---

### Snippet 3 (Easy): StringBuilder
```java
StringBuilder sb = new StringBuilder("abc");
sb.append("d");
System.out.println(sb);
```

<details>
<summary>Output</summary>

```
abcd
```

Explanation: StringBuilder is mutable; append adds to it.

</details>

---

### Snippet 4 (Medium): Autoboxing Null
```java
Integer x = null;
try {
    int y = x;
    System.out.println("no error");
} catch (NullPointerException e) {
    System.out.println("NPE caught");
}
```

<details>
<summary>Output</summary>

```
NPE caught
```

Explanation: Unboxing null throws NPE.

</details>

---

### Snippet 5 (Medium): Pass-by-Value
```java
int x = 5;
void increment(int n) {
    n++;
}
increment(x);
System.out.println(x);
```

<details>
<summary>Output</summary>

```
5
```

Explanation: Primitives are pass-by-value; changes inside the method don't affect the caller's variable.

</details>

---

### Snippet 6 (Medium): Static Counter
```java
class Counter {
    static int count = 0;
    Counter() { count++; }
}
new Counter();
new Counter();
System.out.println(Counter.count);
```

<details>
<summary>Output</summary>

```
2
```

Explanation: The static counter is incremented twice.

</details>

---

### Snippet 7 (Tricky): Integer Cache Boundary
```java
Integer a = 127;
Integer b = 127;
Integer c = 128;
Integer d = 128;
System.out.println(a == b);
System.out.println(c == d);
```

<details>
<summary>Output</summary>

```
true
false
```

Explanation: 127 is the upper boundary of the cache. 128 is outside the cache, so new objects.

</details>

---

### Snippet 8 (Tricky): String Concat and Pool
```java
String a = new String("test");
String b = a.intern();
String c = "test";
System.out.println(b == c);
System.out.println(a == b);
```

<details>
<summary>Output</summary>

```
true
false
```

Explanation: intern() moves to the pool, so b and c are the same object. But a is still the original object on the heap.

</details>

---

### Snippet 9 (Tricky): Ternary with Side Effects
```java
int x = 10;
int y = (x > 5) ? (x = 20) : (x = 30);
System.out.println(x);
System.out.println(y);
```

<details>
<summary>Output</summary>

```
20
20
```

Explanation: The ternary evaluates the true branch (x = 20), which assigns 20 to both x and y.

</details>

---

### Snippet 10 (Tricky): Varargs and Arrays
```java
void foo(int... numbers) {
    System.out.println(numbers.length);
}
foo(1, 2, 3);
foo(new int[]{1, 2, 3});
foo();
```

<details>
<summary>Output</summary>

```
3
3
0
```

Explanation: Varargs is syntactic sugar for arrays. Each call matches the array length.

</details>

---
