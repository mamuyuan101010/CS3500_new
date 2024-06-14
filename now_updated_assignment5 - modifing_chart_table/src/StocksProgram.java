import java.io.IOException;
import java.io.InputStreamReader;

import controller.BetterInvestmentController;
import controller.InvestmentController;
import model.BetterPortfolioManager;
import model.IModel;
import model.PortfolioManager;
import view.IView;
import view.TextView;
import controller.IController;
import view.TextViewV2;

/**
 * The driver of this application/program.
 */
public class StocksProgram {
  /**
   * main method of the program.
   * @param args any command line arguments
   */
  //OLD IMPLEMENTATION WHICH STILL WORKS / BACKWARDS COMPATIBLE
//  public static void main(String[] args) {
//    IModel model = new PortfolioManager();
//    IView view = new TextView(System.out);
//    IController controller = new InvestmentController(
//            new InputStreamReader(System.in), model, view);
//
//    try {
//      controller.start();
//    } catch (IOException e) {
//      System.out.println("An error occurred while running the application: " + e.getMessage());
//    }
//  }
  public static void main(String[] args) {
    IModel model = new BetterPortfolioManager();
    IView view = new TextViewV2(System.out);
    IController controller = new BetterInvestmentController(
            new InputStreamReader(System.in), model, view);

    try {
      controller.start();
    } catch (IOException e) {
      System.out.println("An error occurred while running the application: " + e.getMessage());
    }
  }
}