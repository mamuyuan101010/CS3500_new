package lineChart;

import model.LineChartObject;
import model.PortfolioV2;

import java.time.LocalDate;

import java.util.Objects;

public class LineChart implements ILineChart {
  LocalDate startDate;
  LocalDate endDate;
  LineChartObject object;

  public LineChart(LocalDate startDate, LocalDate endDate, LineChartObject object) {
    this.startDate = Objects.requireNonNull(startDate);
    this.endDate = Objects.requireNonNull(endDate);
    this.object = Objects.requireNonNull(object);
  }


  public String generateChart() {
    LineChartGenerator generator = new LineChartGenerator(startDate, endDate,
            object);
    return generator.generate();
  }
}
