# 📚 Java Class Fundamentals: A Comprehensive Guide

This document provides a detailed overview of core class fundamentals in Java, covering everything from basic class definitions to advanced topics like inner classes and memory management.

-----

## 2.1 Defining Classes and Creating Objects

At the heart of Object-Oriented Programming (OOP) in Java are **classes** and **objects**.

  * **Class:** A blueprint or a template for creating objects. It defines the structure (fields/variables) and behavior (methods) that objects of that class will have.
      * **Syntax:**
        ```java
        class ClassName {
            // instance variables (attributes)
            // methods (behaviors)
            // constructors
        }
        ```
  * **Object:** An instance of a class. When you create an object, you are creating a concrete entity based on the class's blueprint.
      * **Instantiation:** The process of creating an object.

### Creating Objects: The `new` Keyword

The `new` keyword is used to allocate memory for a new object and return a reference to it.

  * **Syntax:**
    ```java
    ClassName objectName = new ClassName();
    ```
      * `ClassName objectName`: Declares a reference variable `objectName` of type `ClassName`.
      * `new ClassName()`: Creates a new object (an instance of `ClassName`) in the heap memory and calls its constructor.
      * `=`: Assigns the reference of the newly created object to `objectName`.

-----

## 2.2 Instance and Static Variables, Methods, and Object Reference Variables

Understanding the difference between instance and static members is crucial for effective class design.

### Instance Variables

  * **Definition:** Variables declared *inside a class but outside any method, constructor, or block*.
  * **Scope:** Tied to a specific object. Each object of the class gets its own copy of instance variables.
  * **Memory:** Stored in the **heap memory** as part of the object.
  * **Access:** Accessed via an object reference (e.g., `objectName.instanceVariable`).

### Static Variables (Class Variables)

  * **Definition:** Variables declared *inside a class but outside any method, constructor, or block*, and prefixed with the `static` keyword.
  * **Scope:** Belongs to the *class itself*, not to any specific object. There's only **one copy** of a static variable, shared by all objects of that class.
  * **Memory:** Stored in the **method area** (part of the heap, sometimes referred to as the "static pool").
  * **Access:** Accessed directly using the class name (e.g., `ClassName.staticVariable`) or optionally via an object reference (though not recommended for clarity).
  * **Use Cases:** For common properties across all instances (e.g., `PI`, `counter` for object count).

### Instance Methods

  * **Definition:** Methods declared *inside a class without the `static` keyword*.
  * **Scope:** Operate on the *instance variables* of a specific object.
  * **Access:** Called using an object reference (e.g., `objectName.instanceMethod()`).
  * **Requirement:** An object must be created to call an instance method.

### Static Methods (Class Methods)

  * **Definition:** Methods declared *inside a class with the `static` keyword*.
  * **Scope:** Belongs to the *class itself*. They can only directly access `static` variables and call `static` methods. They cannot directly access instance variables or instance methods because they don't operate on a specific object.
  * **Access:** Called directly using the class name (e.g., `ClassName.staticMethod()`).
  * **Use Cases:** Utility methods that don't need object-specific data (e.g., `Math.sqrt()`).

### Assigning Object Reference Variables

  * When you assign one object reference variable to another, they both refer to the **same object** in memory. No new object is created.
    ```java
    MyClass obj1 = new MyClass(); // obj1 refers to object A
    MyClass obj2 = obj1;          // obj2 now also refers to object A
    ```
  * Changes made through `obj2` will be visible through `obj1` because they are operating on the same underlying object.

-----

## 2.3 Access Modifiers

Access modifiers control the visibility and accessibility of classes, variables, methods, and constructors within a Java program.

  * **`public`**:
      * **Visibility:** Accessible from **anywhere** (any class, any package).
      * **Use Case:** For elements that should be universally available.
  * **`private`**:
      * **Visibility:** Accessible **only within the class** in which they are declared.
      * **Use Case:** Encapsulation; hiding implementation details and protecting data integrity (often accessed via public getter/setter methods).
  * **`protected`**:
      * **Visibility:** Accessible within the **same package** and by **subclasses (even in different packages)**.
      * **Use Case:** For elements intended for inheritance.
  * **`default` (package-private)**:
      * **Visibility:** Accessible **only within the same package**. No keyword is used; it's the default if no access modifier is specified.
      * **Use Case:** For elements that are part of a package's internal implementation but not intended for external use or inheritance outside the package.

| Modifier     | Same Class | Same Package | Subclass (Diff. Package) | Other Package |
| :----------- | :--------- | :----------- | :----------------------- | :------------ |
| `private`    | Yes        | No           | No                       | No            |
| `default`    | Yes        | Yes          | No                       | No            |
| `protected`  | Yes        | Yes          | Yes                      | No            |
| `public`     | Yes        | Yes          | Yes                      | Yes           |

