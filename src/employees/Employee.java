package employees;

import java.util.ArrayList;

public abstract class Employee {

	protected String id;
	protected String name;
	protected String email;
	protected double salary;
	protected int employeeType;
	/* Employee Types:
	 * 0: God
	 * 5: SalesConsultant
	 * 6: PropertyManager
	 * 7: BranchManager
	 * 8: BranchAdministrator
	 */
	
//	enum employeeType {
//		God,
//		SalesConsultant,
//		PropertyManager,
//		BranchManager,
//		BranchAdmin
//	}
	
	protected ArrayList<String> managedRentalProperties = new ArrayList<String>();
	protected ArrayList<String> consultingSaleProperties = new ArrayList<String>();
	
	public Employee(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email= email;
	}
	
	public void addRentalProperty(String propertyId) {
		this.managedRentalProperties.add(propertyId);
	}
	
	public void addSaleProperty(String propertyId) {
		this.consultingSaleProperties.add(propertyId);
	}
	
	public String getName() {
		return this.name;
	}
	public String getId() {
		return this.id;
	}
	public boolean compareLogin(String email) {
		return this.email.equals(email);
	}
	public int getEmployeeType() {
		// check for null
		return this.employeeType;
	}
	public void setEmployeeType(int employeeType) {
		this.employeeType = employeeType;
	}
	@Override
	public String toString() {
		return String.format("%s, E-mail: %s", this.name, this.email);
	}
	
	public boolean approveHours(PartTimeEmployee tempEmp) {
		
		if ((getEmployeeType() == 7)) {
			double tempSal = tempEmp.getSalary();
			double tempHours = tempEmp.getHoursWorked();
			
			double newPay = (tempSal/12) * (tempHours/38);
			
			tempEmp.changeMonthPay(newPay);
			
			return true;
		}
		
		return false;
	}
	
	public ArrayList<String> getRentalProperties() {
		return managedRentalProperties;
	}
	
	public ArrayList<String> getSaleProperties() {
		return consultingSaleProperties;
	}

	public abstract String fileOutString();

}
