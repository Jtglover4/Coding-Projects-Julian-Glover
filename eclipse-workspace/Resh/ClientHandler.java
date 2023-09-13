package vahidy.assignment3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
	private Socket socket;
	private PrintWriter pw;
	private double spent = 0.0;
	private double gained = 0.0;
	private int state;
	private Trader trader;
	private Server server;
	private List<Schedule> toTrade;
	
	public ClientHandler(Socket socket, Server server, Trader trader) throws IOException {
		this.socket=socket;
		this.server=server;
		this.trader=trader;
		this.state = 1;
		this.pw = new PrintWriter(socket.getOutputStream());
		
		this.start();
	}
	
	public void assignTrades(List<Schedule> toTrade, double toSpend, double toMake) throws IOException, InterruptedException {
		for(Schedule trade : toTrade) {
			String message = "";
			double cost = Math.abs(server.getPriceMap().get(trade.getTicker()) * trade.getAmount());
			if(trade.getAmount() > 0) {
				message = Util.printOut("Assigned purchase of " + trade.getAmount() + " stock(s) of " + trade.getTicker() +
						". Total Cost Estimate: " + server.getPriceMap().get(trade.getTicker()) + " * " + trade.getAmount() + " = " + 
						cost);
			} else {
				message = Util.printOut("Assigned sale of " + (-1*trade.getAmount()) + " stock(s) of " + trade.getTicker() +
						". Total Cost Estimate: " + server.getPriceMap().get(trade.getTicker()) + " * " + (-1*trade.getAmount()) + " = " + 
						cost);
			}
			this.sendMessage(message);
		}
		this.spent += toSpend;
		this.gained += toMake;
		this.toTrade = toTrade;
		this.state = 0;
		printTrades();
	}
	
	public void printTrades() throws IOException, InterruptedException {
		for(Schedule trade : this.toTrade) {
			double cost = Math.abs(server.getPriceMap().get(trade.getTicker()) * trade.getAmount());
			String message = "";
			if(trade.getAmount() > 0) {
				message = Util.printOut("Starting purchase of " + trade.getAmount() + " stock(s) of " + trade.getTicker() +
						". Total Cost: " + server.getPriceMap().get(trade.getTicker()) + " * " + trade.getAmount() + " = " + 
						cost);
			} else {
				message = Util.printOut("Starting sale of " + (-1*trade.getAmount()) + " stock(s) of " + trade.getTicker() +
						". Total Cost: " + server.getPriceMap().get(trade.getTicker()) + " * " + (-1*trade.getAmount()) + " = " + 
						cost);
			}
			this.sendMessage(message);
			Thread.sleep(1000);
			if(trade.getAmount() > 0) {
				this.sendMessage(Util.printOut("Finished purchase of " + trade.getAmount() + " stocks(s) of " + trade.getTicker()));
			} else {
				this.sendMessage(Util.printOut("Finished sale of " + (-1*trade.getAmount()) + " stocks(s) of " + trade.getTicker()));
			}
		}
	}
	
	@Override
	public void run() {
		// -1 = finished, 0 = running, 1 = available
	}
	
	public String getProfit() {
		return String.format("Total profit earned: $%.2f\n", (this.gained));
	}
	
	
	public Socket getSocket() {
		return socket;
	}
	
	public void sendMessage(String message) throws IOException {
		pw.println(message);
		pw.flush();
	}
	
	public double getSpent() {
		return this.spent;
	}
	
	public Trader getTrader() {
		return this.trader;
	}
	
	public boolean isOpen() {
		return this.state == 1;
	}

	public void setFinished() {
		this.state = -1;
		
	}
	
	public void setOpen() {
		this.state = 1;
	}

	public boolean isFinished() {
		return this.state == -1;
	}
}
