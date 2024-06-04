package cs3500.turtle.control;

import java.io.InputStreamReader;

import cs3500.turtle.model.SimpleTurtle;
import cs3500.turtle.model.TurtleModel;

/**
 * Created by blerner on 10/10/16.
 */
public class SimpleController {
  public static void main(String[] args) {
    TurtleModel turtleModel = new SimpleTurtle();
    IController controller = new ControllerImpl(System.out, new InputStreamReader(System.in));
    controller.go(turtleModel);
  }
}
