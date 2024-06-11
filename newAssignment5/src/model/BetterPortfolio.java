package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
  public void addInvestment(LocalDate date, Investment inv, double quantity)
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
      if (t.getDate().isAfter(date)) {
        break;
      }
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
      sellInvestment(date, inv, quantity);
      transactions.add(new Transactions(inv.toString(), quantity, date, Transactions.Types.Sell));
    } else {
      addInvestment(date, inv, quantity);
      transactions.add(new Transactions(inv.toString(), quantity, date, Transactions.Types.Buy));
    }
  }

  @Override
  public void rebalance(LocalDate date, Investment inv, double quantity, String type)
          throws IllegalArgumentException {
    if (!type.equals("bought") && !type.equals("sold")) {
      throw new IllegalArgumentException("unrecognized type of original transaction");
    }

    if (type.equals("bought")) {
      sellInvestment(date, inv, quantity);
      transactions.add(new Transactions(inv.toString(), quantity, date, Transactions.Types.Sell));
    } else {
      addInvestment(date, inv, quantity);
      transactions.add(new Transactions(inv.toString(), quantity, date, Transactions.Types.Buy));
    }
  }

  @Override
  public Map<String, Double> getComposition(LocalDate date) {
    Map<String, Double> composition = new HashMap<>();

    for (Transactions t : transactions) {
      if (t.getDate().isAfter(date)) {
        break;
      }
      composition.putIfAbsent(t.getTicker(), 0.0);
      double currQuant = composition.get(t.getTicker());
      double change = 0;
      if (t.getType().equals(Transactions.Types.Buy)) {
        change += t.getShares();
      } else {
        change -= t.getShares();
      }
      composition.put(t.getTicker(), currQuant + change);
    }
    return composition;
  }

  @Override
  public Map<String, Double> getDistributionVal(LocalDate date, StockDataSource source) {
    Map<String, Double> composition = getComposition(date);
    Map<String, Double> distribution = new HashMap<>();
    for (Map.Entry<String, Double> entry : composition.entrySet()) {
      String ticker = entry.getKey();
      double quantity = entry.getValue();
      Investment stock = new Stock(ticker, source);

      double closingPrice = stock.getEndOfDayVal(date);
      double value = closingPrice * quantity;
      distribution.put(ticker, value);
    }
    return distribution;
  }

  @Override
  public void saveToFile(String filename) throws IOException {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.newDocument();

      Element root = doc.createElement("Portfolio");
      doc.appendChild(root);

      for (Transactions t : transactions) {
        Element transactionElement = doc.createElement("transaction");
        root.appendChild(transactionElement);

        Element ticker = doc.createElement("ticker");
        ticker.appendChild(doc.createTextNode(t.getTicker()));
        transactionElement.appendChild(ticker);

        Element quantity = doc.createElement("quantity");
        quantity.appendChild(doc.createTextNode(Double.toString(t.getShares())));
        transactionElement.appendChild(quantity);

        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode(t.getDate().toString()));
        transactionElement.appendChild(date);

        Element type = doc.createElement("type");
        type.appendChild(doc.createTextNode(t.getType().toString()));
        transactionElement.appendChild(type);
      }

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(filename));
      transformer.transform(source, result);
    } catch (javax.xml.transform.TransformerException | javax.xml.parsers.ParserConfigurationException e) {
      throw new IOException("Error: Could not save portfolio");
    }
  }

}
