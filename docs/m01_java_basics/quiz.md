# Module 1: Java Basics - Quiz

## 10 Multiple-Choice Questions

### Q1: Integer Cache
```java
Integer a = 100;
Integer b = 100;
System.out.println(a == b);  // true or false?
```

<details>
<summary>Answer</summary>

**true**

Explanation: Both are created using autoboxing, which calls Integer.valueOf(). The value 100 is within the cached range (-128 to 127), so both references point to the same cached object.

Interviewer follow-up: "What if the number was 200?"

</details>

---

### Q2: String Pool
```java
String a = "hello";
String b = new String("hello");
System.out.println(a == b);  // true or false?
```

<details>
<summary>Answer</summary>

**false**

Explanation: `a` is a string literal in the pool, `b` is a new String object on the heap. Even though their content is equal, they are different objects.

Interviewer follow-up: "How would you make them refer to the same object?"

</details>

---

### Q3: Autoboxing NPE
```java
Integer x = null;
int y = x;  // What happens?
```

<details>
<summary>Answer</summary>

**NullPointerException at runtime**

Explanation: Autoboxing unboxes the Integer to an int. Since x is null, unboxing throws NPE. This is a common pitfall.

Interviewer follow-up: "How would you prevent this?"

</details>

---

### Q4: Pass-by-Value
```java
void modifyArray(int[] arr) {
    arr[0] = 999;
}

int[] myArray = {1, 2, 3};
modifyArray(myArray);
System.out.println(myArray[0]);  // 999 or 1?
```

<details>
<summary>Answer</summary>

**999**

Explanation: Even though Java is pass-by-value, the value being passed is the reference to the array. Modifying the array's contents via the reference affects the original array.

Interviewer follow-up: "What if we reassign the reference inside modifyArray?"

</details>

---

### Q5: Static vs Instance
```java
class Counter {
    static int count = 0;
    int id = 0;
    
    Counter(int id) {
        this.id = id;
        count++;
    }
}

Counter c1 = new Counter(1);
Counter c2 = new Counter(2);
System.out.println(Counter.count);  // 0, 1, 2, or 3?
```

<details>
<summary>Answer</summary>

**2**

Explanation: `count` is static, shared by all instances. Each constructor call increments it. After creating 2 instances, count is 2.

Interviewer follow-up: "What's the default value of static int count?"

</details>

---

### Q6: Final Field
```java
class Immutable {
    final int value;
    
    Immutable(int value) {
        this.value = value;
    }
}

Immutable obj = new Immutable(42);
obj.value = 99;  // Compile error or runtime error?
```

<details>
<summary>Answer</summary>

**Compile error**

Explanation: A final field cannot be reassigned after initialization. The compiler prevents this.

Interviewer follow-up: "Can you reassign a final reference to a different object?"

</details>

---

### Q7: Varargs Null
```java
void foo(int... numbers) {
    System.out.println(numbers.length);
}

foo(null);  // What is printed?
```

<details>
<summary>Answer</summary>

**NullPointerException**

Explanation: Passing null to varargs passes a null array, not an empty array. Accessing length on null throws NPE.

Interviewer follow-up: "How would you handle this?"

</details>

---

### Q8: StringBuilder Chaining
```java
StringBuilder sb = new StringBuilder("hello");
String result = sb.append(" world").toString();
System.out.println(result);  // What is printed?
```

<details>
<summary>Answer</summary>

**hello world**

Explanation: StringBuilder.append() returns the StringBuilder itself, allowing chaining. toString() converts to String.

Interviewer follow-up: "Is StringBuilder thread-safe?"

</details>

---

### Q9: Ternary Short-Circuit
```java
int x = 5;
int y = (x > 0) ? 10 : (x = 20);
System.out.println(x);  // 5 or 20?
```

<details>
<summary>Answer</summary>

**5**

Explanation: The ternary operator short-circuits. Since `x > 0` is true, only the first branch (10) is evaluated. The second branch (x = 20) is never executed.

Interviewer follow-up: "How does this differ from if-else?"

</details>

---

### Q10: Integer Wrapper Equality
```java
Integer a = new Integer(100);
Integer b = Integer.valueOf(100);
System.out.println(a == b);  // true or false?
```

<details>
<summary>Answer</summary>

**false**

Explanation: `new Integer(100)` creates a new object every time. `Integer.valueOf(100)` returns the cached object (100 is in the range). They are different objects.

Interviewer follow-up: "Why does autoboxing use valueOf instead of new?"

</details>

---
