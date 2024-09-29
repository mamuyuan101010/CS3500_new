package controller;

import java.io.IOException;
import java.util.Scanner;

import model.IModelV2;
import view.IView;
import view.Panels;

//command of saving a portfolio to a file
class HandleSaveFile extends AbstractCommandsV2 {
  private String portfolioName;
  private String path;

  HandleSaveFile(Scanner s) {
    super(s);
  }

  HandleSaveFile(String portfolioName, String path) {
    this.portfolioName = portfolioName;
    this.path = path;
  }

  @Override
  public void prompterHelp(IView v) throws IOException {
    portfolioName = getInput(v, "Which portfolio would you like to save? ");
    path = getInput(v, "Which absolute path on your computer would you like to save to? ");
  }

  @Override
  public void execute(IModelV2 model, IView v) throws IOException {
    try {
      model.savePortfolio(portfolioName, path);
      try {
        v.showCustomMessage("Successfully saved " + portfolioName + " to " + path + "!");
      } catch (Exception e) {
        handleInterfaceMsg("Successfully saved " + portfolioName + " to " + path + "!",
                v, Panels.LoadSavePanel);
      }
    } catch (Exception e) {
      try {
        v.showCustomMessage("Error: " + e.getMessage() + "\n");
      } catch (Exception e1) {
        handleInterfaceMsg("Error: " + e.getMessage() + "\n", v, Panels.LoadSavePanel);
      }
    }
  }

}
