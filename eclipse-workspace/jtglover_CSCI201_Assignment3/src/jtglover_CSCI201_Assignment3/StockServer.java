package jtglover_CSCI201_Assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class StockServer extends Thread{
	
	static int port;
	boolean yes = true;
	Socket s = null;
	PrintWriter pw = null;
	BufferedReader br = null;
	
	public static List<Trader> t = new ArrayList<>();
	
	public StockServer(int p) {
		
		this.port = p;
		
	}
	
	ServerSocket ss = null;
	int left = 0;
	
	public void conn() {
		
		System.out.println("Listening on port " + port + ".");
		System.out.println("Waiting for traders....");
		
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			while(Main.ArrT.size() > t.size()) {

				s = ss.accept();
				System.out.println("Connection from" + s.getInetAddress());
				Trader person = new Trader(this, s, Main.ArrT.get(t.size()));
				if(person != null) {
					t.add(person);
					if(t.size() >= Main.ArrT.size()) {
						yes = false;
					}
				}
				
				if(yes) {
					
					left = Main.ArrT.size() - t.size();
					
					if(left > 0){
						
						
						broadcast(left + " more trader is needed before trades can begin \n" + "Waiting for " + left + " more traders...");
						
						if(left == 1) {
							
							System.out.println("Waiting for 1 more trader...");
							
						}else {
							
							System.out.println("Waiting for " + left + " more traders...");
							
						}
						
					}
					
					
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void assign(List<Setup> A) {
		
		for(Trader trad : t) {
			
				double f = 0.0;
				double e = 0.0;
				ArrayList<Setup> cool = new ArrayList<Setup>();
				for(Setup st: A) {
					if(st.getNumStocks() > 0) {
						double c = st.getNumStocks();
						c*= st.getPrice();
						
						try {
							if((f*c) < Trader.getS().getInitBalance() - Trader.getLose()) {
								cool.add(st);
								f += c;
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					
					}else {
						
						double p = st.getNumStocks()*st.getPrice();
						cool.add(st);
						e += p;
						
					}
					
				}
				if(!cool.isEmpty()) {
					trad.ex(cool, f, e);
					for(Setup o : cool) {
						A.remove(o);
					}
				}
			

			
		}
		
	}
	
	public static void broadcast(String m){
		if(m.length() > 0) {
			if(m != null) {
				for(Trader tr : t) {
					
					tr.message(m);
					
				}
			}
		}
		
	}
	
}
