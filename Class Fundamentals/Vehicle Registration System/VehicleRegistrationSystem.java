/*
Build a Vehicle Registration System where different types of vehicles (Car, Bike, 
Truck) are registered. Use final variables for immutable fields, such as vehicle 
registration numbers. Declare a final class for utility methods and restrict 
overriding certain methods with final. Implement custom annotations like 
@VehicleInfo to mark important fields and use reflection to extract metadata at 
runtime (e.g., print fields annotated with @VehicleInfo). Demonstrate the use of 
Java meta-annotations in defining your custom annotation.
*/

abstract class Vehicle {
	final String registrationNumber;
	
	Vehicle() {
		this.registrationNumber = 0;
	}
	Vehicle(long registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public void getVehicleDetails();
}

class Car extends Vehicle{
	String fuelType;
	boolean hasSunRoof;
	
	Car() {
		super();
		this.fuelType = " ";
		this.hasSunRoof = false;
	}
	Car(String registrationNumber, String fuelType, boolean hasSunRoof) {
		super(registrationNumber);
		this.fuelType = fuelType;
		this.hasSunRoof = hasSunRoof;
	}
	public void getVehicleDetails() {
		System.out.println("Registration Number: " + this.registrationNumber);
		System.out.println("Fuel type: " + this.fuelType);
		System.out.println("has Sun roof: " + this.hasSunRoof);
	}
}

class Bike extends Vehicle{
	String bikeType;
	int bikeCapacityCC;
	
	Bike() {
		super();
		this.bikeType = " ";
		this.bikeCapacityCC = 0;
	}
	Bike(String registrationNumber, String bikeType, int bikeCapacityCC) {
		super(registrationNumber);
		this.bikeType = bikeType;
		this.bikeCapacityCC = bikeCapacityCC;
	}
	public void getVehicleDetails() {
		System.out.println("Registration Number: " + this.registrationNumber);
		System.out.println("Bike type: " + this.bikeType);
		System.out.println("Bike Capacity: " + this.bikeCapacityCC);
	}
}

class Truck extends Vehicle{
	double payloadCapacityTons;
	boolean hazardousMaterialCarrier;
	
	Truck() {
		super();
		this.payloadCapacityTons = 0;
		this.hazardousMaterialCarrier = false;
	}
	Bike(String registrationNumber, double payloadCapacityTons, boolean hazardousMaterialCarrier) {
		super(registrationNumber);
		this.payloadCapacityTons = payloadCapacityTons;
		this.hazardousMaterialCarrier = hazardousMaterialCarrier;
	}
	public void getVehicleDetails() {
		System.out.println("Registration Number: " + this.registrationNumber);
		System.out.println("Payload Capacity (in tons): " + this.payloadCapacityTons);
		System.out.println("hazardousMaterialCarrier: " + this.hazardousMaterialCarrier);
	}
}

class VvehicleRegistrationSystem {
	public stativ void main(String[] args) {}
}