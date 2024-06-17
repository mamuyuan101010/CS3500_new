package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.IModel;
import model.StockDataSource;
import view.IView;

//command for getting crossovers of a stock
class HandleCrossovers extends AbstractCommands {

  HandleCrossovers(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModel model, IView v) throws IOException {
    try {
      String ticker = getTickerSymbol(v);
      LocalDate start = getDatesInput(v, "start examining the crossovers");
      LocalDate end = getDatesInput(v, "end examining the crossovers");
      int days = getDaysBack(v);

      StockDataSource source = getSourceInput(v);
      List<LocalDate> crossovers = model.queryStock(ticker, source)
              .findCrossovers(start, end, days);
      v.showCustomMessage("List of Crossover Dates: " + crossovers + "\n");
    } catch (Exception e) {
      v.showCustomMessage(
              "Error: Insufficient/No data for those parameters. Please try again.\n");
    }
  }
}
