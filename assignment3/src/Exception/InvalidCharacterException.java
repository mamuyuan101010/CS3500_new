package exception;

/**
 * InvalidCharacterException class is for throw an exception caused by illegal character.
 */
public class InvalidCharacterException extends IllegalArgumentException {
  /**
   * InvalidCharacterException constructor construct an illegal argument exception
   * with the invalid character warning message.
   */
  public InvalidCharacterException(char c) {
    super("cannot handle invalid character:" + c);
  }
}
