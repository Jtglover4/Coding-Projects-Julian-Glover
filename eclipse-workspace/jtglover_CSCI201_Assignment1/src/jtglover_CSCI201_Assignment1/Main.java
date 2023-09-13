package jtglover_CSCI201_Assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import org.apache.commons.text.WordUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Gson;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Main {
	
	
	public static void CallMenu() {
		
		System.out.println("	1) Display all public companies");
		System.out.println("	2) Search for a stock (by ticker)");
		System.out.println("	3) Search for all stocks on an exchange");
		System.out.println("	4) Add a new company/stocks");
		System.out.println("	5) Remove a company");
		System.out.println("	6) Sort companies");
		System.out.println("	7) Exit");
		System.out.println("What would you like to do?");
		
		
	}
	

	public static void main(String args[]){
		
		boolean ex = false;
		Scanner sc = null;
		boolean go = false;
		First f = null;
		File t;
		String s1 = "";
		
//		Scanner sc = new Scanner(System.in);
//		String n = sc.nextLine();  
//		System.out.println(n);
		
		//File file = new File("stock.json");
		Scanner sca = null;
		String temp = "";
		
		while(!go) {
			
			System.out.println("What is the name of the company file?");
			
			sc = new Scanner(System.in);
			String s = sc.nextLine();  
			
			try {
				
				File file = new File(s);
				
				sca = new Scanner(file);
				
				while(sca.hasNext()) {
					temp += sca.nextLine();
				}
				
				Gson gson = new Gson();
				f = gson.fromJson(temp, First.class);
				
				t = file;
				s1 = s;
								
				System.out.println("The file has been properly read. \n");
				
				go = true;
				
			}catch(FileNotFoundException e) {
				
				System.out.println("The file " + s +" could not be found.");
				
				
			}catch (NullPointerException nu) {
				
				System.out.println("The file " + s + " is not formatted properly.");
				
			}catch(JsonSyntaxException je) {
				
				System.out.println("The file " + s + " is not formatted properly.");
				
			}
			
		}
			
			while(!ex) {
				
				CallMenu();
				
				sc = new Scanner(System.in);
				int n = sc.nextInt();  
				
				if(n == 1) {
					
					for(int i = 0; i < f.getSize(); i++) {
						
						System.out.print(f.getDatabyIndex(i).getName() + ", ");
						System.out.print("symbol " + f.getDatabyIndex(i).getTicker() + ", ");
						System.out.print("started on " + f.getDatabyIndex(i).getStartDate() + ", ");
						System.out.println("listed on " + f.getDatabyIndex(i).getExchangeCode() + ",");
						System.out.println("\t" + WordUtils.wrap(f.getDatabyIndex(i).getDescription(), 60, "\n\t", false));
						
					}
					System.out.println("\n");
					
				}else if(n == 2){
					
					boolean found = false;
					String s = "";
					
					while(!found) {
						
						boolean vtick = false;
						
							while(!vtick) {
						
							System.out.println("What is the ticker of the company you would like to search for?");
							
							sc = new Scanner(System.in);
							
							s = sc.nextLine();
							
							String s2 = s.toUpperCase();
							
							if(s.compareTo(s2) != 0) {
								
								System.out.println("Ticker must be uppercase, please try again");
								
							}else {
								
								vtick = true;
								
							}
							
						}
						
						//System.out.println(s);
							
						boolean done = false;
												
						for(int p = 0; p < f.getSize(); p++){
							
							if(s.equals(f.getDatabyIndex(p).getTicker())) {
								
								System.out.print(f.getDatabyIndex(p).getName() + ", ");
								System.out.print("symbol " + f.getDatabyIndex(p).getTicker() + ", ");
								System.out.print("started on " + f.getDatabyIndex(p).getStartDate() + ", ");
								System.out.println("listed on " + f.getDatabyIndex(p).getExchangeCode());
								System.out.print("\n");
								found = true;
								done = true;
								break;
							}
							
						}
						
						if(!done){
							System.out.println(s + " could not be found.");
						}
					
					}
					
					
				}else if(n == 3) {
					
					boolean found = false;
					
					ArrayList<Integer> exchange = new ArrayList<Integer>();
					
					while(!found) {
						
						boolean exgood = false;
						String s = "";
						
						while(!exgood) {
						
							System.out.println("What Stock Exchange would you like to search for?");
							
							sc = new Scanner(System.in);
							
							s = sc.nextLine();
							
							String str = s.toUpperCase();
						
							if(s.compareTo(str) == 0) {
								
								exgood = true;
								
							}else {
								
								System.out.println("Exchange name must be uppercase");
								
							}
						
						
						}
						
						for(int i = 0; i < f.getSize(); i++) {
							
							if(s.equals(f.getDatabyIndex(i).getExchangeCode())) {
								
								exchange.add(i);
								
							}
							
						}
						
						if(exchange.size() == 0) {
							System.out.println("No exchange named " + s + " found.");
							
						}else {
							
							for(int i = 0; i < (exchange.size()-1); i++){
								
								System.out.print(f.getDatabyIndex(exchange.get(i)).getTicker() + " and ");
								
							}
							System.out.println(f.getDatabyIndex(exchange.get(exchange.size()-1)).getTicker() +  " found on the " + s + " exchange. \n");
							found = true;
						}
						
						
					}
					
					
				}else if(n ==5) {
					
					boolean d = false;
					
					while(!d) {
					
						System.out.println("\n");
					
						for(int i = 0; i < f.getSize(); i++) {
							
							System.out.println((i+1) + ") " + f.getDatabyIndex(i).getName());
							
						}
						System.out.println("Which company would you like to remove?");
						
						sc = new Scanner(System.in);
						
						int s = sc.nextInt();
						
						if(s > f.getSize()) {
							
							System.out.println("Invalid option, choose again");
							
						}else {
						
							String ttemp = f.getDatabyIndex(s-1).getName();
							
							f.getData().remove(s-1);
							
							System.out.println(ttemp + " is now removed");
							
							System.out.println("\n");
							
							d = true;
						}
					}
					
					
				}else if(n == 6) {
					
					boolean sorted = false;
					
					while(!sorted) {
					
						System.out.println("\n");
						
						System.out.println("1) A to Z");
						
						System.out.println("2) Z to A");
						
						System.out.println("How would you like to sort by?");
						
						sc = new Scanner(System.in);
						
						int s = sc.nextInt();
						
						if(s == 1) {
							
							for(int i = 0; i < f.getSize(); i++) {
								for(int j = i+1; j < f.getSize(); j++) {
									
									if((f.getDatabyIndex(i).getName()).compareToIgnoreCase(f.getDatabyIndex(j).getName()) > 0) {
										Collections.swap(f.getData(), i, j);
									}
									
								}
							}
							System.out.println("Your companies are now sorted from A-Z in alphabetical order \n");
							sorted = true;
							
						}else if(s == 2){
							
							for(int i = 0; i < f.getSize(); i++) {
								for(int j = i+1; j < f.getSize(); j++) {
									
									if((f.getDatabyIndex(i).getName()).compareToIgnoreCase(f.getDatabyIndex(j).getName()) < 0) {
										Collections.swap(f.getData(), i, j);
									}
									
								}
							}
							System.out.println("Your companies are now sorted from Z-A in alphabetical order \n");
							sorted = true;
							
						}else {
							
							System.out.println("Invalid option \n");
							
						}
					
						//sorted = true;
					}
					
					
				}else if(n == 4) {
					
				boolean added = false;
					
				while(!added) {
					
					boolean good = true;
					
					System.out.println("What is the name of the company you would like to add?");
					
					sc = new Scanner(System.in);
					
					String s = sc.nextLine();
					
					for(int i = 0; i < f.getSize(); i++) {
						
						if((s.toUpperCase()).equals((f.getDatabyIndex(i).getName()).toUpperCase())) {
							//System.out.println("There is already an entry for " + s);
							good = false;
							//break;
						}
						
					}
					
					if(good) {
						
						boolean gdate = false;
						
						boolean gtick = false;
						
						String sym = "";
					
						Datum d = new Datum();
						
						while(!gtick){
						
							System.out.println("What is the stock symbol of " + s + "?");
							
							sym = sc.nextLine();
							
							String sup = sym.toUpperCase();
							
							if(sym.compareTo(sup) == 0) {
								
								gtick = true;
								
							}else {
								
								System.out.println("Stock symbol must be uppercase");
								
							}
						
						}
						
						String star = "";
						
						while(!gdate) {
							
							System.out.println("WWhat is the start date of " + s + "?");
							
							star = sc.nextLine();
							
							try {
								
					            LocalDate.parse(star, DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT)); //https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/
					            	
					            gdate = true;
	
					        } catch (DateTimeParseException e) {
					            
					        	System.out.println("Inavlid date");
					            
					        }
						}
						
						System.out.println("What is the exchange where " + s + " is listed?");
						
						String x = sc.nextLine();
						
						System.out.println("What is the description of " + s);
						
						String des = sc.nextLine();
						
						d.setName(s);
						d.setTicker(sym);
						d.setStartDate(star);
						d.setExchangeCode(x);
						d.setDescription(des);
						
						f.getData().add(d);
						
						System.out.println("There is now a new entry for: \n");
					
						System.out.print(f.getDatabyIndex((f.getSize() - 1)).getName() + ", ");
						System.out.print("symbol " + f.getDatabyIndex((f.getSize() - 1)).getTicker() + ", ");
						System.out.print("started on " + f.getDatabyIndex((f.getSize() - 1)).getStartDate() + ", ");
						System.out.println("listed on " + f.getDatabyIndex((f.getSize() - 1)).getExchangeCode() + ",");
						System.out.println("\t" + WordUtils.wrap(f.getDatabyIndex((f.getSize() - 1)).getDescription(), 60, "\n\t", false));
						
						System.out.print("\n");
						
						added = true;
						
					}else {
						
						System.out.println("There is already an entry for " + s);
						
					}
					
				}
					
				}
				else if(n == 7) {
					
					boolean finish = false;
					
				while(!finish) {
					
					System.out.println("\n");
					
					System.out.println("1) Yes");
					
					System.out.println("2) No");
					
					System.out.println("Would you like to save your edits?");

					
					sc = new Scanner(System.in);
					
					int s = sc.nextInt();
					
					if(s == 1) {
						
						try {
							
							Gson fin = new GsonBuilder().setPrettyPrinting().create();
							fin.toJson(f);
							
							FileWriter fi = new FileWriter(s1);
							fi.write(fin.toJson(f));
							fi.close();
							
							System.out.println("Your edits have been saved");
							
							finish = true;
							
						}catch(IOException ie){
							
							
							
						}
						
						
					}else if(s != 2){
						
						System.out.println("Invalid input, please select whether or not to save \n");
						
					}else {
						
						finish = true;
						
					}
				}
					
					
					ex = true; 
						
					System.out.println("Thank you for using my program!");
					
				
				
				
				}else {
					
					System.out.println("Invalid input");
					
				}
					
			}
	}
	
}


