package controller;

import java.io.IOException;
import java.util.Scanner;

import model.IModelV2;
import view.IView;

//command of saving a portfolio to a file
class HandleSaveFile extends AbstractCommandsV2 {
  private String portfolioName;
  private String path;

  HandleSaveFile(Scanner s) {
    super(s);
  }

  @Override
  public void prompter(IView v) throws IOException {
    try {
      portfolioName = getInput(v, "Which portfolio would you like to save? ");
      path = getInput(v, "Which absolute path on your computer would you like to save to? ");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

  @Override
  public void execute(IModelV2 model, IView v) throws IOException {
    try {
      model.savePortfolio(portfolioName, path);
      v.showCustomMessage("Successfully saved " + portfolioName + " to " + path + "!");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
