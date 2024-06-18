import java.io.IOException;
import java.io.InputStreamReader;

import controller.BetterInvestmentController;
//import controller.InvestmentController;
import controller.GraphicController;
import model.BetterPortfolioManager;
import model.IModel;
//import model.MockBetterPM;
//import model.PortfolioManager;
import view.FrameView;
import view.IGraphicView;
import view.IView;
//import view.TextView;
import controller.IController;
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
  public static void main(String[] args) throws IOException {
    IModel model = new BetterPortfolioManager();
    IGraphicView view = new FrameView();
    IController controller = new GraphicController(model, view);
    controller.start();
    try {
      controller.start();
    } catch (IOException e) {
      System.out.println("An error occurred while running the application: " + e.getMessage());
    }
  }
}