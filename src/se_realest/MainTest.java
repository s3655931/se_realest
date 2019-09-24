package se_realest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import customers.Customer;
import employees.Employee;
import properties.RentalProperty;
import properties.SaleProperty;

class MainTest {
	
	private static HashMap<String, RentalProperty> rentalProperties = new HashMap<String, RentalProperty>();
	private static HashMap<String, SaleProperty> saleProperties = new HashMap<String, SaleProperty>();
	private static HashMap<String, Customer> customers = new HashMap<String, Customer>();
	private static HashMap<String, Employee> employees = new HashMap<String, Employee>();
	
	@Before
	void makecust()
	{
		customers.put("C099", new Customer("C099", "Donald Trump", "trump@donaldtrump.com", 1));
	}
	
	
	@Test
	void testMain()
	{
		Menu.removeRentalProperty();
		
	}

}
