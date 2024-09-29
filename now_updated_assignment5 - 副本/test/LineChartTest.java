import lineChart.LineChart;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import model.APIStockDataSource;
import model.BetterPortfolio;
import model.Investment;
import model.PortfolioV2;
import model.Stock;

import static org.junit.Assert.assertEquals;


public class LineChartTest {
  PortfolioV2 portfolio;

  @Before
  public void setUp() throws Exception {
    portfolio = new BetterPortfolio();
    Investment appleStock = new Stock("AAPL", new APIStockDataSource());
    Investment microsoftStock = new Stock("MSFT", new APIStockDataSource());
    Investment deltaAirlineStock = new Stock("DAL", new APIStockDataSource());
    portfolio.addInvestment(LocalDate.of(1994, 10, 31), appleStock,
            1);
    portfolio.addInvestment(LocalDate.of(2006, 12, 20), microsoftStock,
            400);
    portfolio.addInvestment(LocalDate.of(2012, 1, 31), deltaAirlineStock,
            700);
  }


  @Test
  public void generateChartDaily() {
    LineChart chart = new LineChart(LocalDate.of(2020, 1, 10),
            LocalDate.of(2020, 1, 30), portfolio);
    String chartString = chart.generateChart();
    assertEquals("2020-01-10:*************\n" +
            "2020-01-11:*************\n2020-01-12:*************\n" +
            "2020-01-13:***********************\n" +
            "2020-01-14:********************************\n" +
            "2020-01-15:*************************************\n" +
            "2020-01-16:***************************************************\n" +
            "2020-01-17:********************************************************" +
            "\n2020-01-18:********************************************************\n" +
            "2020-01-19:********************************************************\n" +
            "2020-01-20:********************************************************" +
            "\n2020-01-21:******************************************\n" +
            "2020-01-22:*********************************\n" +
            "2020-01-23:******************************************\n" +
            "2020-01-24:*************************\n" +
            "2020-01-25:*************************\n" +
            "2020-01-26:*************************\n" +
            "2020-01-27:\n2020-01-28:*****************\n" +
            "2020-01-29:**********************\n" +
            "2020-01-30:********************************************\n" +
            "with base value of 105000\nEach asterisks value worth 100\n"
            , chartString);
  }

  @Test
  public void generateChartPer3Days() {
    LineChart chart = new LineChart(LocalDate.of(2020, 1, 10),
            LocalDate.of(2020, 4, 10), portfolio);
    String chartString = chart.generateChart();
    assertEquals("2020-01-10:*************************************" +
            "***\n2020-01-14:**************************************" +
            "****\n2020-01-18:*************************************" +
            "********\n2020-01-22:*********************************" +
            "*********\n2020-01-26:********************************" +
            "**********\n2020-01-30:*******************************" +
            "*************\n2020-02-03:****************************" +
            "****************\n2020-02-07:*************************" +
            "************************\n2020-02-11:****************" +
            "**********************************\n2020-02-15:******" +
            "*********************************************\n2020-0" +
            "2-19:************************************************" +
            "***\n2020-02-23:*************************************" +
            "**********\n2020-02-27:*****************************" +
            "*\n2020-03-02:************************************\n2" +
            "020-03-06:******************************\n2020-03-10:" +
            "*****************************\n2020-03-14:**********" +
            "*************\n2020-03-18:***\n2020-03-22:\n2020-03-" +
            "26:****************\n2020-03-30:****************\n20" +
            "20-04-03:********\n2020-04-07:************\n2020-04-" +
            "10:***************\nwith base value of 70100\nEach " +
            "asterisks value worth 900\n", chartString);
  }

  @Test
  public void generateChartPerMonth() {
    LineChart chart = new LineChart(LocalDate.of(2020, 1, 10),
            LocalDate.of(2021, 4, 10), portfolio);
    String chartString = chart.generateChart();
    assertEquals("2020 JAN:************************\n2020 FEB:**************\n2020 MAR:\n2020 APR:*******\n2020 MAY:********\n2020 JUN:******************\n2020 JUL:*****************\n2020 AUG:*****************************\n2020 SEP:**********************\n2020 OCT:*******************\n2020 NOV:*******************************\n2020 DEC:**********************************\n2021 JAN:************************************\n2021 FEB:*******************************************\n2021 MAR:*********************************************\n2021 APR:***************************************************\nwith base value of 83300\nEach asterisks value worth 1000\n", chartString);
  }

