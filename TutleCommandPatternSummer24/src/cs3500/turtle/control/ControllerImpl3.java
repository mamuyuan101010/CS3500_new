package cs3500.turtle.control;

import cs3500.turtle.control.commands.DrawSquareCommand;

/**
 * Created by vidojemihajlovikj on 5/22/24.
 */
public class ControllerImpl3 extends ControllerImpl {
  public ControllerImpl3(Appendable out, Readable in) {
    super(out, in);

    this.commands.put("drawSquare", () -> new DrawSquareCommand(this.out));
  }
}
