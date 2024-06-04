import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import Model.Investment;
import Model.Stock;
import Model.APIStockDataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This is a test class for the StockDataSourceAPI class.
 */
public class StockDataSourceAPITest {
  //initialize it here so API query limit doesn't get overloaded with each test
  //only called once every time the entire test file has to load.
  APIStockDataSource apiSource = new APIStockDataSource();
  Investment googleStock;

  @Before
  public void setUp() {
    googleStock = new Stock("GOOG", apiSource);
  }

  @Test
  public void fetchData() {
    try {
      Stock aapl = new Stock("AAPL", apiSource);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  // try input a string that does not represent a stock
  @Test
  public void fetchDataNoSuchStock() {
    try {
      Stock random = new Stock("NoSuchStock", apiSource);
    } catch (Exception e) {
      assertEquals("Text '    \"Error Message\": \"Invalid API call. Please retry or"
              +
              " visit th...' could not be parsed at index 0", e.getMessage());
    }
  }

  //test no price data found
  @Test
  public void fetchDataNoPrice() {
    Stock aapl = new Stock("AAPL", apiSource);
    try {
      aapl.calculateXDayMovingAvg(LocalDate.parse("2023-01-01"), 4);
      fail("Exception expected");
    } catch (IllegalArgumentException e) {
      assertEquals("data not available for specified dates", e.getMessage());
    }
  }
}