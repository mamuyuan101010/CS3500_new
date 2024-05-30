package exception;

/**
 * OverFlowException class extends IllegalArgumentException class.
 */
public class OverFlowException extends IllegalArgumentException {
  /**
   * OverFlowException constructor that construct an illegal argument Exception
   * with the warning message says your integer is over 32 bits.
   */
  public OverFlowException() {
    super("your input integer is over 32 bits");
  }
}
