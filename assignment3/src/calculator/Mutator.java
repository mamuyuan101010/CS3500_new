package calculator;

/**
 * Mutator class is an abstract class that implements interface IMutator
 * and other kinds of specific Mutator classes need to extend this Mutator
 */
public abstract class Mutator implements IMutator {
  // two fields that represent the string that will return later,
  // and the calculation array that store all values calculators need.
  protected String stringResult;
  protected int[] calculation;

  public Mutator(String stringResult, int[] calculation) throws Exception {
    // condition that status is not in 0 - 3 range
    if (calculation[3] > 3 || calculation[3] < 0) {
      throw new IllegalArgumentException("illegal status number input");
    }
    // condition that operator is not in 0 to 3 range
    else if (calculation[4] > 3 || calculation[4] < 0) {
      throw new IllegalArgumentException("illegal operator number input");
    }
    // condition that string contains characters other than digits or operator
    else if (!StringChecker.onlyContainDigitOrOpr(stringResult)) {
      throw new IllegalArgumentException("illegal currentResult string input");
    }
    // condition that operand2 is negative, since operand2 cannot be negative at all
    // -1 will be the only exception as it is the default value to waiting for
    // assigning other values
    else if (calculation[2] < -1) {
      throw new IllegalArgumentException("negative operand2 input");
    }
    this.stringResult = stringResult;
    this.calculation = calculation.clone();
  }

  /**
   * <ul>
   *   <li>
   *     if c is neither a 0-9 digit nor a operator or equal sign, it will auto return an error
   *   </li>
   *   <li>
   *     any error instance declared in SimpleCalculator Class's java comment will also throw an
   *     exception here. eg: operand1/2 over 32 bits, input equals sign before inputting
   *     the second operand, or before inputting any operator
   *   </li>
   *   <li>
   *     inputting C will cause all values in fields back to their default values, details
   *     of each field's default value is in SimpleCalculator class's java comment
   *   </li>
   *   <li>
   *     the inputting sequence of c is the same as input method of SimpleCalculator class,
   *     the only difference is input method here is a void method, means it does not return
   *     any value, but change its own field instead
   *   </li>
   * </ul>
   * input method is a void method that takes a character c and mutate the current fields
   * based on the input.
   *
   * @param c is a character intake to proceed the program
   */
  @Override
  public void input(char c) {
    if (c == 'C') {
      backIntoDefault();
    } else {
      switch (this.calculation[3]) {
        case 0:
          handleOperand1(c);
          break;
        case 1:
          handleOperator(c);
          break;
        case 2:
          handleOperand2(c);
          break;
        case 3:
          handleResult(c);
          break;
        default:
          throw new IllegalStateException("status should not be other than 0,1,2,3");
      }
    }
  }

  // handleOperator method that is decided based on which type of mutator it belongs to
  protected abstract void handleOperator(char c);

  // handleOperand method that is decided based on which type of mutator it belongs to
  protected abstract void handleOperand(Character c, int operand, boolean isFirstOperand);

  // this method handle result stage by calculate out the result
  // based on the previous given operand1, operand2, and operator
  protected void handleResult(char c) {
    // first calculate the result and store result in the resultInInteger variable
    // condition that c is =, +, -, or *
    if (c == '=' || isOperator(c) || isNumber(c)) {
      switch (this.calculation[4]) {
        // case that no operator is assigned properly
        case 0:
          throw new IllegalStateException("operator is not assigned");
          // case that previous operator is plus
        case 1:
          this.calculation[0] = plus(this.calculation[1], this.calculation[2]);
          break;
        // case that previous operator is minus
        case 2:
          this.calculation[0] = minus(this.calculation[1], this.calculation[2]);
          break;
        // case that previous operator is multiply
        case 3:
          this.calculation[0] = multiply(this.calculation[1], this.calculation[2]);
          break;
        // if any value other than 0, 1, 2, 3, should throw an illegal status exception
        default:
          throw new IllegalStateException("illegal operator status is assigned, "
                  +
                  "given " + String.valueOf(this.calculation[4]) + " is not a valid status");
      }
      // now assign the calculated value in String
      // to the currentResult variable.
      this.stringResult = String.valueOf(this.calculation[0]);
      // continue calculating.
      // condition that c is +, - or *
      if (isOperator(c)) {
        // now in order to continue calculation,
        // we need to return to status 1, operator status
        this.calculation[3] = 1;
        // make the previous result be the first operand
        this.calculation[1] = this.calculation[0];
        // make the operand2 become -1, which is default value waiting for input
        this.calculation[2] = -1;
        // now recall input again to enter operator stage
        input(c);
      }
      // condition that c is a number
      else if (isNumber(c)) {
        input('C');
        input(c);
      }
      // this is a condition that is not expected to reach,
      // should throw an illegal state exception if happen
      /*
      we do not need to handle c is = case, as result will auto computed, and
      as long as status variable is unchanged, it will not change the currentResult
      data, what it will happen is it will recalculate the resultInInteger value and
      reassign the
      same value again.
       */
    }
    // condition that c is not =, +, -, *, or digit.
    else {
      throw new IllegalArgumentException("should not input any character "
              +
              "other than +, -, *, =, or digits, you put: " + c);
    }
  }

  // plus method takes two integers a and b, and
  // return the sum of two integer inputs
  protected int plus(int a, int b) {
    if (((long) a + b) > Integer.MAX_VALUE) {
      return 0;
    }
    return (a + b);
  }

  // minus method takes two integers a and b, and
  // return the difference between the
  // two integer inputs
  protected int minus(int a, int b) {
    if (((long) a - b) < (long) Integer.MIN_VALUE) {
      return 0;
    }
    return (a - b);
  }

  // multiply method takes two integers a and b, and
  // return the product of the
  // two integer inputs
  protected int multiply(int a, int b) {
    if (((long) a * b) > Integer.MAX_VALUE
            || ((long) a * b) < Integer.MIN_VALUE) {
      return 0;
    }
    return (a * b);
  }

  // this method check whether the given character c is an integer from 0 to 9
  protected boolean isNumber(char c) {
    return Character.isDigit(c);
  }

  // this method check whether the given character c is an operator
  protected boolean isOperator(char c) {
    return (c == '+' || c == '-' || c == '*');
  }

  // this method will make all number back into its default value,
  // just like no inputs are given
  protected void backIntoDefault() {
    this.stringResult = "";
    this.calculation = new int[]{0, -1, -1, 0, 0};
  }

  // take the old digit and new digit, make the old digit advance for 1 digit
  protected long digitAdv(long oldDigit, int newDigit) {
    return (oldDigit * 10) + newDigit;
  }

  // this method will take a character c, an integer operand, and a boolean isFirstOperand
  // and will auto assign value for operand1 or 2 and update the currentResult variable
  // at the same time for preparing getResult method.
  protected void assignOperand(Character c, int operand, boolean isFirstOperand) {
    this.stringResult += c;
    if (isFirstOperand) {
      this.calculation[1] = operand;
    } else {
      this.calculation[2] = operand;
    }
  }

  // this method handle the first operand
  protected void handleOperand1(char c) {
    handleOperand(c, this.calculation[1], true);
  }

  // this method handle the second operand
  protected void handleOperand2(char c) {
    handleOperand(c, this.calculation[2], false);
  }
}
