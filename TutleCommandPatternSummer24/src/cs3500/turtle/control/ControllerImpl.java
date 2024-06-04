package cs3500.turtle.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

import cs3500.turtle.control.commands.ITurtleCommand;
import cs3500.turtle.control.commands.MoveTurtleCommand;
import cs3500.turtle.control.commands.RetrieveTurtleCommand;
import cs3500.turtle.control.commands.SaveTurtleCommand;
import cs3500.turtle.control.commands.ShowTurtleCommand;
import cs3500.turtle.control.commands.TurnTurtleCommand;
import cs3500.turtle.model.TurtleModel;

/**
 * Created by vidojemihajlovikj on 5/22/24.
 */
public class ControllerImpl implements IController {
  protected final Appendable out;
  protected final Readable in;
  protected final Map<String, Supplier<ITurtleCommand>> commands;

  public ControllerImpl(Appendable out, Readable in){
    this.out = Objects.requireNonNull(out);
    this.in = Objects.requireNonNull(in);

    this.commands = new HashMap<>();

    this.commands.put("move", () -> new MoveTurtleCommand(this.out));
    this.commands.put("turn", () -> new TurnTurtleCommand(this.out));
    this.commands.put("save", () -> new SaveTurtleCommand(this.out));
    this.commands.put("retrieve", () -> new RetrieveTurtleCommand(this.out));
    this.commands.put("show", () -> new ShowTurtleCommand(this.out));

  }

  @Override
  public void go(TurtleModel m) {
    Scanner s = new Scanner(System.in);

    while (s.hasNext()) {
      String in = s.next();
      if ( in.equalsIgnoreCase("quit")){
        return;
      }

      Supplier<ITurtleCommand> commandSupplier = this.commands.getOrDefault(in, null);
      if ( commandSupplier != null ){
        ITurtleCommand command = new MoveTurtleCommand(this.out);
        command.run(s,m);
      }
    }


  }
}
