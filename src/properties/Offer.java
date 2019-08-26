package properties;

public class Offer {
	
	private String customerId;
	private float price;
	
	public String getCustomer() {
		return this.customerId;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public boolean setPrice(float newprice) {
		this.price = newprice;
		return true;
	}
}
