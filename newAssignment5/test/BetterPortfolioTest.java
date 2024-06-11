import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Map;

import model.APIStockDataSource;
import model.BetterPortfolio;
import model.Investment;
import model.PortfolioV2;
import model.Stock;

import static org.junit.Assert.assertEquals;

public class BetterPortfolioTest {
  PortfolioV2 port;

  @Before
  public void setUp() {
    port = new BetterPortfolio();
    Investment investment = new Stock("GOOG", new APIStockDataSource());
    port.addInvestment(LocalDate.of(2023,6,3), investment, 30);
  }
  @Test
  public void testGetValueOnDate() {
    double resultVal = port.getValueOnDate(LocalDate.of(2024,6,6));
    assertEquals(resultVal, 5350.5, 0.01);
    assertEquals(30, port.getComposition(LocalDate.of(2024,6,6)).get("GOOG"), 0.1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddInvalidInvestment()  {
    port.addInvestment(
            new Stock("GOOG", new APIStockDataSource()), 30.0);
  }

  @Test
  public void testAddInvestment() {
    port.addInvestment(LocalDate.of(2023,6,14),
            new Stock("GOOG", new APIStockDataSource()), 30.0);
    Map<String, Double> resultMap = port.getComposition(LocalDate.of(2023,6,
            14));
    assertEquals( 60.0, resultMap.getOrDefault("GOOG", 0.0),0.01);
  }

  @Test
  public void testSellInvestment() {
    port.sellInvestment(LocalDate.of(2024,6,25),
            new Stock("GOOG", new APIStockDataSource()), 10);
    Map<String, Double> resultMap = port.getComposition(LocalDate.of(2024,6,
            26));
    assertEquals( 20, resultMap.getOrDefault("GOOG", 0.0),0.01);
  }

  @Test
  public void testReversal() {
    port.addInvestment(LocalDate.of(2024,6,25),
            new Stock("GOOG", new APIStockDataSource()), 50);
    port.addInvestment(LocalDate.of(2024,6,25),
            new Stock("AMZN", new APIStockDataSource()), 10);
    port.sellInvestment(LocalDate.of(2024,6,25),
            new Stock("GOOG", new APIStockDataSource()), 10);

    port.reversal(LocalDate.of(2024,6,25),
            new Stock("AMZN", new APIStockDataSource()),6, "bought"
            );
    assertEquals(4, port.getComposition(LocalDate.of(2024,6,
            25)).get("AMZN"), 0.01);
  }

  @Test
  public void testRebalance() {
  }

  @Test
  public void testGetComposition() {
  }

  @Test
  public void testGetDistributionVal() {
  }

  @Test
  public void testSaveToFile() {
  }
}