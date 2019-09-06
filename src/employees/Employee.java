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
	
	private boolean salesConsultant = false;
	private boolean propertyManager = false;
	private boolean branchManager = false;
	private boolean branchAdmin = false;
	
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
		return this.employeeType;
	}
	public void setEmployeeType(int employeeType) {
		this.employeeType = employeeType;
	}
	@Override
	public String toString() {
		return String.format("%s, E-mail: %s", this.name, this.email);
	}
}
