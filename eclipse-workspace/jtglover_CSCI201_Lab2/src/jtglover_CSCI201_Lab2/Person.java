package jtglover_CSCI201_Lab2;

public class Person {
	
	protected String firstName;
	protected String lastName;
	protected String birthdate;
	
	public Person(String fName, String lName, String bday) {
		firstName = fName;
		lastName = lName;
		birthdate = bday;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getBirthdate() {
		return birthdate;
	}
	
	
}
