package lineChart;

import java.time.LocalDate;
import java.util.Objects;

public class LineChart {
  LocalDate startDate;
  LocalDate endDate;
  String portfolioName;

  public LineChart(LocalDate startDate, LocalDate endDate, String portfolio) {
    this.startDate = Objects.requireNonNull(startDate);
    this.endDate = Objects.requireNonNull(endDate);
    this.portfolioName = Objects.requireNonNull(portfolio);
  }

  private void loadPortfolio() {
  }

}
