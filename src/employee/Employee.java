package employee;

public class Employee {

	private String name;
	private int id;
	// private int rate;

	public Employee(String name) {
		this.name = name;
	}
	
	public Employee(String name, int id) {
		this.name = name;
		this.id = id-2;
	}
	
	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [ id = " + id + ", name=" + name + "]";
	}

}
