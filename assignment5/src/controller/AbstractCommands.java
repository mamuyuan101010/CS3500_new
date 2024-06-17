package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import model.APIStockDataSource;
import model.FileStockDataSource;
import model.StockDataSource;
import view.IView;

//abstract class to represent shared operations by each command
abstract class AbstractCommands implements StocksCommands {
  protected final Scanner scanner;

  //abstract constructor for each command
  protected AbstractCommands(Scanner scanner) {
    this.scanner = scanner;
  }

  //combines the process of view displaying text and controller getting user input
  protected String getInput(IView view, String prompt) throws IOException {
    view.showCustomMessage(prompt);
    return scanner.nextLine();
  }

  //set method for retrieving stock ticker symbol
  protected String getTickerSymbol(IView view) throws IOException {
    String input = getInput(view, "Enter ticker symbol of desired stock: ");
    return input.toUpperCase();
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


  //CHANGE TO EXISTING CODE: added a helper method to make inputting the date more user-friendly
  //and less error-prone, removed previous dateInput method
  //gets the specified source of where the stock data has to come from
  protected LocalDate getDatesInput(IView v, String message) throws IOException {
    int year = Integer.parseInt(
            getInput(v, "Year in which you would like to " + message + " (YYYY): "));
    int month = Integer.parseInt(
            getInput(v, "Month in which you would like to " + message + " (1-12): "));
    int day = Integer.parseInt(
            getInput(v, "Day on which you would like to " + message + " (1-28/31): "));
    return LocalDate.of(year, month, day);
  }
}