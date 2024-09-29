package view;

import controller.Features;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
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
    super(new GridLayout(10,1));
    this.setBorder(BorderFactory.createTitledBorder("Query Portfolio"));

    queryValuePortfolioNameField = new JTextField(10);
    queryValueYearField = new JTextField(4);
    queryValueMonthField = new JTextField(2);
    queryValueDayField = new JTextField(2);
    queryValueButton = new JButton("Query Value");


    queryCompositionButton = new JButton("Query Composition");
    JPanel portfolioNameInputPanel = new JPanel(new FlowLayout());
    portfolioNameInputPanel.add(new JLabel("Portfolio Name for Value:"));
    portfolioNameInputPanel.add(queryValuePortfolioNameField);
    this.add(portfolioNameInputPanel);
    JPanel dateInputPanel = new JPanel(new FlowLayout());
    dateInputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
    dateInputPanel.add(queryValueYearField);
    dateInputPanel.add(new JLabel("-"));
    dateInputPanel.add(queryValueMonthField);
    dateInputPanel.add(new JLabel("-"));
    dateInputPanel.add(queryValueDayField);
    this.add(dateInputPanel);
    JPanel queryButtonPanel = new JPanel(new FlowLayout());
    queryButtonPanel.add(queryValueButton);
    queryButtonPanel.add(queryCompositionButton);
    this.add(queryButtonPanel);
  }


  public void addFeatures(Features features) {
    queryValueButton.addActionListener(evt -> {
      try {
        int year = Integer.parseInt(queryValueYearField.getText());
        int month = Integer.parseInt(queryValueMonthField.getText());
        int day = Integer.parseInt(queryValueDayField.getText());
        LocalDate date = LocalDate.of(year, month, day);
        features.getValue(
                date,
                queryValuePortfolioNameField.getText()
        );
      } catch (Exception e) {
          try {
              features.showMessage(e.getMessage(), Panels.QueryPortfolioPanel);
          } catch (IOException ex) {
              throw new RuntimeException(ex);
          }
      }
    });

    queryCompositionButton.addActionListener(evt -> {
      try {
        int year = Integer.parseInt(queryValueYearField.getText());
        int month = Integer.parseInt(queryValueMonthField.getText());
        int day = Integer.parseInt(queryValueDayField.getText());
        LocalDate date = LocalDate.of(year, month, day);
        features.getComposition(
                date,
                queryValuePortfolioNameField.getText()
        );
      } catch (Exception e) {
          try {
              features.showMessage(e.getMessage(), Panels.QueryPortfolioPanel);
          } catch (IOException ex) {
              throw new RuntimeException(ex);
          }
      }
    });
  }
}
