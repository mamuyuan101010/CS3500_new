package Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a centralized local cache for stock data. Any class that implements
 * StockDataSource interface will cache fetched data to this cacheManager, allowing local caching
 * and storage of stock data from any source, whether it be API, file, or manual entry, etc.
 * This class works and interacts with classes of type StockDataSource.
 * Only one instance should exist in the program.
 */
public class CacheManager {
  private static final Map<String, List<Stock.StockData>> stockCache = new HashMap<>();
  // Static member to hold the single instance
  private static CacheManager instance;

  private CacheManager(){}

  public static CacheManager getInstance()
  {
    if (instance == null)
      instance = new CacheManager();
    return instance;
  }

  List<Stock.StockData> fetchData(String stockSymbol) {
    return stockCache.get(stockSymbol);
  }

  void cacheData(String stockSymbol, List<Stock.StockData> data) {
    //maybe change to account for updating queries
    stockCache.put(stockSymbol, data);
  }

  public boolean isDataCached(String stockSymbol) {
    return stockCache.containsKey(stockSymbol);
  }
}
