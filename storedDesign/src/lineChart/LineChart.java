package lineChart;

import model.PortfolioV2;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static lineChart.PeriodUnit.*;

public class LineChart {
  LocalDate startDate;
  LocalDate endDate;
  PortfolioV2 portfolio;

  public LineChart(LocalDate startDate, LocalDate endDate, PortfolioV2 portfolio) {
    this.startDate = Objects.requireNonNull(startDate);
    this.endDate = Objects.requireNonNull(endDate);
    this.portfolio = Objects.requireNonNull(portfolio);
  }

  // listOfValueOnDifferentDates is a method to output a list of ValueDateCombo from
  // the portfolio's value on each a period of time
  private List<ValueDateCombo> ListOfValueOnDifferentDates() {
    List<ValueDateCombo> values = new ArrayList<>();
    for (LocalDate i = startDate; i.isBefore(endDate.plusDays(1)); i = i.plusDays(1)) {
      values.add(new ValueDateCombo(i, this.portfolio.getValueOnDate(i)));
    }
    return values;
  }

  public String generateChart() {
    LineChartGenerator generator = new LineChartGenerator(startDate, endDate,
            ListOfValueOnDifferentDates());
    return generator.generate();
  }

  static class ValueDateCombo {
    private final LocalDate date;
    private final Double value;

    ValueDateCombo(LocalDate date, Double value) {
      this.date = Objects.requireNonNull(date);
      this.value = Objects.requireNonNull(value);
    }

    public String getDate() {
      return date.toString();
    }

    public Double getValue() {
      return value;
    }
  }

  static class LineChartGenerator {
    Map<String, Integer> numberOfAsteriskOnEachDate;
    int eachAsteriskWorth;
    int baseAmount;
    LocalDate startDate;
    LocalDate endDate;

    LineChartGenerator(LocalDate startDate, LocalDate endDate, List<ValueDateCombo> listOfValueOnDifferentDates) {
      this.startDate = Objects.requireNonNull(startDate);
      this.endDate = Objects.requireNonNull(endDate);
      loadValueDates(listOfValueOnDifferentDates);
    }

    public TimeScale scaleDecision() {
      Period period = Period.between(startDate, endDate);
      // years is the number of year between the two dates
      int years = period.getYears();
      // month is the number of months between the two dates
      int months = period.getYears() * 12 + period.getMonths();
      // days is the number of days between the two period
      int days = (int) ChronoUnit.DAYS.between(startDate, endDate);

      int scale = 1;
      PeriodUnit unit = null;
      if (years <= 4) {
        if (months <= 4) {
          unit = Day;
          scale = (days + 1) / 30;
        } else {
          unit = Month;
          scale = (months + 1) / 30;
        }
      } else {
        unit = Year;
        scale = (years + 1) / 30;
      }
      return new TimeScale(unit, scale);
    }


    // this load a list of ValueDateCombo and assign baseAmount, eachAsteriskWorth,
    // and listOfNumberAsterisk instance variable values with the given list.
    private void loadValueDates(List<ValueDateCombo> valueDateCombos) {
      // set the default values
      baseAmount = 0;
      eachAsteriskWorth = 10;
      // condition that max value is over 500, in this case we need to modify the base amount.
      if (getMaxValue(valueDateCombos) > 500) {
        // we change the base amount to be the min of the value in the list
        // round baseValue to an integer, and assign its value to baseAmount
        baseAmount = (int) Math.round(getMinValue(valueDateCombos));
        // condition that range is greater than 500, so we change the value each asterisk worth
        if (getRange(valueDateCombos) > 500) {
          eachAsteriskWorth = (int) (Math.round(getRange(valueDateCombos)) / 50);
        }
      }
      // then set how many asterisks to generate for each DateValueCombo
      numberOfAsteriskOnEachDate = renderValueToAsteriskNumber(valueDateCombos, baseAmount,
              eachAsteriskWorth);
    }

    private Map<String, Integer> renderValueToAsteriskNumber(List<ValueDateCombo> valueDateCombos,
                                                                int baseAmount, int eachAsteriskWorth) {
      // render value to number of asterisks
      Map<String, Integer> returnMap = new HashMap<String, Integer>();
      for (ValueDateCombo valueDateCombo : valueDateCombos) {
        returnMap.put(valueDateCombo.getDate(), (int) Math.round((valueDateCombo.getValue() - baseAmount) / eachAsteriskWorth));
      }
      return returnMap;
    }

    // get min value of the list
    private double getMinValue(List<ValueDateCombo> valueDateCombos) {
      // set the base value as the first value of the list
      double baseValue = valueDateCombos.get(0).getValue();
      // map each value in the list and replace with a smaller one everytime.
      for (ValueDateCombo valueDateCombo : valueDateCombos) {
        baseValue = Math.min(baseValue, valueDateCombo.getValue());
      }
      return baseValue;
    }

    // get max value of the list
    private double getMaxValue(List<ValueDateCombo> valueDateCombos) {
      // set the base value as the first value of the list
      double max = valueDateCombos.get(0).getValue();
      // map each value in the list and replace with a smaller one everytime.
      for (ValueDateCombo valueDateCombo : valueDateCombos) {
        max = Math.max(max, valueDateCombo.getValue());
      }
      return max;
    }

    // return the range value of the list
    private double getRange(List<ValueDateCombo> valueDateCombos) {
      return getMaxValue(valueDateCombos) - getMinValue(valueDateCombos);
    }

    // generate the whole chart table
    String generate() {
      StringBuilder sb = new StringBuilder();
      TimeScale timeScale = scaleDecision();
      int frequency = timeScale.factor;
      PeriodUnit unit = timeScale.unit;
      LocalDate date = startDate;

      // first line to append
      sb.append(date.toString()).append(":");
      sb.append("*".repeat(Math.max(0, numberOfAsteriskOnEachDate.getOrDefault(date.toString(),
              null))));
      sb.append("\n");

      // i represent which row we are at now
      while (date.isBefore(endDate)) {
        // append timestamps
        switch (unit) {
          case PeriodUnit.Day:
            // increase date by frequency
            date = date.plusDays(frequency);
            sb.append(date).append(":");
            // append asterisks
            sb.append("*".repeat(
                    numberOfAsteriskOnEachDate.getOrDefault(date.toString(),
                    null)));
            // append next line
            sb.append("\n");
            break;
            // going to fix month and year later,
          // they need their own frequency
          case PeriodUnit.Month:
            date = date.plusMonths(frequency);
            String output= date.getMonth().toString().substring(0,4);
            sb.append(output).append(":");
            sb.append("\n");
            break;
          case PeriodUnit.Year:
            date = date.plusYears(frequency);
            sb.append(date.getYear()).append(":");
            sb.append("\n");
            break;
          default:
            throw new IllegalStateException("Don't know how to handle unit: " + unit);
        }

      }

      // endDate's data to append
      sb.append(endDate.toString()).append(":");
      sb.append("*".repeat(Math.max(0, numberOfAsteriskOnEachDate.getOrDefault(endDate.toString(),
              null))));
      sb.append("\n");


      sb.append("Each Asterisk Value ").append(eachAsteriskWorth).append("\n");
      sb.append("At Base Value of ").append(baseAmount).append("\n");
      return sb.toString();
    }
  }
}
