import org.junit.Before;
import org.junit.Test;
import Model.APIStockDataSource;
import Model.CacheManager;
import Model.Investment;
import Model.Stock;
import Model.StockDataSource;
import static org.junit.Assert.*;

/**
 * This is a test class for the CacheManager class.
 */
public class CacheManagerTest {
  StockDataSource apiSource = new APIStockDataSource();
  Investment googleStock;
  CacheManager cacheManager;

  @Before
  public void setUp() {
    googleStock = new Stock("GOOG", apiSource);
    cacheManager = CacheManager.getInstance();
  }

  @Test
  //test singleton pattern by making sure only one instance exists
  public void testGetInstance() {
    // Get two instances of CacheManager
    CacheManager instance1 = CacheManager.getInstance();
    CacheManager instance2 = CacheManager.getInstance();

    // Ensure that both instances are the same
    assertEquals(instance1, instance2);
  }

  @Test
  //from the setup, google stock is already fetched, and should now be in local cache
  public void isDataCached() {
    assertTrue(cacheManager.isDataCached("GOOG"));

    //new stock desired not in cache
    assertFalse(cacheManager.isDataCached("AAPL"));

    Investment appleStock = new Stock("AAPL", apiSource);

    //new stock now in cache for future use
    assertTrue(cacheManager.isDataCached("AAPL"));
  }
}