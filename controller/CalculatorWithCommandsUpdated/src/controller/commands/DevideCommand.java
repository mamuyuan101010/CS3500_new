package controller.commands;

import java.io.OutputStream;
import java.util.Objects;
import java.util.Scanner;

import controller.commands.CalculatorCommandUtil;
import model.ICalculator;

/**
 * Created by vidojemihajlovikj on 5/21/24.
 */
public class DevideCommand implements ICalculatorCommand {
  private final OutputStream out;

  public DevideCommand(OutputStream out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void run(Scanner scanner, ICalculator model) {
    int num1 = CalculatorCommandUtil.getNextValidInt(scanner);
    int num2 = CalculatorCommandUtil.getNextValidInt(scanner);
    int result = model.devide(num1, num2);
    CalculatorCommandUtil.writeToOut(result + "",out);
  }
}
