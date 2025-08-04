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
	static class Course {
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
		public String toString(){
			return "Coursename : " + this.courseName + "Credit Points : " + this.creditPoints;
		}
	}
	
	void addCourse(String courseName, int creditPoints) {
		courses.add(new Course(courseName,creditPoints));
	}
	
	//method-local inner class for semester-wise enrollments
	void processSemesterwiseEnrollments(int semester) {
		class semesterEnrollments {
			void printAcademicDetails() {
				System.out.println("Semester : " + semester);
				
				for(Course c : courses) {
					System.out.println(c);
				}
			}
		}
		
		semesterEnrollments se = new semesterEnrollments();
		se.printAcademicDetails();
	}
	
	int countCredits() {
		int totalCredits = 0;
		
		for(Course c : courses) {
			totalCredits += c.creditPoints;
		}
		return totalCredits;
	}
	}
	
	class Scholarship {
		boolean isEligible (Student s) {
			return true;
		}
	}


class StudentPortal {
	public static void main(String[] args) {
		
		Student s1 = new Student(101, "Jay");
		s1.addCourse("Java Programming Language", 4);
		s1.addCourse("Data Structures and Algorithm", 4);
		s1.processSemesterwiseEnrollments(3);
		
		Scholarship s = new Scholarship() {
			public boolean isEligible(Student s1) {
				return s1.countCredits() > 6;
			}
		};
		
		boolean eligible = s.isEligible(s1);
		System.out.println("Scholarship eligibility : " + eligible);
	}
}
