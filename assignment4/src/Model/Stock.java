package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents one form of security, stocks.
 */
public class Stock implements Security {
  private final String ticker;
  private final List<StockData> data;


  /**
   * Constructs a Stock object with its ticker, useful for programmer
   * and the full name, which is user-friendly and retrievable if needed.
   * Uses SimpleImmutableEntry to associate the two values together
   * @param ticker the ticker value associated with the stock
   * //@param name the full name of the stock
   */
  public Stock(String ticker) {
    this.ticker = ticker;
    this.data = new ArrayList<>();
  }

  /**
   * a nested class representing a StockData object.
   * Holds information of prices for a specific day
   * Stock object will have a list of multiple StockData objects.
   */
  static class StockData {
    private final LocalDate date;
    private final double openPrice;
    private final double highPrice;
    private final double lowPrice;
    private final double closePrice;
    private final int volume;

    /**
     * Constructor of StockData.
     * @param d is the date of the stock
     * @param op is the open price of the stock
     * @param hp is the high price of the stock
     * @param lp is the low price of the stock
     * @param cp is the close price of the stock
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

    private boolean dateMatch(LocalDate date) {
      return this.date.equals(date);
    }
  }

  /**
   * addData takes an StockData object and add the given object to the Stock's data field.
   * @param obj is the given stockData
   */
  public void addData(StockData obj) {
    this.data.add(obj);
  }



  /**
   * retrieves the ticker symbol of stock
   * @return ticker symbol
   */
  public String getTicker() {
    return this.ticker;
  }

  /**
   * retrieves the name of stock
   * @return full name of stock
   */
  public String getName() {
    return Ticker.getTicker(this.ticker);
  }

  public void addTicker(String tickerSyb, String tickerName) {
    Ticker.addTicker(tickerSyb, tickerName);
  }

  /**
   * getchGainOrLoss method take in
   * @param startDate the start date of the period to be examined
   * @param endDate   the end date of the period to be examined
   * @return the double number of the close price from endDate minus that from start Date
   *     loss will be negative, gain will be positive.
   */
  @Override
  public double fetchGainOrLoss(LocalDate startDate, LocalDate endDate) {
    return this.getClosePriceFromDate(endDate) - this.getClosePriceFromDate(startDate);
  }

  // take in the date that given and return the closePrice that match the date
  // if no close price match, throw exception.
  private double getClosePriceFromDate(LocalDate date) {
    for (StockData stockData : data) {
      if (stockData.dateMatch(date)) {
        return stockData.closePrice;
      }
    }
    throw new IllegalArgumentException("dateNoteFound");
  }



  @Override
  public double calculateXDayMovingAvg(LocalDate startDate, int days) {
    if (days <= 0) {
      throw new IllegalArgumentException("days must be greater than 0");
    }
    int calculatingDays = days;
    double totalPrice = 0;
    while (days > 0) {
      totalPrice += getClosePriceFromDate(startDate);
      // edit minusDay
      startDate = startDate.minusDays(1);
      days -= 1;
    }
    return totalPrice / calculatingDays;
  }

  @Override
  public List<LocalDate> findCrossovers(LocalDate startDate, LocalDate endDate, int days) {
    return List.of();
  }
}
