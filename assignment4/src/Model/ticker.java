package Model;

import java.util.HashMap;
import java.util.Map;

class Ticker {
  private static Map<String, String> ticker = new HashMap<String,String>();

  static void addTicker(String tickerSymbol, String tickerName) {
    ticker.put(tickerSymbol, tickerName);
  }

  static String getTicker(String tickerSymbol) {
    return ticker.getOrDefault(tickerSymbol,null);
  }
}
