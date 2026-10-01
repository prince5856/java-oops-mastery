# Mock 4 Answers: Intermediate - Inheritance & Polymorphism

## Solution Code

```java
abstract class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    abstract String makeSound();
    abstract void eat(String food);
    
    void sleep() {
        System.out.println(name + " is sleeping");
    }
}

abstract class Mammal extends Animal {
    public Mammal(String name) {
        super(name);
    }
    
    boolean isWarmBlooded() { return true; }
    
    @Override
    void sleep() {
        System.out.println(name + " mammal is sleeping in a cozy spot");
    }
}

class Dog extends Mammal {
    private String breed;
    
    public Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }
    
    @Override
    String makeSound() { return "Woof!"; }
    
    @Override
    void eat(String food) {
        System.out.println(name + " dog eats meat");
    }
    
    void fetch() {
        System.out.println(name + " is fetching the ball!");
    }
}

class Cat extends Mammal {
    private int independenceLevel;
    
    public Cat(String name, int level) {
        super(name);
        this.independenceLevel = level;
    }
    
    @Override
    String makeSound() { return "Meow!"; }
    
    @Override
    void eat(String food) {
        System.out.println(name + " cat eats fish");
    }
    
    void scratch() {
        System.out.println(name + " is scratching the furniture");
    }
}
```

## Q5: Liskov Substitution Principle

**Violation**:
```java
class Bird {
    void fly() { }
}

class Penguin extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}

Bird b = new Penguin();
b.fly();  // Crash! Violates LSP
```

**Fix**: Use composition or better hierarchy
```java
interface Flyer { void fly(); }
class Bird { }
class Eagle extends Bird implements Flyer { }
class Penguin extends Bird { }  // No Flyer
```

## Q6: Class Choice

- **Abstract**: Shared code + enforce contract (Animal)
- **Interface**: Pure contract, multiple impl (Flyer)
- **Concrete**: Instantiable, no subclassing (final class)

**Points**: Follow rubric
