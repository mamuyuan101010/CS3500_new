package lineChart;

import model.PortfolioV2;

import java.time.LocalDate;
import java.time.YearMonth;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import static lineChart.PeriodUnit.Day;
import static lineChart.PeriodUnit.Year;
import static lineChart.PeriodUnit.Month;


class LineChartGenerator {
    List<ValueDateCombo> dateAndValueList = new ArrayList<>();
    int eachAsteriskWorth;
    int baseAmount;
    LocalDate startDate;
    LocalDate endDate;
    TimeScale scale;

    LineChartGenerator(LocalDate startDate, LocalDate endDate, PortfolioV2 portfolio) {
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
        this.scale = scaleDecision();
        loadPortfolio(portfolio);
        setAsterisks(this.dateAndValueList);
    }

    // adjust the correct base amount and each asterisks worth
    private void setAsterisks(List<ValueDateCombo> valueDateCombos) {
        eachAsteriskWorth = 10;
        baseAmount = 0;
        if (getMaxValue(valueDateCombos) > 500) {
            baseAmount = rounding(getMinValue(valueDateCombos));
            if (getRange(valueDateCombos) > 500) {
                eachAsteriskWorth = rounding((getRange(valueDateCombos) / 50));
            }
        }
    }

    // adjust the baes and each asterisks value. if they are lower than 100, they will round to 10,
    // greater than 100 will just round to 100
    private int rounding(double valueToRound) {
        if (valueToRound > 100) {
            return ((int) valueToRound / 100) * 100;
        } else {
            return ((int) valueToRound / 10) * 10;
        }
    }

    // make a list that have maximum 30 lines. given the frequency
    private void loadPortfolio(PortfolioV2 portfolio) {
        int frequency = scale.factor;
        switch (scale.unit) {
            case Day:
                addValueDateCombos(startDate, endDate,
                        frequency, (date, port) -> port.getValueOnDate(date), portfolio);
                break;
            case Month:
                addValueDateCombos(startDate, endDate, frequency,
                        (date, port) -> getEndofMonthValue(date.getMonthValue(), date.getYear(), port),
                        portfolio);
                break;
            case Year:
                addValueDateCombos(startDate, endDate, frequency,
                        (date, port) -> getEndOfYearValue(date.getYear(), port), portfolio);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + scale.unit);
        }
    }

    // take a start date, end date, frequency, portfolio, and a bi-function which turns a date and a portfolio into the
    // value of that portfolio in that given date.
    private void addValueDateCombos(LocalDate start, LocalDate end, int frequency,
                                                    BiFunction<LocalDate, PortfolioV2, Double> valueFunction,
                                                    PortfolioV2 portfolio) {
        for (LocalDate i = start; i.isBefore(end); i = incrementDate(i, frequency)) {
            dateAndValueList.add(new ValueDateCombo(i, valueFunction.apply(i, portfolio)));
        }
        LocalDate lastDateInList = dateAndValueList.get(dateAndValueList.size() - 1).getDate();
        // this will remove last item if repeat with the end date one.
        // Eg: if the end date is 2020/02/01, but end of list is 2020/01/01, and data is listed per year,
        // so 2020/01/01 will directly return 2020's end of year value,
        // in this case we delete 2020/01/01's data and replace with 2020/02/01's value
        removeLastDup(lastDateInList, end);
        dateAndValueList.add(new ValueDateCombo(end,
                valueFunction.apply(end, portfolio)));
    }

    // removeLastDup will check if the end date given has the same month/year
    // as the last item in the ValueComboList, if remove the last item if duplicate
    private void removeLastDup(LocalDate lastDateInList, LocalDate end) {
        switch (scale.unit) {
            case Month:
                if (lastDateInList.getMonth() == end.getMonth()) {
                    dateAndValueList.remove(dateAndValueList.size() - 1);
                }
                break;
            case Year:
                if (lastDateInList.getYear() == end.getYear()) {
                    dateAndValueList.remove(dateAndValueList.size() - 1);
                }
                break;
        }
    }


    // increase date based on the given scale
    private LocalDate incrementDate(LocalDate date, int frequency) {
        switch (scale.unit) {
            case Day:
                return date.plusDays(frequency);
            case Month:
                return date.plusMonths(frequency);
            case Year:
                return date.plusYears(frequency);
            default:
                throw new IllegalStateException("Unexpected value: " + scale.unit);
        }
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
        int years = (int) ChronoUnit.YEARS.between(startDate, endDate);
        int months = (int) ChronoUnit.MONTHS.between(startDate, endDate);
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        int scale = 1; // set scale default value to be 1
        PeriodUnit unit = null;
        if (years <= 4) { // if year is less than 4, unit should be day or month
            if (months <= 4) { // if month less than 4, unit should be day
                unit = Day;
                scale = scaling(days);
            } else {
                unit = Month;
                scale = scaling(months);
            }
        } else { // if year is greater than 4, unit be year
            unit = Year;
            scale = scaling(years);
        }
        return new TimeScale(unit, scale);
    }

    // calculate the scale of the unit if we want to
    // keep the rows less than 30
    private int scaling(int period) {
        return (period - 1) / 29 + 1;
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
                case Day:
                    builder.append(valueDateCombo.getDate());
                    builder.append(":");
                    builder.append(renderAsterisks(valueDateCombo.getValue()));
                    break;

                case Month:
                    builder.append(valueDateCombo.getDate().getYear());
                    builder.append(" ");
                    builder.append(valueDateCombo.getDate().getMonth().toString().substring(0, 3));
                    builder.append(":");
                    builder.append(renderAsterisks(valueDateCombo.getValue()));
                    break;

                case Year:
                    builder.append(valueDateCombo.getDate().getYear());
                    builder.append(":");
                    builder.append(renderAsterisks(valueDateCombo.getValue()));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + scale.unit);
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
