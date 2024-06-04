import java.util.Scanner;

import controller.ControllerImpl;
import controller.IController;
import model.CalculatorImpl;
import model.ICalculator;

/**
 * Created by vidojemihajlovikj on 5/20/24.
 */
public class Main {
  public static void main(String [] args){

    IController controller = new ControllerImpl(System.in, System.out);
    controller.go(new CalculatorImpl());
  }
}
