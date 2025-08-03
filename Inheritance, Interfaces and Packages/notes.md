# 🌳 Java: Inheritance, Interfaces & Packages

This document explores three fundamental pillars of Java programming: **Inheritance** (for code reuse and hierarchy), **Interfaces** (for defining contracts and achieving abstraction), and **Packages** (for organizing and managing code).

-----

## 4.1 Inheritance Fundamentals

Inheritance is a core OOP concept that allows a class to inherit properties and behaviors from another class.

  * **Concept:** A mechanism where one class (the **subclass** or **derived class** or **child class**) acquires the properties and behaviors (fields and methods) of another class (the **superclass** or **base class** or **parent class**).
  * **Keyword:** `extends` is used to establish an inheritance relationship.
    ```java
    class Animal { // Superclass
        void eat() {
            System.out.println("Animal eats");
        }
    }

    class Dog extends Animal { // Subclass
        void bark() {
            System.out.println("Dog barks");
        }
    }
    ```
  * **Benefits:**
      * **Code Reusability:** Avoids redundant code by allowing subclasses to reuse code from superclasses.
      * **Polymorphism:** Enables dynamic method dispatch.
      * **Maintainability:** Easier to manage and update code.

### Understanding the `super` Keyword

The `super` keyword is a reference variable that refers to the **immediate parent class object**.

  * **Uses of `super`:**
    1.  **To refer to immediate parent class instance variables:** When a subclass has a variable with the same name as a variable in its superclass, `super.variableName` can be used to access the superclass's version.
        ```java
        class Parent {
            int value = 10;
        }
        class Child extends Parent {
            int value = 20;
            void display() {
                System.out.println("Child value: " + value);        // 20
                System.out.println("Parent value: " + super.value); // 10
            }
        }
        // Output:
        // Child value: 20
        // Parent value: 10
        ```
    2.  **To invoke immediate parent class methods:** When a subclass overrides a method from its superclass, `super.methodName()` can be used to call the superclass's version of that method.
        ```java
        class Animal {
            void makeSound() { System.out.println("Animal makes a sound"); }
        }
        class Cat extends Animal {
            @Override
            void makeSound() {
                super.makeSound(); // Calls Animal's makeSound()
                System.out.println("Cat meows");
            }
        }
        // Output for new Cat().makeSound():
        // Animal makes a sound
        // Cat meows
        ```
    3.  **To invoke immediate parent class constructors:** `super()` is used to call the superclass's constructor from a subclass's constructor.
          * If a subclass constructor does not explicitly call `super()` or `this()`, Java implicitly inserts a call to the superclass's no-argument constructor (`super();`) as the first statement.
          * If the superclass only has parameterized constructors, the subclass *must* explicitly call one of them using `super(...)`.
          * `super()` must be the **first statement** in the constructor.
        <!-- end list -->
        ```java
        class Vehicle {
            String type;
            Vehicle(String type) { this.type = type; }
        }
        class Car extends Vehicle {
            String model;
            Car(String type, String model) {
                super(type); // Calls Vehicle's constructor
                this.model = model;
            }
        }
        ```

### Dynamic Method Dispatch and Runtime Polymorphism

  * **Polymorphism:** "Many forms." In Java, it means that a single interface can be used for a general class of actions. It allows objects of different classes to be treated as objects of a common type.
  * **Runtime Polymorphism (Dynamic Method Dispatch):**
      * **Concept:** The process by which a call to an overridden method is resolved at **runtime** rather than compile time.
      * **Mechanism:** When a superclass reference variable refers to a subclass object, and an overridden method is called, the JVM determines which version of the method (superclass or subclass) to execute based on the **actual type of the object** at runtime, not the type of the reference variable.
      * **Example:**
        ```java
        class Animal {
            void sound() { System.out.println("Animal makes a sound"); }
        }
        class Dog extends Animal {
            @Override
            void sound() { System.out.println("Dog barks"); }
        }
        class Cat extends Animal {
            @Override
            void sound() { System.out.println("Cat meows"); }
        }

        public class PolymorphismDemo {
            public static void main(String[] args) {
                Animal myAnimal; // Superclass reference variable

                myAnimal = new Dog(); // myAnimal refers to a Dog object
                myAnimal.sound();     // Calls Dog's sound() -> Output: Dog barks

                myAnimal = new Cat(); // myAnimal refers to a Cat object
                myAnimal.sound();     // Calls Cat's sound() -> Output: Cat meows
            }
        }
        ```

