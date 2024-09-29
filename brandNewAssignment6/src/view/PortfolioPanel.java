package view;

import controller.Features;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

class PortfolioPanel extends JPanel {
  private JButton createPortfolioButton;
  private JTextField portfolioNameField;


  PortfolioPanel() {
    super(new FlowLayout());
    this.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
    portfolioNameField = new JTextField(20);
    createPortfolioButton = new JButton("Create Portfolio");
    this.add(new JLabel("Portfolio Name:"));
    this.add(portfolioNameField);
    this.add(createPortfolioButton);
  }

  public void addFeatures(Features features) {
    createPortfolioButton.addActionListener(evt -> {
      try {
        features.createPortfolio(portfolioNameField.getText());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
