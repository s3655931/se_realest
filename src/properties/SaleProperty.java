package properties;
import java.util.HashMap;
import customers.Customer;

public class SaleProperty extends Property{
	
	private double salePrice; //advertised asking price for property
//	private boolean hasSection32 = false;
	HashMap<Integer,Offer> saleOffers = new HashMap<Integer,Offer>(); 
	
	public SaleProperty(String id, String address, String suburb, Customer owner, double price) {
		//the minimum required information from the owner to list a rental property: address/suburb, price
		super(id, address, suburb, owner);
		this.salePrice = price;
	}
	
    public boolean assignNewOwner(Customer customer, float price) {
		this.owner = customer;
		System.out.printf("Customer %s is now the owner of %s,%s after paying $%.2f.",
							this.owner,this.address,this.suburb, price);
		return true;
	}
	
	
	
}

