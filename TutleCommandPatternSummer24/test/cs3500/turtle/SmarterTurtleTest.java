package cs3500.turtle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.turtle.model.Position2D;
import cs3500.turtle.model.SimpleTurtle;
import cs3500.turtle.model.TurtleModel;

import static org.junit.Assert.*;

/**
 * Created by ashesh on 5/23/2016.
 */
public final class SmarterTurtleTest {

  @Test
  public void testInit() {
    TurtleModel t = new SimpleTurtle();

    assertEquals(0.0, t.getPosition().getX(), 0.001);
    assertEquals(0.0, t.getPosition().getY(), 0.001);
  }

  @Test
  public void testMove() {
    TurtleModel t = new SimpleTurtle();

    t.move(10);
    assertEquals(10.0, t.getPosition().getX(), 0.001);
    assertEquals(0.0, t.getPosition().getY(), 0.001);
  }

  @Test
  public void testTurnOnly() {
    TurtleModel t = new SimpleTurtle();

    t.turn(10);
    assertEquals(0.0, t.getPosition().getX(), 0.001);
    assertEquals(0.0, t.getPosition().getY(), 0.001);


    t.turn(-100);
    assertEquals(0.0, t.getPosition().getX(), 0.001);
    assertEquals(0.0, t.getPosition().getY(), 0.001);
  }

  @Test
  public void testTurnAndMove() {
    TurtleModel t = new SimpleTurtle();

    t.turn(30);
    t.move(10);
    assertEquals(10.0 * Math.cos(Math.toRadians(30)), t.getPosition().getX(), 0.001);
    assertEquals(10.0 * Math.sin(Math.toRadians(30)), t.getPosition().getY(), 0.001);
  }



  @Test
  public void testPush() {
    TurtleModel t = new SimpleTurtle();

    t.move(10);
    Position2D initPosition = t.getPosition();
    t.save();
    t.turn(90);
    t.move(10);
    t.retrieve();
    Position2D finalPosition = t.getPosition();
    assertEquals(initPosition.getX(), finalPosition.getX(), 0.01);
    assertEquals(finalPosition.getY(), finalPosition.getY(), 0.01);
  }

}