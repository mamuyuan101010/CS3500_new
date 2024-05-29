package Exception;

/**
 * CalculatorException class is the class that only for handle exceptions.
 */
public class negativeResultException extends IllegalArgumentException{
  public negativeResultException(String s) {
    super(s);
  }
}
