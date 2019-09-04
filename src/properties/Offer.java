package properties;

public class Offer {
	
	//id = propertyId + customerId (eg. P100C100)
	private String id;
	private String customerId;
	private double price;
	
	public Offer(String id, String customerId, double price) {
		this.id = id;
		this.customerId = customerId;
		this.price = price;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getCustomer() {
		return this.customerId;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public boolean setPrice(float newprice) {
		this.price = newprice;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("%s | Customer %s offers $%,.2f\n", this.id, this.customerId, this.price);
	}
}
