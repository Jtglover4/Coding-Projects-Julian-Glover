package jtglover_CSCI201_Assignment2;

import java.util.List;

public class Setup extends Read{
	
	private List<Read> data = null;

	public List<Read> getData() {
	return data;
	}

	public void setData(List<Read> data) {
	this.data = data;
	}
	
	public Read getDatabyIndex(int index) {
		
		return this.data.get(index);
		
	}

}
