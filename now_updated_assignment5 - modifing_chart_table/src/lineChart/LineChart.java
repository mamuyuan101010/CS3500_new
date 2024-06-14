package lineChart;

import model.PortfolioV2;

import java.time.LocalDate;

import java.util.Objects;

public class LineChart implements ILineChart {
  LocalDate startDate;
  LocalDate endDate;
  PortfolioV2 portfolio;

  public LineChart(LocalDate startDate, LocalDate endDate, PortfolioV2 portfolio) {
    this.startDate = Objects.requireNonNull(startDate);
    this.endDate = Objects.requireNonNull(endDate);
    this.portfolio = Objects.requireNonNull(portfolio);
  }


  public String generateChart() {
    LineChartGenerator generator = new LineChartGenerator(startDate, endDate,
            portfolio);
    return generator.generate();
  }
}
