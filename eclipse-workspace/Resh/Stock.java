package CS201_A3;

import java.util.Date;

public class Stock {
	private int time;

	private String ticker;

	private Date date;
	private int quantity;

	public Stock(int time, String ticker, int quantity) {
		this.time = time;
		this.ticker = ticker;
		this.quantity = quantity;

	}

	public int getTime() {
		return time;
	}

	public String getTicker() {
		return ticker;
	}

	public int getQuantity() {
		return quantity;
	}

}
