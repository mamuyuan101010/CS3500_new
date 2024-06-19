package controller;

import java.io.IOException;
import java.time.LocalDate;

import model.StockDataSource;

public interface Features {

  void createPortfolio(String name) throws IOException;
  void addStock(LocalDate date, String portfolioName, String ticker, StockDataSource s, double quantity) throws IOException;
  void sellStock(LocalDate date, String portfolioName, String ticker, double quantity) throws IOException;
  void getValue(LocalDate date, String portfolioName) throws IOException;
  void getComposition(LocalDate date, String portfolioName) throws IOException;
  void savePortfolio(String portfolioName, String fileName) throws IOException;
  void loadPortfolio(String filename, String name) throws IOException;
}
