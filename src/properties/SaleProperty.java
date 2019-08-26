package properties;
import java.util.LinkedList;

public class SaleProperty extends Property{
	
	private float salePrice; //advertised asking price for property
	private String buyerId; //customer id of tenant renting property
	LinkedList<Offer> saleOffers = new LinkedList<Offer>(); 
	
	public SaleProperty(String address, String suburb, String owner, float price) {
		
		//the minimum required information from the owner to list a rental property: address/suburb, price
		super(address, suburb, owner);
		this.salePrice = price;
		
	}
	
	//a property won't have a tenant upon creation, so call this when someone successfully rents it
	public boolean assignNewOwner(String customerId, float price) {
		
		this.owner = customerId;
		System.out.printf("Customer %s is now the owner of %s,%s after paying $%.2f.",
							this.owner,this.address,this.suburb, price);
		return true;
		
	}
	
	
	
}

