package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.IModelV2;
import model.Investment;
import model.StockDataSource;
import model.Transactions;
import view.IView;

//command of reversing a transaction
class HandleReverse extends AbstractCommandsV2 {

  HandleReverse(Scanner s) {
    super(s);
  }

  @Override
  public void execute(IModelV2 model, IView v) throws IOException {
    try {
      String portfolioName = getInput(
              v, "In which portfolio would you like to undo a transaction? ");
      List<Transactions> transactionLog = model.getTransactions(portfolioName);

      v.showCustomMessage("Transactions in portfolio " + portfolioName + ":\n");
      for (int i = 0; i < transactionLog.size(); i++) {
        v.showCustomMessage(transactionLog.toString());
      }
      v.showCustomMessage("\n");

      String ticker = getTickerSymbol(v);
      LocalDate date = getDatesInput(v, "undo a transaction");
      double quantity = Double.parseDouble(
              getInput(v, "How many shares would you like to undo? "));
      String type = getInput(v, "Were those shares previously 'bought' or 'sold'? ");

      StockDataSource source = getSourceInput(v);
      Investment stock = model.queryStock(ticker, source);
      model.reverseTransaction(date, portfolioName, stock, quantity, type);
      v.showCustomMessage("Successfully undone!\n");
    } catch (Exception e) {
      v.showCustomMessage("Error: " + e.getMessage() + "\n");
    }
  }

}
