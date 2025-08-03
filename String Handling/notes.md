# 📝 Java String Handling: Mastering Text Manipulation

This document dives into the fundamental concepts of string handling in Java, exploring the `String`, `StringBuffer`, `StringBuilder` classes, various manipulation techniques, regular expressions, and string tokenization.

-----

## 3.1 Introduction to Strings in Java

In Java, strings are a fundamental part of almost every application. They are sequences of characters.

  * **Immutability of `String` Objects:**

      * `String` objects in Java are **immutable**. This means once a `String` object is created, its content cannot be changed.
      * Any operation that appears to modify a `String` (like concatenation or replacement) actually creates a **new `String` object** with the modified content. The original `String` object remains unchanged in memory.
      * **Reason for Immutability:**
          * **Security:** Used for storing sensitive information (passwords, usernames) as their values won't change after creation.
          * **Thread Safety:** Immutable objects are inherently thread-safe because their state cannot be modified by multiple threads simultaneously.
          * **Performance:** Allows the JVM to optimize string usage, including string literal pooling (where identical string literals point to the same object in the string pool).

  * **Creating `String` Objects:**

    1.  **String Literal (Recommended for fixed strings):**
        ```java
        String s1 = "Hello"; // Created in the String Pool
        ```
          * If "Hello" already exists in the String Pool, `s1` will refer to the existing object. Otherwise, a new object is created and added to the pool.
    2.  **Using `new` Keyword:**
        ```java
        String s2 = new String("World"); // Created in Heap memory (outside the pool)
        String s3 = new String("World"); // Creates another new object in Heap
        ```
          * Even if "World" is in the pool, `new` explicitly creates a new object in the general heap memory.

-----

## 3.2 Methods of the `String` Class

The `String` class provides a rich set of methods for various string operations.

  * **`length()`**: Returns the length of the string.
    ```java
    String str = "Java";
    System.out.println(str.length()); // Output: 4
    ```
  * **`charAt(int index)`**: Returns the character at the specified index.
    ```java
    String str = "Hello";
    System.out.println(str.charAt(1)); // Output: e
    ```
  * **`substring(int beginIndex)`**: Returns a new string that is a substring of this string.
    ```java
    String str = "Programming";
    System.out.println(str.substring(3)); // Output: gramming
    ```
  * **`substring(int beginIndex, int endIndex)`**: Returns a new string that is a substring of this string, from `beginIndex` (inclusive) to `endIndex` (exclusive).
    ```java
    String str = "Programming";
    System.out.println(str.substring(3, 7)); // Output: gram
    ```
  * **`concat(String str)` / `+` operator**: Concatenates the specified string to the end of this string.
    ```java
    String s1 = "Hello";
    String s2 = "World";
    String s3 = s1.concat(s2); // s3 = "HelloWorld"
    String s4 = s1 + " " + s2; // s4 = "Hello World"
    System.out.println(s3); // Output: HelloWorld
    System.out.println(s4); // Output: Hello World
    ```
  * **`indexOf(String sub)`**: Returns the index of the first occurrence of the specified substring.
    ```java
    String str = "Learning Java";
    System.out.println(str.indexOf("Java")); // Output: 9
    ```
  * **`lastIndexOf(String sub)`**: Returns the index of the last occurrence of the specified substring.
    ```java
    String str = "Java is fun, Java is great";
    System.out.println(str.lastIndexOf("Java")); // Output: 14
    ```
  * **`equals(Object obj)`**: Compares this string to the specified object. (Content comparison)
  * **`equalsIgnoreCase(String anotherString)`**: Compares this string to another string, ignoring case considerations.
    ```java
    String str1 = "Java";
    String str2 = "java";
    System.out.println(str1.equals(str2));         // Output: false
    System.out.println(str1.equalsIgnoreCase(str2)); // Output: true
    ```
  * **`startsWith(String prefix)`**: Checks if the string starts with the specified prefix.
  * **`endsWith(String suffix)`**: Checks if the string ends with the specified suffix.
    ```java
    String filename = "document.pdf";
    System.out.println(filename.endsWith(".pdf")); // Output: true
    ```
  * **`contains(CharSequence s)`**: Checks if the string contains the specified sequence of characters.
    ```java
    String sentence = "The quick brown fox";
    System.out.println(sentence.contains("quick")); // Output: true
    ```
  * **`replace(char oldChar, char newChar)`**: Returns a new string with all occurrences of `oldChar` replaced by `newChar`.
  * **`replace(CharSequence target, CharSequence replacement)`**: Replaces all occurrences of `target` with `replacement`.
    ```java
    String text = "hello world";
    System.out.println(text.replace('o', 'a')); // Output: hella warld
    System.out.println(text.replace("world", "Java")); // Output: hello Java
    ```
  * **`toUpperCase()`**: Converts all characters in the string to uppercase.
  * **`toLowerCase()`**: Converts all characters in the string to lowercase.
    ```java
    String msg = "Hello Java";
    System.out.println(msg.toUpperCase()); // Output: HELLO JAVA
    ```
  * **`trim()`**: Removes leading and trailing whitespace from the string.
    ```java
    String padded = "  Trim me   ";
    System.out.println(padded.trim()); // Output: Trim me
    ```
  * **`split(String regex)`**: Splits this string around matches of the given regular expression. Returns a `String[]`.
    ```java
    String data = "apple,banana,cherry";
    String[] fruits = data.split(",");
    for (String fruit : fruits) {
        System.out.println(fruit);
    }
    // Output:
    // apple
    // banana
    // cherry
    ```
  * **`valueOf(...)`**: Converts various data types (int, long, float, double, char[], boolean, Object) to their string representation. This is a static method.
    ```java
    int num = 123;
    String numStr = String.valueOf(num); // "123"
    System.out.println(numStr + 1); // Output: 1231 (concatenation, not addition)
    ```

