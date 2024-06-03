import org.junit.Test;
import org.junit.Before;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

import Model.APIStockDataSource;
import Model.Investment;
import Model.Stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This is a test class for the Stock class.
 */
public class StockTest {
  Investment googleStock;
  //initialize it here so API query limit doesn't get overloaded with each test
  //only called once every time the entire test file has to load.
  APIStockDataSource apiSource = new APIStockDataSource();
  File gainOrLoss5Days;
  File timestamps;
  File movingAvg5Days;

  @Before
  public void setUp() {
    googleStock = new Stock("GOOG", apiSource);
    gainOrLoss5Days = new File("res/5-DayGainOrLoss");
    timestamps = new File("res/Timestamps");
    movingAvg5Days = new File("res/5-DayMovingAverage");
  }

  @Test
  //match the expected answer from spreadsheet data against the program's method
  //data stored in Timestamps and 5-DayGainOrLoss files in res folder
  public void fetchGainOrLoss() {
    //tests only the valid outcomes (when data was available)
    try {
      //set up three different scanners for the appropriate text files needed
      Scanner results = new Scanner(gainOrLoss5Days);
      Scanner endTimes = new Scanner(timestamps);
      Scanner startTimes = new Scanner(timestamps);

      //for a 5-day gain or loss, offset start times by 5 lines
      //offset results and start times by 5 lines to only test the valid outcomes in results
      for (int i = 0; i < 5; i++) {
        results.nextLine();
        startTimes.nextLine();
      }

      while (results.hasNextLine() && startTimes.hasNextLine()) {
        LocalDate endDate = LocalDate.parse(endTimes.nextLine());
        LocalDate startDate = LocalDate.parse(startTimes.nextLine());

        double actualGainOrLoss = googleStock.fetchGainOrLoss(startDate, endDate);
        assertEquals(Double.parseDouble(results.nextLine()), actualGainOrLoss, 0.01);

      }
    } catch (FileNotFoundException e) {
      fail("Specified files not found");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  //test case where end date is newer than the newest available time stamp
  public void PastMaxEndDateFetchGainOrLoss() {
    LocalDate endDate = LocalDate.parse("2027-05-30");
    LocalDate startDate = LocalDate.parse("2024-05-22");

    googleStock.fetchGainOrLoss(startDate, endDate);
  }

  @Test(expected = IllegalArgumentException.class)
  //test case where end date is absent from available data
  public void AbsentEndDateFetchGainOrLoss() {
    LocalDate endDate = LocalDate.parse("2024-05-18");
    LocalDate startDate = LocalDate.parse("2024-05-13");

    googleStock.fetchGainOrLoss(startDate, endDate);
  }

  @Test(expected = IllegalArgumentException.class)
  //test case where start date is absent from available data
  public void AbsentStartDateFetchGainOrLoss() {
    LocalDate endDate = LocalDate.parse("2024-05-15");
    LocalDate startDate = LocalDate.parse("2024-05-11");

    googleStock.fetchGainOrLoss(startDate, endDate);
  }

  @Test(expected = IllegalArgumentException.class)
  //test case where end date is absent from available data
  public void PastMinStartFetchGainOrLoss() {
    LocalDate endDate = LocalDate.parse("2024-05-18");
    LocalDate startDate = LocalDate.parse("2014-02-23");

    googleStock.fetchGainOrLoss(startDate, endDate);
  }

  @Test(expected = IllegalArgumentException.class)
  //test case where end date is before start date
  public void InvalidFetchGainOrLoss() {
    LocalDate endDate = LocalDate.parse("2024-05-15");
    LocalDate startDate = LocalDate.parse("2024-05-21");

    googleStock.fetchGainOrLoss(startDate, endDate);
  }

  @Test
  //match the expected answer from spreadsheet data against the program's method
  //data stored in Timestamps and 5-DayMovingAverage files in res folder
  public void calculateXDayMovingAvg() {
    //tests only the valid outcomes (when data was available)
    try {
      //set up three different scanners for the appropriate text files needed
      Scanner results = new Scanner(movingAvg5Days);
      Scanner mostRecentTime = new Scanner(timestamps);
      Scanner XDaysAgoTime = new Scanner(timestamps);

      //for a 5-day moving average, offset the XDaysAgoTime by 5 timestamps
      for (int i = 0; i < 5; i++) {
        XDaysAgoTime.nextLine();
      }

      while (results.hasNextLine() && XDaysAgoTime.hasNextLine()) {
        LocalDate recent = LocalDate.parse(mostRecentTime.nextLine());

        double actualMovingAvg = googleStock.calculateXDayMovingAvg(recent, 5);
        assertEquals(Double.parseDouble(results.nextLine()), actualMovingAvg, 0.01);
      }
    } catch (FileNotFoundException e) {
      fail("Specified files not found");
    }
  }

  @Test
  public void findCrossovers() {

  }
}