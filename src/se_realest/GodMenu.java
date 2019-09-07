package se_realest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GodMenu extends Menu{

	
	public GodMenu(String userId, int userType) {
		super(userId, userType);
	}
	
	public void godMenu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\nVeryCool™ S&E Real Estate Software Solution\n"
				  + "God Menu\n\n"
				+ "What would you like to do?\n"
				+ "1: Add, Remove, or View Properties\n"
				+ "2: Add, Remove, or View Customers\n"
				+ "3: View Employees\n"
				+ "4: Logout\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1:  propertyGodMenu();
				 break;
		case 2:  customerGodMenu();
				 break;
		case 3:  employeeGodMenu();
				 break;
		case 4:  Main.logout();
				 break;
		default: godMenu();
				 break;
		}
	}
	
	private void propertyGodMenu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\nWhat would you like to do?\n\n"
				+ "1: Add new Rental Property\n"
				+ "2: Add new Sale Property\n"
				+ "3: Remove Rental Property\n"
				+ "4: Remove Sale Property\n"
				+ "5: View all Rental Properties\n"
				+ "6: View all Sale Properties\n"
				+ "7: Assign Agent to Rental Property\n"
				+ "8: See Offers on a Sale Property\n"
				+ "9: See Rental Properties by Suburb\n"
				+ "0: Main Menu\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1:  Main.newRentalProperty();
				 propertyGodMenu();
				 break;
		case 2:  Main.newSaleProperty();
				 break;
		case 5:  System.out.print(Main.getAllRentals());
				 propertyGodMenu();
				 break;
		case 6:  System.out.print(Main.getAllSales());
		 		 propertyGodMenu();
		 		 break;
		case 7:  Main.assignPropertyManager();
				 propertyGodMenu();
				 break;
		case 8:  System.out.println("Please enter Property ID to see Offers:");
				 String id = sc.next();
				 System.out.print(Main.getSaleOffers(id));
				 propertyGodMenu();
				 break;
		case 9:  System.out.println("Please enter suburb you'd like to search for Rental Properties:");
		 	     String suburb = sc.next();
		 	     System.out.print(Main.findRentalsBySuburb(suburb));
		 	     propertyGodMenu();
		case 0:	 godMenu();
		default: propertyGodMenu();
				 break;
		}
	}
	
	private void customerGodMenu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\nWhat would you like to do?\n\n"
				+ "1: Add new Customer\n"
				+ "2: Remove Customer\n"
				+ "3: View all Customers\n"
				+ "4: Main Menu\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1:  Main.newCustomer();
				 break;
		case 3:  System.out.print(Main.getAllCustomers());
				 customerGodMenu();
				 break;
		case 4:  godMenu();
		default: customerGodMenu();
				 break;
		}
	}
	
	private void employeeGodMenu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\nWhat would you like to do?\n\n"
				+ "1: Add new Employee\n"
				+ "2: Remove Employee\n"
				+ "3: View all Employees\n"
				+ "4: Main Menu\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1:  Main.newEmployee();
				 break;
		case 3:  System.out.print(Main.getAllEmployees());
		 		 employeeGodMenu();
		 		 break;
		case 4:  godMenu();
		default: employeeGodMenu();
				 break;
		}
	}
}
