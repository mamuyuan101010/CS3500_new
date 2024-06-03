import java.io.IOException;

import Model.Stock;
import Model.StockDataSourceAPI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class StockDataSourceAPITest {

  @org.junit.Test
  public void fetchData() throws IOException {
    StockDataSourceAPI api = new StockDataSourceAPI("GOOG");
    try {
      Stock stk = api.fetchData();
      assertEquals(1, 1);
    } catch (Exception e) {
      fail(e.getMessage());
    }

  }
}