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
class HandleAddDateInvestment extends AbstractCommandsV2 {

  HandleAddDateInvestment(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModelV2 model, IView v) throws IOException {
    try {
      String portfolioName = getInput(v, "Which portfolio would you like to add to? ");
      String ticker = getTickerSymbol(v);
      LocalDate date = LocalDate.parse(
              getInput(v, "Date at which you would like add on (YYYY-MM-DD): "));
      int quantity = Integer.parseInt(
              getInput(v, "How many whole (non-fractional) shares would you like to add? "));

      StockDataSource source = getSourceInput(v);
      Investment stock = model.queryStock(ticker, source);
      model.addInvestmentToPortfolio(date, portfolioName, stock , quantity);
      v.showCustomMessage("Successfully added!\n");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
