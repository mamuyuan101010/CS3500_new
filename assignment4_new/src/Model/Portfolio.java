package Model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
  private Map<Stock, Integer> stocksPossess;

  public Portfolio() {
    this.stocksPossess = new HashMap<Stock, Integer>();
  }

  /**
   * addInvestment method intake an stock and the number of share
   * @param stock is the stock the user buy
   * @param shares is the number of shares the user buy
   */
  public void addStock(Stock stock, int shares, LocalDate date) {
    if (shares > stock.getVolumeFromDate(date)) {
      throw new IllegalArgumentException("cannot buy stock at the"
              +
              " number more than the volume on that day");
    }
    this.stocksPossess.put(stock, shares);
  }

  /**
   * getPriceOnDate method is the method that takes a date object and get the total value the user
   * has on the given date following the number and name of the stock the user possess.
   * @param date is the given date that used for looking for the total value
   * @return the total value that the user will have based on the number of kinds of stock the user
   * have.
   */
  public double getPriceOnDate(LocalDate date) {
    double totalPrice = 0.00;
    for (Map.Entry<Stock, Integer> entry : stocksPossess.entrySet()) {
      totalPrice += entry.getKey().getClosingPriceFromDate(date) * entry.getValue();
    }
    return totalPrice;
  }
}
