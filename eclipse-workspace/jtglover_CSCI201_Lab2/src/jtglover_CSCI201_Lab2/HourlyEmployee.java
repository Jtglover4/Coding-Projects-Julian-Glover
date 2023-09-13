package jtglover_CSCI201_Lab2;

public class HourlyEmployee extends Employee {

	protected double hourlyRate;
	protected double numberHoursPerWeek;
	
	public HourlyEmployee(String fName, String lName, String bday, int id, String jTitle, String comp,
			double hRate, double numHours) {
		super(fName, lName, bday, id, jTitle, comp);
		hourlyRate = hRate;
		numberHoursPerWeek = numHours;
	}
	
	public double getAnnualSalary() {
		return hourlyRate * numberHoursPerWeek * 52;
	}
	
}
