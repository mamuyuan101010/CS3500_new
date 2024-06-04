import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import Model.Investment;

public class StocksController {
  private Readable in;
  private Appendable out;
  private Investment investment;
  public StocksController(Readable in, Appendable out, Investment investment) {
    this.in = Objects.requireNonNull(in);
    this.out = Objects.requireNonNull(out);
    this.investment = Objects.requireNonNull(investment);
  }

  public void control() throws IllegalStateException {
    Scanner sc = new Scanner(in);
    boolean quit = false;

    //print the welcome message
    this.welcomeMessage();
    while (! quit) {
      writeMessage("Type instruction: "); //prompt for the instruction name
      String userInstruction = sc.next(); //take an instruction name
      if (userInstruction.equals("quit") || userInstruction.equals("q")) {
        quit = true;
      }
      else {
        processCommand(userInstruction, sc, investment);
      }
    }
  }

  // processCommand
  private void processCommand(String userInstruction, Scanner sc, Investment investment) {
  }

  protected void writeMessage(String message) throws IllegalStateException {
    try {
      out.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void welcomeMessage() {
    writeMessage("Welcome to the spreadsheet program!" + System.lineSeparator());
  }

}
