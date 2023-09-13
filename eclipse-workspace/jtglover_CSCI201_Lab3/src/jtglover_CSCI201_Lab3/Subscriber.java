package jtglover_CSCI201_Lab3;

import java.util.Random;

public class Subscriber extends Thread{
	private MessageQueue messages;
	
	public Subscriber(MessageQueue m) {
		messages = m;
	}
	
	public void run() {
		int num = 0;
		while(num < 20) {
			String curr_mess = messages.getMessage();
	        Random randI = new Random();
	        int myRandInt = randI.nextInt(1000);
	        
			if(curr_mess.equals("")) {
				
				System.out.println("Subscriber - tried to read, no message found");
			}
			else {
				
				num++;
				System.out.println("Subscriber - read " + curr_mess + "; time: " + Util.getCurrentTime());
			}
			
			try {
				Thread.sleep(myRandInt);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
