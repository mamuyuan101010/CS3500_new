package lineChart;

import java.util.Objects;

class TimeScale {
  PeriodUnit unit;
  int factor;

  TimeScale(PeriodUnit unit, int factor) {
    this.unit = Objects.requireNonNull(unit);
    this.factor = factor;
  }
}
