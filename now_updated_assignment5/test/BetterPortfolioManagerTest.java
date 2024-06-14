import model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BetterPortfolioManagerTest {

    BetterPortfolioManager portfolioManager;
    BetterPortfolioManager portfolioManager2;

    @Before
    public void setUp() throws Exception {
        // portfolioManager is a new portfolio without anything.
        // portfolioManager2 is a portfolio with a new portfolio named
        // portfolio2 inside.
        portfolioManager = new BetterPortfolioManager();
        portfolioManager2 = new BetterPortfolioManager();
        portfolioManager2.createPortfolio("portfolio2");
    }

    @Test
    public void createPortfolio() {
        portfolioManager.createPortfolio("example");
        assertTrue(portfolioManager.hasPortfolio("example"));
        try {
            portfolioManager.createPortfolio("example");
            fail("should fail here");
        } catch (Exception e) {
            assertEquals("Portfolio with that name already exists",
                    e.getMessage());
        }
        portfolioManager.createPortfolio("example2");
        assertTrue(portfolioManager.hasPortfolio("example2"));
    }

    @Test
    public void addInvestmentToPortfolio() {
        Investment googleStock = new Stock("GOOG", new APIStockDataSource());
        Investment amazonStock = new Stock("AMZN", new APIStockDataSource());
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2020, 1, 1),
                "portfolio2", googleStock, 4);
        Map<String, Double> emptyMap = portfolioManager2.getComposition(LocalDate.of(2019,
                12, 31), "portfolio2");
        for (Map.Entry<String, Double> entry : emptyMap.entrySet()) {
            assertEquals(entry.getValue(), 0.0, 0.001);
        }

        Map<String, Double> googleMap = portfolioManager2.getComposition(LocalDate.of(2020,
                1, 1), "portfolio2");
        for (Map.Entry<String, Double> entry : googleMap.entrySet()) {
            assertEquals(4, entry.getValue(), 0.001);
            assertEquals("GOOG", entry.getKey());
        }

        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022, 1, 3),
                "portfolio2", amazonStock, 5);
        Map<String, Double> googleAmzMap = portfolioManager2.getComposition(LocalDate.of(2022,
                1, 4), "portfolio2");
        assertEquals(4, googleAmzMap.getOrDefault("GOOG", 0.0), 0.01);
        assertEquals(5, googleAmzMap.getOrDefault("AMZN", 0.0), 0.01);


        Map<String, Double> oldGoogleAmzMap = portfolioManager2.getComposition(LocalDate.of(2022,
                1, 2), "portfolio2");
        assertEquals(4, googleAmzMap.getOrDefault("GOOG", 0.0), 0.01);
        assertEquals(0, googleAmzMap.getOrDefault("AMZN", 0.0), 0.01);
        // AMZN should be zero here, but it does not. Fix this bug later

    }

    @Test
    public void sellInvestmentFromPortfolio() {

        Investment googleStock = new Stock("GOOG", new APIStockDataSource());
        Investment amazonStock = new Stock("AMZN", new APIStockDataSource());
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2020, 1, 1),
                "portfolio2", googleStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022, 1, 3),
                "portfolio2", amazonStock, 5);
        Map<String, Double> googleAmzMap = portfolioManager2.getComposition(LocalDate.of(2022,
                1, 4), "portfolio2");
        assertEquals(4, googleAmzMap.getOrDefault("GOOG", 0.0), 0.01);
        assertEquals(5, googleAmzMap.getOrDefault("AMZN", 0.0), 0.01);

        portfolioManager2.sellInvestmentFromPortfolio(LocalDate.of(2023, 1, 3),
                "portfolio2", amazonStock, 3);
        Map<String, Double> amazonLessBy3Data = portfolioManager2.getComposition(LocalDate.of(2023,
                        1, 4),
                "portfolio2");
        assertEquals(4, amazonLessBy3Data.getOrDefault("GOOG", 0.0), 0.01);
        assertEquals(2, amazonLessBy3Data.getOrDefault("AMZN", 0.0), 0.01);
        // the previous date's value should remain
        Map<String, Double> oldAmazonLessBy3Data = portfolioManager2.getComposition(LocalDate.of(2023,
                        1, 2),
                "portfolio2");
        assertEquals(4, oldAmazonLessBy3Data.getOrDefault("GOOG", 0.0), 0.01);
        assertEquals(5, oldAmazonLessBy3Data.getOrDefault("AMZN", 0.0), 0.01);
    }

    @Test
    public void reverseTransaction() {
        Investment googleStock = new Stock("GOOG", new APIStockDataSource());
        Investment amazonStock = new Stock("AMZN", new APIStockDataSource());
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2020, 1, 1),
                "portfolio2", googleStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022, 1, 3),
                "portfolio2", amazonStock, 5);
        Map<String, Double> googleAmzMap = portfolioManager2.getComposition(LocalDate.of(2022,
                1, 4), "portfolio2");
        assertEquals(4, googleAmzMap.getOrDefault("GOOG", 0.0), 0.01);
        assertEquals(5, googleAmzMap.getOrDefault("AMZN", 0.0), 0.01);

        // now reverse the transaction on Amazon
        portfolioManager2.ReverseTransaction(LocalDate.of(2022, 1, 3), "portfolio2",
                amazonStock, 4, "bought");

        Map<String, Double> afterReverseMap = portfolioManager2.getComposition(LocalDate.of(2022,
                1, 4), "portfolio2");
        assertEquals(4, afterReverseMap.getOrDefault("GOOG", 0.0), 0.01);
        assertEquals(1, afterReverseMap.getOrDefault("AMZN", 0.0), 0.01);

        // now try to reverse more than it buy

        try {
            portfolioManager2.ReverseTransaction(LocalDate.of(2022, 1, 3), "portfolio2",
                    amazonStock, 7, "bought");
        } catch (Exception e) {
            assertEquals("Not enough shares to sell. Available: 1.0, Requested: 7.0", e.getMessage());
        }

        Map<String, Double> afterReverseFailMap = portfolioManager2.getComposition(LocalDate.of(2022,
                1, 4), "portfolio2");
        assertEquals(4, afterReverseFailMap.getOrDefault("GOOG", 0.0), 0.01);
        assertEquals(1, afterReverseFailMap.getOrDefault("AMZN", 0.0), 0.01);


    }

    @Test
    public void rebalance() {
        Investment googleStock = new Stock("GOOG", new APIStockDataSource());
        Investment amazonStock = new Stock("AMZN", new APIStockDataSource());
        Investment asmlStock = new Stock("ASML", new APIStockDataSource());
        Investment taiwanSemiStock = new Stock("TSM", new APIStockDataSource());
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,2),
                "portfolio2", googleStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,2),
                "portfolio2", amazonStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,2),
                "portfolio2", asmlStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,2),
                "portfolio2", taiwanSemiStock, 4);
        portfolioManager2.Rebalance("portfolio2", LocalDate.of(2023, 1, 1),
                Map.of("GOOG", 25,
                        "AMZN", 25,
                        "ASML", 30, "TSM", 20
                ));
        Map<String, Double> afterRebalance = portfolioManager2.getDistributionValue(LocalDate.of(2023, 1,
                2), "portfolio2");
        // amazon and google's value are the same
        assertEquals(afterRebalance.getOrDefault("AMZN", -1.0),
                afterRebalance.getOrDefault("GOOG", 0.0), 0.01);
        // asml and tsm's value should be 3 : 2
        assertEquals(afterRebalance.getOrDefault("ASML", -1.0) / 3,
                afterRebalance.getOrDefault("TSM", 0.0) / 2, 0.01);
    }

    @Test
    public void getComposition() {
        Investment googleStock = new Stock("GOOG", new APIStockDataSource());
        Investment amazonStock = new Stock("AMZN", new APIStockDataSource());
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2020, 1, 1),
                "portfolio2", googleStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2020, 1, 1),
                "portfolio2", amazonStock, 6);
        Map<String, Double> amazonGoogle = portfolioManager2.getComposition(LocalDate.of(2020,
                12, 31), "portfolio2");
        assertEquals(amazonGoogle.getOrDefault("GOOG", 0.00), 4, 0.01);
        assertEquals(amazonGoogle.getOrDefault("AMZN", 0.00), 6, 0.01);


        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2020, 1, 1),
                "portfolio2", googleStock, 4);
        Map<String, Double> newAmazonGoogle = portfolioManager2.getComposition(LocalDate.of(2020,
                12, 31), "portfolio2");
        assertEquals(newAmazonGoogle.getOrDefault("GOOG", 0.00), 8, 0.01);
        assertEquals(newAmazonGoogle.getOrDefault("AMZN", 0.00), 6, 0.01);
    }

    @Test
    public void getDistributionValue() {
        Investment googleStock = new Stock("GOOG", new APIStockDataSource());
        Investment amazonStock = new Stock("AMZN", new APIStockDataSource());
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2020,1,1) ,
                "portfolio2", googleStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2020,2,1),
                "portfolio2", amazonStock, 6);
        Map<String, Double> distributeOnlyGoogle
                = portfolioManager2.getDistributionValue(LocalDate.of(2020,1,2) ,
                "portfolio2");
        assertEquals(distributeOnlyGoogle.getOrDefault("GOOG", 0.0), 5469.48, 0.01);
        Map<String, Double> distributionGoogleAMZN = portfolioManager2.getDistributionValue(LocalDate.of(2020,2,5) ,
                "portfolio2");
        assertEquals(distributionGoogleAMZN.getOrDefault("GOOG", 0.0),
                5792.92, 0.01);
        assertEquals(distributionGoogleAMZN.getOrDefault("AMZN", 0.0),
                12239.22, 0.01);
    }

    @Test
    public void savePortfolio() {
        
    }

    @Test
    public void loadPortfolio() {

    }

    @Test
    public void hasPortfolio() {
        BetterPortfolioManager portfolioManager = new BetterPortfolioManager();
        portfolioManager.createPortfolio("this");
        assertTrue(portfolioManager.hasPortfolio("this"));
        assertFalse(portfolioManager.hasPortfolio("that"));
    }

    @Test
    public void getValueOfPortfolio() {
        Investment googleStock = new Stock("GOOG", new APIStockDataSource());
        Investment amazonStock = new Stock("AMZN", new APIStockDataSource());
        Investment asmlStock = new Stock("ASML", new APIStockDataSource());
        Investment taiwanSemiStock = new Stock("TSM", new APIStockDataSource());
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,2),
                "portfolio2", googleStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,2),
                "portfolio2", amazonStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,3),
                "portfolio2", asmlStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,3),
                "portfolio2", taiwanSemiStock, 4);
        assertEquals(24911.72,
                portfolioManager2.getValueOfPortfolio("portfolio2",
                        LocalDate.of(2022,1,2)), 0.01);
        assertEquals(28943.48,
                portfolioManager2.getValueOfPortfolio("portfolio2",
                        LocalDate.of(2022,1,3)),0.01);
    }

    @Test
    public void getTransactions() {
        Investment googleStock = new Stock("GOOG", new APIStockDataSource());
        Investment amazonStock = new Stock("AMZN", new APIStockDataSource());
        Investment asmlStock = new Stock("ASML", new APIStockDataSource());
        Investment taiwanSemiStock = new Stock("TSM", new APIStockDataSource());
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,2),
                "portfolio2", googleStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,2),
                "portfolio2", amazonStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,3),
                "portfolio2", asmlStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,3),
                "portfolio2", taiwanSemiStock, 4);
        portfolioManager2.sellInvestmentFromPortfolio(LocalDate.of(2022,1,3),
                "portfolio2", taiwanSemiStock, 2);

        List<Transactions> transactHistory = portfolioManager2.getTransactions("portfolio2");
        StringBuilder builder = new StringBuilder();
        for (Transactions transaction : transactHistory) {
            builder.append(transaction);
        }
        assertEquals("Ticker: GOOG\tShares: 4.0\tDate: 2022-01-02\t" +
                "Type: BuyTicker: AMZN\tShares: 4.0\tDate: 2022-01-02\tType: " +
                "BuyTicker: ASML\tShares: 4.0\tDate: 2022-01-03\tType: BuyTic" +
                "ker: TSM\tShares: 4.0\tDate: 2022-01-03\tType: BuyTicker: TS" +
                "M\tShares: 2.0\tDate: 2022-01-03\tType: Sell",builder.toString());
    }

    @Test
    public void getChart() {
        Investment googleStock = new Stock("GOOG", new APIStockDataSource());
        Investment amazonStock = new Stock("AMZN", new APIStockDataSource());
        Investment nvidaStock = new Stock("NVDA", new APIStockDataSource());
        Investment appleStock = new Stock("AAPL", new APIStockDataSource());
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,2),
                "portfolio2", googleStock, 4);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,6),
                "portfolio2", amazonStock, 40);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,10),
                "portfolio2", nvidaStock, 400);
        portfolioManager2.addInvestmentToPortfolio(LocalDate.of(2022,1,15),
                "portfolio2", appleStock, 400);
        portfolioManager2.sellInvestmentFromPortfolio(LocalDate.of(2022,1,26),
                "portfolio2", appleStock, 200);
        portfolioManager2.sellInvestmentFromPortfolio(LocalDate.of(2022,1,26),
                "portfolio2", appleStock, 200);
        portfolioManager2.sellInvestmentFromPortfolio(LocalDate.of(2022,1,26),
                "portfolio2", nvidaStock, 400);

        String chart = portfolioManager2.getChart(LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 1, 28), "portfolio2");
        assertEquals("2022-01-01:\n" +
                "2022-01-02:**\n" +
                "2022-01-03:**\n" +
                "2022-01-04:**\n" +
                "2022-01-05:**\n" +
                "2022-01-06:**********************\n" +
                "2022-01-07:**********************\n" +
                "2022-01-08:**********************\n" +
                "2022-01-09:**********************\n" +
                "2022-01-10:****************************************\n" +
                "2022-01-11:****************************************\n" +
                "2022-01-12:*****************************************\n" +
                "2022-01-13:***************************************\n" +
                "2022-01-14:***************************************\n" +
                "2022-01-15:**************************************************\n" +
                "2022-01-16:**************************************************\n" +
                "2022-01-17:**************************************************\n" +
                "2022-01-18:*************************************************\n" +
                "2022-01-19:************************************************\n" +
                "2022-01-20:***********************************************\n" +
                "2022-01-21:*********************************************\n" +
                "2022-01-22:*********************************************\n" +
                "2022-01-23:*********************************************\n" +
                "2022-01-24:*********************************************\n" +
                "2022-01-25:********************************************\n" +
                "2022-01-26:*******************\n" +
                "2022-01-27:*******************\n" +
                "2022-01-28:********************\n" +
                "with base value of 0\n" +
                "Each asterisks value worth 6300\n", chart);
    }
}