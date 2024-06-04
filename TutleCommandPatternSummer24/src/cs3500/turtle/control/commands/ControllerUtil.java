package cs3500.turtle.control.commands;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by vidojemihajlovikj on 5/22/24.
 */
public class ControllerUtil {
  static void append(Appendable appendable, String message){
    Objects.requireNonNull(appendable);
    Objects.requireNonNull(message);

    try{
      appendable.append(message);
    }catch(IOException e){
      throw new IllegalStateException("Couldn't append to appendable.");
    }
  }
}
