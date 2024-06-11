package lineChart;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static lineChart.PeriodUnit.Day;
import static lineChart.PeriodUnit.Month;
import static lineChart.PeriodUnit.Year;

class LineChartGenerator {
  int asterisks;
  String asteriskValue;
  String baseAmount;
  LocalDate startDate;
  LocalDate endDate;

  LineChartGenerator(LocalDate startDate, LocalDate endDate) {
    this.startDate = Objects.requireNonNull(startDate);
    this.endDate = Objects.requireNonNull(endDate);
  }

  public TimeScale scaleDecision() {
    Period period = Period.between(startDate, endDate);
    // years is the number of year between the two dates
    int years = period.getYears();
    int orgYear = years;
    // month is the number of months between the two dates
    int months = period.getYears() * 12 + period.getMonths();
    int orgMonth = months;
    // days is the number of days between the two period
    int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
    int orgDays = days;
    if (days <= 4) {
      throw new IllegalArgumentException("startDate must be 4 days greater than endDate");
    }
    int scale = 1;
    PeriodUnit unit = null;
    if (years <= 4) {
      if (months <= 4) {
        unit = Day;
        loopDate(days, orgDays, scale);
      }
      else {
        unit = Month;
        loopDate(months, orgMonth, scale);
      }
    }
    else {
      unit = Year;
      loopDate(years, orgYear, scale);
    }
    return new TimeScale(unit, scale);
  }

  private void loopDate(int changeDate, int unChangedDate, int scale) {
    while (changeDate > 30) {
      scale ++;
      changeDate = roundUpDiv(unChangedDate, scale);
    }
  }

  private int roundUpDiv (int num1, int num2) {
    if (num1 % num2 == 0) {
      return num1 / num2;
    }
    return num1 / num2 + 1;
  }

  int asteriskValueDeter(int totalValue) {
  }
}