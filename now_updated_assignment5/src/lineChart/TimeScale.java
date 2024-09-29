package lineChart;

import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeScale {
  ChronoUnit unit;
  int factor;

  TimeScale(ChronoUnit unit, int factor) {
    this.unit = Objects.requireNonNull(unit);
    this.factor = factor;
  }
}
