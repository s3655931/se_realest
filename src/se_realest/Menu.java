package se_realest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

	private String userId;
	private int userType;
	/* User Types:
	 * 0: God
	 * 1: Vendor
	 * 2: Landlord
	 * 3: Buyer
	 * 4: Renter
	 * 5: SalesConsultant
	 * 6: PropertyManager
	 * 7: BranchManager
	 * 8: BranchAdministrator
	 */
	
	/*TODO: Incorporate a way to dynamically populate the Menu items depending on the booleans inside the customer/employee.
	 * Eg. If a customer is a landlord but also a prospective buyer, his menu should include all landlord items 
	 * (add, edit properties etc), but also include buyer items (search in suburb, make offer etc) */
	
	public Menu(String userId, int userType) {
		this.userId = userId;
		this.userType = userType;
	}
	
	public void begin() throws FileNotFoundException, IOException {
		System.out.println("\nWelcome " + Main.getCustomerName(userId) + ".");
		switch(this.userType) {
		case 1: vendorMenu();
				break;
		case 2: landlordMenu();
				break;
		case 3: buyerMenu();
				break;
		case 4: renterMenu();
				break;
		}
	}

	private void vendorMenu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\nVeryCool™ S&E Real Estate Software Solution\n"
				  + "--Vendor Menu--\n\n"
				+ "What would you like to do?\n"
				+ "1: Add Sale Property\n"
				+ "2: Remove Sale Property\n"
				+ "3: Edit Sale Property\n"
				+ "4: View all Sale Properties\n"
				+ "5: View Offers on a Sale Property\n"
				+ "6: Logout\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1: newSaleProperty();
				vendorMenu();
				break;
		case 2: removeSaleProperty();
				vendorMenu();
				break;
		case 3: editSaleProperty();
				vendorMenu();
				break;
		case 4: viewAllSaleProperties();
				vendorMenu();
				break;
		case 5: viewOffersOnSaleProperty();
				vendorMenu();
				break;
		case 6: logout();
		default:vendorMenu();
				break;
		}
	}
	
	private void newSaleProperty() {
		Scanner sc = new Scanner(System.in);
		String address;
		String suburb;
		double price;
		System.out.printf("Please enter details for new SALE PROPERTY:\n\n"
				+ "Address (eg. \"123 Fake St\"):");
		address = sc.nextLine();
		System.out.print("Suburb:");
		suburb = sc.nextLine();
		System.out.print("Asking price of Property in dollars (eg. 600000):");
		price = sc.nextInt();
		String id = Main.newPropertyId();
		System.out.print(Main.addSaleProperty(id, address, suburb, this.userId, price));
	}
	
	private void removeSaleProperty() {
		Scanner sc = new Scanner(System.in);
		String target;
		System.out.printf("Please enter ID of Property you'd like to unlist:");
		target = sc.nextLine();
		Main.removeSaleProperty(target);
	}

	private void editSaleProperty() {
		Scanner sc = new Scanner(System.in);
		String target;
		System.out.printf("Please enter ID of Property you'd like to edit:");
		target = sc.nextLine();
		Main.editSaleProperty(target,this.userId);
	}
	
	private void viewAllSaleProperties() {
		System.out.print(Main.getSalesBy(this.userId));
	}

	private void viewOffersOnSaleProperty() {
		Scanner sc = new Scanner(System.in);
		String target;
		System.out.printf("Please enter Property ID to view its purchase offers:");
		target = sc.nextLine();
		Main.editSaleProperty(target,this.userId);
	}

	public void landlordMenu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\n\nVeryCool™ S&E Real Estate Software Solution\n"
				  + "--Landlord Menu--\n\n"
				+ "What would you like to do?\n"
				+ "1: Add Rental Property\n"
				+ "2: Remove Rental Property\n"
				+ "3: Edit Rental Property\n"
				+ "4: View all Rental Properties\n"
				+ "5: View Offers on a Rental Property\n"
				+ "6: Logout\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1: newRentalProperty();
				landlordMenu();
				break;
		case 2: removeRentalProperty();
				landlordMenu();
				break;
		case 3: editRentalProperty();
				landlordMenu();
				break;
		case 4: viewLandlordsRentalProperties();
				landlordMenu();
				break;
		case 5: viewOffersOnRentalProperty();
				landlordMenu();
				break;
		case 6: logout();
		default:landlordMenu();
				break;
		}
	}
	
	private void newRentalProperty() {
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
		String id = Main.newPropertyId();
		System.out.print(Main.addRentalProperty(id, address, suburb, this.userId, price, leaseLength));
	}
	
	private void removeRentalProperty() {
		Scanner sc = new Scanner(System.in);
		String target;
		System.out.printf("Please enter ID of Property you'd like to unlist:");
		target = sc.nextLine();
		Main.removeRentalProperty(target);
	}

	private void editRentalProperty() {
		Scanner sc = new Scanner(System.in);
		String target;
		System.out.printf("Please enter ID of Property you'd like to edit:");
		target = sc.nextLine();
		Main.editRentalProperty(target,this.userId);
	}
	
	private void viewLandlordsRentalProperties() {
		System.out.print(Main.getRentalsBy(this.userId));
	}

	private void viewOffersOnRentalProperty() {
		Scanner sc = new Scanner(System.in);
		String target;
		System.out.printf("Please enter Property ID to view its rental offers:");
		target = sc.nextLine();
		Main.editSaleProperty(target,this.userId);
	}

	private void buyerMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\n\nVeryCool S&E Real Estate Software Solution\n"
				  + "--Buyer Menu--\n\n"
				+ "What would you like to do?\n"
				+ "1: Browse all Properties for Sale\n"
				+ "2: Browse Properties for Sale by Suburb\n"
				+ "3: Apply for a Properties for Sale\n"
				+ "4: View your Sale Property Applications\n"
				+ "5: Logout\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input)
		{
		case 1:
//			System.out.print("dsad");
			break;
			
		case 2:
			break;
			
		case 3:
			break;
			
		case 4:
			break;
		}
	}
	
	
	private void renterMenu() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.printf(
				  "\n\nVeryCool™ S&E Real Estate Software Solution\n"
				  + "--Renter Menu--\n\n"
				+ "What would you like to do?\n"
				+ "1: Browse all Rental Properties\n"
				+ "2: Browse Rental Properties by Suburb\n"
				+ "3: Apply for a Rental Property\n"
				+ "4: View your Rental Applications\n"
				+ "5: Logout\n\n"
				+ "Enter Selection: ");
		int input = sc.nextInt();
		switch(input) {
		case 1: System.out.print(Main.getAvailableRentals());
				renterMenu();
				break;
		case 2: findRentalsBySuburb();
				renterMenu();
				break;
		case 3: applyForRental();
				renterMenu();
				break;		
		case 4: System.out.print(Main.getOffersBy(userId));
				renterMenu();
				break;
		case 5: logout();
		default:renterMenu();
				break;
		}
	}
	
	private void findRentalsBySuburb() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter suburb you'd like to search for Rental Properties:");
	    String suburb = sc.next();
	    System.out.print(Main.findRentalsBySuburb(suburb));
	}
	
	private void applyForRental() {
		Main.applyForRental(userId);
	}
	
	private void logout() throws FileNotFoundException, IOException {
		Main.logout();
	}
}
