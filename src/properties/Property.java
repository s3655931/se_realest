package properties;
import customers.Customer;
import employees.Employee;
import se_realest.*;

public class Property {

	protected String propertyId; //property id (100)
	protected String address; //address (123 Fake St)
	protected String suburb; //suburb (Springfield)
	
	protected Employee employee; //assigned real estate agent
	protected boolean onMarket = false; //includes rental market and sale market
	protected Customer owner; //current customerid, update owner id upon purchase
	
	public Property(String id, String address, String suburb, Customer owner) {
		this.propertyId = Main.newPropertyId();
		this.address = address;
		this.suburb = suburb;
		this.owner = owner;
	}
	
	public void assignPropertyManager(Employee employee) {
		this.employee = employee;
	}
	
	// ID
	// Employee ID
	// Address
	// Suburb
	// Type - unit, etc
	// Rent/Buy
	// Minimum Price
	// Section 32
	
	//For sale/rent?
	
}
