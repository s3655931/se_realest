package employees;

public class PartTimeEmployee extends Employee {

	private double monthlyHours;
	
	public PartTimeEmployee(String id,String name, String email, double salary) {
		super(id, name, email);
		this.salary = salary;
	}
	
	public void setHoursWorked(double hoursWorked) {
		this.monthlyHours = hoursWorked;
	}
	
	public double getHoursWorked() {
		return this.monthlyHours;
	}

	@Override
	public String fileOutString() {
		String text = this.id + "," + this.name + "," + this.email + "," + this.salary;
		return text;
	}
	
//	employees.put("E097", new PartTimeEmployee("E097","Michael Bay", "bang@boom.com", 60000));

	// Hours
	// Modified pay
	
}
