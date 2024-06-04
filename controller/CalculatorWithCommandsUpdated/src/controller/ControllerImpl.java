package controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Supplier;

import controller.commands.ICalculatorCommand;
import controller.commands.MultiplyCommand;
import controller.commands.PlusCommand;
import controller.commands.SubtractCommand;
import model.ICalculator;
import controller.commands.CalculatorCommandUtil;
/**
 * Created by vidojemihajlovikj on 5/20/24.
 */

public class ControllerImpl implements IController {
  protected final InputStream in;
  protected final OutputStream out;
  protected final Map<String, Supplier<ICalculatorCommand>> availableCommands;
  protected final Stack<ICalculatorCommand> undoStack;


  public ControllerImpl(InputStream inputStream, OutputStream printStream) {
    this.in = Objects.requireNonNull(inputStream);
    this.out = Objects.requireNonNull(printStream);

    this.availableCommands = new HashMap<>();
    this.undoStack = new Stack<>();

    this.availableCommands.put("+", () -> new PlusCommand(this.out)  );
    this.availableCommands.put("-",  () -> new SubtractCommand(this.out)  );
    this.availableCommands.put("*",   () -> new MultiplyCommand(this.out) );
    this.availableCommands.put("/",  () -> new MultiplyCommand(this.out) );


  }

  @Override
  public void go(ICalculator model) {
    Scanner scanner = new Scanner(this.in);

    while ( scanner.hasNext() ){
      String operator = scanner.next();
      if ( operator.equalsIgnoreCase("q")){
        CalculatorCommandUtil.writeToOut("Exiting.", this.out);
        return;
      }

      Supplier<ICalculatorCommand> commandGenerator = this.availableCommands.getOrDefault(operator, null);

      if ( commandGenerator != null ){
        ICalculatorCommand commandToRun = commandGenerator.get();
          commandToRun.run(scanner, model);
          this.undoStack.push(commandToRun);
      }
      else{
        CalculatorCommandUtil.writeToOut("Unkown command.", this.out);
      }
    }
  }

}
