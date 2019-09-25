package se_realest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

import properties.*;
import customers.*;
import employees.*;

public class Main {

	private static String user = "";
	private static Menu menu;
	private static GodMenu godMenu;
	
	private static int propertyIdTracker = 100;
	private static int customerIdTracker = 100;
	private static int employeeIdTracker = 100;

	//hashmaps allow for easy data lookup using IDs, which almost every object will have
	static HashMap<String, RentalProperty> rentalProperties = new HashMap<String, RentalProperty>();
	static HashMap<String, SaleProperty> saleProperties = new HashMap<String, SaleProperty>();
	static HashMap<String, Customer> customers = new HashMap<String, Customer>();
	static HashMap<String, Employee> employees = new HashMap<String, Employee>();
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		//provide prefilled demo data
//		feedDemoData();
		inputData(rentalProperties, saleProperties, customers, employees);
		//login
		login();
		//main 
		mainMenu();
	}
	
	public static void login() throws FileNotFoundException, IOException {
		user = "";
		Scanner sc = new Scanner(System.in);
		String input;
		System.out.printf("\n\nWelcome To The VeryCool S&E Real Estate Software Solution\n\n" + 
							"Please enter your e-mail address to login (or leave blank to make a new account):");
		input = sc.nextLine();
		if(input.equals("")) {
			String newCust = newCustomer();
			menu = new Menu(newCust,customers.get(newCust).getCustomerType());
			menu.begin();			
		} else {
		
			//check if user is customer
			for (Customer customer: customers.values()) {
			    if(customer.compareLogin(input)) {
			    	user = customer.getId();
			    	System.out.printf("\nWelcome %s.\n",customer.getName()); 
			    	menu = new Menu(user,customer.getCustomerType());
			    	menu.begin();
			    	break;
			    }
			}
			//check if user is employee
			for (Employee employee: employees.values()) {
			    if(employee.compareLogin(input)) {
			    	user = employee.getId();
			    	System.out.printf("\nWelcome %s.\n",employee.getName());
			    	if(employee.getEmployeeType()==0) {
			    		godMenu = new GodMenu(user,0);
			    		godMenu.godMenu();
			    		break;
			    	} else {
			    		menu = new Menu(user, employee.getEmployeeType());
			    		menu.begin();
			    		break;
			    	}
			    }
			}
		}
	}
	
	public static void mainMenuCustomer() {
		System.out.print("Customer");
		
	}
	
	public static void mainMenuManager() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\n\nWelcome To The VeryCool S&E Real Estate Software Solution\n"
				  + "Branch Manager Menu\n"
				+ "What would you like to do?\n\n"
				+ "1: Add, Remove, or View Properties\n"
				+ "2: Add, Remove, or View Customers\n"
				+ "3: View Employees\n"
				+ "4: Under Construction\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1:  propertyMenu();
				 break;
		case 2:  customerMenu();
				 break;
		case 3:  employeeMenu();
				 break;
		default: mainMenu();
				 break;
		}
	}

	public static void mainMenuEmployee() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\n\nEmployee Menu:"
				+ "What would you like to do?\n\n"
				+ "1: View Your Properties\n"
				+ "2: View Customers\n"
				+ "3: View Employees\n"
				+ "4: Under Construction\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1:  propertyMenu();
				 break;
		case 2:  customerMenu();
				 break;
		case 3:  employeeMenu();
				 break;
		default: mainMenu();
				 break;
		}
	}
		
	public static void mainMenu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\n\nWelcome To The VeryCool S&E Real Estate Software Solution\n\n"
				+ "What would you like to do?\n\n"
				+ "1: Add, Remove, or View Properties\n"
				+ "2: Add, Remove, or View Customers\n"
				+ "3: Add, Remove, or View Employees\n"
				+ "4: Logout\n"
				+ "5: Save data\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1:  propertyMenu();
				 break;
		case 2:  customerMenu();
				 break;
		case 3:  employeeMenu();
				 break;
		case 5:  outputData(rentalProperties, saleProperties, customers, employees);
		case 4:  logout();
		default: mainMenu();
				 break;
		}
	}
	
	private static void propertyMenu() throws FileNotFoundException, IOException {
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
				+ "8: See Offers on a Property\n"
				+ "8: Main Menu\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1:  newRentalProperty();
				 break;
		case 2:  customerMenu();
				 break;
		case 5:  System.out.print(getAllRentals());
				 propertyMenu();
				 break;
		case 6:  System.out.print(getAllSales());
		 		 propertyMenu();
		 		 break;
		case 7:  assignPropertyManager();
				 break;
		case 8:	 mainMenu();
		default: mainMenu();
				 break;
		}
	}
	
	private static void customerMenu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "What would you like to do?\n\n"
				+ "1: Add new Customer\n"
				+ "2: Remove Customer\n"
				+ "3: View all Customers\n"
				+ "4: Main Menu\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1:  newCustomer();
				 mainMenu();
				 break;
		case 2:	 removeCustomer();
				 mainMenu();
				 break;
		case 3:  System.out.print(getAllCustomers());
				 mainMenu();
				 break;
		case 4:  mainMenu();
		default: mainMenu();
				 break;
		}
	}
	
	

	private static void employeeMenu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "What would you like to do?\n\n"
				+ "1: Add new Employee\n"
				+ "2: Remove Employee\n"
				+ "3: View all Employees\n"
				+ "4: Main Menu\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1:  newEmployee();
				 break;
		case 3:  System.out.print(getAllEmployees());
		 		 mainMenu();
		 		 break;
		case 4: mainMenu();
		default: mainMenu();
				 break;
		}
	}
	
	static void newRentalProperty() {
		Scanner sc = new Scanner(System.in);
		String address;
		String suburb;
		String ownerId;
		double price;
		
		System.out.printf("Please enter details for new RENTAL PROPERTY:\n\n"
				+ "Address (eg. \"123 Fake St\"):");
		address = sc.nextLine();
		System.out.print("Suburb:");
		suburb = sc.nextLine();
		System.out.print("Owner's Customer ID (eg. \"C123\"), leave blank to create new Customer:");
		String customer = sc.nextLine();
		if(!customer.isEmpty()) {
			ownerId=customer;
		} else {
			ownerId = newCustomer();
		}
		System.out.print("Weekly Rent Price in dollars (eg. 500):");
		price = sc.nextInt();
		String id = newPropertyId();
		System.out.print("Length of lease in months:");
		int leaseLength = sc.nextInt();
		rentalProperties.put(id, new RentalProperty(id, address, suburb, ownerId, price, leaseLength));
		System.out.printf("\n%s | %s %s, owned by %s, has been successfully listed for $%.2f weekly, for %d months.\n\n",
				id, address,suburb,customers.get(ownerId).getName(),price,leaseLength);
	}
	
	static void newSaleProperty() {
		Scanner sc = new Scanner(System.in);
		String address;
		String suburb;
		String ownerId;
		double price;
		System.out.printf("Please enter details for new SALE PROPERTY:\n\n"
				+ "Address (eg. \"123 Fake St\"):");
		address = sc.nextLine();
		System.out.print("Suburb:");
		suburb = sc.nextLine();
		System.out.print("Owner's Customer ID (eg. \"C123\"), leave blank to create new Customer:");
		String customer = sc.nextLine();
		if(!customer.isEmpty()) {
			ownerId=customer;
		} else {
			ownerId = newCustomer();
		}
		System.out.print("Price of Property in dollars (eg. 600000):");
		price = sc.nextInt();
		String id = newPropertyId();
		saleProperties.put(id, new SaleProperty(id, address, suburb, ownerId, price));
		System.out.printf("\n%s | %s %s, owned by %s, has been successfully listed for $%.2f.\n\n",
				id, address,suburb,ownerId,price);
	}
	
	static String addSaleProperty(String id, String address, String suburb, String ownerId, double price) {
		saleProperties.put(id, new SaleProperty(id, address, suburb, ownerId, price));
		return String.format("\n%s | %s %s, owned by %s, asking $%,.2f, has been sent to S&E for Management. Check back soon for updates.\n\n",
				id, address,suburb,ownerId,price);
	}
	
	static String removeSaleProperty(String propertyId) {
		String output = saleProperties.get(propertyId).toString();
		saleProperties.remove(propertyId);
		return String.format("%s has been successfully unlisted.\n",output);
	}

	static void editSaleProperty(String propertyId, String customerId) {
		Scanner sc = new Scanner(System.in);
		String address;
		String suburb;
		double price;
		System.out.printf("Please enter new details for %s\n\n"
				+ "Address (eg. \"123 Fake St\"):", saleProperties.get(propertyId).toString());
		address = sc.nextLine();
		System.out.print("Suburb:");
		suburb = sc.nextLine();
		System.out.print("Asking price of Property in dollars (eg. 600000):");
		price = sc.nextInt();
		String id = propertyId;
		System.out.print("UPDATE: " + addSaleProperty(id, address, suburb, customerId, price));
	}
	
	static String addRentalProperty(String id, String address, String suburb, String ownerId, double price, int leaseLength) {
		saleProperties.put(id, new SaleProperty(id, address, suburb, ownerId, price));
		return String.format("\n%s | %s %s, asking $%.2f weekly with a %d month lease, has been sent to S&E for Management. Check back soon for updates.\n\n",
				id, address,suburb,ownerId,price, leaseLength);
	}
	
	static String removeRentalProperty(String propertyId) {
		String output = rentalProperties.get(propertyId).toString();
		rentalProperties.remove(propertyId);
		return String.format("%s has been successfully unlisted.\n",output);
	}
	
	static void editRentalProperty(String propertyId, String customerId) {
		Scanner sc = new Scanner(System.in);
		String address;
		String suburb;
		double price;
		int leaseLength;
		System.out.printf("Please enter details for new RENTAL PROPERTY:\n\n"
				+ "Address (eg. \"123 Fake St\"):");
		address = sc.nextLine();
		System.out.print("Suburb:");
		suburb = sc.nextLine();
		System.out.print("Asking weekly rent of Property in dollars (eg. 650):");
		price = sc.nextDouble();
		System.out.print("Length of lease in months:");
		leaseLength = sc.nextInt();
		String id = propertyId;
		System.out.print("UPDATE: " + Main.addRentalProperty(id, address, suburb, customerId, price, leaseLength));
	}
	
	static boolean applyForRental(String customerId) {
		Scanner sc = new Scanner(System.in);
		String appliedForProperty = null;
		while(true)
		{
			System.out.println("Please enter ID of Rental Property you'd like to apply for:");
			appliedForProperty = sc.next();
			if(!appliedForProperty.matches("P[0-9]{3}"))
			{
				System.out.println("Please enter a valid property ID");
			}
			else if(!rentalProperties.containsKey(appliedForProperty))
			{
				System.out.printf("Rental Property ID: %s does not exist\n",appliedForProperty);
			}
			else
			{
				break;
			}
		}
		
		double offerPrice = 0;
		System.out.printf("Applying for %s\nWhat weekly rent would you like to offer? \n"
				+ "(it's unethical to allow bids on rental properties, but we won't tell anyone if you offer higher!)\n"
				+ "Your weekly rent offer (in dollars and cents eg. 500.00):", rentalProperties.get(appliedForProperty).toString());
		while(!sc.hasNextDouble())
		{
				System.out.println("Please input a valid price in the format \"dollar\".\"cents\"");
				sc.next();
		}
		offerPrice = sc.nextDouble();	
		System.out.printf("\nSuccessfully offered $%,.2f weekly for %s.",offerPrice, rentalProperties.get(appliedForProperty).addressString());
		rentalProperties.get(appliedForProperty).newOffer(customerId, offerPrice); 
		customers.get(customerId).makeOffer(appliedForProperty, customerId);
		return true;
	}
	
	static void newEmployee() throws FileNotFoundException, IOException {
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
			System.out.print("Annual pro-rata salary in dollars (and cents if applicable):");
			double salary = sc.nextDouble();
			employees.put(id, new PartTimeEmployee(id, name, email, salary));
			System.out.printf("Part-Time Employee %s | %s successfully added.\n", id, name);
		} else if (workLoad==2) {
			System.out.print("Annual salary in dollars (and cents if applicable):");
			double salary = sc.nextDouble();
			employees.put(id, new FullTimeEmployee(id, name, email, salary));
		}
		employeeMenu();	
	}
	
	static String newCustomer() {
		Scanner sc = new Scanner(System.in);
		String name = null;
		boolean check = false;
		while(check == false)
		{
			System.out.print("Customer Name (eg. \"John Citizen\"):");
			name = sc.nextLine();
			if(!name.matches("[a-zA-Z ]+"))
			{
				System.out.println("Please enter a name without numbers");
			}
			else
			{
				check = true;
			}
		}
		check = false;
		String email = null;
		while(check == false)
		{
			System.out.print("Customer E-mail (eg. \"john@website.com\"):");
			email = sc.nextLine();
			if(!email.matches("[a-zA-Z0-9]+\\@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+"))
			{
				System.out.println("Please enter a valid email");
			}
			else
			{
				check = true;
			}
		}
		check = false;
		int type = 0;
		while(check == false)
		{
			System.out.printf("Customer type:\n"
					+ "Vendor (selling)=1\n"
					+ "Landlord (leasing)=2\n"
					+ "Buyer (prospectively buying a property)=3 or\n"
					+ "Renter (prospectively renting a property)=4\n"
					+ "Please specify:");
			
			if(!sc.hasNextInt()) 
			{
				System.out.println("Input a number 1,2,3 or 4.");
				sc.next();
			}
			else
			{
				int value = sc.nextInt();
				if((value == 1)||(value == 2)||(value == 3)||(value == 4))
				{
					type = value;
					check = true;
				}
				else
				{
					System.out.println("Please input a valid number (1,2,3 or 4).");
				}
				
			}
		}
		
		String id = newCustomerId();
		customers.put(id, new Customer(id,name,email,type));
		System.out.printf("Customer %s, %s has been successfully added.\n",id, name);
		return id;
	}
	
	static void removeCustomer() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Customer ID:");
		String id = sc.nextLine();
		String name1 = customers.get(id).getName();
		customers.remove(id);
		System.out.println("Customer " + id + " - " + name1 + " has been removed.");
		return;
	}
	
	static String getAllSales() {
		StringBuilder result = new StringBuilder();
		saleProperties.forEach((key, value) -> 
		result.append(String.format("ID: %s | %s\n", key, value.toString())));
		return result.toString();
	}
	
	static String getAllRentals() {
		StringBuilder result = new StringBuilder();
		rentalProperties.forEach((key, value) -> 
		result.append(String.format("ID: %s | %s\n", key, value.toString())));
		return result.toString();
	}
	
	static String getAvailableRentals() {
		StringBuilder result = new StringBuilder();
		rentalProperties.forEach(
				(key, value) -> 
				{
					if(value.isAvailable()) {
						result.append(String.format("ID: %s | %s\n", key, value.toString()));
					}
				}
		);
		return result.toString();
	}
	
	static String getSalesBy(String customerId) {
		StringBuilder result = new StringBuilder();
		for (String propertyId: customers.get(customerId).getSaleProperties()) {
			result.append(propertyId + " | " + saleProperties.get(propertyId).toString() + "\n");
		}
		return result.toString();
	}
	
	static String getRentalsBy(String customerId) {
		StringBuilder result = new StringBuilder();
		for (String propertyId: customers.get(customerId).getRentalProperties()) {
			result.append(rentalProperties.get(propertyId).toString() + "\n");
		}
		return result.toString();
	}

	static String findRentalsBySuburb(String suburb) {
		StringBuilder result = new StringBuilder();
		result.append(String.format("Displaying all Rental Properties in %s.\n", suburb));
		for (RentalProperty property : rentalProperties.values()) {
			if(property.compareSuburb(suburb)) {
				result.append(property.getId() + " | " + property.toString() + "\n");
			}
		}
		return result.toString();	
	}
		
	static String getSaleOffers(String propertyId) {
		return saleProperties.get(propertyId).printOffers();
	}
	
	static String getRentalOffers(String propertyId) {
		return rentalProperties.get(propertyId).printOffers();
	}
	
	static String getOffersBy(String customerId) {
		StringBuilder sb = new StringBuilder();
		for (String offer : customers.get(customerId).getOffers()) {
			String property = offer.substring(0,4);
			/*since offerID is P100C100 (propertyID + customerID), you can substring the offerId to get the property.
			 * iterating through the customer's offers array gives you the propertyid and offerid of each of their offers.
			 * using these, you can "get" the offer objects (from the main collection) 
			 * for each discovered property and append their toString. */
			sb.append(String.format("%s(for %s.)\n", rentalProperties.get(property).getOffers().get(offer).toString(),
					rentalProperties.get(property).addressString()));
		}
		return sb.toString();
	}

	static String getAllCustomers() {
		StringBuilder result = new StringBuilder();
		customers.forEach((key, value) -> 
		result.append(String.format("ID: %s | %s\n", key, value.toString())));
		return result.toString();
	}
	
	static String getAllEmployees() {
		StringBuilder result = new StringBuilder();
		employees.forEach((key, value) -> 
		result.append(String.format("ID: %s | %s\n", key, value.toString())));
		return result.toString();
	}

	static void assignPropertyManager() {
		Scanner sc = new Scanner(System.in);
		System.out.printf("\nPlease enter ID of property you'd like to assign a rental agent:\n");
		String property = sc.nextLine();
		System.out.printf("\nPlease enter ID of employee you'd like to assign to rental property:\n");
		String agent = sc.nextLine();
		//using the id of the property, find the associated Property object, then:
		//using the id of the employee, find the associated Employee object, then:
		//co-assign each
		rentalProperties.get(property).assignPropertyManager(agent);
		System.out.printf("%s has been successfully assigned to rental agent %s | %s.\n\n",
				          rentalProperties.get(property).toString(), agent, employees.get(agent).getName());
	}
	
	static void assignSalesConsultant() {
		Scanner sc = new Scanner(System.in);
		System.out.printf("\nPlease enter ID of property you'd like to assign a rental agent:\n");
		String property = sc.nextLine();
		System.out.printf("\nPlease enter ID of employee you'd like to assign to rental property:\n");
		String agent = sc.nextLine();
		//using the id of the property, find the associated Property object, then:
		//using the id of the employee, find the associated Employee object, then:
		//co-assign each
		rentalProperties.get(property).assignPropertyManager(agent);
		System.out.printf("%s has been successfully assigned to rental agent %s | %s.\n\n",
				          rentalProperties.get(property).toString(), agent, employees.get(agent).getName());
	}
	
	public static String getCustomerName(String customerId) {
		return customers.get(customerId).getName();
	}
	
	// Increments the global ID and prefixes with letter corresponding with ID type.
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
	
	
	public static void logout() throws FileNotFoundException, IOException {
		//wipes the instances of Menu objects, removing any customer or employee data from the cache
		System.out.print("Goodbye.");
		godMenu = null;
		menu = null;
		main(null);
	}
	
	public static void feedDemoData() {
		//provides 3 rental properties, 3 renters (from TODO: 3 landlords), 1 vendor and 1 buyer making 1 offer
		customers.put("C097", new Customer("C097", "P. Sherman", "psherman@pearlywhites.com.au", 4));
		customers.get("C097").setCustomerType(4);
		rentalProperties.put("P097", new RentalProperty("P097","42 Wallaby Way", "Sydney", "C097", 500.00, 12));
		customers.get("C097").makeTenantOf("P097");
		employees.put("E097", new PartTimeEmployee("E097","Michael Bay", "bang@boom.com", 60000));
		rentalProperties.get("P097").assignPropertyManager("E097");
		
		customers.put("C011", new Customer("C011", "N. Vester", "nvester@bigmoney.com", 2));
		customers.get("C011").setCustomerType(2);
		rentalProperties.put("P011", new RentalProperty("P011","100 Green Road", "Hawthorn", "C011", 750.00, 12));
		customers.get("C011").addRentalProperty("P011");
		rentalProperties.get("P011").makeAvailable();
		rentalProperties.put("P012", new RentalProperty("P012","200 Red Road", "Hawthorn", "C011", 580.00, 12));
		customers.get("C011").addRentalProperty("P012");
		rentalProperties.get("P012").makeAvailable();
		rentalProperties.put("P013", new RentalProperty("P013","55 Yellow St", "Fitzroy", "C011", 620.00, 12));
		customers.get("C011").addRentalProperty("P013");
		rentalProperties.get("P013").makeAvailable();
		rentalProperties.put("P014", new RentalProperty("P014","42 Wallaby Way", "Footscray", "C011", 550.00, 12));
		customers.get("C011").addRentalProperty("P014");
		rentalProperties.get("P014").makeAvailable();
		
		rentalProperties.put("P098", new RentalProperty("P098","742 Evergreen Terrace", "Springfield", "C098", 800.00, 24));
		customers.put("C098", new Customer("C098", "Homer Simpson", "homer@compuglobalhypermeganet.com", 4));
		customers.get("C098").makeTenantOf("P098");
		customers.get("C097").setCustomerType(4);
		employees.put("E098", new PartTimeEmployee("E098","Jimbo Jones", "jimmy@website.com", 49000));
		rentalProperties.get("P098").assignPropertyManager("E098");
		
		customers.put("C099", new Customer("C099", "Donald Trump", "trump@donaldtrump.com", 1));
		saleProperties.put("P099", new SaleProperty("P099","1600 Pennsylvania Ave", "Washington", "C099", 1000000.00));
		customers.get("C099").vendor = true;
		customers.get("C099").addSaleProperty("P099");
		employees.put("E099", new FullTimeEmployee("E099","Bruce Wayne", "man@bat.com", 1000000));
		saleProperties.get("P099").assignSalesConsultant("E099");
		
		customers.put("C096", new Customer("C096", "Scrooge McDuck", "scrooge@mcduck.com", 3));
		customers.put("C095", new Customer("C095", "Donald Duck", "donald@duck.com", 4));
		//after demo change this to "saleProperties.get("P099").newOffer("C096",950000);" and make saleoffers private
		saleProperties.get("P099").saleOffers.put("P099C096",new Offer("P099C096","C096",950000));
		
		employees.put("E001", new FullTimeEmployee("E001", "Boss Man", "boss@serealest.com.au", 250000));
		employees.get("E001").setEmployeeType(0);
		
		employees.put("E002",  new FullTimeEmployee("E002", "Alice Admin", "alice@serealest.com.au", 90000));			
	}
	
	public static void inputData(HashMap<String, RentalProperty> rentalProperties,
			HashMap<String, SaleProperty> saleProperties,
			HashMap<String, Customer> customers,
			HashMap<String, Employee> employees)throws IOException, FileNotFoundException
	{
		File fileCustomer = new File("testinCustomer.txt");
		Scanner inputCustomer = new Scanner(fileCustomer);
		String tokenCustomer = "";
		String[] textCustomer = null;

		while(inputCustomer.hasNextLine())
		{
			tokenCustomer = inputCustomer.nextLine();
//			System.out.println(tokenCustomer);
			textCustomer = tokenCustomer.split(",");
//			For debugging
//			for(int i = 0; i<textCustomer.length; i++)
//			{
//				System.out.println(i + "." + textCustomer[i]);
//			}
			customers.put(textCustomer[0], new Customer(textCustomer[0], 
					textCustomer[1], textCustomer[2], Integer.parseInt(textCustomer[3])));
		}
		inputCustomer.close();
		
		File fileEmployee = new File("testinEmployee.txt");
		Scanner inputEmployee = new Scanner(fileEmployee);
		String tokenEmployee = "";
		String[] textEmployee = null;
		
		while(inputEmployee.hasNextLine())
		{
			tokenEmployee = inputEmployee.nextLine();
//			System.out.println(tokenEmployee);
			textEmployee = tokenEmployee.split(",");
//			For debugging
//			for(int i = 0; i<textEmployee.length; i++)
//			{
//				System.out.println(i + "." + textEmployee[i]);
//			}
			if(textEmployee.length == 5)
			{
				employees.put(textEmployee[0], new FullTimeEmployee(textEmployee[0], 
						textEmployee[1], textEmployee[2], Double.parseDouble(textEmployee[3])));
				employees.get(textEmployee[0]).setEmployeeType(Integer.parseInt(textEmployee[4]));
			}
			else if(textEmployee.length == 4)
			{
				employees.put(textEmployee[0], new PartTimeEmployee(textEmployee[0], 
						textEmployee[1], textEmployee[2], Double.parseDouble(textEmployee[3])));
			}
			else
			{
				System.out.println("Error");
			}
			
		}
		inputEmployee.close();
		
		File fileSale = new File("testinSale.txt");
		Scanner inputSale = new Scanner(fileSale);
		String tokenSale = "";
		String[] textSale = null;
		
		while(inputSale.hasNextLine())
		{
			tokenSale = inputSale.nextLine();
//			System.out.println(tokenSale);
			textSale = tokenSale.split(",");
//			For debugging
//			for(int i = 0; i<textSale.length; i++)
//			{
//				System.out.println(i + "." + textSale[i]);
//			}
			saleProperties.put(textSale[0], new SaleProperty(textSale[0], 
					textSale[1], textSale[2], textSale[3], Double.parseDouble(textSale[4])));
			customers.get(textSale[3]).vendor = Boolean.parseBoolean(textSale[5]);
			customers.get(textSale[3]).addSaleProperty(textSale[0]);
			saleProperties.get(textSale[0]).assignSalesConsultant(textSale[6]);
		}
		inputSale.close();
		
		File fileRent = new File("testinRental.txt");
		Scanner inputRent = new Scanner(fileRent);
		String tokenRent = "";
		String[] textRent = null;
		
		while(inputRent.hasNextLine())
		{
			tokenRent = inputRent.nextLine();
//			System.out.println(tokenRent);
			textRent = tokenRent.split(",");
//			For debugging
//			for(int i = 0; i<textRent.length; i++)
//			{
//				System.out.println(i + "." + textRent[i]);
//			}
			rentalProperties.put(textRent[0], new RentalProperty(textRent[0], 
					textRent[1], textRent[2], textRent[3], Double.parseDouble(textRent[4]), 
					Integer.parseInt(textRent[5])));
			if(customers.get(textRent[3]).getCustomerType() == 4)
			{
				customers.get(textRent[3]).makeTenantOf(textRent[0]);
			}
			else if(customers.get(textRent[3]).getCustomerType() == 2)
			{
				customers.get(textRent[3]).addRentalProperty(textRent[0]);
				rentalProperties.get(textRent[0]).makeAvailable();
			}
			
			if(textRent[7].compareTo("null") != 0)
			{
				rentalProperties.get(textRent[0]).assignPropertyManager(textRent[7]);
			}

		}
		inputRent.close();
	}	
	
	
	public static void outputData(HashMap<String, RentalProperty> rentalProperties,
			HashMap<String, SaleProperty> saleProperties,
			HashMap<String, Customer> customers,
			HashMap<String, Employee> employees)throws IOException, FileNotFoundException
	{
		File fileCustomer = new File("testoutCustomer.txt");
		PrintWriter outputCustomer = new PrintWriter(fileCustomer);
		for (HashMap.Entry<String, Customer> entry : customers.entrySet())
		{
			outputCustomer.println(entry.getValue().fileOutString());
		}
		outputCustomer.close();
		
		File fileEmployee = new File("testoutEmployee.txt");
		PrintWriter outputEmployee = new PrintWriter(fileEmployee);
		for (HashMap.Entry<String, Employee> entry : employees.entrySet())
		{
			outputEmployee.println(entry.getValue().fileOutString());
		}
		outputEmployee.close();
		
		File fileRental = new File("testoutRental.txt");
		PrintWriter outputRental = new PrintWriter(fileRental);
		for (HashMap.Entry<String, RentalProperty> entry : rentalProperties.entrySet())
		{
			outputRental.println(entry.getValue().fileOutString());
		}
		outputRental.close();
		
		File fileSale = new File("testoutSale.txt");
		PrintWriter outputSale = new PrintWriter(fileSale);
		for (HashMap.Entry<String, SaleProperty> entry : saleProperties.entrySet())
		{
			outputSale.println(entry.getValue().fileOutString());
		}
		outputSale.close();
	}
}