### `final` Keyword in Inheritance

The `final` keyword plays a crucial role in controlling inheritance behavior.

  * **`final` Class:** A `final` class **cannot be subclassed**. This is used when you want to prevent any further extension of a class (e.g., `String`, `System` classes are `final`).
    ```java
    final class ImmutableClass { /* ... */ }
    // class MyClass extends ImmutableClass { } // Compile-time error
    ```
  * **`final` Method:** A `final` method **cannot be overridden** by subclasses. This ensures that the method's implementation remains consistent across the inheritance hierarchy.
    ```java
    class Base {
        final void importantMethod() { /* ... */ }
    }
    class Derived extends Base {
        // @Override void importantMethod() { /* ... */ } // Compile-time error
    }
    ```
  * **`final` Variable:** (Reviewed in Class Fundamentals) A `final` variable can be assigned a value only once. If it's an instance variable, it must be initialized either at declaration or in the constructor.

-----

## 4.2 Abstract Classes and Methods, Casting Objects

Abstraction and object casting are important concepts for designing flexible and safe class hierarchies.

### Abstract Classes and Methods

  * **Abstraction:** The process of hiding the implementation details and showing only the functionality to the user.

  * **Abstract Class:**

      * A class declared with the `abstract` keyword.
      * **Cannot be instantiated** (you cannot create objects of an abstract class directly).
      * Can have both **abstract methods** and **concrete (non-abstract) methods**.
      * Can have constructors (these are called by subclass constructors via `super()`).
      * A class that contains one or more abstract methods *must* be declared abstract.

  * **Abstract Method:**

      * A method declared with the `abstract` keyword.
      * Has **no body** (only a signature followed by a semicolon).
      * Must be declared within an abstract class.
      * **Subclasses must provide an implementation (override)** for all inherited abstract methods, unless the subclass itself is declared abstract.

  * **Example:**

    ```java
    abstract class Shape { // Abstract class
        String color;

        Shape(String color) { this.color = color; } // Constructor

        abstract double getArea(); // Abstract method (no body)
        abstract String getName(); // Another abstract method

        void displayColor() { // Concrete method
            System.out.println("Color: " + color);
        }
    }

    class Circle extends Shape {
        double radius;
        Circle(String color, double radius) {
            super(color);
            this.radius = radius;
        }
        @Override
        double getArea() { return Math.PI * radius * radius; }
        @Override
        String getName() { return "Circle"; }
    }

    public class AbstractDemo {
        public static void main(String[] args) {
            // Shape s = new Shape("Red"); // Compile-time error: Cannot instantiate abstract class
            Circle c = new Circle("Blue", 5.0);
            System.out.println("Shape: " + c.getName() + ", Area: " + c.getArea()); // Output: Shape: Circle, Area: 78.539...
            c.displayColor(); // Output: Color: Blue
        }
    }
    ```

### Casting Objects in Inheritance

Object casting is used to convert an object reference from one type to another within an inheritance hierarchy.

  * **Upcasting (Implicit/Safe):**
      * Casting a subclass object to a superclass type.
      * Always safe and happens implicitly.
      * The object itself *remains* the subclass type, but the reference variable can only access members defined in the superclass (or overridden methods).
      * ```java
          Animal myDog = new Dog(); // Upcasting: Dog object referenced by Animal type
          myDog.sound(); // Calls Dog's sound() due to polymorphism
          // myDog.bark(); // Compile-time error: 'bark()' is not in Animal
        ```
  * **Downcasting (Explicit/Risky):**
      * Casting a superclass reference to a subclass type.
      * Requires an **explicit cast** and can lead to a `ClassCastException` at runtime if the object being cast is not actually an instance of the target subclass (or a subclass of it).
      * Always check with `instanceof` before downcasting to prevent `ClassCastException`.
      * ```java
          Animal myAnimal = new Dog(); // Upcast first
          if (myAnimal instanceof Dog) {
              Dog actualDog = (Dog) myAnimal; // Downcasting: Safe because myAnimal actually holds a Dog
              actualDog.bark(); // Output: Dog barks
          }

          Animal anotherAnimal = new Cat();
          // Dog invalidDog = (Dog) anotherAnimal; // Runtime error: ClassCastException (Cat cannot be cast to Dog)
        ```

