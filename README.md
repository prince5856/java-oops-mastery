# Java + OOP Mastery

## Overview

A complete, hands-on Java + OOP revision workspace for 4-year backend engineers preparing for senior/SDE-2 interviews. **20 modules** covering everything from Java basics to advanced design patterns and machine coding.

**Status**: All modules created with exercises, solutions, tests, and documentation.

## What's Included

### 20 Modules (each with README, exercises, solutions, tests, quiz, interview questions)

1. **Java Basics**: primitives, wrappers, string pool, autoboxing, pass-by-value, static/final, varargs
2. **Classes & Objects**: constructors, this(), initialization order, static vs instance
3. **Encapsulation**: access modifiers, invariants, defensive copying
4. **Inheritance**: super(), method overriding, IS-A vs HAS-A
5. **Polymorphism**: overloading vs overriding, dynamic dispatch, covariant returns
6. **Abstraction**: abstract classes, interfaces, template method, diamond problem
7. **Object Class Contracts**: equals/hashCode/toString, Comparable/Comparator
8. **Object Relationships**: association, aggregation, composition
9. **Nested Classes**: static nested, inner, local, anonymous, memory leaks
10. **Immutability & Records**: immutable class design, records (Java 16+)
11. **Exception Handling**: checked/unchecked, chaining, try-with-resources
12. **Generics**: bounded types, wildcards, PECS, type erasure
13. **Collections Internals**: HashMap, TreeMap, LinkedHashMap, fail-fast, ConcurrentHashMap
14. **Functional Java**: lambdas, streams, Optional, collectors
15. **Concurrency**: threads, synchronized, locks, atomics, ExecutorService, deadlock
16. **JVM Memory**: stack vs heap, garbage collection, string pool, class loading
17. **Modern Java**: var, switch expressions, text blocks, sealed classes, pattern matching
18. **SOLID**: one principle per module with violations and refactors
19. **Design Patterns**: Singleton, Builder, Factory, Strategy, Observer, Decorator, State, Template
20. **Capstone (Machine Coding)**: Parking Lot, Rate Limiter, LRU Cache

## How to Use

### 1. Run All Tests
```bash
mvn clean test -Dgroups=solutions
```

### 2. Run a Single Module's Tests
```bash
mvn test -Dtest=M01SolutionsTest
```

### 3. Review Exercises (with TODOs)
Each module in `src/main/java/com/prep/m{XX}_*/exercises/` has starter code.

### 4. Study Solutions
Solutions in `src/main/java/com/prep/m{XX}_*/solutions/` with explanations.

### 5. Read Revision Notes
Each module has `docs/m{XX}_*/README.md` with key concepts and cheat sheets.

### 6. Quiz & Puzzles
- `docs/m{XX}_*/quiz.md`: 10 MCQs per module with answers.
- `docs/m{XX}_*/guess-the-output.md`: code snippets to trace.
- `src/main/java/com/prep/m{XX}_*/puzzles/`: runnable puzzle code.

### 7. Interview Questions
`docs/m{XX}_*/interview-questions.md`: 10-15 verbal questions with model answers.

## Project Structure

```
java-oops-mastery/
├── pom.xml
├── README.md (this file)
├── PROGRESS.md (checklist)
├── CHEATSHEET.md (one-page summary)
├── MOCK-INTERVIEW.md (3 mock rounds)
├── docs/
│   ├── m01_java_basics/
│   │   ├── README.md (revision notes)
│   │   ├── quiz.md (10 MCQs)
│   │   ├── guess-the-output.md (8-10 puzzles)
│   │   └── interview-questions.md (10-15 Qs)
│   ├── m02_classes_objects/
│   ├── ... (m03 through m20)
├── src/main/java/com/prep/
│   ├── m01_java_basics/
│   │   ├── exercises/ (6-10 exercises with TODOs)
│   │   ├── solutions/ (full working code + comments)
│   │   └── puzzles/ (runnable code snippets)
│   ├── m02_classes_objects/
│   ├── ... (m03 through m20)
├── src/test/java/com/prep/
│   ├── m01_java_basics/
│   │   ├── M01ExercisesTest.java (fails until implemented)
│   │   └── M01SolutionsTest.java (passes)
│   ├── m02_classes_objects/
│   ├── ... (m03 through m20)
```

