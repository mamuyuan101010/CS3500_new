package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class BuySellStocksPanel extends JPanel {

  private JButton buyStockButton;
  private JButton sellStockButton;
  private JTextField tickerField;
  private JTextField quantityField;
  private JTextField yearField;
  private JTextField monthField;
  private JTextField dayField;
  private JRadioButton alphaVantageRadioButton;
  private JRadioButton yahooFinanceRadioButton;
  private ButtonGroup dataSourceButtonGroup;
  private JTextField dataSourceFileField;

  BuySellStocksPanel() {
    super(new FlowLayout());
    JPanel panel = new JPanel(new GridLayout(8, 2));
    this.setBorder(BorderFactory.createTitledBorder("Buy/Sell Stock"));
    tickerField = new JTextField(10);
    quantityField = new JTextField(10);
    JPanel DatePanel = new JPanel(new FlowLayout());
    yearField = new JTextField(4);
    monthField = new JTextField(2);
    dayField = new JTextField(2);
    DatePanel.add(yearField);
    DatePanel.add(monthField);
    DatePanel.add(dayField);
    buyStockButton = new JButton("Buy Stock");

    sellStockButton = new JButton("Sell Stock");

    panel.add(new JLabel("Buy Stock Ticker:"));
    panel.add(tickerField);
    panel.add(new JLabel("Quantity:"));
    panel.add(quantityField);
    panel.add(new JLabel("Date (YYYY-MM-DD):"));
    panel.add(DatePanel);
    JPanel buttons = new JPanel(new FlowLayout());
    buttons.add(buyStockButton);
    buttons.add(sellStockButton);
    panel.add(buttons);

    alphaVantageRadioButton = new JRadioButton("Alpha Vantage");
    yahooFinanceRadioButton = new JRadioButton("Yahoo Finance");

    dataSourceButtonGroup = new ButtonGroup();
    dataSourceButtonGroup.add(alphaVantageRadioButton);
    dataSourceButtonGroup.add(yahooFinanceRadioButton);

    panel.add(new JLabel("Choose Data Source:"));
    JPanel radioPanel = new JPanel(new FlowLayout());
    radioPanel.add(alphaVantageRadioButton);
    radioPanel.add(yahooFinanceRadioButton);
    panel.add(radioPanel);

    dataSourceFileField = new JTextField(20);
    dataSourceFileField.setVisible(false);  // Hide initially
    panel.add(new JLabel("File Name:"));
    panel.add(dataSourceFileField);

    alphaVantageRadioButton.addItemListener(new RadioButtonListener());
    yahooFinanceRadioButton.addItemListener(new RadioButtonListener());

    this.add(panel, BorderLayout.CENTER);
  }

  private class RadioButtonListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
      if (alphaVantageRadioButton.isSelected() || yahooFinanceRadioButton.isSelected()) {
        dataSourceFileField.setVisible(false);
      } else {
        dataSourceFileField.setVisible(true);
      }
      BuySellStocksPanel.this.revalidate();
      BuySellStocksPanel.this.repaint();
    }
  }

  void addActionListener(ActionListener listener) {
    buyStockButton.addActionListener(listener);
    sellStockButton.addActionListener(listener);
  }
}
