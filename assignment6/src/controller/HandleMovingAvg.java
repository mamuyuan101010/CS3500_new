package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import model.IModel;
import model.StockDataSource;
import view.IView;

//command for getting moving avg of a stock
class HandleMovingAvg extends AbstractCommands {

  HandleMovingAvg(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModel model, IView v) throws IOException {
    try {
      String ticker = getTickerSymbol(v);
      LocalDate start = getDatesInput(v, "start the moving average from");
      int days = getDaysBack(v);

      StockDataSource source = getSourceInput(v);
      double avg = model.queryStock(ticker, source).calculateXDayMovingAvg(start, days);
      v.showCustomMessage(days + " Days Moving Average: $" + avg + "\n");
    } catch (Exception e) {
      v.showCustomMessage(
              "Error: Insufficient/No data for those parameters. Please try again.\n");
    }
  }

}
