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
//    IModel model = new BetterPortfolioManager();
//    IView view = new TextViewV2(System.out);
//    IController controller = new BetterInvestmentController(
//            new InputStreamReader(System.in), model, view);
//
//    try {
//      controller.start();
//    } catch (IOException e) {
//      System.out.println("An error occurred while running the application: " + e.getMessage());
//    }

    IModelV2 model = new BetterPortfolioManager();
    GUIView view = new GraphicsView();
    GUIController controller = new GUIController(model);
    //controller.start();
    controller.setView(view);

  }
}