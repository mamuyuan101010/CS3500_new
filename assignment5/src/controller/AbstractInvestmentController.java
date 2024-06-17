package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.IModel;
import view.IView;

/**
 * This is an abstract class representing common features between multiple implementations
 * of the Calculator interface.
 */
abstract class AbstractInvestmentController implements IController {
  protected final IModel model;
  protected final IView view;
  protected final Readable in;
  protected final Map<String, Function<Scanner, StocksCommands>> knownCommands;

  /**
   * Create a controller to work with the specified investment (model)
   * and view (to take and display inputs).
   *
   * @param in the readable input being passed in
   * @param model the portfolio manager to work with (the model)
   * @param view  the text view object for inputs and outputs (the view)
   */
  public AbstractInvestmentController(Readable in, IModel model, IView view) {
    this.model = model;
    this.view = view;
    this.in = in;
    this.knownCommands = new HashMap<>();
    knownCommands.put("1", HandleGainOrLoss::new);
    knownCommands.put("2", HandleMovingAvg::new);
    knownCommands.put("3", HandleCrossovers::new);
    knownCommands.put("4", HandleCreatePortfolio::new);
  }

  @Override
  public void start() throws IllegalStateException, IOException {
    view.welcome();
    Scanner scan = new Scanner(in);
    view.showMenu();
    while (scan.hasNext()) {
      String in = scan.nextLine().toUpperCase();
      startHelp(view, scan, in);
    }
  }

  //the command design pattern bases for finding a command specified in the original
  //implementation
  protected void originalCommands(String in, Scanner scan) throws IOException {
    StocksCommands c;
    Function<Scanner, StocksCommands> cmd =
            knownCommands.getOrDefault(in, null);
    if (cmd == null) {
      view.showCustomMessage("Invalid choice. Please enter a valid choice.\n");
    } else {
      c = cmd.apply(scan);
      c.execute(model, view);
      view.showMenu();
    }
  }

  //implementation of specific logic for each controller
  protected abstract void startHelp(IView view, Scanner scan, String in)
          throws IllegalStateException, IOException;
}
