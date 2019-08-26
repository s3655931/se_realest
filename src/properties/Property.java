package properties;
import se_realest.*;

public class Property {

	protected String propertyId; //property id (100)
	protected String address; //address (123 Fake St)
	protected String suburb; //suburb (Springfield)
	
	protected String employeeId; //assigned real estate agent
	protected boolean onMarket = false; //includes rental market and sale market
	protected String owner; //current customerid, update owner id upon purchase
	
	public Property(String address, String suburb, String owner) {
		
		this.propertyId = Main.newPropertyId();
		this.address = address;
		this.suburb = suburb;
		this.owner = owner;
		
	}
	
	public void assignPropertyManager(String employeeId) {
		
		this.employeeId = employeeId;
		
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
