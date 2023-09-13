package vahidy.assignment3;

public class Schedule {
	private int startTime;
	private String ticker;
	private int amount;
	
	public Schedule() {
		startTime = 0;
		ticker = "";
		amount = 0;
	}
	
	public Schedule(int sTime, String tckr, int amnt) {
		this.startTime = sTime;
		this.ticker = tckr;
		this.amount = amnt;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "time=" + getStartTime() + ",ticker=" + getTicker() + ",amount=" + getAmount();
	}
}
