package Exception;

public class InvalidCharacterException extends IllegalArgumentException{
  public InvalidCharacterException(char c) {
    super("cannot handle invalid character:" + c);
  }
}
