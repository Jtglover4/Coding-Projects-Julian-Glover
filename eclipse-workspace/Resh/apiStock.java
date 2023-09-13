package CS201_A3;

public class apiStock {
	 private double c;
	    private String name;
	    private long t;

	    public double getPriceNow() {
	        return c;

	    }

	    public double getTime() {
	        return t;
	    }

	    public String getTicker() {
	        return name;
	    }

	    public void setTicker(String name) {
	        this.name = name;

	    }

}
