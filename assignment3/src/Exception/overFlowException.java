package Exception;

public class overFlowException extends IllegalArgumentException{
  public overFlowException() {
    super("your input integer is over 32 bits");
  }
}