## Running the Project

### Prerequisites
- Java 21
- Maven 3.6+

### Quick Start
```bash
# Navigate to project
cd java-oops-mastery

# Compile
mvn clean compile

# Run all solution tests (should pass)
mvn test -Dtest="M*SolutionsTest"

# Run single module tests
mvn test -Dtest=M01SolutionsTest
```

### Run a Specific Puzzle
```bash
cd src/main/java/com/prep/m01_java_basics/puzzles
javac Puzzle1_IntegerCache.java
java Puzzle1_IntegerCache
```

## Study Path

### Week 1-2: Foundation (Modules 1-5)
- Java Basics (types, strings, pass-by-value)
- Classes & Objects (constructors, initialization)
- Encapsulation (access modifiers, invariants)
- Inheritance (super, overriding)
- Polymorphism (overloading, dynamic dispatch)

### Week 3: OOP Deep Dive (Modules 6-10)
- Abstraction (abstract classes, interfaces)
- Object Contracts (equals, hashCode)
- Relationships (composition, aggregation)
- Nested Classes (memory leaks)
- Immutability (defensive copying, records)

### Week 4: Advanced Java (Modules 11-17)
- Exceptions (checked, chaining)
- Generics (bounded types, PECS)
- Collections (HashMap internals, fail-fast)
- Functional Java (lambdas, streams)
- Concurrency (threads, locks, atomics)
- JVM Memory (stack, heap, GC)
- Modern Java (var, sealed, pattern matching)

### Week 5-6: Architecture & Practice (Modules 18-20)
- SOLID Principles (S, O, L, I, D)
- Design Patterns (Singleton, Builder, Factory, etc.)
- Capstone (Parking Lot, Rate Limiter, LRU Cache)
- Mock Interviews

## Tips for 4-Year Engineers

### Focus Areas
- **Internals**: HashMap resize, String pool, GC, class loading.
- **Edge Cases**: Integer cache boundaries, null handling, autoboxing.
- **Trade-offs**: Composition vs inheritance, synchronized vs ReentrantLock.
- **Design**: SOLID, patterns, when to use each approach.

### Common Pitfalls
1. Assuming `==` and `equals()` are the same.
2. Forgetting autoboxing can throw NPE.
3. Pass-by-value confusion for collections.
4. Initialization order in constructors.
5. Not implementing equals/hashCode correctly.

### Interview Tips
- **Explain WHY**: Not just "it works", but "because...".
- **Know Trade-offs**: "I'd use X in this case because A, but Y if B".
- **Code Confidently**: Practice exercises until fluent.
- **Ask Clarifying Questions**: "Do you want thread-safe?", "What's the scale?".

## Building Understanding

### For Each Module
1. Read `docs/m{XX}/README.md` (cheat sheet).
2. Do exercises in `exercises/` (code + run tests).
3. Compare with `solutions/` (learn alternatives).
4. Try quiz in `docs/m{XX}/quiz.md`.
5. Trace through guess-the-output puzzles.
6. Answer interview questions verbally.

### Verification
```bash
# Compile everything
mvn clean compile

# Run all solution tests
mvn test -Dtest="M*SolutionsTest"

# Check a single module
mvn test -Dtest=M01SolutionsTest,M02SolutionsTest
```

## Key Resources in Docs

- `PROGRESS.md`: Checklist per module (notes, quiz, exercises).
- `CHEATSHEET.md`: One-page review of all 20 modules.
- `MOCK-INTERVIEW.md`: 3 full 60-minute mock interview rounds.

## Notes

- All exercises have TODOs; solutions have full implementations.
- Tests tagged "exercises" fail until you implement; "solutions" pass.
- Quiz answers and output explanations are hidden in `<details>` tags.
- Interview questions are phrased as a 4-year engineer would answer them.
- Design patterns module shows common pitfalls (e.g., Singleton variants that break).

## License

Created for interview preparation. Use freely for learning.

---

**Last Updated**: 2026-10-01  
**Status**: Complete (20 modules, all tests passing)
