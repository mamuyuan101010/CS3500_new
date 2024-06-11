package model;

import java.time.LocalDate;

/**
 * This PortfolioV2 interface specifies the operations for a better and
 * more complex portfolio than defined before. It builds off of previously
 * specified operations by enhancing or adding functionality.
 */
public interface PortfolioV2 {

  /**
   * addInvestment method takes in an investment and quantity desired.
   * It also takes in a date on when the user wishes to buy the investment
   *
   * @param date is when the user wishes to buy the investment
   * @param investment    is the stock the user wishes to buy
   * @param quantity refers to how many shares the user would like to buy
   */
  void addInvestment(LocalDate date, Investment investment, int quantity)
          throws IllegalArgumentException;

  /**
   * sellInvestment method takes in an investment and quantity desired.
   * It also takes in a date on when the user wishes to sell the investment.
   * sells investment from user portfolio in valid cases when there are appropriate
   * investments to be sold
   *
   * @param date is when the user wishes to buy the investment
   * @param investment    is the stock the user wishes to buy
   * @param quantity refers to how many shares the user would like to buy
   */
  void sellInvestment(LocalDate date, Investment investment, double quantity)
          throws IllegalArgumentException;

  /**
   * getValueOnDate takes a LocalDate object and gets the total value of a simple portfolio object
   * on a given day.
   *
   * @param date is the given date that is used for looking for the total value
   * @return the total value that the user will have based on the number of kinds of investments
   *         the user has.
   */
  double getValueOnDate(LocalDate date);

  /**
   * Allows a user to reverse a sale if a previous transaction made mistakenly renders their
   * desired transaction to be invalid
   * @param date the date on which to reverse transaction
   * @param inv the investment to be reversed
   * @param quantity the amount of shares to buy or sell
   * @param type the original type of transaction, buy or sell
   */
  void reversal(LocalDate date, Investment inv, double quantity, String type);

}
