import org.junit.Before;
import org.junit.Test;

import Model.APIStockDataSource;
import Model.CacheManager;
import Model.Investment;
import Model.Stock;

import static org.junit.Assert.*;

/**
 * This is a test class for the CacheManager class.
 */
public class CacheManagerTest {
  APIStockDataSource apiSource = new APIStockDataSource();
  Investment googleStock;

  @Before
  public void setUp() {
    googleStock = new Stock("GOOG", apiSource);
  }

  @Test
  //from the setup, google stock is already fetched, and should now be in local cache
  public void isDataCached() {
    assertTrue(CacheManager.isDataCached("GOOG"));

    //new stock desired not in cache
    assertFalse(CacheManager.isDataCached("AAPL"));

    Stock appleStock = new Stock("AAPL", apiSource);

    //new stock now in cache for future use
    assertTrue(CacheManager.isDataCached("AAPL"));
  }
}