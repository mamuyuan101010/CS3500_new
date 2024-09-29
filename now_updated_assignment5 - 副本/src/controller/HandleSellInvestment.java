package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import model.IModelV2;
import model.Investment;
import model.Stock;
import model.StockDataSource;
import view.IView;

//command of adding stock to portfolio
class HandleSellInvestment extends AbstractCommandsV2 {

  HandleSellInvestment(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModelV2 model, IView v) throws IOException {
    try {
      String portfolioName = getInput(v, "Which portfolio would you like to sell from? ");
      String ticker = getTickerSymbol(v);
      LocalDate date = LocalDate.parse(
              getInput(v, "Date at which you would like to sell on (YYYY-MM-DD): "));
      double quantity = Double.parseDouble(
              getInput(v, "How many shares would you like to sell? "));

      StockDataSource source = getSourceInput(v);
      Investment stock = model.queryStock(ticker, source);
      model.sellInvestmentFromPortfolio(date, portfolioName, stock , quantity);
      v.showCustomMessage("Successfully sold!\n");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
