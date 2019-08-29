package properties;
import customers.Customer;

public class Offer {
	
	private String customerId;
	private double price;
	
	public Offer(Customer customer, double price) {
		this.customerId = customer.getId();
		this.price = price;
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
}
