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

    IModelV2 model = new BetterPortfolioManager();
    GUIView view = new GraphicsView();
    GUIController controller = new GUIController(model);
    controller.setView(view);

  }
}