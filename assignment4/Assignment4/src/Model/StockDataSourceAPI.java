package Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.time.LocalDate;

public class StockDataSourceAPI implements StockDataSource {
  private final String apiKey = "HQXG89K57GNO2RJP";
  private final String stockSymbol; // user input the stockSymbol
  private URL url = null;

  public StockDataSourceAPI(String stockSymbol) throws MalformedURLException, IOException {
    this.stockSymbol = stockSymbol;
  }

  public Stock fetchData() {
    try {
      /*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       */
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    Stock stock = new Stock(stockSymbol);

    try {
      in = url.openStream();
      Scanner sc = new Scanner(in);

      //skip header line
      String header = sc.nextLine();
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] parameters = line.split(",");

        LocalDate ld = LocalDate.parse(parameters[0]);
        double op = Double.parseDouble(parameters[1]);
        double hp = Double.parseDouble(parameters[2]);
        double lp = Double.parseDouble(parameters[3]);
        double cp = Double.parseDouble(parameters[4]);
        int vol = Integer.parseInt(parameters[5]);

        Stock.StockData data = new Stock.StockData(ld, op, hp, lp, cp, vol);
        stock.addData(data);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    return stock;
  }
}