-----

## 3.3 `StringBuffer` and `StringBuilder`: Mutable Strings

While `String` objects are immutable, `StringBuffer` and `StringBuilder` provide mutable sequences of characters, ideal for scenarios where string content needs frequent modification.

  * **`StringBuffer`**:

      * **Mutability:** Mutable (content can be changed).
      * **Thread Safety:** **Thread-safe** (methods are `synchronized`).
      * **Performance:** Slower than `StringBuilder` due to synchronization overhead.
      * **Use Case:** When string modifications are performed by multiple threads simultaneously and thread safety is critical.

  * **`StringBuilder`**:

      * **Mutability:** Mutable (content can be changed).
      * **Thread Safety:** **Not thread-safe** (methods are not `synchronized`).
      * **Performance:** Faster than `StringBuffer`.
      * **Use Case:** When string modifications are performed in a single-threaded environment, or when external synchronization is handled. Recommended for most general-purpose string manipulation.

**Common Methods (similar for both):**

  * **`append(...)`**: Adds content to the end.
    ```java
    StringBuilder sb = new StringBuilder("Hello");
    sb.append(" World");
    System.out.println(sb); // Output: Hello World
    ```
  * **`insert(int offset, ...)`**: Inserts content at a specific position.
    ```java
    StringBuilder sb = new StringBuilder("Hello");
    sb.insert(5, " Java");
    System.out.println(sb); // Output: Hello Java
    ```
  * **`delete(int start, int end)`**: Deletes characters from `start` (inclusive) to `end` (exclusive).
    ```java
    StringBuilder sb = new StringBuilder("HelloWorld");
    sb.delete(5, 10); // Deletes "World"
    System.out.println(sb); // Output: Hello
    ```
  * **`reverse()`**: Reverses the sequence of characters.
    ```java
    StringBuilder sb = new StringBuilder("racecar");
    sb.reverse();
    System.out.println(sb); // Output: racecar (still!)
    ```
  * **`toString()`**: Converts the `StringBuffer` or `StringBuilder` object back to an immutable `String`. (Always call this at the end if you need a `String` object)
    ```java
    StringBuilder sb = new StringBuilder("Final String");
    String result = sb.toString();
    System.out.println(result); // Output: Final String
    ```

-----

## 3.4 String Manipulation Techniques

Beyond the direct methods, several techniques are common for advanced string manipulation.

  * **Parsing Numbers from Strings:**
    ```java
    String numStr = "123";
    int num = Integer.parseInt(numStr); // For int
    double dbl = Double.parseDouble("12.34"); // For double
    System.out.println(num + 1); // Output: 124
    System.out.println(dbl + 1.0); // Output: 13.34
    ```
  * **Converting String to Character Array:**
    ```java
    String text = "Code";
    char[] chars = text.toCharArray();
    for (char c : chars) {
        System.out.print(c + " "); // Output: C o d e
    }
    System.out.println();
    ```
  * **Checking for Empty or Null Strings:**
    ```java
    String s1 = null;
    String s2 = "";
    String s3 = "  ";

    // Best practice for null and empty
    System.out.println(s1 == null || s1.isEmpty()); // Error if s1 is null with isEmpty()
    System.out.println(s2.isEmpty()); // Output: true
    System.out.println(s3.trim().isEmpty()); // Output: true (after trimming whitespace)

    // Using String.isBlank() (Java 11+)
    // isBlank() returns true if the string is empty or contains only whitespace characters.
    // isEmpty() returns true only if the string length is 0.
    String s4 = "   ";
    System.out.println(s4.isEmpty());  // Output: false
    System.out.println(s4.isBlank());  // Output: true
    ```
  * **Formatting Strings (using `String.format()` or `System.out.printf()`):**
    ```java
    String name = "Alice";
    int age = 30;
    String formattedString = String.format("Name: %s, Age: %d", name, age);
    System.out.println(formattedString); // Output: Name: Alice, Age: 30

    System.out.printf("Value: %.2f%n", 123.4567); // Output: Value: 123.46
    ```

