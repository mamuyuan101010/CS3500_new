import org.junit.Test;
import org.junit.Before;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
  File crossovers;

  @Before
  public void setUp() {
    googleStock = new Stock("GOOG", apiSource);
    gainOrLoss5Days = new File("res/5-DayGainOrLoss");
    timestamps = new File("res/Timestamps");
    movingAvg5Days = new File("res/5-DayMovingAverage");
    crossovers = new File("res/30DayCrossovers");
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
        LocalDate daysAgo = LocalDate.parse(XDaysAgoTime.nextLine());

        double actualMovingAvg = googleStock.calculateXDayMovingAvg(recent, 5);
        assertEquals(Double.parseDouble(results.nextLine()), actualMovingAvg, 0.01);
      }
    } catch (FileNotFoundException e) {
      fail("Specified files not found");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  //test case where days inputted for moving average is negative
  public void NegativeDaysMovingAvg() {
    LocalDate startDate = LocalDate.parse("2024-05-21");
    googleStock.calculateXDayMovingAvg(startDate, -4);
  }

  @Test(expected = IllegalArgumentException.class)
  //test case where inputted date is absent from list
  public void AbsentDateMovingAvg() {
    LocalDate startDate = LocalDate.parse("2027-05-21");
    googleStock.calculateXDayMovingAvg(startDate, 8);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  //test case where inputted date does not have enough previous days
  public void TooCloseToMinMovingAvg() {
    LocalDate startDate = LocalDate.parse("2014-04-03");
    googleStock.calculateXDayMovingAvg(startDate, 20);
  }

  @Test
  //testing valid outcomes of findCrossovers
  public void findCrossovers() {
    try {
      //scan the two files for the relevant data, and turn them into two arrays that
      //should be of equal size
      Scanner crosses = new Scanner(crossovers);
      Scanner times = new Scanner(timestamps);

      List<String> clines = new ArrayList<>();
      List<String> tlines = new ArrayList<>();

      while (crosses.hasNextLine() && times.hasNextLine()) {
        clines.add(crosses.nextLine());
        tlines.add(times.nextLine());
      }

      String[] crossovers = clines.toArray(new String[0]);
      String[] timestamps = tlines.toArray(new String[0]);

      //fuzz testing that runs 20,000 cases
      //chooses two random indices that are within 20 indexes apart for testing speed purposes
      for (int i = 0; i < 20000; i++) {
        List<LocalDate> results = new ArrayList<>();
        Random rand = new Random(25);
        int recentDate = rand.nextInt(timestamps.length);
        int olderDate = recentDate + rand.nextInt(20);

        //if a crossover value represents yes for a crossover, add the parallel timestamp value
        //to the results list since findCrossovers generates a list of LocalDates
        for (int j = recentDate; j <= olderDate; j++) {
          if (crossovers[j].equals("Yes")) {
            results.add(LocalDate.parse(timestamps[j]));
          }
        }
        List<LocalDate> actualCrossovers = googleStock
                .findCrossovers(
                        LocalDate.parse(timestamps[olderDate]),
                        LocalDate.parse(timestamps[recentDate]),
                        30);

        //compare the LocalDates within the actual and expected lists.
        assertEquals(results, actualCrossovers);
      }

    } catch (FileNotFoundException e) {
      fail("Specified files not found");
    }

  }
}