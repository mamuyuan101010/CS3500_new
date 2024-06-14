package lineChart;

import java.time.LocalDate;
import java.util.Objects;

import model.PortfolioV2;

public class LineChart {
  LocalDate startDate;
  LocalDate endDate;
  PortfolioV2 portfolio;

  public LineChart(LocalDate startDate, LocalDate endDate, PortfolioV2 portfolio) {
    this.startDate = Objects.requireNonNull(startDate);
    this.endDate = Objects.requireNonNull(endDate);
    this.portfolio = Objects.requireNonNull(portfolio);
  }

  private void loadPortfolio() {
    
  }

}
