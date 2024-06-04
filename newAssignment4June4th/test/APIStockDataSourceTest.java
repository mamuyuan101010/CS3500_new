import java.io.IOException;
import java.time.format.DateTimeParseException;
import org.junit.Before;
import org.junit.Test;
import Model.Investment;
import Model.Stock;
import Model.APIStockDataSource;
import static org.junit.Assert.fail;

/**
 * This is a test class for the StockDataSourceAPI class.
 */
public class APIStockDataSourceTest {
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

  //try to fetch data for a stock that does not exist
  @Test(expected = DateTimeParseException.class)
  public void fetchDataNoSuchStock() {
    new Stock("ABCDE", apiSource);
  }
}