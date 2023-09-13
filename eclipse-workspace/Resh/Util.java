package vahidy.assignment3;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

public class Util {
	public static Stock loadStock(String ticker) {
		String link = "https://finnhub.io/api/v1/quote?symbol=" + ticker + "&token=" + Key.API_KEY;
		Stock stock = null;
//		Utilized this resource: https://zetcode.com/java/gson/
		try (InputStream is = new URL(link).openStream();
                Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)){
			
			Gson gson = new Gson();
			stock = gson.fromJson(reader, Stock.class);
			stock.setTicker(ticker);
		} catch (IOException e) {
			printOut("Failed to load stock with ticker: " + ticker);
		}
		return stock;
	}
	
	@SuppressWarnings("deprecation")
	public static String printOut(String message) {
		long timeDiff = System.currentTimeMillis() - ServerMain.startTime;
		Date date = new Date(timeDiff);
		date.setHours(0);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(sdf.format(date)).append("] ").append(message);
		return sb.toString();
	}
}
