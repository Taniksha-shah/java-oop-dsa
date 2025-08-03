/*
Create a Student Portal System that stores student details and their enrolled 
courses. Implement a main Student class and define an inner class Course. Use 
method-local inner classes to represent semester-wise enrollments, and 
anonymous inner classes for scholarship eligibility logic. Override the equals(), 
toString(), and hashCode() methods from the Object class to compare student 
objects. Demonstrate how objects are created in the heap, how method calls go 
on the stack, and when objects become eligible for garbage collection.
*/

import java.util.ArrayList;
import java.util.Objects;

class Student {
    private int ID;
    private String name;
    ArrayList<Course> courses = new ArrayList<>();

    Student() {
        this.ID = 0;
        this.name = " ";
    }

    Student(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    //inner class - a class inside a class
    class Course {
        String courseName;
        int creditPoints;

        Course() {
            this.courseName = " ";
            this.creditPoints = 0;
        }

        Course(String courseName, int creditPoints) {
            this.courseName = courseName;
            this.creditPoints = creditPoints;
        }

        public String toString() {
            return "Coursename : " + this.courseName + " | Credit Points : " + this.creditPoints;
        }
    }

    void addCourse(String courseName, int creditPoints) {
        courses.add(new Course(courseName, creditPoints));
    }

    //method-local inner class for semester-wise enrollments
    void processSemesterwiseEnrollments(int semester) {
        class SemesterEnrollments {
            void printAcademicDetails() {
                System.out.println("Semester : " + semester);
                for (Course c : courses) {
                    System.out.println(c);
                }
            }
        }
        SemesterEnrollments se = new SemesterEnrollments();
        se.printAcademicDetails();
    }

    int countCredits() {
        int totalCredits = 0;
        for (Course c : courses) {
            totalCredits += c.creditPoints; // Corrected field name
        }
        return totalCredits;
    }

    // Override equals(), toString(), and hashCode() methods from the Object class
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return ID == student.ID; // Comparing based on ID
    }

    public int hashCode() {
        return Objects.hash(ID); // Hashing based on ID
    }

    public String toString() {
        return "Student{" +
               "ID=" + ID +
               ", name='" + name + '\'' +
               ", totalCredits=" + countCredits() +
               '}';
    }
}

class ScholarshipEligibilityChecker {
    boolean isEligible(Student s) {
        return false; // Default implementation, to be overridden by anonymous class
    }
}


class StudentPortal {
    public static void main(String[] args) {

        Student s1 = new Student(101, "Jay");
        s1.addCourse("Java Programming Language", 4);
        s1.addCourse("Data Structures and Algorithm", 4);
        s1.processSemesterwiseEnrollments(3);

        // Demonstrate equals(), hashCode(), toString()
        Student s3 = new Student(101, "Jay K."); // Same ID as s1
        System.out.println(s1); // Uses toString()
        System.out.println(s3); // Uses toString()
        System.out.println("s1.equals(s3)? " + s1.equals(s3)); // Uses equals()
        System.out.println("s1 hashCode: " + s1.hashCode()); // Uses hashCode()
        System.out.println("s3 hashCode: " + s3.hashCode()); // Uses hashCode()

        // anonymous inner classes for scholarship eligibility logic
        ScholarshipEligibilityChecker scholarshipChecker = new ScholarshipEligibilityChecker() {
            @Override
            public boolean isEligible(Student studentToCheck) { // Corrected parameter name
                return studentToCheck.countCredits() > 6; // Corrected method call
            }
        };

        boolean eligible = scholarshipChecker.isEligible(s1);
        System.out.println("Scholarship eligibility : " + eligible);

        // Demonstrate how objects become eligible for garbage collection (conceptual)
        Student tempStudent = new Student(999, "Temporary Student");
        tempStudent = null; // Object previously referenced by tempStudent is now eligible for GC
    }
}