-----

## 2.4 Using `final` and Understanding `this`

These keywords are fundamental for controlling mutability and referencing the current object.

### Using `final`

The `final` keyword can be applied to variables, methods, and classes:

  * **`final` Variable**:
      * **Behavior:** A `final` variable can be initialized **only once**. Its value cannot be changed after initialization.
      * **Use Case:** To create constants (e.g., `final int MAX_VALUE = 100;`).
  * **`final` Method**:
      * **Behavior:** A `final` method **cannot be overridden** by subclasses.
      * **Use Case:** To prevent undesirable modification of core logic in derived classes.
  * **`final` Class**:
      * **Behavior:** A `final` class **cannot be subclassed** (inherited from).
      * **Use Case:** To prevent further extension of a class, often for security reasons or to ensure immutability (e.g., `String` class).

### Understanding the `this` Keyword

The `this` keyword is a reference to the **current object** (the object on which the method or constructor is being invoked).

  * **Common Uses:**
      * **Referring to Current Class Instance Variables:** To differentiate between instance variables and local/method parameters that have the same name.
        ```java
        public void setAge(int age) {
            this.age = age; // 'this.age' refers to the instance variable
        }
        ```
      * **Invoking Current Class Constructor (Constructor Chaining):** Used within a constructor to call another constructor of the *same class*.
        ```java
        public MyClass(int value) {
            this.value = value;
        }
        public MyClass() {
            this(0); // Calls the MyClass(int) constructor
        }
        ```
          * **Rule:** `this()` call must be the **first statement** in the constructor.
      * **Returning the Current Class Instance:** Used in methods to return the current object.
        ```java
        public MyClass incrementValue() {
            this.value++;
            return this; // Returns the current object
        }
        ```

-----

## 2.5 Constructors and `instanceof` Operator

Constructors are special methods for initializing objects, and `instanceof` checks object type.

### Constructors

  * **Definition:** A special type of method used to **initialize new objects**. They have the same name as the class and *do not have a return type* (not even `void`).
  * **Invocation:** Automatically called when an object is created using the `new` keyword.

#### Types of Constructors:

  * **Default Constructor (No-Argument Constructor):**
      * If you don't define any constructor in your class, Java provides a **default (no-argument) constructor** implicitly.
      * It initializes instance variables to their default values (e.g., `0` for numbers, `null` for objects, `false` for booleans).
  * **Parameterized Constructor:**
      * A constructor that takes one or more arguments.
      * Used to initialize instance variables with specific values provided during object creation.
      * **Important:** If you define any parameterized constructor, Java will **not** provide the default constructor. You must explicitly define a no-argument constructor if you still need one.

### Constructor Overloading

  * A class can have **multiple constructors** as long as each has a different parameter list (different number of arguments or different types/order of arguments). This is known as constructor overloading.
    ```java
    class Box {
        double width, height, depth;

        Box() { // Default constructor
            width = height = depth = 0.0;
        }
        Box(double w, double h, double d) { // Parameterized constructor
            width = w;
            height = h;
            depth = d;
        }
        Box(double side) { // Another parameterized constructor
            width = height = depth = side;
        }
    }
    ```

### `instanceof` Operator

  * **Purpose:** Used to check if an object is an **instance of a particular class** or an instance of a class that implements a particular interface.
  * **Syntax:**
    ```java
    objectName instanceof ClassName_or_InterfaceName
    ```
  * **Returns:**
      * `true` if `objectName` is an instance of `ClassName_or_InterfaceName` (or a subclass/implementation).
      * `false` otherwise.
      * If `objectName` is `null`, it returns `false`.

### The role of the `this()` Constructor Call

  * As mentioned in section 2.4, `this()` is used within a constructor to **call another constructor of the same class**.
  * It facilitates **constructor chaining**, allowing one constructor to perform initial setup and then delegate to another constructor for more specific initialization.
  * **Rule:** It **must be the very first statement** in the calling constructor.

-----

## 2.6 Nested and Inner Classes

Java allows classes to be defined within other classes, offering better encapsulation and logical grouping.

### 1\. Static Nested Class

  * **Definition:** A static class defined *inside another class*. It behaves like a top-level class but is a member of the outer class.
  * **Key Characteristics:**
      * Declared with the `static` keyword.
      * **Cannot directly access non-static members (instance variables or methods) of the outer class.**
      * Can directly access static members of the outer class.
      * Does **not** require an instance of the outer class to be created.
      * Accessed using `OuterClass.StaticNestedClass`.

