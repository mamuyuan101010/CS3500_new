package cs3500.turtle.control.commands;

import java.util.Objects;
import java.util.Scanner;

import cs3500.turtle.model.Position2D;
import cs3500.turtle.model.TurtleModel;

/**
 * Created by vidojemihajlovikj on 5/22/24.
 */
public class ShowTurtleCommand implements ITurtleCommand {
  private final Appendable out;

  public ShowTurtleCommand(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void run(Scanner s, TurtleModel m) {
    Position2D position2D = m.getPosition();
    ControllerUtil.append(this.out, position2D.toString());
  }
}
