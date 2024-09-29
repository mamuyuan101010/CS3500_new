package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.IModel;
import view.IView;

/**
 * This class represents the controller of an interactive text-based investment's application.
 */
public class InvestmentController implements IController {
  private final IModel model;
  private final IView view;
  private final Readable in;

  /**
   * Create a controller to work with the specified investment (model)
   * and view (to take and display inputs).
   *
   * @param model the portfolio manager to work with (the model)
   * @param view  the text view object for inputs and outputs (the view)
   */
  public InvestmentController(Readable in, IModel model, IView view) {
    if ((model == null) || (view == null)) {
      throw new IllegalArgumentException("model or view is null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public void start() throws IllegalStateException, IOException {
    view.welcome();
    Scanner scan = new Scanner(in);

    Map<String, Function<Scanner, StocksCommands>> knownCommands = new HashMap<>();
    knownCommands.put("1", HandleGainOrLoss::new);
    knownCommands.put("2", HandleMovingAvg::new);
    knownCommands.put("3", HandleCrossovers::new);
    knownCommands.put("4", HandleCreatePortfolio::new);
    knownCommands.put("5", HandleAddInvestmentToPortfolio::new);
    knownCommands.put("6", HandleGetPortfolioValue::new);

    view.showMenu();
    while (scan.hasNext()) {
      StocksCommands c;
      String in = scan.nextLine();
      if (in.equals("7")) {
        view.goodbye();
        return;
      }
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
  }
}

