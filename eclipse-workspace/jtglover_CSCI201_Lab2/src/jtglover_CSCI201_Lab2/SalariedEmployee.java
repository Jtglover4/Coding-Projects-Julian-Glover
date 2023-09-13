package jtglover_CSCI201_Lab2;

public class SalariedEmployee extends Employee {

	
protected double annualSalary;
	
	public SalariedEmployee(String fName, String lName, String bday, int id, String jTitle, String comp,
			double annSalary) {
		super(fName, lName, bday, id, jTitle, comp);
		annualSalary = annSalary;
	}
	
	public double getAnnualSalary() {
		return annualSalary;
	}
	
}
