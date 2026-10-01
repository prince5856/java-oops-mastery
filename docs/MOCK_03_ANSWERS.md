# Mock 3 Answers: Easy - OOP Principles Introduction

## Solution

```java
abstract class Shape {
    public abstract double calculateArea();
    public abstract String getName();
    
    public void printInfo() {
        System.out.printf("Shape: %s, Area: %.2f%n", getName(), calculateArea());
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public String getName() {
        return "Circle";
    }
}

class Rectangle extends Shape {
    private double width, height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
    
    @Override
    public String getName() {
        return "Rectangle";
    }
}

class Triangle extends Shape {
    private double base, height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
    
    @Override
    public String getName() {
        return "Triangle";
    }
}
```

## Behavioral Answers

**Q5: Why abstract classes?**
- Force subclasses to implement methods
- Share common behavior (printInfo)
- Cannot instantiate directly (Shape s = new Shape() ❌)
- Define contract for subclasses

**Q6: super() chaining**
- super() calls parent constructor
- Must be first statement in child constructor
- Parent initialized before child
- Allows parent setup, then child customization

**Scoring**: See rubric above
