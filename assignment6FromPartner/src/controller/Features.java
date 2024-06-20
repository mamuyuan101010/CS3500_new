package controller;

import java.io.IOException;
import java.time.LocalDate;

import model.StockDataSource;

/**
 * This interface represents the features that the current GUI user interface supports.
 * Essentially, the user should be able to perform all of these specified operations through
 * the GUI.
 */
public interface Features {

  void createPortfolio(String name) throws IOException;
  void addStock(LocalDate date, String portfolioName, String ticker, StockDataSource s, int quantity) throws IOException;
  void sellStock(LocalDate date, String portfolioName, String ticker, double quantity) throws IOException;
  void getValue(LocalDate date, String portfolioName) throws IOException;
  void getComposition(LocalDate date, String portfolioName) throws IOException;
  void savePortfolio(String portfolioName, String fileName) throws IOException;
  void loadPortfolio(String filename, String name) throws IOException;
}
