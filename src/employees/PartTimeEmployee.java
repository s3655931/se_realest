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

	// Hours
	// Modified pay
	
}
