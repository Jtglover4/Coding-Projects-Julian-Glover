package CS201_A3;

public class Trader {
	// this just assigns name and stock per a certain trader
    // takes ID and budget

    private int id;
    private double budget;

    public Trader(int id, double budget) {
        this.id = id;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public double getBudget() {
        return budget;
    }
}
