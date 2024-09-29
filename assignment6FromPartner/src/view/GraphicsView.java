package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.Features;

public class GraphicsView implements GUIView {
  private JFrame frame;
  private JPanel mainPanel;
  private CreatePortfolioSaveLoadPanel createPortSaveLoadfolioPanel;
  private BuySellStocksPanel buySellStockPanel;
  private QueryPortfolioPanel queryPortfolioPanel;


  public GraphicsView() {
    initialize();
  }

  private void initialize() {
    frame = new JFrame("Investment Application");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);

    mainPanel = new JPanel(new GridLayout(1, 3));

    createPortSaveLoadfolioPanel = new CreatePortfolioSaveLoadPanel();

    buySellStockPanel = new BuySellStocksPanel();

    queryPortfolioPanel = new QueryPortfolioPanel();

    mainPanel.add(createPortSaveLoadfolioPanel);
    mainPanel.add(buySellStockPanel);
    mainPanel.add(queryPortfolioPanel);

    frame.add(mainPanel);
    frame.pack();
    frame.setVisible(true);

  }

  @Override
  public void show() {
    frame.setVisible(true);
  }


  @Override
  public void addFeatures(Features features) {
    this.buySellStockPanel.addFeatures(features);
    this.createPortSaveLoadfolioPanel.addFeatures(features);
    this.queryPortfolioPanel.addFeatures(features);
  }


  @Override
  public void showCustomMessage(String message) {
    System.out.println(message);
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
}