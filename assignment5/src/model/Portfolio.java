package model;

import java.time.LocalDate;

/**
 * This interface specifies the required operations for all forms of portfolios
 * A portfolio holds investment objects.
 */
public interface Portfolio {

  /**
   * addInvestment method takes in an investment and quantity desired.
   *
   * @param investment    is the stock the user wishes to buy
   * @param quantity refers to how many shares the user would like to buy
   */
  void addInvestment(Investment investment, int quantity);

  /**
   * getValueOnDate takes a LocalDate object and gets the total value of a simple portfolio object
   * on a given day.
   *
   * @param date is the given date that is used for looking for the total value
   * @return the total value that the user will have based on the number of kinds of investments
   *         the user has.
   */
  double getValueOnDate(LocalDate date);
}
