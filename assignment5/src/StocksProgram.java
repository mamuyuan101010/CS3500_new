import java.io.IOException;
import java.io.InputStreamReader;

import controller.InvestmentController;
import model.IModel;
import model.PortfolioManager;
import view.IView;
import view.TextView;
import controller.IController;

/**
 * The driver of this application/program.
 */
public class StocksProgram {
  /**
   * main method of the program.
   * @param args any command line arguments
   */
  public static void main(String[] args) {
    IModel model = new PortfolioManager();
    IView view = new TextView(System.out);
    IController controller = new InvestmentController(
            new InputStreamReader(System.in), model, view);

    try {
      controller.start();
    } catch (IOException e) {
      System.out.println("An error occurred while running the application: " + e.getMessage());
    }
  }
}