package Controller;

import java.io.IOException;
import java.util.Scanner;

import Model.Investment;

/**
 * This class represents the controller of an interactive investment's application.
 * This controller offers a simple text interface in which the user can
 * type instructions to retrieve and examine data regarding investments.
 * This controller works with any Readable to read its inputs
 */
public class InvestmentController {
  private Readable readable;
  private Investment investment;
  private Appendable appendable;
  /**
   * Create a controller to work with the specified investment (model),
   * readable (to take inputs)
   * @param investment the investment to work with (the model)
   * @param readable the Readable object for inputs
   */
  public InvestmentController(Investment investment, Readable readable, Appendable appendable) {
    if ((investment == null) || (readable == null) || (appendable == null)) {
      throw new IllegalArgumentException("Investment or readable is null");
    }
    this.investment = investment;
    this.readable = readable;
    this.appendable = appendable;
  }

  /**
   * The main method that relinquishes control of the application to the controller.
   * @throws IllegalStateException if the controller is unable to transmit output
   */
  public void control() throws IllegalStateException {
    Scanner sc = new Scanner(readable);
    boolean quit = false;
    
    //print the welcome message
    this.welcomeMessage();

    while (!quit && sc.hasNext()) { //continue until the user quits
      writeMessage("Type instruction: "); //prompt for the instruction name
      String userInstruction = sc.next(); //take an instruction name
      if (userInstruction.equals("quit") || userInstruction.equals("q")) {
        quit = true;
      }
      else {
        this.processCommand(userInstruction, sc, investment);
      }
    }

  }

  protected void processCommand(String userInstruction, Scanner sc, Investment investment) {
    int row;
    int col;
    double value;

    switch (userInstruction) {
      case "assign-value": //assign a value to a cell
        try {
          row = getRowNum(sc.next()); //get in the row string
          col = sc.nextInt(); //get in the column number, starting with 1
          value = sc.nextDouble();
          System.out.println("Setting cell (" + row + "," + (col - 1));
          investment.set(row, col - 1, value); //use the spreadinvestment
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "print-value": //print a value from the cell
        try {
          row = getRowNum(sc.next()); //get the row string
          col = sc.nextInt(); //get the column number, starting with 1
          writeMessage("Value: " + investment.get(row, col - 1) + System.lineSeparator());
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "menu": //print the menu of supported instructions
        welcomeMessage();
        break;
      default: //error due to unrecognized instruction
        writeMessage("Undefined instruction: " + userInstruction + System.lineSeparator());
    }
  }

  //converts a row string into a row number starting with 0
  protected int getRowNum(String rowLetters) throws IllegalArgumentException {
    int rownumber = 0;

    for (int i = 0; i < rowLetters.length(); i = i + 1) {
      char c = rowLetters.charAt(i);
      if (!Character.isAlphabetic(c)) {
        throw new IllegalArgumentException("Invalid row");
      }
      rownumber = 26 * rownumber + ((int) Character.toLowerCase(c) - 'a' + 1);
    }
    return rownumber - 1;
  }

}

