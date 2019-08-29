package se_realest;
import java.util.*;
import properties.*;
import customers.*;
import employees.*;

public class Main {

	private static int propertyIdTracker = 100;
	private static int customerIdTracker = 100;
	private static int employeeIdTracker = 100;

	static HashMap<String, RentalProperty> rentalProperties = new HashMap<String, RentalProperty>();
	static HashMap<String, SaleProperty> saleProperties = new HashMap<String, SaleProperty>();
	static HashMap<String, Customer> customers = new HashMap<String, Customer>();
	static HashMap<String, Employee> employees = new HashMap<String, Employee>();
	
	public static void main(String[] args) {
		
		//main 
		mainMenu();
		
	}
	
	public static void mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "Welcome To The VeryCool™ SE Real Estate Software Solution\n\n"
				+ "What would you like to do?\n\n"
				+ "1: Add, Remove, or View Properties\n"
				+ "2: Add, Remove, or View Customers\n"
				+ "3: Add, Remove, or View Employees\n"
				+ "4: Under Construction\n\n"
				+ "Enter Selection: ");
		switch(sc.nextInt()) {
		case 1:  propertyMenu();
				 break;
		case 2:  customerMenu();
				 break;
		default: mainMenu();
				 break;
		}
	}
	
	private static void propertyMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "What would you like to do?\n\n"
				+ "1: Add new Rental Property\n"
				+ "2: Add new Sale Property\n"
				+ "3: Remove Rental Property\n"
				+ "4: Remove Sale Property\n"
				+ "5: View all Rental Properties\n"
				+ "6: View all Sale Properties\n"
				+ "7: Under Construction\n\n"
				+ "Enter Selection: ");
		switch(sc.nextInt()) {
		case 1:  newRentalProperty();
				 break;
		case 2:  customerMenu();
				 break;
		case 5:  System.out.print(getAllRentals());
				 propertyMenu();
				 break;
		default: mainMenu();
				 break;
		}
	}
	
	private static void customerMenu() {
		
	}
	
	public static void newRentalProperty() {
		Scanner sc = new Scanner(System.in);
		String address;
		String suburb;
		String owner;
		double price;
		System.out.printf("Please enter details for new RENTAL PROPERTY:\n\n"
				+ "Address (eg. \"123 Fake St\"):");
		address = sc.nextLine();
		System.out.print("Suburb:");
		suburb = sc.nextLine();
		System.out.print("Owner Name (eg. \"John Citizen\"):");
		owner = sc.nextLine();
		System.out.print("Weekly Rent Price in dollars (eg. 500):");
		price = sc.nextInt();
		rentalProperties.put(newPropertyId(), new RentalProperty(address, suburb, owner, price));
		System.out.printf("\n%s %s, owned by %s, has been successfully listed for $%.2f weekly.\n\n",
				address,suburb,owner,price);
		propertyMenu();
	}
	
	public void newSaleProperty(String address, String suburb, String owner, float price) {
		saleProperties.put(newPropertyId(), new SaleProperty(address, suburb, owner, price));
	}
	
	private static String getAllRentals() {
		StringBuilder result = new StringBuilder();
		rentalProperties.forEach((key, value) -> 
		result.append(String.format("ID: %s | %s\n", key, value.toString())));
		return result.toString();
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
