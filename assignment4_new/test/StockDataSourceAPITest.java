import java.io.File;
import java.io.IOException;

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
  public void fetchData() throws IOException {
    try {
      Stock aapl = new Stock("AAPL", apiSource);
    } catch (Exception e) {
      fail(e.getMessage());
    }

  }

  //test no price data found
}