### 2\. Inner Class (Non-Static Inner Class)

  * **Definition:** A non-static class defined *inside another class*.
  * **Key Characteristics:**
      * **Implicitly holds a reference to an instance of its enclosing (outer) class.**
      * **Can directly access all members (static and non-static) of the outer class**, including `private` members.
      * **Requires an instance of the outer class to be created** before an instance of the inner class can be created.
      * Accessed using `OuterClass.InnerClass innerObj = outerObj.new InnerClass();`

### 3\. Method-Local Inner Class

  * **Definition:** An inner class defined *inside a method* (or a block).
  * **Key Characteristics:**
      * **Scope:** Its scope is limited to the method/block in which it is defined. It cannot be accessed from outside that method.
      * **Access:** Can access members of the outer class. Can access `final` or effectively `final` local variables of the enclosing method.
      * **Instantiation:** Objects of this class can only be created within the method where it is defined.

### 4\. Anonymous Inner Class

  * **Definition:** An inner class without a name. It is defined and instantiated in a single expression.
  * **Key Characteristics:**
      * Used when you need to create an object of a class that implements an interface or extends a class **only once** and for a specific purpose.
      * Cannot have explicitly defined constructors.
      * Cannot be `static`.
      * Can access `final` or effectively `final` local variables of the enclosing method.
      * Often used for event handling (e.g., `ActionListener`), thread creation (`Runnable`), or defining custom behavior on the fly.

-----

## 2.7 Understanding the Object Class

The `Object` class is the **root of the class hierarchy** in Java. Every class in Java directly or indirectly inherits from the `Object` class.

  * **Default Behavior:** If you don't override its methods, your custom classes will use the `Object` class's default implementations.

### Key Methods of the `Object` Class

  * **`equals(Object obj)`:**
      * **Purpose:** Indicates whether some other object is "equal to" this one.
      * **Default Implementation:** Performs a **reference comparison** (`this == obj`), meaning it returns `true` only if both references point to the *exact same object* in memory.
      * **Overriding:** Often overridden in custom classes to provide a **logical or value comparison** (e.g., two `Person` objects are equal if their `id` and `name` are the same).
  * **`hashCode()`:**
      * **Purpose:** Returns a hash code value for the object.
      * **Contract with `equals()`:**
          * If two objects are `equal` according to the `equals()` method, then calling `hashCode()` on each of the two objects *must* produce the same integer result.
          * If two objects are *not* equal, their `hashCode()` values *may or may not* be different (ideally, they should be different for better performance in hash-based collections like `HashMap`).
      * **Overriding:** Should always be overridden when `equals()` is overridden to maintain the contract, especially when objects of the class will be stored in hash collections.
  * **`toString()`:**
      * **Purpose:** Returns a string representation of the object.
      * **Default Implementation:** Typically returns a string consisting of the class name, an "@" sign, and the unsigned hexadecimal representation of the object's hash code (e.g., `ClassName@abcdef12`).
      * **Overriding:** Highly recommended for custom classes to provide a **meaningful and human-readable representation** of the object's state (e.g., `Person[name=Alice, age=30]`). This is very useful for logging and debugging.
  * **`clone()`:**
      * **Purpose:** Creates and returns a copy of this object.
      * **Requires:** The class must implement the `Cloneable` interface (a marker interface).
      * **Default Implementation:** Performs a **shallow copy** (copies field values; if a field is an object reference, the *reference* is copied, not the object it points to).
      * **Usage:** Often requires careful overriding to implement deep cloning for complex objects.
  * **`getClass()`:**
      * **Purpose:** Returns the runtime class of this `Object`.
      * **Returns:** A `Class` object, which can be used for reflection (inspecting class information at runtime).
  * **`notify()`, `notifyAll()`, `wait()`:**
      * These methods are used for **inter-thread communication** and synchronization. They are fundamental to Java's concurrency model and are typically called within `synchronized` blocks.

### Overriding `toString()` and `equals()` in Custom Classes

It's common practice to override `toString()` and `equals()` for clarity and correct behavior when working with custom objects.

#### Overriding `toString()` Example:

```java
class Book {
    String title;
    String author;
    int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
    }

    public static void main(String[] args) {
        Book myBook = new Book("The Java Handbook", "J. Doe", 2023);
        System.out.println(myBook); // Calls myBook.toString() implicitly
    }
}
```

**Output:**

```
Book [title=The Java Handbook, author=J. Doe, year=2023]
```

#### Overriding `equals()` and `hashCode()` Example:

