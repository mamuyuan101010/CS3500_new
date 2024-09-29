package model;

import java.time.LocalDate;

/**
 * This interface specifies the required operations for all forms of portfolios
 * A portfolio holds investment objects.
 */
public interface Portfolio {
  //CHANGES TO EXISTING DESIGN:
  //add sellInvestment method, the previous implementation only focused on buying which is why
  //this was overlooked, but it only makes sense for any sort of portfolio to be able to
  //remove objects if one can add objects to it.
  //SimplePortfolio now has a simple sellInvestment method that is the opposite of addInvestment.

  //changed addInvestment to take in a double quantity, not int quantity for 2 reasons:
  //1. The controller already uses Integer.parseInt to ensure that the user types in an int and
  //not a double. That invalid input is already accounted for at time of input, so no need to
  //double enforce that constraint.
  //2. By removing this double constraint, the programmer now has more flexibility when it comes to
  //any new or existing functionality that involves adding shares. The programmer can add partial
  //shares as needed for any computations as all that matters is that the user cannot buy
  //partial shares.

  /**
   * addInvestment method takes in an investment and quantity desired.
   *
   * @param investment is the stock the user wishes to buy
   * @param quantity refers to how many shares the user would like to buy
   */
  void addInvestment(Investment investment, double quantity);

  /**
   * sellInvestment method takes in an investment and quantity desired.
   *
   * @param investment is the stock the user wishes to sell
   * @param quantity refers to how many shares the user would like to sell
   */
  void sellInvestment(Investment investment, double quantity);

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
   *copy method generate a new Portfolio without link to the old one
   * @return a new Portfolio without link to the old one
   */
  Portfolio copy();
}
