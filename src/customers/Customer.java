package customers;

class Customer {

	private String name;
	private String email;
	private String id;
	
	private LandLord landlord;
	private Vendor vendor;
	private Tenant tenant;
	private Buyer buyer;
	
	Customer(String name, String email)
	{
		this.name = name;
		this.email = email;
		
		//id with regex
	}
	
}
