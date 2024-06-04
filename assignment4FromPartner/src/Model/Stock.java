package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents one form of investment, stocks.
 * A stock has a ticker symbol associated with it and a list of historical data
 * regarding previous prices.
 */
public class Stock implements Investment {
  private final String ticker;
  private final List<StockData> data;


  /**
   * Constructs a Stock object with its ticker symbol.
   *
   * @param ticker the ticker value associated with the stock
   */
  public Stock(String ticker, StockDataSource source) {
    this.ticker = ticker;
    this.data = source.fetchData(ticker);
  }

  /**
   * a nested class representing a StockData object.
   * Holds information of prices for a specific day
   * Stock object will have a list of multiple StockData objects.
   */
  public static class StockData {
    private final LocalDate date;
    private final double openPrice;
    private final double highPrice;
    private final double lowPrice;
    private final double closePrice;
    private final int volume;

    /**
     * Constructor of StockData.
     *
     * @param d   is the date of the stock
     * @param op  is the open price of the stock
     * @param hp  is the high price of the stock
     * @param lp  is the low price of the stock
     * @param cp  is the close price of the stock
     * @param vol is the volume of the stock
     */
    public StockData(LocalDate d, double op, double hp, double lp, double cp, int vol) {
      this.date = d;
      this.openPrice = op;
      this.highPrice = hp;
      this.lowPrice = lp;
      this.closePrice = cp;
      this.volume = vol;
    }
  }

  @Override
  public double fetchGainOrLoss(LocalDate startDate, LocalDate endDate)
          throws IllegalArgumentException {
    double startingPrice = -1;
    double endingPrice = -1;

    if (startDate.isAfter(endDate)) {
      throw new IllegalArgumentException("End date must come after start date");
    }

    for (StockData point : this.data) {
      if (point.date.equals(startDate)) {
        startingPrice = point.closePrice;
      }
      if (point.date.equals(endDate)) {
        endingPrice = point.closePrice;
      }
    }

    if (startingPrice == -1 || endingPrice == -1) {
      throw new IllegalArgumentException("Invalid date range or data not available.");
    }

    return (endingPrice - startingPrice);
  }

  @Override
  public double calculateXDayMovingAvg(LocalDate startDate, int days)
          throws IllegalArgumentException, IndexOutOfBoundsException {
    if (days <= 0) {
      throw new IllegalArgumentException("days must be greater than 0");
    }

    int recentIdx = -1;
    int startIdx = -1;

    for (StockData point : this.data) {
      if (point.date.equals(startDate)) {
        recentIdx = data.indexOf(point);
        startIdx = recentIdx + days;
      }
    }
    if (recentIdx == -1) {
      throw new IllegalArgumentException("data not available for specified date");
    }
    else if (startIdx < 0) {
      throw new IndexOutOfBoundsException("data cannot go back that far");
    }

    List<StockData> acquiredData = data.subList(recentIdx, startIdx);
    double total = 0.0;
    for (StockData point : acquiredData) {
      total += point.closePrice;
    }
    return total / acquiredData.size();
  }

  @Override
  public List<LocalDate> findCrossovers(LocalDate startDate, LocalDate endDate, int days) {
    List<LocalDate> returnList = new ArrayList<>();
    int OldIndex = -1;
    int RecentIndex = -1;

    if (startDate.isAfter(endDate)) {
      throw new IllegalArgumentException("End date must come after start date");
    }

    for (StockData point : this.data) {
      if (point.date.equals(startDate)) {
        OldIndex = data.indexOf(point);
      }
      if (point.date.equals(endDate)) {
        RecentIndex = data.indexOf(point);
      }
    }
    if (OldIndex == -1 || RecentIndex == -1) {
      throw new IllegalArgumentException("data not found on given dates");
    }
    for (int i = RecentIndex; i <= OldIndex; i++) {
      if (data.get(i).closePrice > calculateXDayMovingAvg(data.get(i).date, days)) {
        returnList.add(data.get(i).date);
      }
    }
    return returnList;
  }
}
