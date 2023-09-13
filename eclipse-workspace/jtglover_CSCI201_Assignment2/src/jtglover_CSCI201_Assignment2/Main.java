package jtglover_CSCI201_Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Semaphore;

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
import java.util.HashMap;

public class Main {
	
	static long timmmy;
	static int account = 0;
	static int total = 0;
	
	public static void main(String args[]){
			
			boolean ex = false;
			Scanner sc = null;
			boolean go = false;
			boolean go2 = false;
			Stock f = null;
			Setup set = null;
			File t;
			boolean inputa = false;
			String s1 = "";
			
			int time = 0;
			
			ArrayList <Setup> Arr = new ArrayList <Setup>();
			
			Scanner sca = null;
			String temp = "";
			String temp2 = "";
			
			while(!go) {
				
				System.out.println("What is the name of the file containing the company information?");
				
				sc = new Scanner(System.in);
				String s = sc.nextLine();  
				
				if(s.charAt(s.length() - 1) != 'n'){
					
					System.out.println("The file " + s + " is not formatted properly.");
					
				}else {
				
					try {
						
						File file = new File(s);
						
						sca = new Scanner(file);
						
						while(sca.hasNext()) {
							temp += sca.nextLine();
						}
						
						Gson gson = new Gson();
						f = gson.fromJson(temp, Stock.class);
						
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
				
			}
			
			while(!go2) {
				
				System.out.println("What is the name of the file containing the schedule information?");
				
				sc = new Scanner(System.in);
				String s = sc.nextLine();  
				
				try {
					
					File file = new File(s);
					
					sca = new Scanner(file);
					
					sca.useDelimiter(",|\\r\\n");
					//sca.useDelimiter(" ");
					
					int i = 0;
				
					while (sca.hasNext())  
					{  
						
						Setup temp3 =  new Setup();
						
						temp3.setInitTime(Integer.valueOf(sca.next()));
						//System.out.println(temp3.getInitTime());
						temp3.setTicker(sca.next());
						//System.out.println(temp3.getTicker());
						temp3.setNumStocks(Integer.valueOf(sca.next()));
						//System.out.println(temp3.getNumStocks());
						temp3.setPrice(Integer.valueOf(sca.next()));
						//System.out.println(temp3.getPrice());
						
						Arr.add(i, temp3);
						i++;
						
					}   
					
					go2 = true;
				
				}catch(FileNotFoundException e) {
					
					System.out.println("The file " + s +" could not be found.");
					
					
				}catch (NullPointerException nu) {
					
					System.out.println("The file " + s + " is not formatted properly.");
					
				}catch(NumberFormatException e) {
					
					System.out.println("The file " + s + " is not formatted properly.");
					
				}
			}
			while(!inputa) {
				System.out.println("What is the Initial Balance?");
				
				sc = new Scanner(System.in);
				
				if(sc.hasNextInt()) {
					account = sc.nextInt();  
					inputa = true;
				}else {
					
					System.out.println("Please enter an integer");
					
				}
			}
						
			HashMap<String, Semaphore> HM = new HashMap<String, Semaphore>();
			
			for(int i = 0; i < f.getSize(); i++) {
				
				Semaphore tempsem = new Semaphore(f.getDatabyIndex(i).getStockBrokers(), true);
				
				HM.put(Arr.get(i).getTicker(), tempsem);
				
			}
					
//			for(Setup i : Arr) {
//				
//				total += Math.abs(i.getNumStocks());
//				
//			}
			total = Arr.size();
			
			//System.out.println(total);
			
			timmmy = System.currentTimeMillis();
			
			for(int i = 0; i < Arr.size(); i++) {
				
				int inittime = Arr.get(i).getInitTime();
				String ticker = Arr.get(i).getTicker();
//				int numtrades = Arr.get(i).getNumStocks();
//				int p = Arr.get(i).getPrice();
				
				while((System.currentTimeMillis() - timmmy) < inittime*1000) {};
				
				Runnable run = new Broker(HM.get(ticker), Arr.get(i));
				Thread three = new Thread(run);
				three.start();
				
			}
		
	}
}
