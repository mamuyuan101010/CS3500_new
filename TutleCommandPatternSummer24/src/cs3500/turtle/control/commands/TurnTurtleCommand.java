package cs3500.turtle.control.commands;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import cs3500.turtle.model.TurtleModel;

/**
 * Created by vidojemihajlovikj on 5/22/24.
 */
public class TurnTurtleCommand implements ITurtleCommand {
  private final Appendable out;

  public TurnTurtleCommand(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void run(Scanner s, TurtleModel m) {
    try {
      double d = s.nextDouble();
      m.turn(d);
    } catch (InputMismatchException ime) {
      ControllerUtil.append(this.out, ("Bad turn angle to turn"));
    }
  }
}
