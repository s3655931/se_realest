package properties;
import java.util.HashMap;

import se_realest.Main;

public class SaleProperty extends Property{
	
	private double salePrice; //advertised asking price for property
//	private boolean hasSection32 = false;
	public HashMap<String, Offer> saleOffers = new HashMap<String, Offer>();
	private HashMap<String, Inspection> saleInspections = new HashMap<String, Inspection>(); // collection of inspections
	
	public SaleProperty(String id, String address, String suburb, String ownerId, double price) {
		//the minimum required information from the owner to list a rental property: address/suburb, price
		super(id, address, suburb, ownerId);
		this.salePrice = price;
		this.onMarket = true;
	}
	
	public void assignSalesConsultant(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public void newOffer(String customerId, int price) {
		this.saleOffers.put(this.propertyId + customerId, new Offer(this.propertyId + customerId, customerId, price));
		System.out.printf("Customer %s has successfully applied for %s %s with an offer of $%d.",customerId,this.address, this.suburb, price);
	}
	
	public HashMap<String,Offer> getOffers(){
		return this.saleOffers;
	}
	
	public String printOffers() {
		StringBuilder result = new StringBuilder();
		this.saleOffers.forEach((key, value) -> 
		result.append(value.toString() + "\n"));
		return result.toString();
	}
	
    @Override
    public String toString() {
    	return String.format("%s %s, owned by %s, asking $%,.2f, %s%s%s", 
				this.address, this.suburb, Main.getCustomerName(this.ownerId), this.salePrice, 
				this.onMarket ? "Available for purchase. " : "Unavailable for purchase. ", 
				this.onMarket ? this.saleOffers.size() + " offer(s). " : "", 
				this.onMarket ? this.saleInspections.size() + " inspection(s) scheduled." : "");
    }

	public String fileOutString() {
		String text = this.propertyId + "," + this.address + "," + this.suburb + ","
				+ this.ownerId + "," + this.salePrice + "," + this.onMarket + ","
				+ this.employeeId;
		
		return text;
	}

}

