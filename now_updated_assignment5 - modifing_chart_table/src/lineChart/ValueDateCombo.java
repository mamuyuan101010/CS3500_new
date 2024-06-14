package lineChart;

import java.time.LocalDate;
import java.util.Objects;

class ValueDateCombo {
    private final LocalDate date;
    private final Double value;

    ValueDateCombo(LocalDate date, double value) {
        this.date = Objects.requireNonNull(date);
        this.value = Objects.requireNonNull(value);
    }

    LocalDate getDate() {
        return LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
    }

    Double getValue() {
        return value;
    }
}