package Mutator;

/**
 * StringChecker is a function abstract class that only serves to test whether the given string
 * has any characters other than digits or operators.
 */
public class StringChecker {
  /**
   * method to find out whether the given string contain any character that
   * is neither a digit nor an operator.
   * @param s is the string given
   * @return the true or false value whether the given string
   *     contain any character that
   *     is neither a digit nor an operator
   */
  public static boolean onlyContainDigitOrOpr(String s) {
    char[] charArray = s.toCharArray();
    boolean returnValue = true;
    for (Character c : charArray) {
      returnValue = returnValue && isValueOrOpr(c);
    }
    return returnValue;
  }

  // helper method to find out whether the given character belongs to
  // a value or an operator
  private static boolean isValueOrOpr(Character c) {
    return Character.isDigit(c) || c == '+' || c == '-'
            || c == '*';
  }
}
