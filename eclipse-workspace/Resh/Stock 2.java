package vahidy.assignment3;

public class Stock {
	private double c;
	private String name;
	private long t;
	
	public double getCurrentPrice() {
		return c;
	}
	
	public long getTime() {
		return t;
	}
	
	public String getTicker() {
		return name;
	}
	
	public void setTicker(String ticker) {
		this.name = ticker;
	}
	
	@Override
	public String toString() {
		return "ticker=" + name + ",current_price=" + c;
	}
}
