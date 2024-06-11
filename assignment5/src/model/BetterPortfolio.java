package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents enhanced and new functionality for the existing SimplePortfolio
 * class. Allows the user to perform more operations with their portfolio.
 */
public class BetterPortfolio implements PortfolioV2 {
  private final SimplePortfolio delegate;
  private final List<Transactions> transactions;

  /**
   * Constructs an empty Portfolio object initialized with nothing.
   */
  public BetterPortfolio() {
    delegate = new SimplePortfolio();
    transactions = new ArrayList<>();
  }

  @Override
  public double getValueOnDate(LocalDate date) {
    return delegate.getValueOnDate(date);
  }

  @Override
  public void addInvestment(LocalDate date, Investment inv, int quantity)
          throws IllegalArgumentException {
    delegate.addInvestment(inv, quantity);
    Transactions t =
            new Transactions(inv.toString(), quantity, date, Transactions.Types.Buy);
    transactions.add(t);
  }

  @Override
  public void sellInvestment(LocalDate date, Investment investment, double quantity)
          throws IllegalArgumentException {
    if (!transactions.isEmpty()) {
      if (date.isBefore(transactions.get(transactions.size() - 1).getDate())) {
        throw new IllegalArgumentException("Must conduct sales in chronological order");
      }
    } else {
      double availQuant = getAvailableQuantity(date, investment.toString());
      if (quantity > availQuant) {
        throw new IllegalArgumentException(
                "Not enough shares to sell. "
                        + "Available: " + availQuant
                        + ", Requested: " + quantity);
      }
      delegate.sellInvestment(investment, quantity);
      Transactions t = new Transactions(
              investment.toString(), quantity, date, Transactions.Types.Sell);
      transactions.add(t);
    }
  }

  //helper method for logic determining if there are enough shares to sell
  private double getAvailableQuantity(LocalDate date, String investmentName) {
    double quantity = 0;
    for (Transactions t : transactions) {
      if (t.getDate().isAfter(date)) break;
      if (t.getTicker().equals(investmentName)) {
        if (t.getType() == Transactions.Types.Buy) {
          quantity += t.getShares();
        } else if (t.getType() == Transactions.Types.Sell) {
          quantity -= t.getShares();
        }
      }
    }
    return quantity;
  }

  @Override
  public void reversal(LocalDate date, Investment inv, double quantity, String type)
          throws IllegalArgumentException {
    if (!type.equals("bought") && !type.equals("sold")) {
      throw new IllegalArgumentException("unrecognized type of original transaction");
    }

    if (type.equals("bought")) {
      //sell
    } else {
      //buy
    }
  }

  //save

  //load

  //composition

  //composition value

  //re balance

  ///new better portfolio manager
  ///add commands
  ///testing
}
