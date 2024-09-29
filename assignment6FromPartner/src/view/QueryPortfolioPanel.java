package view;

import controller.Features;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

class QueryPortfolioPanel extends JPanel {

  private JButton queryValueButton;
  private JTextField queryValuePortfolioNameField;
  private JTextField queryValueYearField;
  private JTextField queryValueMonthField;
  private JTextField queryValueDayField;

  private JButton queryCompositionButton;
  List<Features> featureListeners = new ArrayList<>();
  QueryPortfolioPanel() {
    this.setBorder(BorderFactory.createTitledBorder("Query Portfolio"));

    JPanel panel = new JPanel(new GridLayout(6, 2));

    queryValuePortfolioNameField = new JTextField(10);
    queryValueYearField = new JTextField(4);
    queryValueMonthField = new JTextField(2);
    queryValueDayField = new JTextField(2);
    queryValueButton = new JButton("Query Value");


    queryCompositionButton = new JButton("Query Composition");
    JPanel portfolioNameInputPanel = new JPanel(new FlowLayout());
    portfolioNameInputPanel.add(new JLabel("Portfolio Name for Value:"));
    portfolioNameInputPanel.add(queryValuePortfolioNameField);
    panel.add(portfolioNameInputPanel);
    JPanel dateInputPanel = new JPanel(new FlowLayout());
    dateInputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
    dateInputPanel.add(queryValueYearField);
    dateInputPanel.add(new JLabel("-"));
    dateInputPanel.add(queryValueMonthField);
    dateInputPanel.add(new JLabel("-"));
    dateInputPanel.add(queryValueDayField);
    panel.add(dateInputPanel);
    JPanel queryButtonPanel = new JPanel(new FlowLayout());
    queryButtonPanel.add(queryValueButton);
    queryButtonPanel.add(queryCompositionButton);
    panel.add(queryButtonPanel);

    this.add(panel, BorderLayout.CENTER);
  }


  void addFeatures(Features f) { this.featureListeners.add(f); }
}
