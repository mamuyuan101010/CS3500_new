package controller.commands;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Created by vidojemihajlovikj on 5/21/24.
 */
public class CalculatorCommandUtil {
  public static ReadState getNextInt(Scanner scanner){
    if (scanner.hasNextInt()){
      return new ReadState(InputResult.GOOD, scanner.nextInt() );
    }
    String badInput = scanner.next();//throw away the giberish.
    //I will not throw the exception...

    return new ReadState(InputResult.FAIL,Integer.MIN_VALUE);
  }
  //+ 3 sdfsdf sdfsad sdf
  public static int getNextValidInt(Scanner scanner){
    while ( scanner.hasNext()){
      ReadState readState = getNextInt(scanner);
      if ( readState.getInputResult() == InputResult.GOOD ){
        return readState.getResult();
      }
    }
    throw new IllegalStateException("Run out of input, was expecting an integer.");
  }

  public static void writeToOut(String message, OutputStream out){
    try {
      out.write(message.getBytes());
    }catch(IOException e){
      throw new IllegalStateException("Writing failed.");
    }
  }
}
