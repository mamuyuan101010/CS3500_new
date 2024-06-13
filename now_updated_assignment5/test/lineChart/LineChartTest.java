package lineChart;

import org.junit.Test;

import java.time.LocalDate;

import model.APIStockDataSource;
import model.BetterPortfolio;
import model.Investment;
import model.PortfolioV2;
import model.Stock;

import static org.junit.Assert.assertEquals;


public class LineChartTest {

  @Test
  public void generateChart() {
    PortfolioV2 portfolio = new BetterPortfolio();
    Investment appleStock = new Stock("AAPL", new APIStockDataSource());
    Investment microsoftStock = new Stock("MSFT", new APIStockDataSource());
    Investment deltaAirlineStock = new Stock("DAL", new APIStockDataSource());
    portfolio.addInvestment(LocalDate.of(1994, 10, 31), appleStock,
            1);
    portfolio.addInvestment(LocalDate.of(2006, 12, 20), microsoftStock,
            400);
//    portfolio.addInvestment(LocalDate.of(2012, 1, 31), deltaAirlineStock,
//            700);
    LineChart chart = new LineChart(LocalDate.of(1994, 1, 10),
            LocalDate.of(2020, 1, 7), portfolio);
    String chartString = chart.generateChart();
    assertEquals("", chartString);
  }
}