-----

## 4.3 Introduction to Interfaces

Interfaces define a contract for behavior. They are a powerful mechanism for achieving abstraction and multiple inheritance of *type*.

  * **Definition:** A blueprint of a class. It contains abstract methods (implicitly `public abstract` before Java 8) and constants (implicitly `public static final`).
  * **Keyword:** `interface` is used to declare an interface.
  * **Implementation:** Classes use the `implements` keyword to implement an interface. A class must provide concrete implementations for all abstract methods declared in the interface (unless the class itself is abstract).
  * **Key Characteristics:**
      * **Cannot be instantiated** directly.
      * All methods are implicitly `public` (and `abstract` before Java 8).
      * All variables are implicitly `public static final`.
      * Introduced `default` and `static` methods in Java 8, and `private` methods in Java 9.

### Interface Segregation Principle (ISP)

  * **Concept:** Clients should not be forced to depend on interfaces they do not use.
  * **Implication:** It's better to have many small, specific interfaces rather than one large, general-purpose interface.
  * **Benefit:** Reduces coupling and makes systems more flexible and maintainable.

### Interface Methods and Variables

  * **Abstract Methods:**
      * Prior to Java 8, all methods in an interface were implicitly `public abstract`.
      * No method body.
      * Must be implemented by concrete classes.
  * **Default Methods (Java 8+):**
      * Provide a default implementation for a method directly in the interface.
      * Allows adding new methods to an interface without breaking existing implementing classes.
      * Declared with the `default` keyword.
      * Can be overridden by implementing classes.
    <!-- end list -->
    ```java
    interface MyInterface {
        void abstractMethod();
        default void defaultMethod() {
            System.out.println("Default implementation.");
        }
    }
    class MyClass implements MyInterface {
        @Override
        public void abstractMethod() { System.out.println("Abstract method implemented."); }
    }
    // Output for new MyClass().defaultMethod(): Default implementation.
    ```
  * **Static Methods (Java 8+):**
      * Methods defined in an interface with the `static` keyword.
      * Belong to the interface itself, not to any implementing object.
      * **Cannot be overridden** by implementing classes.
      * Accessed directly using the interface name (e.g., `MyInterface.staticMethod()`).
      * Useful for utility methods related to the interface.
    <!-- end list -->
    ```java
    interface MyInterface {
        static void staticMethod() {
            System.out.println("Static method in interface.");
        }
    }
    // MyInterface.staticMethod(); // Output: Static method in interface.
    // new MyClass().staticMethod(); // Compile-time error
    ```
  * **Private Methods (Java 9+):**
      * Methods defined in an interface with the `private` keyword.
      * Can be `static` or non-`static`.
      * Used to share common code between `default` methods or `static` methods within the *same* interface.
      * Cannot be accessed or implemented by outside classes.
    <!-- end list -->
    ```java
    interface MyInterface {
        default void commonLogic() {
            privateHelper(); // Calls private method
        }
        private void privateHelper() {
            System.out.println("Private helper logic.");
        }
    }
    // Output for new MyClass().commonLogic(): Private helper logic.
    ```
  * **Interface Variables:**
      * All variables declared in an interface are implicitly `public static final`.
      * They are constants and must be initialized at the time of declaration.
    <!-- end list -->
    ```java
    interface Constants {
        int MAX_VALUE = 100; // public static final int MAX_VALUE = 100;
    }
    // System.out.println(Constants.MAX_VALUE); // Output: 100
    ```

### Multiple Inheritance Using Interfaces

  * Java does **not support multiple inheritance of classes** (a class cannot extend more than one class directly) to avoid the "diamond problem."
  * However, Java **supports multiple inheritance of *type* using interfaces**. A class can `implement` multiple interfaces.
      * This allows a class to inherit behavior contracts from multiple sources without inheriting implementation details that could lead to conflicts.
    <!-- end list -->
    ```java
    interface Flyable { void fly(); }
    interface Swimmable { void swim(); }

    class Duck implements Flyable, Swimmable {
        @Override
        public void fly() { System.out.println("Duck flies"); }
        @Override
        public void swim() { System.out.println("Duck swims"); }
    }
    ```

