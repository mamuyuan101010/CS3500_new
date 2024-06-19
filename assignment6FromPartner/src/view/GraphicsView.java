package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import controller.Features;
import model.APIStockDataSource;
import model.FileStockDataSource;
import model.StockDataSource;

public class GraphicsView implements GUIView {
  private JFrame frame;
  private JPanel mainPanel;
  private JPanel createPortfolioPanel;
  private BuySellStocksPanel buySellStockPanel;
  private JPanel queryPortfolioPanel;

  private JButton createPortfolioButton;
  private JTextField portfolioNameField;

//  private JButton buyStockButton;
//  private JTextField tickerField;
//  private JTextField quantityField;
//  private JTextField yearField;
//  private JTextField monthField;
//  private JTextField dayField;
//
//  private JButton sellStockButton;

  private JButton queryValueButton;
  private JTextField queryValuePortfolioNameField;
  private JTextField queryValueDateField;

  private JButton queryCompositionButton;
  private JTextField queryCompositionPortfolioNameField;
  private JTextField queryCompositionDateField;

//  private JRadioButton alphaVantageRadioButton;
//  private JRadioButton yahooFinanceRadioButton;
//  private ButtonGroup dataSourceButtonGroup;
//  private JTextField dataSourceFileField;

  // load and save file
  private JButton loadButton;
  private JButton saveButton;
  private JTextField fileName;

  public GraphicsView() {
    initialize();
  }

  private void initialize() {
    frame = new JFrame("Investment Application");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);

    mainPanel = new JPanel(new GridLayout(1, 3));

    createPortfolioPanel = new JPanel(new BorderLayout());
    setupCreatePortfolioPanel();

    buySellStockPanel = new BuySellStocksPanel();

    queryPortfolioPanel = new JPanel(new BorderLayout());
    setupQueryPortfolioPanel();

    mainPanel.add(createPortfolioPanel);
    mainPanel.add(buySellStockPanel);
    mainPanel.add(queryPortfolioPanel);

    frame.add(mainPanel);
    frame.pack();
    frame.setVisible(true);

  }

  private void setupCreatePortfolioPanel() {
    createPortfolioPanel.setBorder(BorderFactory.createTitledBorder(""));
    JPanel panel = new JPanel(new GridLayout(2, 1));
    JPanel inputPanel = new JPanel(new FlowLayout());
    inputPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
    portfolioNameField = new JTextField(20);
    createPortfolioButton = new JButton("Create Portfolio");
    inputPanel.add(new JLabel("Portfolio Name:"));
    inputPanel.add(portfolioNameField);
    inputPanel.add(createPortfolioButton);
    panel.add(inputPanel);

    // add load and save file
    loadButton = new JButton("Load");
    saveButton = new JButton("Save");
    fileName = new JTextField(20);
    JPanel loadSavePanel = new JPanel(new FlowLayout());
    loadSavePanel.setBorder(BorderFactory.createTitledBorder("Load/Save Files"));
    loadSavePanel.add(new JLabel("Enter File Name Here: "));
    loadSavePanel.add(fileName);
    JPanel loadSave = new JPanel(new FlowLayout());
    loadSave.add(loadButton);
    loadSave.add(saveButton);
    loadSavePanel.add(loadSave);
    panel.add(loadSavePanel);
    createPortfolioPanel.add(panel, BorderLayout.CENTER);
  }

  // this method is for handling buy and sell stocks
