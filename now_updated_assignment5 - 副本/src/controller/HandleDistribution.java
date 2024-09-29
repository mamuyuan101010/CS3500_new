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
class HandleDistribution extends AbstractCommandsV2 {

  HandleDistribution(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModelV2 model, IView v) throws IOException {
    try {
      String portfolioName = getInput(v, "Which portfolio would you like to find the distribution of values of? ");
      LocalDate date = LocalDate.parse(
              getInput(v, "Date at which you would like the distribution of values (YYYY-MM-DD): "));
      Map<String, Double> dist = model.getDistributionValue(date, portfolioName);

      StringBuilder message = new StringBuilder();
      message.append(portfolioName).append(" distribution of values on ").append(date).append(":\n");
      for (Map.Entry<String, Double> entry : dist.entrySet()) {
        message.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
      }

      v.showCustomMessage(message.toString());
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
