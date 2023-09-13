package jtglover_CSCI201_Assignment2;

import java.util.List;

public class Stock {
	
	private List<Datum> data = null;

	public List<Datum> getData() {
	return data;
	}
	
	public Datum getDatabyIndex(int index) {
		
		return this.data.get(index);
		
	}

	public void setData(List<Datum> data) {
	this.data = data;
	}
	
	public int getSize() {
		
		return this.data.size();
		
	}
	
}
