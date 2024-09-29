import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import model.ModelImpl;
import view.MyView;

/**
 * Created by vidojemihajlovikj on 6/10/24.
 */
public class Main {
  public static void main(String [] args){
    IModel model = new ModelImpl();
    MyView view = new MyView();
    IController controller = new ControllerImpl(model, view);

   controller.go();
  }
}
