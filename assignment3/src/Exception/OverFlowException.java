package Exception;

public class OverFlowException extends IllegalArgumentException{
  public OverFlowException() {
    super("your input integer is over 32 bits");
  }
}
