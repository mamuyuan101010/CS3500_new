package cs3500.turtle.control.commands;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import cs3500.turtle.model.Position2D;
import cs3500.turtle.model.TurtleModel;

/**
 * Created by vidojemihajlovikj on 5/22/24.
 */
public class DrawMoveTurtleCommand implements ITurtleCommand {
  private final Appendable out;
  private final List<Position2D> points;
  public DrawMoveTurtleCommand(Appendable out, List<Position2D> points) {
    this.out = Objects.requireNonNull(out);
    this.points = Objects.requireNonNull(points);
  }

  @Override
  public void run(Scanner s, TurtleModel m) {
    try {
      double d = s.nextDouble();
      points.add(m.getPosition());
      m.move(d);
      points.add(m.getPosition());

    } catch (InputMismatchException ime) {
      ControllerUtil.append(this.out, ("Bad turn distance to move"));
    }
  }
}
