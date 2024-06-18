package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

import model.IModel;
import view.IGraphicView;
import view.IView;
import view.IViewListener;

public class GraphicController implements IController, IViewListener {

  private final IModel model;
  private final IGraphicView view;

  private final ActionListener buttonListener;
  public GraphicController(IModel model, IGraphicView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.addViewListener(this);
    this.buttonListener = new ButtonActionListener();
  }

  private class ButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "Create Portfolio Button":
          // Handle "Echo Button" action
          break;
        case "Exit Button":
          // Handle "Exit Button" action
          break;
        // Add cases for other buttons/actions
        default:
          // Handle unknown action or invalid input
          break;
      }
    }
  }


  @Override
  public void start() throws IllegalStateException, IOException {
    view.setVisible(true);
  }

  @Override
  public void handleAddStock(String name, Double quantity) {

  }

  @Override
  public void handleSellStock(String name, Double quantity) {

  }
}
