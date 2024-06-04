package controller.commands;

import java.util.Scanner;

import model.ICalculator;

/**
 * Created by vidojemihajlovikj on 5/21/24.
 */
public interface ICalculatorCommand {
  void run(Scanner scanner, ICalculator model);
}
