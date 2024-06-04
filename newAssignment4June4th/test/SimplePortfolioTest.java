import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import Model.APIStockDataSource;
import Model.Investment;
import Model.Portfolio;
import Model.SimplePortfolio;
import Model.Stock;
import Model.StockDataSource;

import static org.junit.Assert.assertEquals;

public class SimplePortfolioTest {
  Investment googleStock;
  //initialize it here so API query limit doesn't get overloaded with each test
  //only called once every time the entire test file has to load.
  StockDataSource apiSource = new APIStockDataSource();

  @Before
  public void setUp() {
    googleStock = new Stock("GOOG", apiSource);
  }

  @Test
  public void addInvestmentAndValue() {
    LocalDate recent = LocalDate.parse("2024-05-29");
    Portfolio port = new SimplePortfolio();
    port.addInvestment(googleStock, 6);
    assertEquals(177.4 * 6, port.getValueOnDate(recent), 0.01);
  }

}