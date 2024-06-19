package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.Border;

public class FrameView extends JFrame implements IGraphicView, ActionListener, KeyListener, MouseListener {
  private final JButton createPortfolio;
  private final JLabel portfolioTitle;
  private final JTextArea nameOfPortfolio;
  private final JTextArea stockName;
  private final JButton addStock;
  private final JButton sellStock;
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private  final JTextArea enterQuantity;
  private final List<IViewListener> myListeners;
  private final Canvas myCanvas;
  private final JLabel dateLabel;
  private final JButton optionSelect;
  private final JButton enter;

  public FrameView() {
    super();
    setTitle("Stock Simulator");
    Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);

    // Main panel with horizontal layout
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane, BorderLayout.CENTER);

    // First column: Portfolio section
    JPanel column1 = new JPanel();
    column1.setLayout(new BoxLayout(column1, BoxLayout.Y_AXIS)); // Vertical layout
    column1.setPreferredSize(new Dimension(300, 300));
    portfolioTitle = new JLabel("New Portfolio");
    createPortfolio = new JButton("Create Portfolio");
    nameOfPortfolio = new JTextArea("Portfolio Name", 1, 2);
    column1.add(portfolioTitle);
    column1.add(nameOfPortfolio);
    column1.add(createPortfolio);
    column1.setBorder(blackBorder);

    // Second column: Stock section
    JPanel column2 = new JPanel();
    column2.setLayout(new BoxLayout(column2, BoxLayout.Y_AXIS)); // Vertical layout
    column2.setPreferredSize(new Dimension(300, 300));
    stockName = new JTextArea("Enter the name of stock", 1, 2);
    enterQuantity = new JTextArea("Enter quantity", 1, 2);
    addStock = new JButton("Add Stock");
    sellStock = new JButton("Sell Stock");
    column2.add(stockName);
    column2.add(enterQuantity);
    column2.add(addStock);
    column2.add(sellStock);
    column2.setBorder(blackBorder);

    // Third column: Value and composition section
    JPanel column3 = new JPanel();
    column3.setLayout(new FlowLayout());
    column3.setPreferredSize(new Dimension(300, 300));
    dateLabel = new JLabel("Select Date:");
    column3.add(dateLabel, BorderLayout.NORTH);
    optionSelect = new JButton("date");
    enter = new JButton("enter");
    JPanel blankPanel = new JPanel();
    blankPanel.setLayout(new FlowLayout());
    blankPanel.add(optionSelect);
    blankPanel.add(enter);
    column3.add(blankPanel, BorderLayout.SOUTH);
    column3.setBorder(blackBorder);

    // Fourth column: Save and load panel
    JPanel column4 = new JPanel();
    column4.setLayout(new BoxLayout(column4, BoxLayout.Y_AXIS)); // Vertical layout
    column4.setPreferredSize(new Dimension(300,300));
    JTextArea fileName = new JTextArea("file name");
    JButton save = new JButton("save");
    JButton load = new JButton("load");
    column4.add(fileName);
    column4.add(save);
    column4.add(load);
    column4.setBorder(blackBorder);

    // Add columns to main panel
    mainPanel.add(column1);
    mainPanel.add(column2);
    mainPanel.add(column3);
    mainPanel.add(column4);

    // Canvas for visualization (assuming you want this at the bottom)
    myCanvas = new Canvas();
    add(myCanvas, BorderLayout.SOUTH);

    // Set frame properties
    setSize(1200, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    myListeners = new ArrayList<>();
  }


  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void addViewListener(IViewListener viewListener) {
    this.myListeners.add(Objects.requireNonNull(viewListener));
  }
}
