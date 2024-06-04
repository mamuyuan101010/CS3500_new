package cs3500.turtle.control.commands;

import java.util.Scanner;

import cs3500.turtle.model.TurtleModel;

/**
 * Created by vidojemihajlovikj on 5/22/24.
 */
public interface ITurtleCommand {
  void run(Scanner s, TurtleModel m);
}
