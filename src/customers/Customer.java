package customers;

import java.util.ArrayList;
import java.util.HashMap;
import properties.Offer;

public class Customer {

	private String id;
	private String name;
	private String email;
	private int customerType;
	/* Customer Types:
	 * 1: Vendor
	 * 2: Landlord
	 * 3: Buyer
	 * 4: Renter
	 */
	
	public boolean vendor = false;
	public boolean landlord = false;
	public boolean buyer = false;
	public boolean renter = false;

	private ArrayList<String> saleProperties;
	private ArrayList<String> rentalProperties;
	private ArrayList<String> boughtProperties;
	private String rentingProperty;
	
	private ArrayList<String> madeOffers = new ArrayList<String>();

	public Customer(String id, String name, String email, int customerType)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		switch(customerType) {
			case 1: this.vendor = true;
					this.customerType = 1;
			 		saleProperties = new ArrayList<String>();
					break;
			case 2: this.landlord = true;
					this.customerType = 2;
					rentalProperties = new ArrayList<String>();
					break;
			case 3: this.buyer = true;
					this.customerType = 3;
					boughtProperties = new ArrayList<String>();
					break;
			case 4: this.renter = true;
					this.customerType = 4;
					break;
			default:break;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public boolean compareLogin(String email) {
		return this.email.equals(email);
	}
	
	//add given property to this vendor's portfolio
	public void addSaleProperty(String propertyId) {
		this.vendor = true;
		this.saleProperties.add(propertyId);
	}
	//add given property to this landlord's portfolio
	public void addRentalProperty(String propertyId) {
		this.landlord = true;
		this.rentalProperties.add(propertyId);
	}
	//note that this customer purchased a property
	public void addBoughtProperty(String propertyId) {
		this.buyer = true;
		this.boughtProperties.add(propertyId);
	}
	//mark this customer as a tenant of the given property
	public void makeTenantOf(String propertyId) {
		this.renter = true;
		this.rentingProperty = propertyId;
	}
	
	public ArrayList<String> getSaleProperties() {
		return this.saleProperties;
	}
	
	public ArrayList<String> getRentalProperties() {
		return this.rentalProperties;
	}
	
	public int getCustomerType() {
		return this.customerType;
	}
	
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}
	
	//builds a string representing all the roles of this customer
	public String getAllCustomerTypes() {
		return((this.vendor ? "1" : "") + (this.landlord ? "2" : "") + (this.buyer ? "3" : "") + (this.renter ? "4" : ""));
	}
	
	public void makeOffer(String propertyId, String customerId) {
		this.madeOffers.add(propertyId + customerId);
	}
	
	public ArrayList<String> getOffers() {
		return this.madeOffers;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name + " E-Mail: " + this.email);
		sb.append(landlord ? " Landlord." : "");
		sb.append(vendor ? " Vendor." : "");
		sb.append(renter ? " Renter." : "");
		sb.append(buyer ? " Prospective Buyer." : "");
		return sb.toString();
	}
	
}
