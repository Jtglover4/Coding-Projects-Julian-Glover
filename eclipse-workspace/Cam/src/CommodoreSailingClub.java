import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class CommodoreSailingClub {
    
	public static String CSV_PATH = "";
	public static List<Boat> boatList = new ArrayList<Boat>();

    public static void main(String[] args) {
    	
    	if(args[0].compareTo(".csv") == 0){
    		try {
				CSV_PATH = "FleetData.csv";
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else {
    		
    		try {
    			FileInputStream fi = new FileInputStream("FleetData.db");
    			ObjectInputStream o = new ObjectInputStream(fi);
    			Object obj = null;
    			while((obj = o.readObject()) != null) {
    				try {
						boatList.add((Boat)obj);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			o.close();
    			
    			
    		}catch(Exception e){
    			
    		}
    		
    	}
        Scanner keyboard = null;
        
//        try {
//          File file = new File(CSV_PATH);
//          FileReader fr = new FileReader(file);
//          BufferedReader br = new BufferedReader(fr);
//          String line = "";
//          while ((line = br.readLine()) != null) {
//              String[] newBoat = line.split(",");
//              Boat boat = new Boat(newBoat[0], newBoat[1], Integer.parseInt(newBoat[2]), newBoat[3], Integer.parseInt(newBoat[4]), Double.parseDouble(newBoat[5]), 0);
//              boatList.add(boat);
//          }
//      }
//      catch (Exception e) {
//          e.printStackTrace();
//      }
  //   try {   
        File file = new File(CSV_PATH);
//		
		Scanner sca = null;
//		
//		try {
//			sca = new Scanner(file);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		sca.useDelimiter("\n");
//		sca.useDelimiter(",");
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        List<String> lines = new ArrayList<>();
        String grab = null;
        try {
			while ((grab = reader.readLine()) != null) {
			    lines.add(grab);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println(lines.get(0));
        System.out.println(lines.size());
        String[] st;
        for(int i = 0; i < lines.size(); i++) {
        	st = lines.get(i).split(",");
        	Boat temp =  new Boat();
        	
        	temp.setBoatType(st[0]);
			System.out.println(temp.getBoatType());
			temp.setBoatName(st[1]);
//			System.out.println(temp.getBoatName());
			temp.setYearMan(Integer.valueOf(st[2]));
//			System.out.println(temp.getYearMan());
			temp.setMakeModel(st[3]);
//			System.out.println(temp.getMakeModel());
			temp.setBoatLength(Integer.valueOf(st[4]));
//			System.out.println(temp.getBoatLength());
			//String s = sca.next();
			temp.setBoatPrice(Double.parseDouble(st[5]));
//			System.out.println(temp.getBoatPrice());
			//System.out.println(sca.next());
			temp.setBoatExpenses(0);
						
			boatList.add(i, temp);
        	
        }
        System.out.println(boatList.size());
		
//		int i = 0;
//	
//		while (sca.hasNext())  
//		{  
//			
//			Boat temp =  new Boat();
//						
//			temp.setBoatType(sca.next());
//			System.out.println(temp.getBoatType());
//			temp.setBoatName(sca.next());
//			System.out.println(temp.getBoatName());
//			temp.setYearMan(Integer.valueOf(sca.next()));
//			System.out.println(temp.getYearMan());
//			temp.setMakeModel(sca.next());
//			System.out.println(temp.getMakeModel());
//			temp.setBoatLength(Integer.valueOf(sca.next()));
//			System.out.println(temp.getBoatLength());
//			String s = sca.next();
//			temp.setBoatPrice(Double.parseDouble(s));
//			System.out.println(temp.getBoatPrice());
//			//System.out.println(sca.next());
//			temp.setBoatExpenses(0);
//						
//			boatList.add(i, temp);
//			i++;
//			
//		}   
		
//	}catch(FileNotFoundException e) {
//		
//		System.out.println("The file " + CSV_PATH +" could not be found.");
//		
//		
//	}catch (NullPointerException nu) {
//		
//		System.out.println("The file " + CSV_PATH + " is not formatted properly.");
//		
//	}catch(NumberFormatException e) {
//		
//		System.out.println("The file " + CSV_PATH + " is not formatted properly.");
//		
//	}


        System.out.println("Welcome to the Fleet Management System");
        System.out.println("--------------------------------------");
        String menu;
        menu = "";
        boolean exit = false;
       

        while (!exit){
        	keyboard = new Scanner(System.in);
            System.out.println("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");
            //keyboard.nextLine();
            menu = keyboard.nextLine();
           
               if(menu.toUpperCase().compareTo("P") == 0) {
                    System.out.println("Fleet Report:");

                    double moneySpent = 0;
                    double moneyPaid = 0;

                    for (int i = 0; i < boatList.size(); i++) {
                        moneySpent = moneySpent + boatList.get(i).getBoatExpenses();
                        moneyPaid = moneyPaid + boatList.get(i).getBoatPrice();
                        System.out.println(boatList.get(i).getBoatType() + " "+ boatList.get(i).getBoatName() + " " + boatList.get(i).getYearMan() + " " + boatList.get(i).getMakeModel() + " "+ boatList.get(i).getBoatLength() + "' : Paid $ " + boatList.get(i).getBoatPrice() + " : Spent $" + boatList.get(i).getBoatExpenses());
                    }
                    System.out.println("Total: Paid $" + moneyPaid + " : Spent $ " + moneySpent);
                    //break;

               }else if(menu.toUpperCase().compareTo("A") == 0) {
                    System.out.println("Please enter the new boat CSV data");
                    String csvData = keyboard.nextLine();
                    String[] newBoat = csvData.split(",");
                    Boat boat = new Boat(newBoat[0], newBoat[1], Integer.parseInt(newBoat[2]), newBoat[3], Integer.parseInt(newBoat[4]), Double.parseDouble(newBoat[5]), 0);
                    boatList.add(boat);
                    System.out.println();
                    //break;

               }else if(menu.toUpperCase().compareTo("R") == 0) {
                    System.out.println("Which boat do you want to remove?");
                    String removeBoat = keyboard.nextLine();
                    boolean go = false;
                   
                   for(int i = 0; i < boatList.size(); i++) {
                	   if(boatList.get(i).getBoatName().toUpperCase().compareTo(removeBoat) == 0) {
                		   boatList.remove(i);
                		   go = true;
                	   }
                   }
                    if (!go) {
                        System.out.println("Cannot find boat " + removeBoat);
                    }
                    
//                    Iterator<Boat> itr = boatList.iterator();
//                    while (itr.hasNext()) {
//                        Boat boat1 = itr.next();
//                        if (removeBoat.equalsIgnoreCase(boat1.getBoatName())) {
//                            itr.remove();
//                            System.out.println("");
//                        }
//                    }
                   //break;

        		}else if(menu.toUpperCase().compareTo("E") == 0) {
                    System.out.println("Which boat do you want to spend on?");
                    String s = keyboard.nextLine();
                    String spendBoat = s.toUpperCase();
                    int index = 0;
                    boolean go = false;
                    for(int i = 0; i < boatList.size(); i++) {
                 	   if(boatList.get(i).getBoatName().toUpperCase().compareTo(spendBoat) == 0) {
                 		   go = true;
                 		   index = i;
                 	   }
                    }
                     if (!go) {
                         System.out.println("Cannot find boat " + s);
                     }else {
                    //System.out.println(index);
                    //boolean trueBoat = trueBoat(spendBoat);
	                    System.out.println("How much do you want to spend?");
	                    double expenseAmount = keyboard.nextDouble();
	                    //keyboard.nextLine();
	                    if(boatList.get(index).getBoatPrice() < boatList.get(index).getBoatExpenses() + expenseAmount) {
	                    	System.out.println("Expense not permitted, only $" + (boatList.get(index).getBoatPrice() - boatList.get(index).getBoatExpenses()) + " left to spend.");
	                    }else {
	                    	boatList.get(index).setBoatExpenses(boatList.get(index).getBoatExpenses() + expenseAmount);
                            System.out.println("Expense authorized, $" + boatList.get(index).getBoatExpenses() + " spent.");
	                    }
	                    
//	                    Iterator<Boat> itr = boatList.iterator();
//	                    Boat boat1 = null;
//	                    while (itr.hasNext()) {
//	                        boat1 = itr.next();
//	                        if (spendBoat.equalsIgnoreCase(boat1.getBoatName())) {
//	                            if (boat1.getBoatPrice() >= boat1.getBoatExpenses() + expenseAmount) {
//	                                boat1.setBoatExpenses(boat1.getBoatExpenses() + expenseAmount);
//	                                System.out.println("Expense authorized, $" + boat1.getBoatExpenses() + " spent.");
//	                            } else {
//	                                System.out.println("Expense not permitted, only $" + (boat1.getBoatExpenses() + expenseAmount - boat1.getBoatPrice()) + " left to spend.");
//	                            }
//	                        }
//	                    }
                     }
                    //break;

        		}else if(menu.toUpperCase().compareTo("X") == 0) {
                    System.out.println("Thank you for using our Fleet Management System");
        			
                    File secondPeopleFile = new File("FleetData.db");
					ObjectOutputStream writeObj = null;
					try
						{
						writeObj = new ObjectOutputStream(new FileOutputStream(secondPeopleFile));
						for(Boat boatO:boatList)
						{
						writeObj.writeObject(boatO);
						                    }
						writeObj.close();
						} catch (IOException e)
						{
						System.out.println("Problems writing to file");
						e.printStackTrace();
						}
                    
                    exit = true;
                    //System.exit(0);

        		}else{
                    System.out.println("Invalid menu option, try again");
                } // end of switch menu
        }

        }

//        public static boolean trueBoat(String removeBoat) {
//            for (Boat boat : Boat.boatList) {
//                if (removeBoat.equalsIgnoreCase (boat.getBoatName())) {
//                    return true;
//                }
//            }
//            return false;
//        }

//        public static List <Boat> loadInfo() {
//            List <Boat> info = new ArrayList<Boat>();
//            try {
//                File file = new File(CSV_PATH);
//                FileReader fr = new FileReader(file);
//                BufferedReader br = new BufferedReader(fr);
//                String line = "";
//                while ((line = br.readLine()) != null) {
//                    String[] newBoat = line.split(",");
//                    Boat boat = new Boat(newBoat[0], newBoat[1], Integer.parseInt(newBoat[2]), newBoat[3], Integer.parseInt(newBoat[4]), Double.parseDouble(newBoat[5]), 0);
//                    info.add(boat);
//                }
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//            return info;
//        }


    }//end of main

