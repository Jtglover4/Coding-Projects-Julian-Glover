package jtglover_CSCI201_Assignment1;

import java.util.List;

public class First {

private List<Datum> data = null;

public List<Datum> getData() {
return data;
}

public void setData(List<Datum> data) {
this.data = data;
}

public Datum getDatabyIndex(int index) {
	
	return this.data.get(index);
	
}

public int getSize() {
	return data.size();
}

}
