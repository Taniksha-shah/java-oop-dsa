# Understanding Generic Methods and Varargs in Java

This document explores **Generic Methods** and **Varargs (Variable Arguments)** in Java, two powerful features that enhance code flexibility and reusability.

-----

## 🚀 Generic Methods: Flexible Code with Type Safety

Generic methods allow you to write a single method declaration that can be called with arguments of different types. This promotes **code reusability** and provides **compile-time type safety**.

### 📝 Definition

A method capable of operating on *any data type* is known as a **generic method**.

### 📐 Basic Syntax

The type parameter `T` (or any uppercase letter) is declared *before* the return type.

```java
<AccessSpecifier> <ReturnType> methodName(<T> typeParameterName) {
    // Body of method
}
```

### 💡 Example 1: Simple Generic Print Method

This example demonstrates a basic generic method `genericPrint` that can print values of various data types.

```java
class DemoClass {
    // Defining a generic method to print any data type
    <T> void genericPrint(T t) {
        System.out.println(t);
    }

    public static void main (String[] args) {
        DemoClass obj = new DemoClass(); // Initialize the object
        obj.genericPrint(12);
        obj.genericPrint("Hello, World!");
        obj.genericPrint(12.09090);
    }
}
```

**Output:**

```
12
Hello, World!
12.0909
```

### 💡 Example 2: Static Generic Method

Generic methods can also be `static`. In this case, the type parameter is declared immediately after the `static` keyword.

```java
class StaticGenericMethodDemo {

    static <T> void genericPrint(T t) {
        // Corrected: Use getClass().getName() for type name
        System.out.println(t.getClass().getName() + " : " + t);
    }

    public static void main(String[] args) {
        genericPrint(12);
        genericPrint("Hello, World!");
        genericPrint(12.09090);
    }
}
```

**Output:**

```
java.lang.Integer : 12
java.lang.String : Hello, World!
java.lang.Double : 12.0909
```

### 📋 Parameter Passing

  * Parameters passed to generic methods should typically be **class types** (e.g., `Integer`, `String`, `Double`, custom `Person` objects). Primitive types are *auto-boxed* to their corresponding wrapper classes.

### 💡 Example 3: Generic Method for Swapping Values (Important Note on Pass-by-Value)

This example illustrates a generic `swap` method. However, it's crucial to understand that **Java's pass-by-value mechanism** means this `swap` method will *not* modify the original `String` or `Person` references passed from `main`. It only swaps the copies within the `swap` method's scope.

```java
class SwapTest {
    // The type parameter <T> must be declared before the return type for static methods
    public static <T> void swap(T a, T b) {
        T temp;
        temp = a;
        a = b;
        b = temp;
        // In Java, arguments are passed by value.
        // Swapping 'a' and 'b' here only changes the local copies, not the original references.
        System.out.println("  Inside swap - a: " + a + ", b: " + b);
    }

    public static void main(String[] args) {
        String a = "Hello";
        String b = "World";

        System.out.println("Before swap (Strings): String 1 : " + a + ", String 2 : " + b);
        swap(a, b); // This will not swap the original strings
        System.out.println("After swap (Strings): String 1 : " + a + ", String 2 : " + b);

        System.out.println("\n--- Testing with Objects ---");
        Person p1 = new Person(12, "Alice");
        Person p2 = new Person(24, "Ruby");

        System.out.println("Before swap (Objects): Person 1 : " + p1.name + ", Person 2 : " + p2.name);
        swap(p1, p2); // This will not swap the original objects
        System.out.println("After swap (Objects): Person 1 : " + p1.name + ", Person 2 : " + p2.name);
    }
}

class Person {
    int age;
    String name;

    Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
```

**Output:**

