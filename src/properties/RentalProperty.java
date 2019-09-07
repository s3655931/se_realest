package properties;


import java.util.HashMap;
import se_realest.Main;

public class RentalProperty extends Property {

	private double rentalPrice; // weekly rental price for property (460.00)
	private int leaseLength; // months of rental lease
	private String tenantId; // customerId of tenant renting property
	private HashMap<String, Offer> rentalOffers = new HashMap<String,Offer>(); // collction of rental offers (applications)
	private HashMap<String, Inspection> rentalInspections = new HashMap<String, Inspection>(); // collection of inspections

	public RentalProperty(String id, String address, String suburb, String ownerId, double price, int leaseLength) {
		// the minimum required information from the owner to list a rental property:
		// address/suburb, price, lease length
		super(id, address, suburb, ownerId);
		this.rentalPrice = price;
		this.leaseLength = leaseLength;
	}

	// a property won't have a tenant upon creation, so call this when someone successfully rents it
	public boolean assignTenant(String tenantId, int leaseLength) {
		this.tenantId = tenantId;
		System.out.printf("Customer %s is now the tenant of %s,%s for %i months, paying %.2f per week.", 
				this.tenantId, this.address, this.suburb, this.leaseLength, this.rentalPrice);
		return true;
	}
	
	public void assignPropertyManager(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getTenant() {
		return this.tenantId;
	}
	
	public boolean compareSuburb(String suburb) {
		return this.suburb.toLowerCase().equals(suburb.toLowerCase());
	}
	
	public void newOffer(String customerId, double price) {
		this.rentalOffers.put(this.propertyId + customerId, new Offer(this.propertyId + customerId, customerId, price));
	}
	
	
	public HashMap<String,Offer> getOffers(){
		return this.rentalOffers;
	}
	
	public String printOffers() {
		StringBuilder result = new StringBuilder();
		this.rentalOffers.forEach((key, value) -> 
		result.append(value.toString() + "\n"));
		return result.toString();
	}

	public String addressString() {
		return (this.address + " " + this.suburb);
	}
	
	@Override
	public String toString() {
		return String.format("%s %s, owned by %s, $%,.2f weekly, %s%s", 
				this.address, this.suburb, Main.getCustomerName(this.ownerId), this.rentalPrice, 
				this.onMarket ? "Available for rent. " : "Unavailable for rent. ", this.onMarket ? this.rentalOffers.size() + " offers." : "");
	}
	
	public String fileOutString()
	{
		String text = this.propertyId + "," + this.address + "," + this.suburb + ","
				+ this.ownerId + "," + this.rentalPrice + "," + this.leaseLength;
		return text;
	}
}
