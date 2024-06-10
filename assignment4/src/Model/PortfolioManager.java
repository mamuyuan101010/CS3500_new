package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a Portfolio Manager class that stores a user's created portfolios.
 * This Manager will allow a user to differentiate between multiple portfolios
 * when attempting to query a specific one.
 */
public class PortfolioManager implements IModel {
  private final Map<String, Portfolio> portfolios;

  /**
   * Constructs an empty Portfolio object initialized with nothing.
   */
  public PortfolioManager() {
    portfolios = new HashMap<>();
  }

  @Override
  public void createPortfolio(String name) {
    if (!hasPortfolio(name)) {
      portfolios.put(name, new SimplePortfolio());
    } else {
      throw new IllegalArgumentException("Portfolio with that name already exists");
    }
  }

  @Override
  public boolean hasPortfolio(String name) {
    return portfolios.containsKey(name);
  }

  @Override
  public Stock queryStock(String symbol, StockDataSource source) {
    return new Stock(symbol, source);
  }

  @Override
  public void addInvestmentToPortfolio(String portfolioName, Investment stock, int quantity) {
    Portfolio portfolio = portfolios.get(portfolioName);
    if (hasPortfolio(portfolioName)) {
      portfolio.addInvestment(stock, quantity);
    } else {
      throw new IllegalArgumentException("Portfolio not found: " + portfolioName);
    }
  }

  @Override
  public double getValueOfPortfolio(String name, LocalDate date) {
    if (hasPortfolio(name)) {
      return portfolios.get(name).getValueOnDate(date);
    } else {
      throw new IllegalArgumentException("Portfolio not found: " + name);
    }
  }
}
