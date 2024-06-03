import org.junit.Test;
import calculator.StringChecker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * class StringCheckerTest is the test class for abstract class StringChecker.
 */
public class StringCheckerTest {

  // this test check whether the given String contains only digits or operator
  @Test
  public void onlyContainDigitOrOpr() {
    assertTrue(StringChecker.onlyContainDigitOrOpr("+-*1234"));
    assertFalse(StringChecker.onlyContainDigitOrOpr("not_digit_or_operator"));
    assertFalse(StringChecker.onlyContainDigitOrOpr("+-*1234/"));
    assertFalse(StringChecker.onlyContainDigitOrOpr("+-*1234="));
  }
}