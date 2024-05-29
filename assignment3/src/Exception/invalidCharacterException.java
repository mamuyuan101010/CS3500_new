package Exception;

public class invalidCharacterException extends IllegalArgumentException{
  public invalidCharacterException(char c) {
    super("cannot handle invalid character" + c);
  }
}
