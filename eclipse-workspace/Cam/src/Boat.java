import java.util.ArrayList;

public class Boat {

    private String boatType;
    private String boatName;
    private int yearMan;
    private String makeModel;
    private int boatLength;
    private double boatPrice;
    private double boatExpenses;

    public static ArrayList<Boat> boatList = new ArrayList<Boat>();



    public Boat(String boatType, String boatName, int yearMan, String makeModel, int boatLength, double boatPrice, double boatExpenses){

        this.boatType = boatType;
        this.boatName = boatName;
        this.yearMan = yearMan;
        this.makeModel = makeModel;
        this.boatLength = boatLength;
        this.boatPrice = boatPrice;
        this.boatExpenses = boatExpenses;

    }

    public Boat() {
    }

    public String getBoatType(){
        return boatType;
    }
    public void setBoatType(String boatType){
        this.boatType = boatType;
    }

    public String getBoatName() {
        return boatName;
    }
    public void setBoatName(String boatName){
        this.boatName = boatName;
    }

    public int getYearMan(){
        return yearMan;
    }
    public void setYearMan(int yearMan){
        this.yearMan = yearMan;
    }

    public String getMakeModel(){
        return makeModel;
    }
    public void setMakeModel(String makeModel){
        this.makeModel = makeModel;
    }

    public int getBoatLength(){
        return boatLength;
    }
    public void setBoatLength(int boatLength){
        this.boatLength = boatLength;
    }

    public double getBoatPrice(){
        return boatPrice;
    }
    public void setBoatPrice(double boatPrice){
        this.boatPrice = boatPrice;
    }

    public double getBoatExpenses(){
        return boatExpenses;
    }
    public void setBoatExpenses(double v){
        this.boatExpenses = v;
    }


    public String toString(){
        return boatType + boatName + yearMan + makeModel + boatLength + " : " + "Paid $" + boatPrice + " : " + "Spent $" + boatExpenses;
    }


}//end of boat class