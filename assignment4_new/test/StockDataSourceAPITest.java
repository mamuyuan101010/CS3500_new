import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import Model.CacheManager;
import Model.Investment;
import Model.Stock;
import Model.APIStockDataSource;
import Model.StockDataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
      Stock aapl = new Stock("NoSuchStock", apiSource);
    } catch (Exception e) {
      assertEquals("Text '    \"Error Message\": \"Invalid API call. Please retry or"
              +
              " visit th...' could not be parsed at index 0", e.getMessage());
    }
  }

  //test no price data found
  @Test
  public void fetchDataNoPrice() {
    try {
      Stock aapl = new Stock("AAPL", apiSource);
      try {
        aapl.calculateXDayMovingAvg(LocalDate.parse("2023-06-04"), 4);
      } catch (Exception e) {
        assertEquals("data not available for specified dates", e.getMessage());
      }
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}