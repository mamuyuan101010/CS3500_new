package controller;

import java.io.IOException;

import model.IModel;
import view.IView;

/**
 * This interface represents the possible commands the controller can execute
 * Reinforces the command design pattern.
 */
public interface StocksCommands {

  /**
   * Every command that implements this interface can execute their respective action.
   * @param model the model to be passed in and commands to be executed on
   * @param v the view to be passed in so each command can transmit specific messages to display
   */
  void execute(IModel model, IView v) throws IOException;
}