```java
class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        // 1. Check if it's the same object reference
        if (this == obj) return true;
        // 2. Check for null and if objects are of the same class
        if (obj == null || getClass() != obj.getClass()) return false;
        // 3. Cast the object and compare relevant fields
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        // Combine hash codes of relevant fields
        // A common simple way: 31 * result + field_hashCode
        int result = Integer.hashCode(x);
        result = 31 * result + Integer.hashCode(y);
        return result;
    }

    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        Point p2 = new Point(10, 20);
        Point p3 = new Point(30, 40);

        System.out.println("p1.equals(p2): " + p1.equals(p2)); // true
        System.out.println("p1.equals(p3): " + p1.equals(p3)); // false
        System.out.println("p1.hashCode(): " + p1.hashCode());
        System.out.println("p2.hashCode(): " + p2.hashCode()); // Should be same as p1
    }
}
```

**Output:**

```
p1.equals(p2): true
p1.equals(p3): false
p1.hashCode(): 3527
p2.hashCode(): 3527
```

-----

## 2.8 Memory Management in Java

Java provides automatic memory management, abstracting away much of the complexity, primarily through its garbage collector.

### Stack vs. Heap Memory

  * **Stack Memory:**
      * **Purpose:** Used for storing **local variables**, **method calls**, and **primitive data types**.
      * **Lifecycle:** Data in the stack is allocated and deallocated automatically as methods are called and return (LIFO - Last-In, First-Out).
      * **Speed:** Faster for allocation/deallocation.
      * **Size:** Relatively smaller.
  * **Heap Memory:**
      * **Purpose:** Used for storing **all objects** (instances of classes) created with the `new` keyword, as well as **instance variables**.
      * **Lifecycle:** Objects in the heap have a longer and more complex lifecycle. They are created when `new` is used and are removed by the Garbage Collector when no longer referenced.
      * **Speed:** Slower for allocation/deallocation compared to stack.
      * **Size:** Larger, dynamic memory area.

### Object Lifecycle and Garbage Collection

  * **Object Lifecycle:**
    1.  **Creation:** An object is created using `new`, memory is allocated on the heap, and a constructor is called.
    2.  **Usage:** The object is used by the application (its methods are called, its fields are accessed).
    3.  **Unreferenced:** When an object is no longer reachable from any active part of the program (e.g., all references to it go out of scope or are set to `null`), it becomes eligible for garbage collection.
    4.  **Garbage Collection:** The Java Garbage Collector (GC) automatically identifies and reclaims memory occupied by unreferenced objects.
  * **Garbage Collection (GC):**
      * **Automatic:** Java's GC runs in the background, automatically managing memory. Developers don't explicitly free memory.
      * **Algorithms:** Various GC algorithms (e.g., Serial, Parallel, CMS, G1, ZGC) exist, each with different strategies for finding and collecting garbage.
      * **Purpose:** Prevents memory leaks (where unreachable objects still occupy memory) and reduces developer burden.
      * **`System.gc()` / `Runtime.getRuntime().gc()`:** These methods are **hints** to the JVM to run the garbage collector, but there's no guarantee it will run immediately or at all. Relying on them for critical memory management is bad practice.

### Manual Memory Management and Implications

  * **Java's Design:** Java was designed to *abstract away* manual memory management (unlike C++). You don't use `delete` or `free`.
  * **Implications:**
      * **Reduced Development Effort:** Developers spend less time on memory bugs (e.g., double-free, dangling pointers).
      * **Increased Robustness:** Fewer memory-related crashes.
      * **Performance Trade-offs:** GC introduces overhead, which can sometimes lead to "pauses" (stop-the-world events) if not tuned correctly, especially in performance-critical applications. Modern GCs minimize these pauses.
      * **Memory Leaks (Logical):** While Java prevents *native* memory leaks, you can still have "logical" memory leaks where objects are still referenced but are no longer needed by the application (e.g., objects added to a `List` that is never cleared). This means GC cannot reclaim them.

-----

## 2.9 Java Annotations and Meta-annotations, Reflection

Annotations provide a way to add metadata to Java code, while reflection allows runtime inspection and manipulation.

