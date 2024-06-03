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
  public double calculateXDayMovingAvg(LocalDate startDate, int days) {
    return 0;
  }

  @Override
  public List<LocalDate> findCrossovers(LocalDate startDate, LocalDate endDate, int days) {
    return List.of();
  }
}
