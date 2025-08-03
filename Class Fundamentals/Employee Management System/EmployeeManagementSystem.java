/*
Design a simple Employee Management System to model employees in a 
company.
-> Each employee should have attributes like ID, name, department, and 
salary.
-> Use appropriate access modifiers to ensure encapsulation. Implement 
default and parameterized constructors, and demonstrate constructor overloading. 
-> Track the number of employees created using a static variable and showcase the 
use of the this keyword and this() constructor chaining. 
-> Use instanceof to check object types during runtime when dealing with contract vs. permanent employees.
*/

class Employee {
	protected int ID;
	protected String name;
	protected String department;
	protected long salary;
	
	//tracking the number of employees created
	static int count = 0;
	
	//default constructor
	Employee() {
		//this keyword
		//this.ID = 0;
		//this.name = " ";
		//this.department = " ";
		//this.salary = 0;
		
		//this() constructor
		this(0, " ", " ", 0);
	}
	
	//parameterized constructor
	Employee(int ID, String name, String department, long salary) {
		this.ID = ID;
		this.name = name;
		this.department = department;
		this.salary = salary;
		count++;
	}
	
}

class ContractEmployee extends Employee {
	boolean isRenewable;
	
	//default constructor
	ContractEmployee() {
		super();		//super() constructor calls parent class default constructor
		isRenewable = false;
	}
	
	//parameterized constructor
	ContractEmployee(int ID, String name, String department, long salary, boolean isRenewable) {
		super(ID,name,department,salary);	//super(...) constructor calls parent class parameterized constructor
		this.isRenewable = isRenewable;
	}
}

class PermanentEmployee extends Employee {
	String insurancePolicy;
	
	//default constructor
	PermanentEmployee() {
		super();		//super() constructor calls parent class default constructor
		insurancePolicy = " ";
	}
	
	//parameterized constructor
	PermanentEmployee(int ID, String name, String department, long salary, String insurancePolicy) {
		super(ID,name,department,salary);	//super(...) constructor calls parent class parameterized constructor
		this.insurancePolicy = insurancePolicy;
	}
}

class EmployeeManagementSystem {
	public static void main(String[] args) {
		Employee e = new Employee(1, "Alice", "DCE", 2000);
		ContractEmployee ce = new ContractEmployee(2, "Ruby", "DCE", 20000, false);
		PermanentEmployee pe = new PermanentEmployee(3, "Sita", "DCE", 40000, "Health Insurance");
		
		System.out.println("Employee count: " + Employee.count);
		System.out.println("pe instanceof PermanentEmployee : " + (pe instanceof PermanentEmployee));
	}
}