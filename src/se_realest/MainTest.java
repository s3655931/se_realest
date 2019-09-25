package se_realest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import customers.Customer;
import properties.Offer;
import properties.RentalProperty;

class MainTest {
	
	@BeforeAll
	static void makecust()
	{
		Main.customers.put("C101", new Customer("C101", "Donald Trump", "trump@donaldtrump.com", 1));
		Main.rentalProperties.put("P098", new RentalProperty("P098","742 Evergreen Terrace", "Springfield", "C101", 800.00, 24));
		
	}
	
	
	//Unsuccessful test for removing property and inputting incorrect ID
	@Test
	void testremoveRental()
	{
		Menu menu = new Menu("C099",1);
		try
		{
			menu.removeRentalProperty();
		}
		
		catch(NullPointerException e)
		{
			throw new NullPointerException("Property does not exist");
		}
		
		
		assertTrue(Main.rentalProperties.containsKey("P098"));
	}
	
	//Unsuccessful test for adding customer that will probably overwrite another customer
	@Test
	void testAddcustomer() throws Exception
	{
		String oldName = Main.customers.get("C101").getName();
		Main.newCustomer();
		String newName = Main.customers.get("C101").getName();
		
		if(!newName.equals(oldName))
		{
			throw new Exception("Incorrect names");
		}
		
		assertEquals(newName,"Donald Trump");
	}
	
	//Successful application for rental property and check if the property's offer is under the 
	//customer ID
	@Test
	void testApplyRental()
	{
		Main.rentalProperties.get("P098").newOffer("C101", 321); 
		Main.customers.get("C101").makeOffer("P098", "C101");
		String custID = Main.rentalProperties.get("P098").getOffers().get("P098C101").getCustomer();
		assertEquals(custID,"C101");
	}

	//Successful test for showing that finding properties in suburbs work
	@Test
	void testSearchBySuburb()
	{
		String firstStr = Main.findRentalsBySuburb("Springfield");
		Main.rentalProperties.put("P014", new RentalProperty("P014","42 Wallaby Way", "Springfield", "C101", 550.00, 12));
		String secondStr = Main.findRentalsBySuburb("Springfield");
		Boolean checkStr = firstStr.equals(secondStr); 
		
		assertFalse(checkStr);
	}
	
	
}