### Functional Interfaces

  * **Definition:** An interface that contains **exactly one abstract method**.
  * **Purpose:** Designed to be used with **Lambda Expressions** and **Method References** (introduced in Java 8) to enable functional programming paradigms in Java.
  * **Annotation:** Can be optionally annotated with `@FunctionalInterface` to ensure it adheres to the single abstract method rule (compiler error if violated).
  * **Example:**
    ```java
    @FunctionalInterface
    interface Calculator {
        int operate(int a, int b);
        // default void log() { /* ... */ } // Default methods are allowed
        // static void info() { /* ... */ } // Static methods are allowed
        // void anotherAbstractMethod(); // Compile-time error if uncommented
    }

    public class FunctionalInterfaceDemo {
        public static void main(String[] args) {
            Calculator add = (x, y) -> x + y; // Lambda expression
            System.out.println("Sum: " + add.operate(10, 5)); // Output: Sum: 15
        }
    }
    ```

### Marker Interfaces

  * **Definition:** An interface that contains **no methods or fields**. It serves only to "mark" a class as having a certain property or capability.
  * **Purpose:** To provide runtime type information about objects. The JVM or other frameworks can then perform specific actions based on whether a class implements a marker interface.
  * **Examples:**
      * `Serializable`: Marks a class whose objects can be converted into a byte stream (serialized).
      * `Cloneable`: Marks a class whose objects can be cloned using the `clone()` method.

-----

## 4.4 Packages and the Java Module System

Packages organize classes, and the Module System (Java 9+) provides a higher level of organization and strong encapsulation.

### Introduction to Packages

  * **Definition:** A mechanism to **organize classes and interfaces** into logical groups.
  * **Purpose:**
      * **Naming Collision Prevention:** Allows classes with the same name to coexist in different packages.
      * **Access Control:** Provides a way to control the visibility of classes and their members (package-private access).
      * **Easier Management:** Makes it easier to locate and reuse related classes.
  * **Naming Convention:** Packages are typically named in **reverse domain name format** (e.g., `com.example.myapp.utilities`). All lowercase.
  * **`package` keyword:** Must be the first non-comment, non-whitespace statement in a Java source file.

### Access Modifiers and Packages

  * **`public`:** Accessible from anywhere.
  * **`protected`:** Accessible within the same package and by subclasses (even in different packages).
  * **`default` (package-private):** Accessible only within the same package.
  * **`private`:** Accessible only within the same class.

### Creating and Importing Packages

  * **Creating a Package:**
    1.  Declare the package at the top of the `.java` file: `package com.mycompany.myproject;`
    2.  Place the `.java` file in a directory structure that matches the package name (e.g., `com/mycompany/myproject/MyClass.java`).
  * **Importing Packages:**
      * **`import` keyword:** Used to bring classes or entire packages into the current scope, so you don't have to use their fully qualified names.
      * **Importing a single class:**
        ```java
        import java.util.ArrayList;
        // ...
        ArrayList<String> list = new ArrayList<>();
        ```
      * **Importing all classes in a package (wildcard import):**
        ```java
        import java.util.*; // Imports all classes in java.util
        // ...
        ArrayList<String> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        ```
      * **Implicit Imports:** `java.lang` package is always implicitly imported.

### Static Imports (Java 5+)

  * **Purpose:** Allows you to import `static` members (fields and methods) of a class directly, so you don't need to qualify them with the class name.
  * **Syntax:** `import static packageName.ClassName.staticMember;` or `import static packageName.ClassName.*;`
  * **Example:**
    ```java
    import static java.lang.Math.PI;
    import static java.lang.System.out; // Import static 'out' field

    public class StaticImportDemo {
        public static void main(String[] args) {
            out.println("Value of PI: " + PI); // No need for Math.PI or System.out
        }
    }
    // Output: Value of PI: 3.141592653589793
    ```

