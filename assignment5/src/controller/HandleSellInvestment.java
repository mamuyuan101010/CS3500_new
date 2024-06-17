package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import model.IModelV2;
import view.IView;

//command of selling stock from portfolio
class HandleSellInvestment extends AbstractCommandsV2 {

  HandleSellInvestment(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModelV2 model, IView v) throws IOException {
    try {
      String portfolioName = getInput(v, "Which portfolio would you like to sell from? ");
      String ticker = getTickerSymbol(v);
      LocalDate date = getDatesInput(v, "sell investment");
      double quantity = Double.parseDouble(
              getInput(v, "How many shares would you like to sell? "));

      model.sellInvestmentFromPortfolio(date, portfolioName, ticker , quantity);
      v.showCustomMessage("Successfully sold!\n");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
