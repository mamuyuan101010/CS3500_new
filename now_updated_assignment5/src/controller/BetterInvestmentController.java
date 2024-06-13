package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.IModel;
import model.IModelV2;
import view.IView;

/**
 * This class represents the controller of an interactive text-based investment's application.
 */
public class BetterInvestmentController extends InvestmentController {
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
  public BetterInvestmentController(Readable in, IModel model, IView view) {
    super(in, model, view);
    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public void start() throws IllegalStateException, IOException {
    view.welcome();
    Scanner scan = new Scanner(in);

    Map<String, Function<Scanner, StocksCommandsV2>> knownCommands2 = new HashMap<>();
    knownCommands2.put("5", HandleAddDateInvestment::new);
    knownCommands2.put("6", HandleSellInvestment::new);
    knownCommands2.put("8", HandleReverse::new);
    knownCommands2.put("C", HandleComposition::new);
    knownCommands2.put("D", HandleDistribution::new);
    knownCommands2.put("S", HandleSaveFile::new);
    knownCommands2.put("L", HandleLoadFile::new);
    knownCommands2.put("7", HandleRebalance::new);

    Map<String, Function<Scanner, StocksCommands>> knownCommands = new HashMap<>();
    knownCommands.put("1", HandleGainOrLoss::new);
    knownCommands.put("2", HandleMovingAvg::new);
    knownCommands.put("3", HandleCrossovers::new);
    knownCommands.put("4", HandleCreatePortfolio::new);
    knownCommands.put("9", HandleGetPortfolioValue::new);

    view.showMenu();
    while (scan.hasNext()) {
      StocksCommandsV2 c2;
      StocksCommands c1;
      String in = scan.nextLine().toUpperCase();
      if (in.equals("Q")) {
        view.goodbye();
        return;
      }
      Function<Scanner, StocksCommandsV2> cmd =
              knownCommands2.getOrDefault(in, null);
      if (cmd != null) {
        c2 = cmd.apply(scan);
        c2.execute((IModelV2) model, view);
        view.showMenu();
      } else {
        Function<Scanner, StocksCommands> cmd1 =
                knownCommands.getOrDefault(in, null);
        if (cmd1 == null) {
          view.showCustomMessage("Invalid choice. Please enter a valid choice.\n");
        } else {
          c1 = cmd1.apply(scan);
          c1.execute(model, view);
          view.showMenu();
        }
      }
    }
  }
}

