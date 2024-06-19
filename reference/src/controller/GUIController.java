package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import model.IModelV2;
import model.StockDataSource;
import view.GUIView;

public class GUIController implements Features {
  private final IModelV2 model;
  private GUIView view;

  public GUIController(IModelV2 m) {
    model = m;
  }

  public void setView(GUIView v) {
    view = v;
    view.addFeatures(this);
  }

  public void delegate(String key) {
    //knownCommands.get(key).prompter;
    //knownCommands.get(key).execute;
  }


  @Override
  public void createPortfolio(String name) throws IOException {
    StocksCommands c =  new HandleCreatePortfolio(name);
    c.execute(model, view);

    //new HandleCreatePortfolio(null).execute();
    //delegate the specifc key
  }

  @Override
  public void addStock(LocalDate date, String portfolioName, String ticker, StockDataSource s, double quantity) throws IOException {
    StocksCommandsV2 c =  new HandleAddDateInvestment(null);
    c.execute(model, view);
  }

  @Override
  public void sellStock(LocalDate date, String portfolioName, String ticker, double quantity) throws IOException {
    StocksCommandsV2 c =  new HandleSellInvestment(null);
    c.execute(model, view);
  }

  @Override
  public void getValue(LocalDate date, String portfolioName) throws IOException {
    StocksCommands c =  new HandleGetPortfolioValue(null);
    c.execute(model, view);
  }

  @Override
  public void getComposition(LocalDate date, String portfolioName) throws IOException {
//    StocksCommandsV2 c =  new HandleComposition(new Scanner("eh"));
//    c.execute(model, view);
    try {
      Map<String, Double> comp = model.getComposition(date, portfolioName);

      StringBuilder message = new StringBuilder();
      message.append(portfolioName).append(" composition on ").append(date).append(":\n");
      for (Map.Entry<String, Double> entry : comp.entrySet()) {
        message.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
      }

      view.showCustomMessage(message.toString());
    } catch (Exception e) {
      view.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

  @Override
  public void savePortfolio(String portfolioName, String fileName) throws IOException {
    StocksCommandsV2 c =  new HandleSaveFile(null);
    c.execute(model, view);
  }

  @Override
  public void loadPortfolio(String filename, String name) throws IOException {
    StocksCommandsV2 c =  new HandleLoadFile(null);
    c.execute(model, view);
  }

}
