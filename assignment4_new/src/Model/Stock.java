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

  /**
   * fetchGainOrLoss method return a boolean number of the money the stock earn,
   * if the buying time is the startDate, and the selling time is the endDate
   * if loss money, the return boolean will be negative.
   * @param startDate the start date of the period to be examined
   * @param endDate   the end date of the period to be examined
   * @return a double amount of money that represent the money
   * earned from the startDate to the endDate.
   * @throws IllegalArgumentException if the given start or end date is invalid.
   */
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

  // return the index of StockData that has the same date of the given one
  // if the StockData does not contain the StockData with the given date,
  // an illegal exception will be thrown.
  private int getIndexFromDate(LocalDate date) {
    for (int i = 0; i < data.size(); i++) {
      if (data.get(i).date.equals(date)) {
        return i;
      }
    }
    throw new IllegalArgumentException("dateNoteFound");
  }

  double getClosingPriceFromDate(LocalDate date) {
    return data.get(getIndexFromDate(date)).closePrice;
  }

  int getVolumeFromDate(LocalDate date) {
    return data.get(getIndexFromDate(date)).volume;
  }

//  @Override
//  public double calculateXDayMovingAvg(LocalDate startDate, int days) {
//    if (days <= 0) {
//      throw new IllegalArgumentException("days must be greater than 0");
//    }
//    // abstract the subList from the data, start from the x days before the given startDate
//    // and end at the given startDate.
//    List<StockData> returnDataList = data.subList(getIndexFromDate(startDate) - days,
//            getIndexFromDate(startDate) + 1);
//    double totalClosingPrice = 0;
//    for (StockData stockData : returnDataList) {
//      // calculate total price
//      totalClosingPrice += stockData.closePrice;
//    }
//    return totalClosingPrice / returnDataList.size(); // get the average closing price
//  }

  @Override
  public double calculateXDayMovingAvg(LocalDate startDate, int days) {
    if (days <= 0) {
      throw new IllegalArgumentException("days must be greater than 0");
    }

    int recentIdx = -1;
    int startIdx = -1;

    for (StockData point : this.data) {
      if (point.date.equals(startDate)) {
        recentIdx = data.indexOf(point);
        startIdx = startIdx - days;
      }
    }
    if (recentIdx == -1 || startIdx < 0) {
      throw new IllegalArgumentException("data not available for specified dates");
    }
    List<StockData> acquiredData = data.subList(startIdx, recentIdx + 1);
    double total = 0.0;
    for (StockData point : acquiredData) {
      total += point.closePrice;
    }
    return total / acquiredData.size();
  }

  /**
   * findCrossOver method is a method that takes a range of dates, and
   * check each date in teh range of dates whether their close price is greater
   * than their x day moving average.
   * @param startDate the start date of the specified range of days
   * @param endDate the end date of the specified range of days
   * @param days the positive number of days for the crossovers to be determined from
   * @return the List of dates that has greater closing price than their
   *     x day moving average (x is the given integer "days")
   */
  @Override
  public List<LocalDate> findCrossovers(LocalDate startDate, LocalDate endDate, int days) {
    List<LocalDate> returnList = new ArrayList<LocalDate>();
    int indexStart = -1;
    int indexEnd = -1;
    for (StockData point : this.data) {
      if (point.date.equals(startDate)) {
        indexStart = data.indexOf(point);
      }
      if (point.date.equals(endDate)) {
        indexEnd = data.indexOf(point);
      }
    }
    if (indexStart == -1 || indexEnd == -1) {
      throw new IllegalArgumentException("data not found on given dates");
    }
    if (indexStart > indexEnd) {
      throw new IllegalArgumentException("start index exceeds end index");
    }
    for (int i = indexStart; i <= indexEnd; i++) {
      if (data.get(i).closePrice > calculateXDayMovingAvg(data.get(i).date, days)) {
        returnList.add(data.get(i).date);
      }
    }
    return returnList;
  }
}
