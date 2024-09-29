package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.Features;

public class GraphicsView2 implements GUIView {
    private JFrame frame;
    private JPanel mainPanel;
    private PortfolioPanel createPortfolioPanel;
    private JLabel createPortfolioLabel;
    private LoadSaveFilePanel loadSavePanel;
    private JLabel loadSaveLabel;
    private BuySellStocksPanel buySellStockPanel;
    private JLabel buySellStockLabel;
    private QueryPortfolioPanel queryPortfolioPanel;
    private JLabel queryPortfolioLabel;


    public GraphicsView2() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Investment Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        mainPanel = new JPanel(new GridLayout(1, 3));

        // create four panels
        createPortfolioPanel = new PortfolioPanel();
        loadSavePanel = new LoadSaveFilePanel();
        buySellStockPanel = new BuySellStocksPanel();
        queryPortfolioPanel = new QueryPortfolioPanel();

        // create four labels for displaying the results of the four panels
        createPortfolioLabel = new JLabel();

        loadSaveLabel = new JLabel();
        buySellStockLabel = new JLabel();
        queryPortfolioLabel = new JLabel();

        createPortfolioPanel.add(new JPanel(new FlowLayout()).add(createPortfolioLabel));
        loadSavePanel.add(new JPanel(new FlowLayout()).add(loadSaveLabel));
        buySellStockPanel.add(new JPanel(new FlowLayout()).add(buySellStockLabel));
        queryPortfolioPanel.add(new JPanel(new FlowLayout()).add(queryPortfolioLabel));

        JPanel column1 = new JPanel(new GridLayout(2, 1));
        column1.add(createPortfolioPanel);
        column1.add(loadSavePanel);
        mainPanel.add(column1);
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
    public void setButtonListener(ActionListener listener) {
    }


    @Override
    public void addFeatures(Features features) {
        this.createPortfolioPanel.addFeatures(features);
        this.loadSavePanel.addFeatures(features);
        this.queryPortfolioPanel.addFeatures(features);
        this.buySellStockPanel.addFeatures(features);
    }

    @Override
    public void showMessage(String message, Panels panels) {
        switch (panels) {
            case Panels.BuySellPanel:
                this.buySellStockLabel.setText(message);
                break;
            case Panels.QueryPortfolioPanel:
                this.queryPortfolioLabel.setText(message);
                break;
            case Panels.LoadSavePanel:
                this.loadSaveLabel.setText(message);
                break;
            case Panels.PortfolioPanel:
                this.createPortfolioLabel.setText(message);
                break;
            default:
                throw new IllegalStateException("Uknown panel is handled."
                        + panels.toString());
        }
    }


    @Override
    public void showCustomMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
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