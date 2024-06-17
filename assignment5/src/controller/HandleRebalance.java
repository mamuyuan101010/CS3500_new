package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.IModelV2;
import view.IView;

//command of rebalancing a portfolio
class HandleRebalance extends AbstractCommandsV2 {

  HandleRebalance(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModelV2 model, IView v) throws IOException {
    try {
      v.showCustomMessage(
              "When handling future sales, make sure to refer to the "
                      + "balanced values, not the pre-balanced values!");
      String portfolioName = getInput(v, "\nWhich portfolio would you like to rebalance? ");
      LocalDate date = getDatesInput(v, "rebalance");

      displayDistribution(model, v, date, portfolioName);
      int numOfStocks = model.getComposition(date, portfolioName).size();
      Map<String, Integer> weights = new HashMap<>();

      for (int i = 0; i < numOfStocks; i++) {
        String ticker = getInput(v, "Enter ticker symbol for stock " + (i + 1) + ": ");
        int weight = Integer.parseInt(getInput(v, "Enter weight for " + ticker + ": "));
        weights.put(ticker, weight);
      }
      model.rebalance(portfolioName, date, weights);
      v.showCustomMessage("Successfully rebalanced the portfolio!\n");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
