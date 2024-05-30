package exception;

/**
 * CalculatorException class is the class that only for handle exceptions.
 */
public class NegativeOperandException extends IllegalArgumentException {
  /**
   * NegativeOperandException constructor construct an illegal Argument Exception
   * with warning message
   * says that it cannot handle negative operand.
   */
  public NegativeOperandException() {
    super("cannot handle negative operand");
  }
}
