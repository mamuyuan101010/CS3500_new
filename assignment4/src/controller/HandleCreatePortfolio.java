package controller;

import java.io.IOException;
import java.util.Scanner;

import model.IModel;
import view.IView;

//command for creating a portfolio
class HandleCreatePortfolio extends AbstractCommands {

  HandleCreatePortfolio(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModel model, IView v) throws IOException {
    try {
      String portfolioName = getInput(v, "What name would you like to give your portfolio? ");
      model.createPortfolio(portfolioName);
      v.showCustomMessage("Successfully created!\n");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
