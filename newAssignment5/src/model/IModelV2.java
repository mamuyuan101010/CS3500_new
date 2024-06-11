package model;

import java.io.IOException;
import java.time.LocalDate;

/**
 * This interface specifies the operations for an implementation of the main model of this program.
 * This V2 version has additional functionality compared to those only available to the
 * SimplePortfolio objects. This interface supports BetterPortfolios.
 */
public interface IModelV2 extends IModel{

  @Override
  //dates are needed, so methods extended from portfolio are not supported anymore
  //as we now need dates passed in
  default void addInvestmentToPortfolio(String portfolioName, Investment stock, int quantity) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * get the value of one of user's portfolios.
   *
   * @param name name of portfolio which user wants value of
   * @param date the date at which the portfolio is to be valued
   * @return the total value of all Investments in portfolio
   */
  double getValueOfPortfolio(String name, LocalDate date);

  /**
   * Adds a stock to one of user's portfolios.
   *
   * @param date The date on which to add the investment
   * @param portfolioName name of portfolio for stock to be added to
   * @param stock the stock to be added
   * @param quantity number of shares to be added
   */
  void addInvestmentToPortfolio(
          LocalDate date, String portfolioName, Investment stock, double quantity);

  /**
   * sells a stock from one of user's portfolios.
   *
   * @param date The date on which to sell the investment
   * @param portfolioName name of portfolio for stock to be sold from
   * @param stock the stock to be sold
   * @param quantity number of shares to be sold
   */
  void sellInvestmentFromPortfolio(
          LocalDate date, String portfolioName, Investment stock, double quantity);

  /**
   * Transactions must be made in chronological order. If a user messes up a transaction, they
   * can undo or reverse the transaction making it as if it never happened.
   *
   * @param date The date of the transaction to be reversed
   * @param portfolioName the name of the portfolio where the transaction resides
   * @param inv the investment involved
   * @param quantity the number of shares they would like to reverse (buy or sell)
   * @param type the type of the original transaction (was it a buy or a sell)
   */
  void ReverseTransaction(
          LocalDate date, String portfolioName, Investment inv, double quantity, String type);

  /**
   * This method gives the user the composition of their portfolio at a specified date in time.
   * A composition consists of the names of investments they hold and the quantity of that
   * investment that they hold
   *
   * @param date the date on which the user would like the composition of their portfolio
   */
  void getComposition(LocalDate date, String portfolioName);

  /**
   * This method gives the user the distribution of value of their portfolio at a specified
   * date in time. A distribution of value consists of the names of investments they hold and the
   * value of that investment that they hold
   *
   * @param date the date on which the user would like the composition of their portfolio
   */
  void getDistributionValue(LocalDate date, String portfolioName, StockDataSource source);

  /**
   * This method allows a user to save a created portfolio from this program to their computer.
   * The user specifies the absolute path of where they would like this file to be stored.
   *
   * @param fileName the absolute path of the location of where this file is to be stored.
   */
  void savePortfolio(String portfolioName, String fileName) throws IOException;

  /**
   * This method allows a user to load a saved portfolio from their program to this program.
   * The user specifies the absolute path of where they would like this file to be retrieved
   * from. Once loaded, this portfolio can do all the actions an internal program portfolio
   * can do.
   *
   * @param filename the absolute path of the location of where this file is to be acquired from.
   */
  void loadPortfolio(String filename) throws IOException;
}
