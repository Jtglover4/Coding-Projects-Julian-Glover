package jtglover_CSCI201_Lab3;

import java.util.Random;

public class Messenger extends Thread{
private MessageQueue messages;
	
	public Messenger(MessageQueue m) {
		messages = m;
	}
	
	public void run() {
		for(int i = 1; i < 21; i++) {
	        Random randI = new Random();
	        int myRandInt = randI.nextInt(1000);
			messages.addMessage("Message #" + i);
			System.out.println("Messenger - insert " + "Message #" + i + "; time: " + Util.getCurrentTime());
			try {
				Thread.sleep(myRandInt);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("Messenger - insert " + "Message #" + i + "; time: " + Util.getCurrentTime());
		}
	}
}