### JAR Files (Java Archive)

  * **Definition:** A package file format used to aggregate many Java class files, associated metadata, and resources (text, images, etc.) into a single file.
  * **Purpose:**
      * **Distribution:** Easy way to distribute Java applications and libraries.
      * **Deployment:** Used for deploying applications.
      * **Security:** Can be digitally signed.
  * **Creation:** Created using the `jar` tool (part of the JDK).
    ```bash
    # Create a JAR from compiled classes in 'bin' directory
    jar -cvf myapp.jar -C bin .
    ```
  * **Execution:**
    ```bash
    java -jar myapp.jar
    ```

-----

## 4.5 Introduction to the Java Module System (JPMS - Java Platform Module System)

Introduced in Java 9 (Project Jigsaw), the Module System provides a higher level of aggregation than packages, enabling more reliable configuration and stronger encapsulation.

### Creating and Declaring a Module

  * **Module:** A named, self-describing collection of code (packages, classes, resources) and data.
  * **Module Declaration:** Defined in a file named `module-info.java` at the root of the module's source directory.
  * **Basic Structure of `module-info.java`:**
    ```java
    module com.example.mymodule {
        // Module directives go here
    }
    ```

### Understanding Module Descriptors (`module-info.java`)

The `module-info.java` file contains directives that define a module's characteristics:

  * **`requires`:** Declares a dependency on another module.
      * `requires java.base;` (Implicitly required by all modules).
      * `requires my.other.module;`
      * **`requires transitive`:** If module A `requires transitive` module B, then any module that `requires` module A will also implicitly `requires` module B. This is useful for exposing API dependencies.
  * **`exports`:** Specifies which packages within the module are accessible to other modules.
      * `exports com.example.mymodule.api;` // Only `api` package is visible
      * **`exports com.example.mymodule.api to specific.module;`** // Qualified export: only `specific.module` can access.
  * **`opens`:** Allows packages to be accessed via **reflection** at runtime, even if they are not `exported`.
      * `opens com.example.mymodule.internal;`
      * **`opens com.example.mymodule.internal to specific.framework;`** // Qualified open.
  * **`uses`:** Declares that the module uses a **service** (an interface or abstract class).
      * `uses com.example.service.MyService;`
  * **`provides ... with`:** Declares that the module provides an implementation of a **service**.
      * `provides com.example.service.MyService with com.example.service.impl.MyServiceImpl;`

### Working with Multiple Modules

  * **Modular JARs:** Modules are typically packaged as modular JARs.
  * **Module Path:** When running a modular application, you specify the module path (`--module-path` or `-p`) which tells the JVM where to find the modules.
  * **Strong Encapsulation:** By default, if a package is not `exported`, it is strongly encapsulated and inaccessible from outside the module, even via reflection (unless `opened`). This is a key security and maintainability feature.

### Services and Service Loaders

  * **Services:** A way for modules to provide and consume implementations of an interface or abstract class without compile-time coupling.

  * **Service Provider Interface (SPI):** The interface or abstract class that defines the service.

  * **Service Provider:** A concrete implementation of the SPI.

  * **`ServiceLoader`:** A class in `java.util` that allows applications to discover and load service providers at runtime.

  * **Module Directives for Services:**

      * `uses` directive in the *consumer* module's `module-info.java` (declares it will use a service).
      * `provides ... with` directive in the *provider* module's `module-info.java` (declares it provides an implementation).

  * **Example Scenario:**

      * **`com.example.service` module:**
          * `module-info.java`: `exports com.example.service;`
          * `com/example/service/MyService.java` (interface)
      * **`com.example.service.impl` module:**
          * `module-info.java`: `requires com.example.service; provides com.example.service.MyService with com.example.service.impl.MyServiceImpl;`
          * `com/example/service/impl/MyServiceImpl.java` (implements `MyService`)
      * **`com.example.app` module:**
          * `module-info.java`: `requires com.example.service; uses com.example.service.MyService;`
          * `MyApp.java`: Uses `ServiceLoader.load(MyService.class)` to find and use implementations.

This modularity enhances application reliability, maintainability, and performance by explicitly defining dependencies and encapsulating internal implementation details.

-----