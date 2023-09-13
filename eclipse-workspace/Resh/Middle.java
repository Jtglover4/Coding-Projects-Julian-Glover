package jtglover_CSCI201_Assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Middle extends Thread{
	
	BufferedReader b = null;
	Socket s = null;
	
	public Middle(String h, int p) {
		
		try {
			s = new Socket(h, p);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			b = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.start();
		
	}
	
	String j;
	public void run() {
		
		while(true) {
			
			try {
				//System.out.println("HIT");
				j = b.readLine();
				//System.out.println("HIT2");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(j != null){
				
				if(j == "END") {
					try {
						s.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				
				if(j.toUpperCase().compareTo("END") == 0) {
					
					try {
						s.close();
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}else {
					System.out.println(j);
				}
				
			}
			
			
		}
		
	}
	public static void main(String args[]){
		Scanner sc = null;
		System.out.println("Welcome to SalStocks v2.0!");
		
		System.out.println("Enter the server hostname:");
	
		sc = new Scanner(System.in);
		String hostname = sc.nextLine(); 
		
		System.out.println("Enter the server port:");
		
		sc = new Scanner(System.in);
		int port = sc.nextInt();
		
		sc.close();
		
		new Middle(hostname, port);
	}
	
	
}