```
Before swap (Strings): String 1 : Hello, String 2 : World
  Inside swap - a: World, b: Hello
After swap (Strings): String 1 : Hello, String 2 : World

--- Testing with Objects ---
Before swap (Objects): Person 1 : Alice, Person 2 : Ruby
  Inside swap - a: Person@XYZ, b: Person@ABC // (Actual hash codes will vary)
After swap (Objects): Person 1 : Alice, Person 2 : Ruby
```

*Note on `SwapTest` output:* The `Person` objects' `toString()` method is not overridden, so `System.out.println` will print their default string representation (class name + hash code). The key takeaway is that `p1` and `p2` in `main` *do not change*, even though `a` and `b` within the `swap` method do.

-----

## 📦 Varargs: Flexible Number of Arguments

**Varargs** (variable arguments) allow a method to accept zero or multiple arguments of a specified type. This simplifies method overloading and makes APIs more user-friendly.

### 📝 Definition

Varargs allow you to pass a **variable number of values** to a method. Internally, these values are treated as an array within the method's body.

### 🚀 Declaration Methods for Varargs

There are primarily three ways to declare methods that accept a variable number of arguments:

#### 1\. Using an Array (Traditional Approach)

You explicitly pass an array to the method.

  * **Syntax:**

    ```java
    void methodName(T[] arrayName);
    ```

  * **Example:**

    ```java
    class VarargsMethodDemo {
        static void varargsMethod(int v[]) {
            System.out.print("Array elements: ");
            for(int i : v) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        public static void main(String[] args) {
            int x[] = {1, 2, 3, 4};
            int y[] = {2, 4};
            int z[] = { }; // An empty array is valid

            varargsMethod(x);
            varargsMethod(y);
            varargsMethod(z);
        }
    }
    ```

**Output:**

```
Array elements: 1 2 3 4 
Array elements: 2 4 
Array elements: 
```

#### 2\. Using Ellipses (`...`) - The Modern Varargs Syntax

This is the standard and most common way to declare a varargs method in Java. The compiler handles the array creation implicitly.

  * **Syntax:**

    ```java
    <AccessSpecifier> <ReturnType> methodName(<Type>...<arrayName>) {
        // Body of method
    }
    ```

  * **Example:**

    ```java
    class VarargsMethodDemo {
        static void varargsMethod(int ... v) {
            System.out.println("Number of arguments : " + v.length);
            System.out.print("Arguments: ");
            for(int i : v) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        public static void main(String[] args) {
            varargsMethod(1, 2, 3, 4); // Pass individual arguments
            varargsMethod(-2, 4);
            varargsMethod(); // Can be called with zero arguments
        }
    }
    ```

**Output:**

```
Number of arguments : 4
Arguments: 1 2 3 4 
Number of arguments : 2
Arguments: -2 4 
Number of arguments : 0
Arguments: 
```

#### 3\. Using `Object...` - For Heterogeneous Arguments

This is the most flexible approach for varargs when you need to pass arguments of *different types* to a single method call. It leverages the `Object` class as the base type.

  * **Concept:** It uses ellipses (`...`) in conjunction with the `Object` type, allowing any data type (including primitives, which are auto-boxed) to be passed.

  * **Syntax:**

    ```java
    public static void methodName(Object ... obj) {
        // Body of method
    }
    ```

  * **Example:**

    ```java
    class VarargsMethodDemo {
        static void varargsMethod(Object ... obj) {
            System.out.println("Number of arguments: " + obj.length);
            System.out.print("Arguments: ");
            for(Object o : obj) {
                System.out.print(o + " "); // Changed 'i' to 'o'
            }
            System.out.println();
        }

        public static void main(String[] args) {
            varargsMethod(1, "string", 3, false); // Mixed types
            varargsMethod(-2, 4, 34, 2, 5, 7);
            varargsMethod(); // No arguments
        }
    }
    ```

**Output:**

```
Number of arguments: 4
Arguments: 1 string 3 false 
Number of arguments: 6
Arguments: -2 4 34 2 5 7 
Number of arguments: 0
Arguments: 
```
