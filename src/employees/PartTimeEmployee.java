package employees;

public class PartTimeEmployee extends Employee {

	double hourlyPay;
	int weeklyHours;
	
	public PartTimeEmployee(String id,String name, String email, double hourlyPay, int weeklyHours) {
		super(id, name, email);
		this.hourlyPay = hourlyPay;
		this.weeklyHours = weeklyHours;
	}

	// Hours
	// Modified pay
	
}
