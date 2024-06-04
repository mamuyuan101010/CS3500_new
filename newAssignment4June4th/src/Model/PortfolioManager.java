package Model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a Portfolio Manager class that stores a user's created portfolios.
 * This Manager will allow a user to differentiate between multiple portfolios
 * when attempting to query a specific one.
 */
public class PortfolioManager {
  private final Map<String, Portfolio> portfolios;

  /**
   * Constructs an empty Portfolio object initialized with nothing.
   */
  public PortfolioManager() {
    portfolios = new HashMap<>();
  }

  /**
   * creates a portfolio with a specific name.
   */
  public void createPortfolio(String name) {
    if (!hasPortfolio(name)) {
      portfolios.put(name, new SimplePortfolio());
    } else {
      throw new IllegalArgumentException("Portfolio with that name already exists");
    }
  }

  /**
   * sees if portfolio exists in this manager
   * @param name name of portfolio to see if it exists
   * @return whether it is in portfolio manager or not
   */
  public boolean hasPortfolio(String name) {
    return portfolios.containsKey(name);
  }

  /**
   * Returns a stock object that can be queried for further analysis
   *
   * @param symbol ticker symbol of stock to be queried
   * @param source the source the use would like to use for the stock
   * @return a stock object matching the description entered
   */
  public Stock queryStock(String symbol, StockDataSource source) {

    return new Stock(symbol, source);
  }

  /**
   * Adds a stock to one of user's portfolios
   *
   * @param portfolioName name of portfolio for stock to be added to
   * @param stock the stock to be added
   * @param quantity number of shares to be added
   */
  public void addInvestmentToPortfolio(String portfolioName, Investment stock, int quantity) {
    Portfolio portfolio = portfolios.get(portfolioName);
    if (portfolio != null) {
      portfolio.addInvestment(stock, quantity);
    } else {
      throw new IllegalArgumentException("Portfolio not found: " + portfolioName);
    }
  }

  /**
   * get the value of one of user's portfolios
   *
   * @param name name of portfolio which user wants value of
   * @param date the date at which the portfolio is to be valued
   * @return the total value of all Investments in portfolio
   */
  public double getValueOfPortfolio(String name, LocalDate date) {
    if (portfolios.containsKey(name)) {
      return portfolios.get(name).getValueOnDate(date);
    } else {
      throw new IllegalArgumentException("Portfolio not found: " + name);
    }
  }
}
