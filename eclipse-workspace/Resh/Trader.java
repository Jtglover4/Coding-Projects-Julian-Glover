package jtglover_CSCI201_Assignment3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Trader extends Thread{
	
	StockServer s;
	static SetupTwo t;
	public boolean g;
	static PrintWriter print = null; 
	private Socket sock = null;
	
	static double plus = 0.0;
	static double lose = 0.0;
	
	public Trader(StockServer s, Socket so, SetupTwo setupTwo) {
		
		sock = so;
		this.s = s;
		this.t = setupTwo;
		this.g = true;
		
		try {
			this.print = new PrintWriter(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.start();
		
	}
	
	public void setG() {
		this.g =true;
	}
	public static SetupTwo getS() {
		return t;
	}
	public static double getLose() {
		return lose;
	}
	
	public static void message(String m) {
		
			
		String s = "";
		
		long t = System.currentTimeMillis();
			
		DateFormat d = new SimpleDateFormat("H:mm:ss:SSS");
		Date ans = new Date(t);
			
		s = s + "[" + d.format(ans) + "] ";
		
		s += m;
			
		try {
			print.write(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			print.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	public void ex(ArrayList<Setup> as, double l, double b) {
		
		int i = 0;
		//System.out.println(as.size());
		while(i < as.size()) {
			
			if(as.get(i).getNumStocks() < 0) {
				
				message("Assigned sale of " + (-1)*as.get(i).getNumStocks() + " stock(s) of " + as.get(i).getTicker() + 
						". Total gain estimate = " + as.get(i).getPrice() + "*" + (-1)*as.get(i).getNumStocks() + " = " + (as.get(i).getPrice()* (-1)*as.get(i).getNumStocks()) + "\n");
				
				
			}else if(as.get(i).getNumStocks() > 0){
				
				message("Assigned sale of " + as.get(i).getNumStocks() + " stock(s) of " + as.get(i).getTicker() + 
						". Total cost estimate = " + as.get(i).getPrice() + "*" + as.get(i).getNumStocks() + " = " + (as.get(i).getPrice()*as.get(i).getNumStocks()) + "\n");				
				
			}
			
			i++;
		}
		
		this.g = false;
		this.plus += l;
		this.lose += b;
		
		i = 0;
			
		while(i < as.size()) {
			if(as.get(i).getNumStocks() < 0) {
				message("Starting sale of " + (-1)*as.get(i).getNumStocks() + " stock(s) of " + as.get(i).getTicker() + 
						". Total gain estimate = " + as.get(i).getPrice() + "*" + (-1)*as.get(i).getNumStocks() + " = " + (as.get(i).getPrice()* (-1)*as.get(i).getNumStocks()) + "\n");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				message("Finished sale of " + (-1)*as.get(i).getNumStocks() + " stock(s) of " + as.get(i).getTicker() + "\n");
			}else if(as.get(i).getNumStocks() > 0) {
					
				message("Starting purchase of " + as.get(i).getNumStocks() + " stock(s) of " + as.get(i).getTicker() + 
						". Total gain estimate = " + as.get(i).getPrice() + "*" + as.get(i).getNumStocks() + " = " + (as.get(i).getPrice()* (-1)*as.get(i).getNumStocks() + "\n"));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				message("Finished purchase of " + as.get(i).getNumStocks() + " stock(s) of " + as.get(i).getTicker() + "\n");
					
			}
			i++;
		}
		
	}

	
	
	
	
	
}
