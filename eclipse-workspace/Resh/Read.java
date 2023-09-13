package jtglover_CSCI201_Assignment3;

public class Read {
	
	private Integer initTime;

	private String ticker;

	private Integer numStocks;
	
	private double price;
	
	public Integer getInitTime() {
		return initTime;
	}

	public void setInitTime(Integer time) {
		this.initTime = time;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Integer getNumStocks() {
		return numStocks;
	}

	public void setNumStocks(Integer numstocks) {
		this.numStocks = numstocks;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double p) {
		this.price = p;
	}
	
}
