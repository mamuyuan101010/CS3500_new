package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import model.IModel;
import view.IView;

//command for getting value of portfolio
class HandleGetPortfolioValue extends AbstractCommands {

  HandleGetPortfolioValue(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModel model, IView v) throws IOException {
    try {
      String portfolioName = getInput(v, "Which portfolio would you like to find the value of? ");
      LocalDate date = LocalDate.parse(
              getInput(v, "Date at which you would like value (YYYY-MM-DD): "));
      double value = model.getValueOfPortfolio(portfolioName, date);
      v.showCustomMessage(portfolioName + " was valued at " + value + " on that date\n");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
