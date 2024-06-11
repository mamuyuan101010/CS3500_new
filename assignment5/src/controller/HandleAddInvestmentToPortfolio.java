package controller;

import java.io.IOException;
import java.util.Scanner;

import model.IModel;
import model.Investment;
import model.Stock;
import model.StockDataSource;
import view.IView;

//command of adding stock to portfolio
class HandleAddInvestmentToPortfolio extends AbstractCommands {

  HandleAddInvestmentToPortfolio(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModel model, IView v) throws IOException {
    try {
      String portfolioName = getInput(v, "Which portfolio would you like to add to? ");
      String ticker = getTickerSymbol(v);
      int quantity = Integer.parseInt(
              getInput(v, "How many whole (non-fractional) shares would you like to add? "));

      StockDataSource source = getSourceInput(v);
      Investment stock = model.queryStock(ticker, source);
      model.addInvestmentToPortfolio(portfolioName, stock , quantity);
      v.showCustomMessage("Successfully added!\n");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
