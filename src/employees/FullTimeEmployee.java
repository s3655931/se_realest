package employees;

public class FullTimeEmployee extends Employee {

	public FullTimeEmployee(String id,String name, String email, double salary) {
		super(id, name, email);
		this.salary = salary;
	}

	@Override
	public String fileOutString() {
		String text = this.id + "," + this.name + "," + this.email + "," + this.salary + ","
				+ getEmployeeType();
		
//		employees.put("E001", new FullTimeEmployee("E001", "Boss Man", "boss@serealest.com.au", 250000));
//		employees.get("E001").setEmployeeType(0);
		return text;
	}

	// Hours
	// Modified pay
	
}
