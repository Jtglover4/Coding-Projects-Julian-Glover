package jtglover_CSCI201_Assignment3;

import java.util.List;

public class SetupTwo extends ReadTwo{
	
	private List<ReadTwo> data = null;

	public List<ReadTwo> getData() {
	return data;
	}

	public void setData(List<ReadTwo> data) {
	this.data = data;
	}
	
	public ReadTwo getDatabyIndex(int index) {
		
		return this.data.get(index);
		
	}
	
}
