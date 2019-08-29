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
				  "\n\nWelcome To The VeryCool™ SE Real Estate Software Solution\n\n"
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
				+ "7: Assign Agent to Rental Property\n"
				+ "8: Main Menu\n\n"
				+ "Enter Selection: ");
		switch(sc.nextInt()) {
		case 1:  newRentalProperty();
				 break;
		case 2:  customerMenu();
				 break;
		case 5:  System.out.print(getAllRentals());
				 propertyMenu();
				 break;
		case 7:  assignRentalAgent();
				 break;
		case 8:	 mainMenu();
		default: mainMenu();
				 break;
		}
	}
	
	private static void customerMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "What would you like to do?\n\n"
				+ "1: Add new Customer\n"
				+ "2: Remove Customer\n"
				+ "3: View all Customers\n"
				+ "4: Main Menu\n\n"
				+ "Enter Selection: ");
		switch(sc.nextInt()) {
		case 1:  newCustomer();
				 break;
		case 3:  System.out.print(getAllCustomers());
				 mainMenu();
				 break;
		case 4:  mainMenu();
		default: mainMenu();
				 break;
		}
	}
	
	private static void employeeMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "What would you like to do?\n\n"
				+ "1: Add new Employee\n"
				+ "2: Remove Employee\n"
				+ "3: View all Employees\n"
				+ "4: Main Menu\n\n"
				+ "Enter Selection: ");
		switch(sc.nextInt()) {
		case 1:  newEmployee();
				 break;
		case 4: mainMenu();
		default: mainMenu();
				 break;
		}
	}
	
	public static void newRentalProperty() {
		Scanner sc = new Scanner(System.in);
		String address;
		String suburb;
		Customer owner;
		double price;
		
		System.out.printf("Please enter details for new RENTAL PROPERTY:\n\n"
				+ "Address (eg. \"123 Fake St\"):");
		address = sc.nextLine();
		System.out.print("Suburb:");
		suburb = sc.nextLine();
		System.out.print("Owner's Customer ID (eg. \"C123\"), leave blank to create new Customer:");
		String customer = sc.nextLine();
		if(!customer.isEmpty()) {
			owner=customers.get(customer);
		} else {
			owner = customers.get(newCustomer());
		}
		System.out.print("Weekly Rent Price in dollars (eg. 500):");
		price = sc.nextInt();
		String id = newPropertyId();
		System.out.print("Length of lease in months:");
		int leaseLength = sc.nextInt();
		rentalProperties.put(id, new RentalProperty(id, address, suburb, owner, price, leaseLength));
		System.out.printf("\n%s %s, owned by %s, has been successfully listed for $%.2f weekly.\n\n",
				address,suburb,owner.getName(),price);
		propertyMenu();
	}
	
	public void newSaleProperty() {
		Scanner sc = new Scanner(System.in);
		String address;
		String suburb;
		Customer owner;
		double price;
		System.out.printf("Please enter details for new SALE PROPERTY:\n\n"
				+ "Address (eg. \"123 Fake St\"):");
		address = sc.nextLine();
		System.out.print("Suburb:");
		suburb = sc.nextLine();
		System.out.print("Owner's Customer ID (eg. \"C123\"), leave blank to create new Customer:");
		String customer = sc.nextLine();
		if(!customer.isEmpty()) {
			owner=customers.get(customer);
		} else {
			owner = customers.get(newCustomer());
		}
		System.out.print("Price of Property in dollars (eg. 600000):");
		price = sc.nextInt();
		String id = newPropertyId();
		saleProperties.put(id, new SaleProperty(id, address, suburb, owner, price));
		System.out.printf("\n%s %s, owned by %s, has been successfully listed for $%.2f.\n\n",
				address,suburb,owner,price);
		propertyMenu();
	}
	
	
	private static void newEmployee() {
		Scanner sc = new Scanner(System.in);
		String name;
		String email;
		int workLoad;
		System.out.printf("Please enter details for new Employee:\n\n"
				+ "Name:");
		name = sc.nextLine();
		System.out.print("Email:");
		email = sc.nextLine();
		System.out.print("Part-Time or Full-Time? Part-Time=1, Full-Time=2:");
		workLoad = sc.nextInt();
		String id = newEmployeeId();
		if(workLoad==1){
			System.out.print("Hourly pay in dollars (and cents if applicable)(eg. 19 or 21.50):");
			double hourlyPay = sc.nextDouble();
			System.out.print("Weekly rostered hours:");
			int weeklyHours = sc.nextInt();
			employees.put(id, new PartTimeEmployee(id, name, email, hourlyPay, weeklyHours));
			System.out.printf("Part-Time Employee %s successfully added.");
		} else if (workLoad==2) {
			System.out.print("Yearly salary in dollars (and cents if applicable):");
			double salary = sc.nextDouble();
			employees.put(id, new FullTimeEmployee(id, name, email, salary));
		}
		employeeMenu();	
	}
	
	private static String newCustomer() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Customer Name (eg. \"John Citizen\"):");
		String name = sc.nextLine();
		System.out.print("Customer E-mail (eg. \"john@website.com\"):");
		String email = sc.nextLine();
		String id = newCustomerId();
		customers.put(id, new Customer(id,name,email));
		System.out.printf("Customer %s, %s has been successfully added.\n",id, name);
		return id;
	}
	
	private static String getAllRentals() {
		StringBuilder result = new StringBuilder();
		rentalProperties.forEach((key, value) -> 
		result.append(String.format("ID: %s | %s\n", key, value.toString())));
		return result.toString();
	}

	private static String getAllCustomers() {
		StringBuilder result = new StringBuilder();
		customers.forEach((key, value) -> 
		result.append(String.format("ID: %s | %s\n", key, value.toString())));
		return result.toString();
	}
	
	private static void assignRentalAgent() {
		Scanner sc = new Scanner(System.in);
		System.out.printf("\nPlease enter ID of property you'd like to assign a rental agent:\n");
		String property = sc.nextLine();
		System.out.printf("\nPlease enter ID of employee you'd like to assign to rental property:\n");
		String agent = sc.nextLine();
		//using the id of the property, find the associated Property object
		//using the id of the employee, find the associated Employee object
		//then set the agent of the found property to the found property
		rentalProperties.get(property).assignPropertyManager(employees.get(agent));
		System.out.printf("%s has been successfully assigned to the rental agent %s.",
				          rentalProperties.get(property).toString(), employees.get(agent).getName());
		propertyMenu();
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
