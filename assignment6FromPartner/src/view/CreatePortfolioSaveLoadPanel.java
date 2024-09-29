package view;

import controller.Features;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

class CreatePortfolioSaveLoadPanel extends JPanel {
  private JButton createPortfolioButton;
  private JTextField portfolioNameField;
  // load and save file
  private JButton loadButton;
  private JButton saveButton;
  private JTextField fileName;
  List<Features> featureListeners = new ArrayList<>();

  CreatePortfolioSaveLoadPanel() {
    this.setLayout(new GridLayout(2, 1));
    JPanel inputPanel = new JPanel(new FlowLayout());
    inputPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
    portfolioNameField = new JTextField(20);
    createPortfolioButton = new JButton("Create Portfolio");
    inputPanel.add(new JLabel("Portfolio Name:"));
    inputPanel.add(portfolioNameField);
    inputPanel.add(createPortfolioButton);
    this.add(inputPanel);

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
    this.add(loadSavePanel);
  }

  void addFeatures(Features f) { this.featureListeners.add(f); }
}
