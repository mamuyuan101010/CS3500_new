package Exception;

/**
 * CalculatorException class is the class that only for handle exceptions.
 */
public class NegativeOperandException extends IllegalArgumentException{
  public NegativeOperandException() {
    super("cannot handle negative operand");
  }
}
