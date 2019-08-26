package se_realest;
import java.util.*;
import properties.*;

public class Main {

	private static int propertyIdTracker = 100;
	private static int customerIdTracker = 100;
	private static int employeeIdTracker = 100;
	
	LinkedList<RentalProperty> rentalProperties = new LinkedList<RentalProperty>(); 
	LinkedList<SaleProperty> saleProperties = new LinkedList<SaleProperty>();

	public static void main(String[] args) {
		
		//main 
		
	}
	
	public void newRentalProperty() {
		
		//add rentalproperty object to linkedlist, using the constructor (String address, String suburb, String owner, float price)
		//rentalProperties.add(new RentalProperty());
		
	}
	
	// Increments the global ID and prefixes with letter corresponding with customer type.
	public static String newPropertyId() {
		propertyIdTracker++;
		return ("P" + propertyIdTracker);
	}
	
	public static String newCustomerId() {
		customerIdTracker++;
		return ("C" + customerIdTracker);
	}
	
	public static String newEmployeeId() {
		employeeIdTracker++;
		return ("E" + employeeIdTracker);
	}
	
}