### Overview and Purpose of Annotations

  * **Definition:** Metadata that can be added to Java source code. They are special markers that provide information about the code, but they don't directly affect the execution of the code itself.

  * **Purpose:**

      * **Information for the Compiler:** Used by the compiler to detect errors or suppress warnings (e.g., `@Override`, `@SuppressWarnings`).
      * **Runtime Processing:** Can be processed at runtime by JVM or other tools (e.g., `@Deprecated` for APIs, annotations used by frameworks like Spring or JUnit).
      * **Code Generation:** Tools can use annotations to generate code (e.g., Lombok).
      * **Documentation:** Can serve as a form of specialized documentation.

  * **Built-in Annotations:**

      * `@Override`: Indicates a method overrides a superclass method.
      * `@Deprecated`: Marks an API element as outdated; compiler warns if used.
      * `@SuppressWarnings`: Suppresses specific compiler warnings.
      * `@FunctionalInterface`: Indicates an interface is a functional interface (has one abstract method).
      * `@SafeVarargs`: Asserts that varargs parameters don't cause unsafe operations.

### Custom Annotation

You can define your own annotations for specific purposes.

  * **Declaration:** Uses the `@interface` keyword.

  * **Retention Policy:** Determines how long the annotation is kept (source, class, runtime).

      * `@Retention(RetentionPolicy.SOURCE)`: Discarded by the compiler.
      * `@Retention(RetentionPolicy.CLASS)`: Stored in the `.class` file but not available at runtime.
      * `@Retention(RetentionPolicy.RUNTIME)`: Stored in the `.class` file and available at runtime via reflection (most powerful).

  * **Target:** Specifies where the annotation can be applied (class, method, field, etc.).

      * `@Target(ElementType.METHOD)`: Can only annotate methods.
      * `@Target({ElementType.TYPE, ElementType.FIELD})`: Can annotate classes and fields.

  * **Example Custom Annotation:**

    ```java
    import java.lang.annotation.*;

    @Retention(RetentionPolicy.RUNTIME) // Available at runtime
    @Target(ElementType.METHOD)        // Can be applied to methods
    public @interface MyCustomAnnotation {
        String value() default "default"; // An element with a default value
        int count() default 1;
    }

    class MyAnnotatedClass {
        @MyCustomAnnotation(value = "Hello", count = 5)
        public void myMethod() {
            System.out.println("Method executed.");
        }
    }
    ```

### Meta-annotations

  * **Definition:** Annotations that are applied to *other annotations*. They provide information about the annotation itself.
  * **Common Meta-annotations:**
      * `@Retention`: (Discussed above) Specifies the retention policy.
      * `@Target`: (Discussed above) Specifies the applicable targets.
      * `@Documented`: Indicates that elements using this annotation should be documented by Javadoc.
      * `@Inherited`: Indicates that an annotation type is automatically inherited by subclasses.
      * `@Repeatable`: Allows the same annotation to be applied multiple times to a single declaration.

### Reflection in Java

  * **Definition:** A powerful feature that allows a Java program to **examine or modify its own behavior at runtime**.

  * **Purpose:** Enables applications to inspect classes, interfaces, fields, and methods at runtime without knowing their names at compile time. It can also be used to create new instances of classes, invoke methods, and get/set field values dynamically.

  * **Key Classes/Interfaces:**

      * `Class`: Represents classes and interfaces.
      * `Constructor`: Represents a class constructor.
      * `Method`: Represents a class method.
      * `Field`: Represents a class field.

  * **Use Cases:**

      * **Frameworks:** Heavily used by frameworks (e.g., Spring, Hibernate, JUnit) for dependency injection, ORM mapping, testing, etc.
      * **Debugging Tools:** IDEs and debuggers use reflection to inspect objects and call methods.
      * **Serialization/Deserialization:** Libraries use reflection to convert objects to/from formats like JSON or XML.
      * **Dynamic Proxies:** Creating proxy objects at runtime.

  * **Caution:**

      * **Performance Overhead:** Reflection can be slower than direct access because it bypasses normal Java optimizations.
      * **Security Issues:** Can potentially expose private members, breaking encapsulation.
      * **Increased Complexity:** Code using reflection can be harder to read and maintain.

  * **Example (Accessing Annotation via Reflection):**

    ```java
    import java.lang.reflect.Method;

    // (Assume MyCustomAnnotation and MyAnnotatedClass from above are defined)

    public class AnnotationReader {
        public static void main(String[] args) throws NoSuchMethodException {
            MyAnnotatedClass obj = new MyAnnotatedClass();
            Class<?> cls = obj.getClass();

            // Get the method object
            Method method = cls.getMethod("myMethod");

            // Check if the annotation is present
            if (method.isAnnotationPresent(MyCustomAnnotation.class)) {
                // Get the annotation instance
                MyCustomAnnotation annotation = method.getAnnotation(MyCustomAnnotation.class);

                System.out.println("Annotation value: " + annotation.value());
                System.out.println("Annotation count: " + annotation.count());
            }
        }
    }
    ```

**Output:**

```
Annotation value: Hello
Annotation count: 5
```

-----