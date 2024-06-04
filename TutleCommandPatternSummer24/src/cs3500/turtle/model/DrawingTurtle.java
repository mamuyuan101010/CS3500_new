package cs3500.turtle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vidojemihajlovikj on 5/22/24.
 */
public class DrawingTurtle extends SimpleTurtle implements IMoveTurtle {
  private final List<Position2D> points;

  public DrawingTurtle() {
    this.points = new ArrayList<>();
  }

  @Override
  public void move(double d){
    this.points.add(super.getPosition());
    super.move(d);
    this.points.add(super.getPosition());
  }

  public List<Position2D> getPoints(){
    return new ArrayList<>(this.points);
  }
}
