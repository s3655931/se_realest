package properties;
import java.util.HashMap;

public class RentalProperty extends Property{
	
	private double rentalPrice; //weekly rental price for property (460.00)
	private int leaseLength; //months of rental lease
	private String tenantId; //customer id of tenant renting property
	HashMap<Integer,Offer> rentalOffers = new HashMap<Integer,Offer>(); //list of rental offers (applications)
	
	public RentalProperty(String address, String suburb, String owner, double price) {
		
		//the minimum required information from the owner to list a rental property: address/suburb, price
		super(address, suburb, owner);
		this.rentalPrice = price;
		
	}
	
	//a property won't have a tenant upon creation, so call this when someone successfully rents it
	public boolean assignTenant(String customerId, int leaseLength) {
		
		this.tenantId = customerId;
		System.out.printf("Customer %s is now the tenant of %s,%s for %i months, paying %.2f per week.",
							this.tenantId,this.address,this.suburb, this.leaseLength, this.rentalPrice);
		return true;
		
	}
	
	
	@Override
	public String toString() {
		return String.format("%s %s, owned by %s, $%.2f weekly.",this.address, this.suburb, this.owner, this.rentalPrice);
		
	}
	
	
}
