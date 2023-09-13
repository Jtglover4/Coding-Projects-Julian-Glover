package CS201_A3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class TraderThreads extends Thread{
	 private Server server;
	    private Trader trader;
	    private int status = 1;
	    private PrintWriter pw;
//	    private ObjectOutputStream oos;
//	    private ObjectInputStream ois;
	    // private Socket socket;
	    private double cashSpent = 0.0;
	    private double cashMade = 0.0;

	    public TraderThreads(Server server, Trader trader, Socket socket) {

	        try {
	            this.server = server;
	            this.trader = trader;
	            this.status = 1;
//	            this.oos = new ObjectOutputStream(socket.getOutputStream());
//	            this.ois = new ObjectInputStream(socket.getInputStream());
	            this.pw = new PrintWriter(socket.getOutputStream());
	            this.start();
	        } catch (IOException e) {
	            e.printStackTrace();

	        }
	    }

	    public Trader getTrader() {
	        return this.trader;
	    }
	    
	    public void otherSendMessage(String message) {
	    	pw.println(message);
	        pw.flush();
	    }

	    public void sendMessage(String message) throws IOException {
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	    	Date d = new Date(System.currentTimeMillis() - Server.startTime);
	    	d.setHours(0);
	    	message = "[" + sdf.format(d) + "] " + message;
	        // send message to client
	    	pw.println(message);
	        pw.flush();
	    }

	    public Double getProfit() {

	        return cashMade;

	    }

	    public Double getSpent() {

	        return cashSpent;
	    }

	    public Boolean isAvailable() {
	        if (status == 1) {
	            return true;

	        } else {
	            return false;
	        }
	    }

	    public void assignTrades(ArrayList<Stock> trades, double spent, double gained) throws IOException, InterruptedException {
	   
	        for (int i = 0; i < trades.size(); i++) {
	            // figure out sale and purchase
	            if (trades.get(i).getQuantity() > 0) {
	                sendMessage("Assigned purchase of " + trades.get(i).getQuantity() + " stock(s) of "
	                        + trades.get(i).getTicker() + ". Total gain estimate = "
	                        + Server.stockPrice.get(trades.get(i).getTicker()) + "*" + trades.get(i).getQuantity() + " = "
	                        + (Server.stockPrice.get(trades.get(i).getTicker()) * trades.get(i).getQuantity()));

	                // Assigned sale of 1 stock(s) of MSFT. Total gain estimate = 250.66 * 1 =
	                // 250.66.

	            } else {
	                sendMessage("Assigned sale of " + (-1 * trades.get(i).getQuantity()) + " stock(s) of "
	                        + trades.get(i).getTicker() + ". Total gain estimate = "
	                        + Server.stockPrice.get(trades.get(i).getTicker()) + "*" + (-1 * trades.get(i).getQuantity())
	                        + " = "
	                        + (Server.stockPrice.get(trades.get(i).getTicker()) * (-1 * trades.get(i).getQuantity())));

	            }

	        }

	        this.status = 0;
	        this.cashMade += gained;
	        this.cashSpent += spent;
	        printTrades(trades);

	    }

	    public void printTrades(ArrayList<Stock> trades) throws IOException, InterruptedException {
	        for (int i = 0; i < trades.size(); i++) {
	            // double cost = Math.abs.
	            if (trades.get(i).getQuantity() > 0) {
	                sendMessage("Starting purchase of " + trades.get(i).getQuantity() + " stock(s) of "
	                        + trades.get(i).getTicker() + ". Total gain = "
	                        + Server.stockPrice.get(trades.get(i).getTicker()) + "*" + trades.get(i).getQuantity() + " = "
	                        + (Server.stockPrice.get(trades.get(i).getTicker()) * trades.get(i).getQuantity()));
	                Thread.sleep(1000);
	                sendMessage(
	                        "Finished sale of " + trades.get(i).getQuantity() + " stock(s) of " + trades.get(i).getTicker());

	            } else {
	                sendMessage("Starting sale of " + (-1 * trades.get(i).getQuantity()) + " stock(s) of "
	                        + trades.get(i).getTicker() + ". Total gain = "
	                        + Server.stockPrice.get(trades.get(i).getTicker()) + "*" + (-1 * trades.get(i).getQuantity())
	                        + " = "
	                        + (Server.stockPrice.get(trades.get(i).getTicker()) * (-1 * trades.get(i).getQuantity())));
	                Thread.sleep(1000);
	                sendMessage(
	                        "Finished purchase of " + (-1*trades.get(i).getQuantity()) + " stock(s) of " + trades.get(i).getTicker());

	            }

	            // double cost = Math.abs(server.get)

	        }
	    }

		public void setAvailable() {
			this.status = 1;
			
		}


}
