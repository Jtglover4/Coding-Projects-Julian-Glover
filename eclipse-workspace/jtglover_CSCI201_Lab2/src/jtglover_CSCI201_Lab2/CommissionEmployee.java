package jtglover_CSCI201_Lab2;

public class CommissionEmployee extends SalariedEmployee {

	protected double salesTotal;
	protected double commissionPercentage;
	
	public CommissionEmployee(String fName, String lName, String bday, int id, String jTitle, String comp,
			double annSalary, int sales, double commPercent) {
		super(fName, lName, bday, id, jTitle, comp, annSalary);
		salesTotal = sales;
		commissionPercentage = commPercent;
	}
	
	public double getAnnualSalary() {
		return annualSalary + salesTotal * commissionPercentage;
	}
	
}
