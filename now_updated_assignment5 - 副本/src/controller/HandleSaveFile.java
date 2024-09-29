package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import model.IModelV2;
import model.Investment;
import model.Stock;
import model.StockDataSource;
import view.IView;

//command of adding stock to portfolio
class HandleSaveFile extends AbstractCommandsV2 {

  HandleSaveFile(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModelV2 model, IView v) throws IOException {
    try {
      String portfolioName = getInput(v, "Which portfolio would you like to save? ");
      String path = getInput(v, "Which absolute path on your computer would you like to save to? ");
      model.savePortfolio(portfolioName, path);

      v.showCustomMessage("Successfully saved " + portfolioName + " to " + path + "!");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
