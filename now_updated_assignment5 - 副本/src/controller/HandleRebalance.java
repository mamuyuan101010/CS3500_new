package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.IModelV2;
import model.Investment;
import model.Stock;
import model.StockDataSource;
import view.IView;

//command of adding stock to portfolio
class HandleRebalance extends AbstractCommandsV2 {

  HandleRebalance(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModelV2 model, IView v) throws IOException {
    try {
      v.showCustomMessage("When handling future sales, make sure to refer to the balanced values, not the pre-balanced values!");
      String portfolioName = getInput(v, "Which portfolio would you like to rebalance? ");
      LocalDate date = LocalDate.parse(
              getInput(v, "Date at which you would like to rebalance (YYYY-MM-DD): "));

      Map<String, Double> dist = model.getDistributionValue(date, portfolioName);
      StringBuilder showStocks = new StringBuilder();
      showStocks.append(portfolioName).append(" distribution of values on ").append(date).append(":\n");
      for (Map.Entry<String, Double> entry : dist.entrySet()) {
        showStocks.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
      }

      v.showCustomMessage(showStocks.toString());
      int numOfStocks = model.getComposition(date, portfolioName).size();
      Map<String, Integer> weights = new HashMap<>();

      for (int i = 0; i < numOfStocks; i++) {
        String ticker = getInput(v, "Enter ticker symbol for stock " + (i + 1) + ": ");
        int weight = Integer.parseInt(getInput(v, "Enter weight for " + ticker + ": "));
        weights.put(ticker, weight);
      }
      model.Rebalance(portfolioName, date, weights);
      v.showCustomMessage("Successfully rebalanced the portfolio!\n");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
