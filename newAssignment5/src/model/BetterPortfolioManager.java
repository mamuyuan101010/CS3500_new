package model;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * This is a Better Portfolio Manager class that stores a user's created portfolios.
 * This Manager will allow a user to differentiate between multiple portfolios
 * when attempting to query a specific one. It has more functionality in regard to
 * what a user can do with a portfolio. It can also load previously saved portfolios
 * from an XML file.
 */
public class BetterPortfolioManager implements IModelV2 {
  private final Map<String, PortfolioV2> portfolios;

  /**
   * Constructs an empty Portfolio object initialized with nothing.
   */
  public BetterPortfolioManager() {
    portfolios = new HashMap<>();
  }

  @Override
  public void createPortfolio(String name) {
    if (!hasPortfolio(name)) {
      portfolios.put(name, new BetterPortfolio());
    } else {
      throw new IllegalArgumentException("Portfolio with that name already exists");
    }
  }

  @Override
  public boolean hasPortfolio(String name) {
    return portfolios.containsKey(name);
  }

  @Override
  public Investment queryStock(String symbol, StockDataSource source) {
    return new Stock(symbol, source);
  }

  @Override
  public void addInvestmentToPortfolio(
          LocalDate date, String portfolioName, Investment stock, double quantity) {
    PortfolioV2 portfolio = portfolios.get(portfolioName);
    if (hasPortfolio(portfolioName)) {
      portfolio.addInvestment(date, stock, quantity);
    } else {
      throw new IllegalArgumentException("Portfolio not found: " + portfolioName);
    }
  }

  @Override
  public void sellInvestmentFromPortfolio(
          LocalDate date, String portfolioName, Investment stock, double quantity) {
    PortfolioV2 portfolio = portfolios.get(portfolioName);
    if (hasPortfolio(portfolioName)) {
      portfolio.sellInvestment(date, stock, quantity);
    } else {
      throw new IllegalArgumentException("Portfolio not found: " + portfolioName);
    }
  }

  @Override
  public void ReverseTransaction(
          LocalDate date, String portfolioName, Investment inv, double quantity, String type) {
    PortfolioV2 portfolio = portfolios.get(portfolioName);
    if (hasPortfolio(portfolioName)) {
      portfolio.reversal(date, inv, quantity, type);
    } else {
      throw new IllegalArgumentException("Portfolio not found: " + portfolioName);
    }
  }

  @Override
  public void getComposition(LocalDate date, String portfolioName) {
    PortfolioV2 portfolio = portfolios.get(portfolioName);
    if (hasPortfolio(portfolioName)) {
      portfolio.getComposition(date);
    } else {
      throw new IllegalArgumentException("Portfolio not found: " + portfolioName);
    }
  }

  @Override
  public void getDistributionValue(LocalDate date, String portfolioName, StockDataSource source) {
    PortfolioV2 portfolio = portfolios.get(portfolioName);
    if (hasPortfolio(portfolioName)) {
      portfolio.getDistributionVal(date, source);
    } else {
      throw new IllegalArgumentException("Portfolio not found: " + portfolioName);
    }
  }

  @Override
  public void savePortfolio(String portfolioName, String fileName) throws IOException {
    PortfolioV2 portfolio = portfolios.get(portfolioName);
    if (hasPortfolio(portfolioName)) {
      portfolio.saveToFile(fileName);
    } else {
      throw new IllegalArgumentException("Portfolio not found: " + portfolioName);
    }
  }


  @Override
  public void loadPortfolio(String filePath) throws IOException {
//    try {
//      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//      DocumentBuilder builder = factory.newDocumentBuilder();
//      Document doc = builder.parse(new File(filePath));
//      PortfolioV2 portfolio = new BetterPortfolio();
//
//      NodeList list = doc.getElementsByTagName("transaction");
//      for (int i = 0; i < list.getLength(); i++) {
//        Node transactionNode = list.item(i);
//      }
//
//
//    } catch (javax.xml.transform.TransformerException
//             | javax.xml.parsers.ParserConfigurationException | org.xml.sax.SAXException | java.io.IOException e) {
//      throw new IOException("Error: Could not save portfolio");
//    }
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