//  private void setupBuySellStockPanel() {
//    buySellStockPanel.setBorder(BorderFactory.createTitledBorder("Buy/Sell Stock"));
//
//    JPanel panel = new JPanel(new GridLayout(8, 2));
//
//    tickerField = new JTextField(10);
//    quantityField = new JTextField(10);
//    JPanel DatePanel = new JPanel(new FlowLayout());
//    yearField = new JTextField(4);
//    monthField = new JTextField(2);
//    dayField = new JTextField(2);
//    DatePanel.add(yearField);
//    DatePanel.add(monthField);
//    DatePanel.add(dayField);
//    buyStockButton = new JButton("Buy Stock");
//
//    sellStockButton = new JButton("Sell Stock");
//
//    panel.add(new JLabel("Buy Stock Ticker:"));
//    panel.add(tickerField);
//    panel.add(new JLabel("Quantity:"));
//    panel.add(quantityField);
//    panel.add(new JLabel("Date (YYYY-MM-DD):"));
//    panel.add(DatePanel);
//    JPanel buttons = new JPanel(new FlowLayout());
//    buttons.add(buyStockButton);
//    buttons.add(sellStockButton);
//    panel.add(buttons);
//
//    alphaVantageRadioButton = new JRadioButton("Alpha Vantage");
//    yahooFinanceRadioButton = new JRadioButton("Yahoo Finance");
//
//    dataSourceButtonGroup = new ButtonGroup();
//    dataSourceButtonGroup.add(alphaVantageRadioButton);
//    dataSourceButtonGroup.add(yahooFinanceRadioButton);
//
//    panel.add(new JLabel("Choose Data Source:"));
//    JPanel radioPanel = new JPanel(new FlowLayout());
//    radioPanel.add(alphaVantageRadioButton);
//    radioPanel.add(yahooFinanceRadioButton);
//    panel.add(radioPanel);
//
//    dataSourceFileField = new JTextField(20);
//    dataSourceFileField.setVisible(false);  // Hide initially
//    panel.add(new JLabel("File Name:"));
//    panel.add(dataSourceFileField);
//
//    alphaVantageRadioButton.addItemListener(new RadioButtonListener());
//    yahooFinanceRadioButton.addItemListener(new RadioButtonListener());
//
//    buySellStockPanel.add(panel, BorderLayout.CENTER);
//  }

  private void setupQueryPortfolioPanel() {
    queryPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Query Portfolio"));

    JPanel panel = new JPanel(new GridLayout(6, 2));

    queryValuePortfolioNameField = new JTextField(10);
    queryValueDateField = new JTextField(10);
    queryValueButton = new JButton("Query Value");

    queryCompositionPortfolioNameField = new JTextField(10);
    queryCompositionDateField = new JTextField(10);
    queryCompositionButton = new JButton("Query Composition");

    panel.add(new JLabel("Portfolio Name for Value:"));
    panel.add(queryValuePortfolioNameField);
    panel.add(new JLabel("Date (YYYY-MM-DD):"));
    panel.add(queryValueDateField);
    panel.add(queryValueButton);

    panel.add(new JLabel("Portfolio Name for Composition:"));
    panel.add(queryCompositionPortfolioNameField);
    panel.add(new JLabel("Date (YYYY-MM-DD):"));
    panel.add(queryCompositionDateField);
    panel.add(queryCompositionButton);

    queryPortfolioPanel.add(panel, BorderLayout.CENTER);
  }

  @Override
  public void show() {
    frame.setVisible(true);
  }

  @Override
  public void setButtonListener(ActionListener listener) {
    createPortfolioButton.addActionListener(listener);

    // call the method in buySellStockPanel to add action listener
    buySellStockPanel.addActionListener(listener);


    queryValueButton.addActionListener(listener);
    queryCompositionButton.addActionListener(listener);
  }

  @Override
  public void addFeatures(Features features) {
    createPortfolioButton.addActionListener(evt -> {
      try {
        features.createPortfolio(portfolioNameField.getText());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    buyStockButton.addActionListener(evt -> {
      try {
        features.addStock(
                LocalDate.of(Integer.valueOf(yearField.getText()),
                        Integer.valueOf(monthField.getText())
                        ,Integer.valueOf(dayField.getText())),
                portfolioNameField.getText(),
                tickerField.getText(),
                getSelectedStockDataSource(),
                Double.parseDouble(quantityField.getText())
        );
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    sellStockButton.addActionListener(evt -> {
      try {
        features.sellStock(
                LocalDate.of(Integer.valueOf(yearField.getText()),
                        Integer.valueOf(monthField.getText())
                        ,Integer.valueOf(dayField.getText())),
                portfolioNameField.getText(),
                tickerField.getText(),
                Double.parseDouble(quantityField.getText())
        );
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    queryValueButton.addActionListener(evt -> {
      try {
        features.getValue(
                LocalDate.parse(queryValueDateField.getText()),
                queryValuePortfolioNameField.getText()
        );
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    queryCompositionButton.addActionListener(evt -> {
      try {
        features.getComposition(
                LocalDate.parse(queryCompositionDateField.getText()),
                queryCompositionPortfolioNameField.getText()
        );
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  private StockDataSource getSelectedStockDataSource() {
    if (alphaVantageRadioButton.isSelected()) {
      return new APIStockDataSource();
    } else if (yahooFinanceRadioButton.isSelected() && !dataSourceFileField.getText().isEmpty()) {
      return new FileStockDataSource(dataSourceFileField.getText());
    } else {
      return null; // Default to null or handle appropriately
    }
  }

  @Override
  public void showCustomMessage(String message) {
    JOptionPane.showMessageDialog(frame, message);
  }

  @Override
  public void showMenu() throws IOException {
    // Optional: Implement menu display if needed
  }

  @Override
  public void welcome() {
    showCustomMessage("Welcome to Investment Application");
  }

  @Override
  public void goodbye() {
    showCustomMessage("Goodbye!");
  }

//  private class RadioButtonListener implements ItemListener {
//    @Override
//    public void itemStateChanged(ItemEvent e) {
//      if (alphaVantageRadioButton.isSelected() || yahooFinanceRadioButton.isSelected()) {
//        dataSourceFileField.setVisible(false);
//      } else {
//        dataSourceFileField.setVisible(true);
//      }
//      buySellStockPanel.revalidate();
//      buySellStockPanel.repaint();
//    }
//  }
}