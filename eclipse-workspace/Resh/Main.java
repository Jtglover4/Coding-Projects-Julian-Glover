package jtglover_CSCI201_Assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import jtglover_CSCI201_Assignment3.Setup;
import jtglover_CSCI201_Assignment3.SetupTwo;


public class Main {
	
	public static List<SetupTwo> ArrT = Collections.synchronizedList(new ArrayList<SetupTwo>());
	public static ArrayList <Setup> Arr = new ArrayList <Setup>();
	
	public static void main(String args[]){
		
		Scanner sc = null;
		Scanner sca = null;
		boolean go = false;
		boolean go2 = false;
		
		
		while(!go) {
			
			System.out.println("What is the path of the schedule file?");
			
			sc = new Scanner(System.in);
			String s = sc.nextLine();  
			
			try {
				
				File file = new File(s);
				
				sca = new Scanner(file);
				
				sca.useDelimiter(",|\\r\\n");
												
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
					
					sca.nextLine();
					
					Arr.add(i, temp3);
					i++;
					
				}   
				
				System.out.println("The schedule file has been properly read.");
				
				go = true;
			
			}catch(FileNotFoundException e) {
				
				System.out.println("The file " + s +" could not be found.");
				
				
			}catch (NullPointerException nu) {
				
				System.out.println("The file " + s + " is not formatted properly.");
				
			}catch(NumberFormatException e) {
				
				System.out.println("The file " + s + " is not formatted properly.");
				
			}
		}
		
//		for(int i = 0; i < Arr.size(); i++) {
//			
//			System.out.println(Arr.get(i).getTicker());
//			
//		}
		
		while(!go2) {
			
			System.out.println("What is the path of the traders file?");
			
			sc = new Scanner(System.in);
			String s = sc.nextLine();  
			
			try {
				
				File file = new File(s);
				
				sca = new Scanner(file);
				
				sca.useDelimiter(",|\\r\\n");
				
				int i = 0;
			
				while (sca.hasNext())  
				{  
					
					SetupTwo temp3 =  new SetupTwo();
					
					temp3.setSerialNum(Integer.valueOf(sca.next()));
					temp3.setInitBalance(Integer.valueOf(sca.next()));
					
					ArrT.add(i, temp3);
					i++;
					
				}   
				
				System.out.println("The traders file has been properly read.");
				
				go2 = true;
			
			}catch(FileNotFoundException e) {
				
				System.out.println("The file " + s +" could not be found.");
				
				
			}catch (NullPointerException nu) {
				
				System.out.println("The file " + s + " is not formatted properly.");
				
			}catch(NumberFormatException e) {
				
				System.out.println("The file " + s + " is not formatted properly.");
				
			}
			
		}
		
		kill k = new kill();
		
		for(int i = 0; i < Arr.size(); i++) {
			
			URL u = null;
			
			try {
				u = new URL("https://finnhub.io/api/v1/quote?symbol=" + Arr.get(i).getTicker() + "&token=cdhl30qad3i07d1jb7o0cdhl30qad3i07d1jb7og");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			try {
				sca = new Scanner(u.openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			String temp ="";
			
			while(sca.hasNext()) {
				temp += sca.nextLine();
			}
			
			Gson gson = new Gson();
			k = gson.fromJson(temp, kill.class);
			Arr.get(i).setPrice(k.getc());
		}
		
		
			
		StockServer ser = new StockServer(3456);
		
		ArrayList<Setup> temp = new ArrayList<Setup>();
		boolean next = false;
		
		long star;
		
		ser.conn();
		Trader.message("Starting Service." + "\n");
		System.out.println("Starting Service.");
		int i = 0;
		
		star = System.currentTimeMillis();
		
		while(i < Arr.size()) {
			while(((System.currentTimeMillis()-star)/1000) < Arr.get(i).getInitTime()) {}
			temp.add(Arr.get(i));
			i++;
			if(temp.isEmpty()) {
				
			}else {
				StockServer.assign(temp);
				next = true;
			}
		}
		StockServer.broadcast("Processing Complete. Thank you \n");
		StockServer.broadcast("END");
		System.out.println("Processing Complete.");
		
		
	}
	
	
}


