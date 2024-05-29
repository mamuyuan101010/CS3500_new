package Exception;

/**
 * CalculatorException class is the class that only for handle exceptions.
 */
public class negativeOperandException extends IllegalArgumentException{
  public void negativeOperandException(String s) {
    super(s);
  }
}
