case "X": System.out.println("Exiting the Fleet Management System"); //Write to fleetdata.db writeSerializedFleetData(boatList); break; default: System.out.println("Invalid menu option, try again"); } }
}
private static ArrayList<Boat> readCSVFileToList(String fileName) throws NumberFormatException { ArrayList<Boat> boatList=new ArrayList<>(); //Initialize reader BufferedReader br=null;
try { br = new BufferedReader(new FileReader(fileName));
 String line=null;
while ((line = br.readLine()) != null) //returns a Boolean value  {  String [] lines= line.split(","); // use comma as separator  BoatType boatType=lines[0].equalsIgnoreCase("POWER")?BoatType.POWER:BoatType.SAILING; Boat boat=new Boat(boatType, lines[1], Integer.parseInt(lines[2]), lines[3], Double.parseDouble(lines[4]), Double.parseDouble(lines[5]), 0); boatList.add(boat); } } catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
return boatList;  }
private static ArrayList<Boat> readFleetData(String fileName) throws IOException, ClassNotFoundException { ArrayList<Boat> boatList=new ArrayList<>(); try { FileInputStream fileIn = new FileInputStream(fileName); ObjectInputStream objectIn = new ObjectInputStream(fileIn); Object obj = null; while((obj = objectIn.readObject())!=null) { boatList.add((Boat)obj); } objectIn.close(); } catch (Exception e) { // TODO Auto-generated catch block }
return boatList; }
private static void writeSerializedFleetData(ArrayList<Boat> boatList) { // write people to another file File secondPeopleFile = new File("C:\\Users\\asus\\FleetData.db"); ObjectOutputStream writeObj = null; try { writeObj = new ObjectOutputStream(new FileOutputStream(secondPeopleFile));
for(Boat boatO:boatList) { writeObj.writeObject(boatO);
} writeObj.close(); } catch (IOException e) { System.out.println("Problems writing to file");
e.printStackTrace(); } }
}
O
































