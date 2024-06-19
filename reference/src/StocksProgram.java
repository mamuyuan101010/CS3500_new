import controller.GUIController;
import model.BetterPortfolioManager;
import model.IModelV2;
import view.GUIView;
import view.GraphicsView;

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