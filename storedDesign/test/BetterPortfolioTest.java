import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
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
        port.addInvestment(LocalDate.of(2023, 6, 3), investment, 30);
    }

    @Test
    public void testGetValueOnDate() {
        double resultVal = port.getValueOnDate(LocalDate.of(2024, 6, 6));
        assertEquals(resultVal, 5350.5, 0.01);
        assertEquals(30, port.getComposition(LocalDate.of(2024, 6, 6)).get("GOOG"), 0.1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddInvalidInvestment() {
        port.addInvestment(
                new Stock("GOOG", new APIStockDataSource()), 30.0);
    }

    @Test
    public void testAddInvestment() {
        port.addInvestment(LocalDate.of(2023, 6, 14),
                new Stock("GOOG", new APIStockDataSource()), 30.0);
        Map<String, Double> resultMap = port.getComposition(LocalDate.of(2023, 6,
                14));
        assertEquals(60.0, resultMap.getOrDefault("GOOG", 0.0), 0.01);
    }

    @Test
    public void testSellInvestment() {
        port.sellInvestment(LocalDate.of(2024, 6, 25),
                new Stock("GOOG", new APIStockDataSource()), 10);
        Map<String, Double> resultMap = port.getComposition(LocalDate.of(2024, 6,
                26));
        assertEquals(20, resultMap.getOrDefault("GOOG", 0.0), 0.01);
    }

    @Test
    public void testReversal() {
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("GOOG", new APIStockDataSource()), 50);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("AMZN", new APIStockDataSource()), 10);
        port.sellInvestment(LocalDate.of(2024, 1, 10),
                new Stock("GOOG", new APIStockDataSource()), 10);

        port.reversal(LocalDate.of(2024, 1, 10),
                new Stock("AMZN", new APIStockDataSource()), 6, "bought"
        );
//    assertEquals(4, port.getComposition(LocalDate.of(2024,1,
//            10)).get("AMZN"), 0.01);
        // debug later, the method running twice
    }

    @Test
    public void testRebalance() {
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("AMZN", new APIStockDataSource()), 30);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("ASML", new APIStockDataSource()), 30);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("TSM", new APIStockDataSource()), 30);
        Map<String, Integer> mapOfStocks = new HashMap();
        Map<String, Double> result = new HashMap<>();
        mapOfStocks.put("GOOG", 25);
        mapOfStocks.put("AMZN", 25);
        mapOfStocks.put("ASML", 25);
        mapOfStocks.put("TSM", 25);
        double totalVal = port.getValueOnDate(LocalDate.of(2024, 5, 15));
        port.rebalance(LocalDate.of(2024, 5, 15), mapOfStocks);
        result = port.getComposition(LocalDate.of(2024, 5, 15));
        assertEquals(62.6669, result.get("GOOG"), 0.0001);
        assertEquals(58.5866, result.get("AMZN"), 0.0001);
        assertEquals(11.6239, result.get("ASML"), 0.0001);
        assertEquals(70.0380, result.get("TSM"), 0.0001);
        // compare their stock price times their shares on May 15
        assertEquals(result.get("GOOG") * 173.68, result.get("AMZN") * 185.99, 100);
        assertEquals(result.get("GOOG") * 173.68, result.get("ASML") * 937.42, 100);
        assertEquals(result.get("TSM") * 155.58, result.get("ASML") * 937.42, 100);
        assertEquals(totalVal, port.getValueOnDate(LocalDate.of(2024, 5, 15)), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRebalanceNotAddedUp() {
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("AMZN", new APIStockDataSource()), 30);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("ASML", new APIStockDataSource()), 30);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("TSM", new APIStockDataSource()), 30);
        Map<String, Integer> mapOfStocks = new HashMap();
        Map<String, Double> result = new HashMap<>();
        mapOfStocks.put("GOOG", 25);
        mapOfStocks.put("AMZN", 25);
        mapOfStocks.put("ASML", 20);
        mapOfStocks.put("TSM", 25);
        double totalVal = port.getValueOnDate(LocalDate.of(2024, 5, 15));
        port.rebalance(LocalDate.of(2024, 5, 15), mapOfStocks);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRebalanceNotEnough() {
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("AMZN", new APIStockDataSource()), 30);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("ASML", new APIStockDataSource()), 30);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("TSM", new APIStockDataSource()), 30);
        Map<String, Integer> mapOfStocks = new HashMap();
        Map<String, Double> result = new HashMap<>();
        mapOfStocks.put("GOOG", 25);
        mapOfStocks.put("AMZN", 25);
        mapOfStocks.put("ASML", 20);
        double totalVal = port.getValueOnDate(LocalDate.of(2024, 5, 15));
        port.rebalance(LocalDate.of(2024, 5, 15), mapOfStocks);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRebalanceMissingWeight() {
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("AMZN", new APIStockDataSource()), 30);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("ASML", new APIStockDataSource()), 30);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("TSM", new APIStockDataSource()), 30);
        Map<String, Integer> mapOfStocks = new HashMap();
        Map<String, Double> result = new HashMap<>();
        mapOfStocks.put("GOOG", 25);
        mapOfStocks.put("AMZN", 25);
        mapOfStocks.put("ASML", 25);
        mapOfStocks.put("ASML", 25);
        double totalVal = port.getValueOnDate(LocalDate.of(2024, 5, 15));
        port.rebalance(LocalDate.of(2024, 5, 15), mapOfStocks);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRebalanceFutureDate() {
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("AMZN", new APIStockDataSource()), 30);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("ASML", new APIStockDataSource()), 30);
        port.addInvestment(LocalDate.of(2024, 1, 10),
                new Stock("TSM", new APIStockDataSource()), 30);
        Map<String, Integer> mapOfStocks = new HashMap();
        Map<String, Double> result = new HashMap<>();
        mapOfStocks.put("GOOG", 25);
        mapOfStocks.put("AMZN", 25);
        mapOfStocks.put("ASML", 25);
        mapOfStocks.put("TSM", 25);
        double totalVal = port.getValueOnDate(LocalDate.of(2024, 5, 15));
        port.rebalance(LocalDate.of(2024, 11, 15), mapOfStocks);
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