-----

## 3.5 Regular Expressions (Regex)

Regular Expressions are powerful patterns used for matching, searching, and manipulating strings based on specific character sequences. In Java, `java.util.regex` package provides the API.

  * **Core Classes:**

      * `Pattern`: Represents a compiled regular expression.
      * `Matcher`: Used to perform match operations on a character sequence by interpreting a `Pattern`.

  * **Common Use Cases:**

      * **Validation:** Email addresses, phone numbers, passwords.
      * **Searching:** Finding specific patterns in text.
      * **Replacement:** Replacing parts of a string that match a pattern.
      * **Splitting:** Splitting a string based on complex delimiters.

  * **Basic Regex Syntax Elements:**

      * `.`: Any character (except newline).
      * `*`: Zero or more occurrences of the preceding element.
      * `+`: One or more occurrences.
      * `?`: Zero or one occurrence.
      * `[]`: Character set (e.g., `[abc]` matches 'a', 'b', or 'c').
      * `[^]`: Negated character set (e.g., `[^abc]` matches any char except 'a', 'b', 'c').
      * `\d`: A digit (0-9).
      * `\D`: A non-digit.
      * `\w`: A word character (a-z, A-Z, 0-9, \_).
      * `\W`: A non-word character.
      * `\s`: A whitespace character.
      * `\S`: A non-whitespace character.
      * `^`: Start of a line.
      * `$`: End of a line.

  * **Example (Matching):**

    ```java
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;

    String text = "The quick brown fox jumps over the lazy dog.";
    String regex = "fox"; // Pattern to find "fox"

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);

    if (matcher.find()) {
        System.out.println("Found match: " + matcher.group()); // Output: Found match: fox
    } else {
        System.out.println("No match found.");
    }
    ```

  * **Example (Replacing):**

    ```java
    String text = "Email: test@example.com, Phone: 123-456-7890";
    // Replace all digits with X
    String replacedText = text.replaceAll("\\d", "X");
    System.out.println(replacedText); // Output: Email: test@example.com, Phone: XXX-XXX-XXXX
    ```

-----

## 3.6 String Tokenization

String tokenization is the process of breaking a string into smaller parts (tokens) based on delimiters.

  * **Traditional Approach: `StringTokenizer` (Legacy)**

      * Part of `java.util.StringTokenizer`.
      * **Drawback:** Does not support regular expressions, and cannot differentiate between delimiters and actual tokens if the delimiter is an empty string. It's generally **not recommended** for new code due to better alternatives.

  * **Modern Approach: `String.split()` (Recommended)**

      * As shown in section 3.2, the `split()` method of the `String` class is the preferred way to tokenize strings in modern Java.
      * It uses **regular expressions** as delimiters, offering much more power and flexibility.

  * **Example (`String.split()` Recap):**

    ```java
    String csvLine = "ID,Name,Email,Age";
    String[] parts = csvLine.split(","); // Delimiter is a comma
    System.out.println("Number of tokens: " + parts.length); // Output: 4
    for (String part : parts) {
        System.out.println("Token: " + part.trim()); // trim() to remove potential leading/trailing whitespace
    }
    // Output:
    // Token: ID
    // Token: Name
    // Token: Email
    // Token: Age
    ```

  * **Example (Splitting with Multiple Delimiters / Regex):**

    ```java
    String mixedDelimiters = "apple;banana,cherry|date";
    // Split by comma, semicolon, or pipe
    String[] fruits = mixedDelimiters.split("[,;|]");
    for (String fruit : fruits) {
        System.out.println("Fruit: " + fruit);
    }
    // Output:
    // Fruit: apple
    // Fruit: banana
    // Fruit: cherry
    // Fruit: date
    ```

-----