package vahidy.assignment3;

public class Trader {
	private int serialNumber;
	private int initialBalance;
	
	public Trader(int serialNumber, int initialBalance) {
		this.serialNumber = serialNumber;
		this.initialBalance = initialBalance;
	}
	
	public int getSerialNumber() {
		return this.serialNumber;
	}
	
	public int getInitialBalance() {
		return this.initialBalance;
	}
}
