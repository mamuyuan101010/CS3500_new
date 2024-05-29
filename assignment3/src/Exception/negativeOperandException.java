package Exception;

/**
 * CalculatorException class is the class that only for handle exceptions.
 */
public class negativeOperandException extends IllegalArgumentException{
  public negativeOperandException(String s) {
    super("cannot handle negative operand");
  }
}