  @Test
  public void generateChartPer2Month() {
    LineChart chart = new LineChart(LocalDate.of(2018, 1, 10),
            LocalDate.of(2022, 2, 10), portfolio);
    String chartString = chart.generateChart();
    assertEquals("2018 JAN:**\n2018 MAR:\n2018 MAY:**\n2018 JUL:****\n2018 SEP:*******\n2018 NOV:********\n2019 JAN:*\n2019 MAR:*****\n2019 MAY:*******\n2019 JUL:**************\n2019 SEP:*************\n2019 NOV:****************\n2020 JAN:********************\n2020 MAR:*****\n2020 MAY:**********\n2020 JUL:****************\n2020 SEP:*******************\n2020 NOV:************************\n2021 JAN:****************************\n2021 MAR:*********************************\n2021 MAY:************************************\n2021 JUL:******************************************\n2021 SEP:******************************************\n2021 NOV:****************************************************\n2022 JAN:************************************************\n2022 FEB:*********************************************\nwith base value of 75000\nEach asterisks value worth 1600\n", chartString);
  }

  @Test
  public void generateChartPerYear() {
    LineChart chart = new LineChart(LocalDate.of(1991, 1, 10),
            LocalDate.of(2021, 4, 10), portfolio);
    String chartString = chart.generateChart();
    assertEquals("1991:\n1993:\n1995:\n1997:\n1999:\n2001:\n2003:\n2005:\n2007:*****\n2009:****\n2011:***\n2013:***********\n2015:******************\n2017:***********************\n2019:*********************************\n2021:***************************************************\nwith base value of 0\nEach asterisks value worth 3200\n", chartString);
  }

  @Test
  public void generateChartPer2Year() {
    LineChart chart = new LineChart(LocalDate.of(1964, 4, 10),
            LocalDate.of(2024, 4, 10), portfolio);
    String chartString = chart.generateChart();
    assertEquals("1964:\n1967:\n1970:\n1973:\n1976:\n1979:\n1982:\n1985:\n1988:\n1991:\n1994:\n1997:\n2000:\n2003:\n2006:***\n2009:***\n2012:*****\n2015:**************\n2018:******************\n2021:***************************************\n2024:**************************************************\nwith base value of 0\nEach asterisks value worth 4200\n", chartString);
  }

  @Test
  public void generateChartLessThen4Days() {
    LineChart chart = new LineChart(LocalDate.of(2020, 1, 10),
            LocalDate.of(2020, 1, 13), portfolio);
    String chartString = chart.generateChart();
    assertEquals("2020-01-10:*\n2020-01-11:*\n2020-" +
            "01-12:*\n2020-01-13:*******************************" +
            "****************************************************" +
            "**************\nwith base value of 106300\nEach ast" +
            "erisks value worth 10\n", chartString);
  }

  @Test
  public void generateChartViewValueIncreaseInSudden() {
    LineChart chart = new LineChart(LocalDate.of(1990, 1, 1),
            LocalDate.of(2013, 1, 1), portfolio);
    String chartString = chart.generateChart();
    assertEquals("1990:\n1991:\n1992:\n1993:" +
            "\n1994:\n1995:\n1996:\n1997:\n1998:\n1999:" +
            "\n2000:\n2001:\n2002:\n2003:\n2004:\n2005:" +
            "\n2006:********************\n2007:********" +
            "****************\n2008:*************\n2009" +
            ":*********************\n2010:**************" +
            "*****\n2011:******************\n2012:*****" +
            "****************************\n2013:*******" +
            "******************************************" +
            "*********\nwith base value of 0\nEach aster" +
            "isks value worth 600\n", chartString);
  }

  @Test
  public void seeDeltaBuyInChangeOnThirtyDays() {
    LineChart chart = new LineChart(LocalDate.of(2012, 1, 20),
            LocalDate.of(2012, 2, 19), portfolio);
    // there's a big difference after Jan 30.
    String chartString = chart.generateChart();
    assertEquals("2012-01-20:**\n2012-01-22:**\n2012-01-24:*\n2012-01-26:*\n2012-01-28:\n2012-01-30:**\n2012-02-01:*******************************************************************************\n2012-02-03:************************************************************************************\n2012-02-05:************************************************************************************\n2012-02-07:**********************************************************************************\n2012-02-09:*************************************************************************************\n2012-02-11:**********************************************************************************\n2012-02-13:************************************************************************************\n2012-02-15:********************************************************************************\n2012-02-17:*************************************************************************************\n2012-02-19:*************************************************************************************\nwith base value of 12100\nEach asterisks value worth 100\n", chartString);
  }
}