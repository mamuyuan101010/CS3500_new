package model;

import java.time.LocalDate;

/**
 * This class represents a transaction object. A transaction holds information such as
 * stock involved, amount of shares, date of transaction, and type of transaction
 */
public class Transactions {
  public enum Types {
    Buy, Sell;
  }
  private final String ticker;
  private final double shares;
  private final LocalDate date;
  private final Types type;

  public Transactions(String ticker, double shares, LocalDate date, Types type) {
    this.ticker = ticker;
    this.shares = shares;
    this.date = date;
    this.type = type;
  }

  /**
   * Convenient getter for referencing stock involved in transaction.
   * @return String ticker
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Convenient getter for referencing shares involved in transaction.
   * @return number of shares
   */
  public double getShares() {
    return shares;
  }

  /**
   * Convenient getter for referencing date involved in transaction.
   * @return date of transaction
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Convenient getter for referencing type of transaction.
   * @return Transaction type
   */
  public Types getType() {
    return type;
  }

  @Override
  public String toString() {
    return "Ticker: " + ticker + "\tShares: " + shares + "\tDate: " + date + "\tType: " + type;
  }
}
