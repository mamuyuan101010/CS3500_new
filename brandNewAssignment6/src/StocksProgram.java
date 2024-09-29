import java.io.IOException;
import java.io.InputStreamReader;

import controller.BetterInvestmentController;
import controller.IController;
import controller.InvestmentController;
import controller.GUIController;
import model.BetterPortfolioManager;
import model.IModel;
import model.MockBetterPM;
import model.PortfolioManager;
import model.IModelV2;
import view.GUIView;
import view.GraphicsView;
import view.GraphicsView2;
import view.IView;
import view.TextView;
import view.TextViewV2;


/**
 * The driver of this application/program.
 */
public class StocksProgram {

  /**
   * main method of the program.
   *
   * @param args any command line arguments
   */
  public static void main(String[] args) {

    if (args.length == 0) {
      IModelV2 model = new BetterPortfolioManager();
      GUIView view = new GraphicsView2();
      GUIController controller = new GUIController(model);
      controller.setView(view);
    } else if (args.length == 1 && args[0].equals("-text")) {
      IModel model = new BetterPortfolioManager();
      IView view = new TextViewV2(System.out);
      IController controller = new BetterInvestmentController(
              new InputStreamReader(System.in), model, view);

      try {
        controller.start();
      } catch (IOException e) {
        System.out.println("An error occurred while running the application: " + e.getMessage());
      }
    } else {
      System.err.println("Invalid command-line arguments. Use one of the following:");
      System.err.println("java -jar Program.jar -text : Opens the text-based interface.");
      System.err.println("java -jar Program.jar : Opens the graphical user interface.");
    }

  }
}