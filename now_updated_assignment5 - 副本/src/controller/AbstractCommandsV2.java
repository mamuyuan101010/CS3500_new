package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import model.APIStockDataSource;
import model.FileStockDataSource;
import model.StockDataSource;
import view.IView;

//abstract class to represent shared operations by each command
abstract class AbstractCommandsV2 implements StocksCommandsV2 {
  protected final Scanner scanner;

  //abstract constructor for each command
  protected AbstractCommandsV2(Scanner scanner) {
    this.scanner = scanner;
  }

  //combines the process of view displaying text and controller getting user input
  protected String getInput(IView view, String prompt) throws IOException {
    view.showCustomMessage(prompt);
    return scanner.nextLine();
  }

  //set method for retrieving stock ticker symbol
  protected String getTickerSymbol(IView view) throws IOException {
    return getInput(view, "Enter ticker symbol of desired stock: ");
  }

  //set method for retrieving a date
  protected LocalDate getDate(IView view, String specific) throws IOException {
    return LocalDate.parse(
            getInput(view, specific + " date of the range you will examine (YYYY-MM-DD): "));
  }

  //set method for retrieving a number of days for any applicable command
  protected int getDaysBack(IView view) throws IOException {
    return Integer.parseInt(getInput(view, "How many days would you like to go back? "));
  }

  //gets the specified source of where the stock data has to come from
  protected StockDataSource getSourceInput(IView view) throws IOException {
    view.showCustomMessage("Source of stock data? API or File: ");
    String sourceType = scanner.nextLine().toLowerCase();
    switch (sourceType) {
      case "api":
        return new APIStockDataSource();
      case "file":
        view.showCustomMessage("Provide the file path: ");
        return new FileStockDataSource(scanner.nextLine());
      default:
        throw new IllegalArgumentException("Source not recognized");
    }
  }
}