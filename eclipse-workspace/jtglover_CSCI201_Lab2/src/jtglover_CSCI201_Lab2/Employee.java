package jtglover_CSCI201_Lab2;

public abstract class Employee extends Person {

	protected int employeeID;
	protected String jobTitle;
	protected String company;
	
	public Employee(String fName, String lName, String bday, int id, String jTitle, String comp) {
		super(fName, lName, bday);
		employeeID = id;
		jobTitle = jTitle;
		company = comp;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public String getCompany() {
		return company;
	}
	
	public abstract double getAnnualSalary();
	
}
