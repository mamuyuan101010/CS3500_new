package view;

import controller.Features;
import model.APIStockDataSource;
import model.FileStockDataSource;
import model.StockDataSource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

class BuySellStocksPanel extends JPanel {
  private final JButton buyStockButton;
  private final JButton sellStockButton;
  private final JRadioButton localFileButton;
  private final JTextField dataSourceFileField;
  private boolean useAPI;
  private JTextField yearField;
  private JTextField monthField;
  private JTextField dayField;
  private JTextField portfolioNameField;
  private JTextField tickerField;
  private JTextField buyQuantityField;
  private JTextField sellQuantityField;

  BuySellStocksPanel() {
    super(new GridLayout(10, 1));
    // set up panels, labels, and text fields.
    this.setBorder(BorderFactory.createTitledBorder("Buy/Sell Stock"));
    tickerField = new JTextField(10);
    buyQuantityField = new JTextField(10);
    sellQuantityField = new JTextField(10);

    // date field
    JPanel datePanel = new JPanel(new FlowLayout());
    yearField = new JTextField(4);
    monthField = new JTextField(2);
    dayField = new JTextField(2);
    datePanel.add(new JLabel("Date (YYYY-MM-DD):"));
    datePanel.add(yearField);
    datePanel.add(new JLabel("-"));
    datePanel.add(monthField);
    datePanel.add(new JLabel("-"));
    datePanel.add(dayField);

    // portfolio name field
    JPanel portfolioName = new JPanel(new FlowLayout());
    portfolioName.add(new JLabel("Portfolio Name:"));
    portfolioNameField= new JTextField(20);
    portfolioName.add(portfolioNameField);

    // button field
    buyStockButton = new JButton("Buy Stock");
    sellStockButton = new JButton("Sell Stock");

    // ticker name field
    JPanel stockTickerPanel = new JPanel(new FlowLayout());
    stockTickerPanel.add(new JLabel("Stock Ticker:"));
    stockTickerPanel.add(tickerField);
    this.add(stockTickerPanel);

    // buy/sell field
    JPanel buyPanel = new JPanel(new FlowLayout());
    buyPanel.add(new JLabel("Quantity:"));
    buyPanel.add(buyQuantityField);
    buyPanel.add(buyStockButton);
    JPanel sellPanel = new JPanel(new FlowLayout());
    sellPanel.add(new JLabel("Quantity:"));
    sellPanel.add(sellQuantityField);
    sellPanel.add(sellStockButton);

    this.add(datePanel);
    this.add(portfolioName);
    this.add(buyPanel);
    this.add(sellPanel);


    // api and local file option field
    JRadioButton aPIButton = new JRadioButton("API");
    localFileButton = new JRadioButton("Local File");

    ButtonGroup dataSourceButtonGroup = new ButtonGroup();
    dataSourceButtonGroup.add(aPIButton);
    dataSourceButtonGroup.add(localFileButton);

    this.add(new JLabel("Choose Data Source:"));
    JPanel radioPanel = new JPanel(new FlowLayout());
    radioPanel.add(aPIButton);
    radioPanel.add(localFileButton);
    this.add(radioPanel);

    dataSourceFileField = new JTextField(20);
    dataSourceFileField.setVisible(false);  // Hide initially
    this.add(new JLabel("Local File Name:"));
    this.add(dataSourceFileField);

    aPIButton.addItemListener(new RadioButtonListener());
    localFileButton.addItemListener(new RadioButtonListener());

  }

  StockDataSource sourceGenerator() {
    if (this.useAPI) {
      return new APIStockDataSource();
    }
    return new FileStockDataSource(dataSourceFileField.getText());
  }

  private class RadioButtonListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
      if (localFileButton.isSelected()) {
        dataSourceFileField.setVisible(true);
        useAPI = false;
      } else {
        dataSourceFileField.setVisible(false);
        useAPI = true;
      }
      BuySellStocksPanel.this.revalidate();
      BuySellStocksPanel.this.repaint();
    }
  }

  public void addFeatures(Features features) {
    buyStockButton.addActionListener(evt -> {
      try {
        int year = Integer.parseInt(yearField.getText());
        int month = Integer.parseInt(monthField.getText());
        int day = Integer.parseInt(dayField.getText());
        LocalDate date = LocalDate.of(year, month, day);
        features.addStock(
                date,
                portfolioNameField.getText(),
                tickerField.getText(),
                sourceGenerator(),
                Integer.parseInt(buyQuantityField.getText())
        );
      } catch (Exception e) {
          try {
              features.showMessage(e.getMessage(), Panels.BuySellPanel);
          } catch (IOException ex) {
              throw new RuntimeException(ex);
          }
      }
    });

    sellStockButton.addActionListener(evt -> {
      try {
        int year = Integer.parseInt(yearField.getText());
        int month = Integer.parseInt(monthField.getText());
        int day = Integer.parseInt(dayField.getText());
        LocalDate date = LocalDate.of(year, month, day);
        features.sellStock(
                date,
                portfolioNameField.getText(),
                tickerField.getText(),
                Double.parseDouble(sellQuantityField.getText())
        );
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
