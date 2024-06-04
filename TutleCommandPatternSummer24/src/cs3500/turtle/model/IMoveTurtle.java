package cs3500.turtle.model;

import java.util.List;

import cs3500.turtle.model.Position2D;
import cs3500.turtle.model.TurtleModel;

/**
 * Created by vidojemihajlovikj on 5/22/24.
 */
public interface IMoveTurtle extends TurtleModel {
  List<Position2D> getPoints();
}
