package se_realest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashMap;
import customers.Customer;
import properties.*;

public class JoshTests {

    static HashMap<String, SaleProperty> saleProperties = new HashMap<String, SaleProperty>();
    static HashMap<String, SaleProperty> backupSaleProperties = new HashMap<String, SaleProperty>();
    static HashMap<String, RentalProperty> rentalProperties = new HashMap<String, RentalProperty>();
    static HashMap<String, Customer> customers = new HashMap<String, Customer>();
	
	@BeforeAll
	static void setup(){
		
		//Test1
		saleProperties.put("P100", new SaleProperty("P100","123 Fake St","Springfield","C100",500000));
		backupSaleProperties.put("P100", new SaleProperty("P100","123 Fake St","Springfield","C100",500000));
		//Test2
		saleProperties.put("P200", new SaleProperty("P200","200 Red Road","Hawthorn","C200",450000));	
		backupSaleProperties.put("P200", new SaleProperty("P200","200 Red Road","Hawthorn","C200",450000));
		//Test3
		customers.put("C300", new Customer("C300", "Old Owner", "old@owner.com", 1));
		customers.put("C301", new Customer("C301", "New Owner", "new@owner.com", 3));
		saleProperties.put("P300", new SaleProperty("P300","300 Big Terrace","Richmond","C300",650000));
		backupSaleProperties.put("P300", new SaleProperty("P300","300 Big Terrace","Richmond","C300",650000));
		//Test4
		rentalProperties.put("P400", new RentalProperty("P400","400 Silly Street","Funkytown","C450",500,12));
		customers.put("C400", new Customer("C400", "Early Bird", "early@bird.com", 4));
		customers.put("C401", new Customer("C401", "Slow Poke", "slow@poke.com", 4));
	
	}
	
	// Test1 - Positive Test of Editing a Sale Property (functionally identical to editing a Rental)
	@Test
	void TestSuccessfulEditProperty(){		
		// Actions
		editSaleProperty("P100", "321 Real St", "Shelbyville", "C100", 490000);

		// Actual Results
		String actualOutput = saleProperties.get("P100").fileOutString();
		String initialProperty = backupSaleProperties.get("P100").fileOutString();
		String editedProperty = saleProperties.get("P100").fileOutString();
		
		// Expected Results
		String expectedOutput = "P100,321 Real St,Shelbyville,C100,490000.0,true,null"; //fileoutString of hardcoded edit example
		
		// Asssertions
	    assertEquals(expectedOutput, actualOutput); //
	    assertNotEquals(initialProperty, editedProperty);
	    
	    // Conclusion
	    /* 
	     * This test passing proves the following:
	     * -The way our HashMaps are setup means our data isn't immutable
	     * -It's safe to use this editing method to update almost all of our software's objects
	     * -We can rely on the data inside the collection at any point, 
	     *  since it's instantly updated at time of editing
	     */
	}
	
	// Test2 - Negative Test of User Input (continuing the editProperty scenario for simplicity),
	// testing inputs that don't adhere to the requirements (e.g a street address requiring a number)
	@Test
	void TestUnsuccessfulEditProperty(){
		//     oldAddress = 200 Red Road
		String illegalInputAddress = "Green Road"; //simulating a user forgetting to input the street number when changing the street name
		
		// Asssertions
		editSaleProperty("P200", "200 Red Road", "Hawthorn", "C200", 450000); // testing that legal inputs work
        assertThrows(IllegalArgumentException.class,
        		    () -> editSaleProperty("P200", illegalInputAddress, "Hawthorn", "C200", 450000), // illegal input
		            "Expected IllegalArgumentException due to lack of number in newAddress, but one wasn't thrown.");
        String initialProperty = backupSaleProperties.get("P200").fileOutString();//these variables need to be declared
		String editedProperty = saleProperties.get("P200").fileOutString();       //after the action inside assertThrows()
        assertEquals(initialProperty, editedProperty);
        
	    // Conclusion
	    /* 
	     * This test passing proves the following:
	     * -Data collections won't be updated if Input Validation fails
	     * -It's safe to use this model of error handling for almost all of our software's user input, 
	     *  since all cases are functionally similar
	     */
    }
	
	// Test3 - Positive Test of Buying a Sale Property
	@Test
	void TestSuccessfulAssignNewOwner(){	
		
		String propertyId = "P300";
		String oldOwnerId = saleProperties.get(propertyId).getOwner();
		String newOwnerId = "C301";
		
		// Actions
		buySaleProperty(propertyId, newOwnerId, 645000);

		// Asssertions
	    assertEquals(saleProperties.get(propertyId).getOwner(), newOwnerId); //
	    assertTrue(customers.get(newOwnerId).getBoughtProperties().contains(propertyId));
	    assertFalse(customers.get(oldOwnerId).getSaleProperties().contains(propertyId));
	    
	    // Conclusion
	    /* 
	     * This test passing proves the following:
	     * -The interactions between Customers and Properties are healthy
	     * -No leftover data is hiding in objects, everything is updated when needed
	     * -Combining HashMap and ArrayList has no ill effects, 
	     *  the list of keys is used to access objects in the map as intended
	     */
	}
	
	// Test4 - Negative Test of Rental Application,
	// where while a user is applying, the property is taken off the market
	@Test
	void TestUnsuccessfulRentalApplication(){
		
		// Actions
		rentalProperties.get("P400").assignTenant("C400", 12); //EarlyBird is now tenant, property is taken off market
		
		// Asssertions
		assertThrows(RuntimeException.class,
        		    () -> applyForRental("P400","C401",500), // SlowPoke's application somehow still gets sent
		            "Expected RuntimeException due to the property going off the market "
		            + "while a customer was in the process of applying, but one wasn't thrown.");
        assertTrue(rentalProperties.get("P400").getOffers().size() == 0); //make sure the offer didn't sneak through
        
	    // Conclusion
	    /* 
	     * This test passing proves the following:
	     * -The rental application system is protected from edge cases (timing-wise)
	     * -Offers will only go through if the property is available
	     */
    }

	static void editSaleProperty(String propertyId, String newAddress, String newSuburb, String newCustomerId, double newPrice) throws IllegalArgumentException{
		if(!newAddress.matches(".*\\d.*")) { //regular expression to check if the string doesn't contain numbers
			throw new IllegalArgumentException();
		} else {
			saleProperties.put(propertyId, new SaleProperty(propertyId, newAddress, newSuburb, newCustomerId, newPrice));
		}
	}
	
	static void buySaleProperty(String propertyId, String newOwner, float price) {
		customers.get(saleProperties.get(propertyId).getOwner()).removeSaleProperty(propertyId);
		saleProperties.get(propertyId).assignNewOwner(newOwner, price);
		customers.get(newOwner).addBoughtProperty(propertyId);
	}
	
	static void applyForRental(String propertyId, String applicantId, float price) throws RuntimeException{
		if(!rentalProperties.get(propertyId).isAvailable()) {
			throw new RuntimeException();
		} else {
			rentalProperties.get(propertyId).newOffer(applicantId, 500);
		}
	}
}
	