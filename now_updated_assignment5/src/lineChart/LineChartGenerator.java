package lineChart;
import model.PortfolioV2;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lineChart.PeriodUnit.Day;
import static lineChart.PeriodUnit.Year;
import static lineChart.PeriodUnit.Month;


class LineChartGenerator {
    List<ValueDateCombo> dateAndValueList;
    int eachAsteriskWorth;
    int baseAmount;
    LocalDate startDate;
    LocalDate endDate;
    TimeScale scale;

    LineChartGenerator(LocalDate startDate, LocalDate endDate, PortfolioV2 portfolio) {
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
        this.scale = scaleDecision();
        this.dateAndValueList = loadPortfolio(portfolio);
        setAsterisks(this.dateAndValueList);
    }

    private void setAsterisks(List<ValueDateCombo> valueDateCombos) {
        eachAsteriskWorth = 10;
        baseAmount = 0;
        if (getMaxValue(valueDateCombos) > 500) {
            baseAmount = (int) getMinValue(valueDateCombos);
            if (getRange(valueDateCombos) > 500) {
                eachAsteriskWorth = (int) (getRange(valueDateCombos) / 50);
            }
        }
    }

    // make a list that have maximum 30 lines. given the frequency
    private List<ValueDateCombo> loadPortfolio(PortfolioV2 portfolio) {
        List<ValueDateCombo> dateAndValueList = new ArrayList<>();
        int frequency = scale.factor;
        switch (scale.unit) {
            case Day -> {
                for (LocalDate i = startDate; i.isBefore(endDate); i = i.plusDays(frequency)) {
                    dateAndValueList.add(new ValueDateCombo(i, portfolio.getValueOnDate(i)));
                }
                dateAndValueList.add(new ValueDateCombo(endDate, portfolio.getValueOnDate(endDate)));
            }
            case Month -> {
                for (LocalDate i = startDate; i.isBefore(endDate); i = i.plusMonths(frequency)) {
                    dateAndValueList.add(new ValueDateCombo(i,
                            getEndofMonthValue(i.getMonthValue(), i.getYear(), portfolio)));
                }
                if (dateAndValueList.get(dateAndValueList.size()).getDate().getMonth() != endDate.getMonth()) {
                    dateAndValueList.add(new ValueDateCombo(endDate, portfolio.getValueOnDate(endDate)));
                }
            }
            case Year -> {
                for (LocalDate i = startDate; i.isBefore(endDate); i = i.plusYears(frequency)) {
                    dateAndValueList.add(new ValueDateCombo(i,
                            getEndOfYearValue(i.getYear(), portfolio)));
                }
                if (dateAndValueList.get(dateAndValueList.size()).getDate().getYear() != endDate.getYear()) {
                    dateAndValueList.add(new ValueDateCombo(endDate, portfolio.getValueOnDate(endDate)));
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + scale.unit);
        }
        return dateAndValueList;
    }

    // get the closing price on the end of month day
    private double getEndofMonthValue(int month, int year, PortfolioV2 portfolio) {
        LocalDate endOfMonth = getEndOfMonth(year, month);
        return portfolio.getValueOnDate(endOfMonth);
    }

    // get the closing price on the end of year day
    private double getEndOfYearValue(int year, PortfolioV2 portfolio) {
        LocalDate endOfYear = LocalDate.of(year, 12, 31);
        return portfolio.getValueOnDate(endOfYear);
    }

    private LocalDate getEndOfMonth(int year, int month) {
        // YearMonth class to handle year and month
        YearMonth yearMonth = YearMonth.of(year, month);
        // Get the last day of the month
        return yearMonth.atEndOfMonth();
    }

    // keep it. This method return Time gap between each line of chartTable
    private TimeScale scaleDecision() {
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
                scale = (days - 1) / 30 + 1;
            } else {
                unit = Month;
                scale = (months - 1) / 30 + 1;
            }
        } else {
            unit = Year;
            scale = (years - 1) / 30 + 1;
        }
        return new TimeScale(unit, scale);
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
        StringBuilder builder = new StringBuilder();
        for (ValueDateCombo valueDateCombo : dateAndValueList) {
            switch (scale.unit) {
                case Day -> {
                    builder.append(valueDateCombo.getDate());
                    builder.append(":");
                    builder.append(renderAsterisks(valueDateCombo.getValue()));
                }
                case Month -> {
                    builder.append(valueDateCombo.getDate().getYear());
                    builder.append(" ");
                    builder.append(valueDateCombo.getDate().getMonth().toString().substring(0, 3));
                    builder.append(":");
                    builder.append(renderAsterisks(valueDateCombo.getValue()));
                }
                case Year -> {
                    builder.append(valueDateCombo.getDate().getYear());
                    builder.append(":");
                    builder.append(renderAsterisks(valueDateCombo.getValue()));
                }
            }
        }
        builder.append("with base value of ").append(baseAmount);
        builder.append("\n");
        builder.append("Each asterisks value worth ").append(eachAsteriskWorth);
        builder.append("\n");
        return builder.toString();
    }

    private String renderAsterisks(double value) {
        StringBuilder asterisks = new StringBuilder();
        int numberOfAsterisks = 0;
        numberOfAsterisks = (int) Math.round((value - baseAmount)
                / eachAsteriskWorth);
        asterisks.append("*".repeat(Math.max(0, numberOfAsterisks)));
        asterisks.append("\n");
        return asterisks.toString();
    }
}
