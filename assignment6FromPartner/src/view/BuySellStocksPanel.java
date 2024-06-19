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
  private JRadioButton aPIButton;
  private JRadioButton localFileButton;
  private ButtonGroup dataSourceButtonGroup;
  private JTextField dataSourceFileField;

  BuySellStocksPanel() {
    super(new FlowLayout());
    JPanel panel = new JPanel(new GridLayout(8, 2));
    this.setBorder(BorderFactory.createTitledBorder("Buy/Sell Stock"));
    tickerField = new JTextField(10);
    quantityField = new JTextField(10);
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
    buyStockButton = new JButton("Buy Stock");

    sellStockButton = new JButton("Sell Stock");

    JPanel buyStockTickerPanel = new JPanel(new FlowLayout());
    buyStockTickerPanel.add(new JLabel("Buy Stock Ticker:"));
    buyStockTickerPanel.add(tickerField);
    panel.add(buyStockTickerPanel);
    JPanel quantityPanel = new JPanel(new FlowLayout());
    quantityPanel.add(new JLabel("Quantity:"));
    quantityPanel.add(quantityField);
    panel.add(datePanel);
    JPanel buttons = new JPanel(new FlowLayout());
    buttons.add(buyStockButton);
    buttons.add(sellStockButton);
    panel.add(buttons);

    aPIButton = new JRadioButton("API");
    localFileButton = new JRadioButton("Local File");

    dataSourceButtonGroup = new ButtonGroup();
    dataSourceButtonGroup.add(aPIButton);
    dataSourceButtonGroup.add(localFileButton);

    panel.add(new JLabel("Choose Data Source:"));
    JPanel radioPanel = new JPanel(new FlowLayout());
    radioPanel.add(aPIButton);
    radioPanel.add(localFileButton);
    panel.add(radioPanel);

    dataSourceFileField = new JTextField(20);
    dataSourceFileField.setVisible(false);  // Hide initially
    panel.add(new JLabel("Local File Name:"));
    panel.add(dataSourceFileField);

    aPIButton.addItemListener(new RadioButtonListener());
    localFileButton.addItemListener(new RadioButtonListener());

    this.add(panel, BorderLayout.CENTER);
  }

  private class RadioButtonListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
      if (localFileButton.isSelected()) {
        dataSourceFileField.setVisible(true);
      }
      else {
        dataSourceFileField.setVisible(false);
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
