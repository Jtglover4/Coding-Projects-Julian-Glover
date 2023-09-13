package jtglover_CSCI201_Assignment2;

import java.util.Scanner;

public class Read {

	private Integer initTime;

	private String ticker;

	private Integer numStocks;
	
	private Integer price;
	
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer p) {
		this.price = p;
	}
	
}
