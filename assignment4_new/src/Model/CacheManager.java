package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a centralized local cache for stock data. Any class that implements
 * StockDataSource interface will cache fetched data to this cacheManager, allowing local caching
 * and storage of stock data from any source, whether it be API, file, or manual entry, etc.
 * This class works and interacts with classes of type StockDataSource.
 */
public class CacheManager {
  private static final Map<String, List<Stock.StockData>> stockCache = new HashMap<>();

  static List<Stock.StockData> fetchData(String stockSymbol) {
    return stockCache.get(stockSymbol);
  }

  static void cacheData(String stockSymbol, List<Stock.StockData> data) {
    //maybe change to account for updating queries
    stockCache.put(stockSymbol, data);
  }

  public static boolean isDataCached(String stockSymbol) {
    return stockCache.containsKey(stockSymbol);
  }
}
