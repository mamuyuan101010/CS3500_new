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


    List<Features> featureListeners = new ArrayList<>();
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
    private JTextField quantityField;

    private Action buyStockAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            for (Features f : featureListeners) {
                try {
                    f.addStock(LocalDate.of(Integer.parseInt(yearField.getText()),
                                    Integer.parseInt(monthField.getText()),
                                    Integer.parseInt(dayField.getText())),
                            portfolioNameField.getText(), tickerField.getText(), sourceGenerator(),
                            Integer.parseInt(quantityField.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    };

    BuySellStocksPanel() {
        // set up panels, labels, and text fields.
        super(new FlowLayout());
        JPanel panel = new JPanel(new GridLayout(9, 2));
        this.setBorder(BorderFactory.createTitledBorder("Buy/Sell Stock"));
        tickerField = new JTextField(10);
        quantityField = new JTextField(10);

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
        panel.add(stockTickerPanel);

        // quantity field
        JPanel quantityPanel = new JPanel(new FlowLayout());
        quantityPanel.add(new JLabel("Quantity:"));
        quantityPanel.add(quantityField);
        panel.add(datePanel);
        panel.add(portfolioName);
        panel.add(quantityPanel);
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(buyStockButton);
        buttons.add(sellStockButton);
        panel.add(buttons);

        // api and local file option field
        JRadioButton aPIButton = new JRadioButton("API");
        localFileButton = new JRadioButton("Local File");

        ButtonGroup dataSourceButtonGroup = new ButtonGroup();
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
        buyStockButton.addActionListener(buyStockAction);
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

    // Includes this new feature listener in responding to keys
    void addFeatures(Features f) { this.featureListeners.add(f); }
}
