package jtglover_CSCI201_Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Semaphore;

import org.apache.commons.text.WordUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

public class Broker extends Thread implements Runnable {
	
	
	private Semaphore sem;
	private String ticker;
	private Integer quant;
	private Integer price;
	
	Broker(Semaphore semmy, Setup s){
		
		this.sem = semmy;
		this.ticker = s.getTicker();
		this.quant = s.getNumStocks();
		this.price = s.getPrice();
		
	}
	
	public static String OutputTime(long t) {
		
		String s = "";
		
		t -= 57600000;
		
		DateFormat d = new SimpleDateFormat("H:mm:ss:SSS");
		Date ans = new Date(t);
		
		s = s + "[" + d.format(ans) + "]";
		
		return s;
		
	}
	

	
	public void run() {
		
		try {
			
			sem.acquire();
			
		}catch(InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
		if(quant > 0) {
			
			System.out.println(OutputTime(System.currentTimeMillis() - Main.timmmy) + " Starting purchase of " + quant + " stocks of "  + ticker);
			
			if(quant*price > Main.account) {
				
				System.out.println("Transaction failed due to insufficient balance. Unsuccessful purchase of " + quant + " stocks of " + ticker);
				
			}else {
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Main.account -= quant*price;
				
				System.out.println(OutputTime(System.currentTimeMillis() - Main.timmmy) + " Finished purchase of " + quant + " stocks of "  + ticker);
				
				System.out.println("Current Balance after trade: " + Main.account);
				
			}			
			
			
			
		}else if(quant < 0) {
			
			System.out.println(OutputTime(System.currentTimeMillis() - Main.timmmy) + " Starting sale of " + (-1)*quant + " stocks of "  + ticker);
			
			//ain.account += (-1*quant)*price;
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Main.account += (-1*quant)*price;
			
			System.out.println(OutputTime(System.currentTimeMillis() - Main.timmmy) + " Finished sale of " + (-1)*quant + " stocks of "  + ticker);
			
			System.out.println("Current Balance after trade: " + Main.account);
			
		}
		
		
		sem.release();
		
		Main.total--;
		
		if(Main.total == 0) {
			
			System.out.println("All trades completed!");
			
		}
					
		}
	
	}
