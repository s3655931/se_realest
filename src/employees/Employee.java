package employees;

public abstract class Employee {

	private String id;
	private String name;
	private String email;
	
	public Employee(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email= email;
	}
	
	public String getName() {
		return this.name;
	}
}
