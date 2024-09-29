package lineChart;

import model.LineChartObject;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiFunction;

import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.YEARS;

class LineChartGenerator {
    TreeMap<LocalDate, Double> dateAndValues = new TreeMap<>();
    int eachAsteriskWorth;
    int baseAmount;
    LocalDate startDate;
    LocalDate endDate;
    TimeScale scale;
    LineChartGenerator(LocalDate startDate, LocalDate endDate, LineChartObject object) {
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
        this.scale = scaleDecision();
        loadObject(object);
        setAsterisks();
    }
    // adjust the correct base amount and each asterisks worth
    private void setAsterisks() {
        eachAsteriskWorth = 10;
        baseAmount = 0;
        if (Collections.max(dateAndValues.values()) > 500) {
            baseAmount = rounding(Collections.min(dateAndValues.values()));
            if (getRange() > 500) {
                eachAsteriskWorth = rounding((getRange() / 50));
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
    private void loadObject(LineChartObject object) {
        int frequency = scale.factor;
        switch (scale.unit) {
            case DAYS:
                populateMap(startDate, endDate,
                        frequency, (date, port) -> port.getValueOnDate(date), object);
                break;
            case MONTHS:
                populateMap(startDate, endDate, frequency,
                        (date, port) -> getEndofMonthValue(date.getMonthValue(), date.getYear(), port),
                        object);
                break;
            case YEARS:
                populateMap(startDate, endDate, frequency,
                        (date, port) -> getEndOfYearValue(date.getYear(), port), object);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + scale.unit);
        }
    }
    // take a start date, end date, frequency, portfolio, and a bi-function which turns a date and a portfolio into the
    // value of that portfolio in that given date.
    private void populateMap(LocalDate start, LocalDate end, int frequency,
                             BiFunction<LocalDate, LineChartObject, Double> valueFunction,
                             LineChartObject object) {
        LocalDate date = start;
        while (date.isBefore(end) || date.isEqual(end)) {
            dateAndValues.put(date, valueFunction.apply(date, object));
            date = incrementDate(date, frequency);
        }
        removeLastDup(dateAndValues.lastKey(), end);
        dateAndValues.put(end, valueFunction.apply(end, object));
    }
    // removeLastDup will check if the end date given has the same month/year
    // as the last item in the ValueComboList, if remove the last item if duplicate
    private void removeLastDup(LocalDate lastDateInList, LocalDate end) {
        switch (scale.unit) {
            case MONTHS:
                if (lastDateInList.getMonth() == end.getMonth()) {
                    dateAndValues.pollLastEntry();
                }
                break;
            case YEARS:
                if (lastDateInList.getYear() == end.getYear()) {
                    dateAndValues.pollLastEntry();
                }
                break;
        }
    }

    // increase date based on the given scale
    private LocalDate incrementDate(LocalDate date, int frequency) {
        switch (scale.unit) {
            case DAYS:
                return date.plusDays(frequency);
            case MONTHS:
                return date.plusMonths(frequency);
            case YEARS:
                return date.plusYears(frequency);
            default:
                throw new IllegalStateException("Unexpected value: " + scale.unit);
        }
    }
    // get the closing price on the end of month day
    private double getEndofMonthValue(int month, int year, LineChartObject object) {
        LocalDate endOfMonth = getEndOfMonth(year, month);
        return object.getValueOnDate(endOfMonth);
    }
    // get the closing price on the end of year day
    private double getEndOfYearValue(int year, LineChartObject object) {
        LocalDate endOfYear = LocalDate.of(year, 12, 31);
        return object.getValueOnDate(endOfYear);
    }
    private LocalDate getEndOfMonth(int year, int month) {
        // YearMonth class to handle year and month
        YearMonth yearMonth = YearMonth.of(year, month);
        // Get the last day of the month
        return yearMonth.atEndOfMonth();
    }
    // keep it. This method return Time gap between each line of chartTable
    private TimeScale scaleDecision() {
        int years = (int) YEARS.between(startDate, endDate);
        int months = (int) MONTHS.between(startDate, endDate);
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        int scale; // set scale default value to be 1
        ChronoUnit unit;
        if (years <= 4) { // if year is less than 4, unit should be day or month
            if (months <= 4) { // if month less than 4, unit should be day
                unit = ChronoUnit.DAYS;
                scale = scaling(days);
            } else {
                unit = MONTHS;
                scale = scaling(months);
            }
        } else { // if year is greater than 4, unit be year
            unit = YEARS;
            scale = scaling(years);
        }
        return new TimeScale(unit, scale);
    }
    // calculate the scale of the unit if we want to
    // keep the rows less than 30
    private int scaling(int period) {
        return (period - 1) / 29 + 1;
    }
    // return the range value of the list
    private double getRange() {
        return Collections.max(dateAndValues.values()) - Collections.min(dateAndValues.values());
    }
    // generate the whole chart table
    String generate() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<LocalDate, Double> entry : dateAndValues.entrySet()) {
            switch (scale.unit) {
                case DAYS:
                    builder.append(entry.getKey());
                    builder.append(":");
                    builder.append(renderAsterisks(entry.getValue()));
                    break;
                case MONTHS:
                    builder.append(entry.getKey().getYear());
                    builder.append(" ");
                    builder.append(entry.getKey().getMonth().toString(), 0, 3);
                    builder.append(":");
                    builder.append(renderAsterisks(entry.getValue()));
                    break;
                case YEARS:
                    builder.append(entry.getKey().getYear());
                    builder.append(":");
                    builder.append(renderAsterisks(entry.getValue()));
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
 