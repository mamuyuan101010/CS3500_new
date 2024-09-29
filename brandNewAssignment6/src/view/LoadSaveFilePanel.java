package view;

import controller.Features;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoadSaveFilePanel extends JPanel {
    private JButton loadButton;
    private JButton saveButton;
    private JTextField fileName;
    private JTextField portfolioName;
    LoadSaveFilePanel() {
        super(new FlowLayout());
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        fileName = new JTextField(20);
        portfolioName = new JTextField(20);
        this.setLayout(new GridLayout(6, 1));
        this.setBorder(BorderFactory.createTitledBorder("Load/Save Files"));
        JPanel fileNamePanel = new JPanel(new FlowLayout());
        fileNamePanel.add(new JLabel("Enter File Name Here: "));
        fileNamePanel.add(fileName);
        JPanel portfolioNamePanel = new JPanel(new FlowLayout());
        portfolioNamePanel.add(new JLabel("Enter Portfolio Name Here: "));
        portfolioNamePanel.add(portfolioName);
        this.add(fileNamePanel);
        this.add(portfolioNamePanel);
        JPanel loadSave = new JPanel(new FlowLayout());
        loadSave.add(loadButton);
        loadSave.add(saveButton);
        this.add(loadSave);
    }

    void addFeatures(Features features) {
        saveButton.addActionListener(evt -> {
            try {
                features.savePortfolio(portfolioName.getText(), fileName.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        loadButton.addActionListener(evt -> {
            try {
                features.loadPortfolio(fileName.getText(), portfolioName.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
