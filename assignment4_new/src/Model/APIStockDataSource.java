package Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * One way for the program to obtain stock data is through an API.
 * This class represents data obtained through the Alpha Vantage API.
 */
public class APIStockDataSource implements StockDataSource {
  private static final Map<String, List<Stock.StockData>> stockCache = new HashMap<>();

  /**
   * Constructs an object that represents a stock with its symbol.
   */
  public APIStockDataSource() {}

  public List<Stock.StockData> fetchData(String stockSymbol) {

    //check if the stock has already been queried
    //if so, use local cache instead of making a new query again
    if (CacheManager.isDataCached(stockSymbol)) {
      return CacheManager.fetchData(stockSymbol);
    }

    URL url;
    List<Stock.StockData> results = new ArrayList<>();
    try {
      //String apiKey = "HQXG89K57GNO2RJP";
      String apiKey = "W0M1JOKC82EZEQA8";
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in;

    try {
      in = url.openStream();
      Scanner sc = new Scanner(in);

      //skip header line
      sc.nextLine();

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
        results.add(data);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }

    CacheManager.cacheData(stockSymbol, results);
    return results;
  }

}
