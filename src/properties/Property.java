package properties;
import se_realest.*;

public class Property {

	protected String propertyId; //property id (100)
	protected String address; //address (123 Fake St)
	protected String suburb; //suburb (Springfield)
	
	protected String employeeId; //assigned real estate agent's ID
	protected boolean onMarket = false; //includes rental market and sale market
	protected String ownerId; //current customerid, update owner id upon purchase
	
	public Property(String id, String address, String suburb, String ownerId) {
		this.propertyId = id;
		this.address = address;
		this.suburb = suburb;
		this.ownerId = ownerId;
	}
		
    public boolean assignNewOwner(String ownerId, float price) {
		this.ownerId = ownerId;
		System.out.printf("Customer %s - %s is now the owner of %s,%s after paying $%.2f.",
							this.ownerId, Main.getCustomerName(ownerId), this.address,this.suburb, price);
		return true;
	}
    
    public void makeAvailable() {
    	this.onMarket = true;
    }
    
    public void makeUnavailable() {
    	this.onMarket = false;
    }
    
    public String getId() {
    	return this.propertyId;
    }
    
    public boolean isAvailable() {
    	return this.onMarket;
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
