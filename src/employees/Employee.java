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
	
	public String getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return String.format("%s, E-mail: %s", this.name, this.email);
	}
}
