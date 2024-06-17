package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import model.IModel;
import model.StockDataSource;
import view.IView;

//fetching the gain or loss of a stock command
class HandleGainOrLoss extends AbstractCommands {

  HandleGainOrLoss(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModel model, IView v) throws IOException {
    try {
      String ticker = getTickerSymbol(v);
      LocalDate start = getDatesInput(v, "start finding the gain or loss");
      LocalDate end = getDatesInput(v, "end finding the gain or loss");

      StockDataSource source = getSourceInput(v);
      double gainOrLoss = model.queryStock(ticker, source).fetchGainOrLoss(start, end);
      v.showCustomMessage("Gain or Loss: $" + gainOrLoss + "\n");
    } catch (Exception e) {
      v.showCustomMessage(
              "Error: Insufficient/No data for those parameters. Please try again.\n");
    }
  }

}
