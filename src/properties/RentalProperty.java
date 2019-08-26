package properties;
import java.util.LinkedList;

public class RentalProperty extends Property{
	
	private float rentalPrice; //weekly rental price for property (460.00)
	private int leaseLength; //months of rental lease
	private String tenantId; //customer id of tenant renting property
	LinkedList<Offer> rentalOffers = new LinkedList<Offer>(); //list of rental offers (applications)
	
	public RentalProperty(String address, String suburb, String owner, float price) {
		
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
	
	
	
}
