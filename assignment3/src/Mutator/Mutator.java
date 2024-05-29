package Mutator;
import Exception.NegativeOperandException;
import Exception.InvalidCharacterException;
import Exception.OverFlowException;
/**
 * Mutator class is an abstract class that implements interface IMutator
 * and other kinds of specific Mutator classes need to extend this Mutator
 */
public abstract class Mutator implements IMutator {
  // two fields that represent the string that will return later,
  // and the calculation array that store all values calculators need.
  protected String stringResult;
  /**
   * resultInInteger only store the calculated result in integer and prepare
   * for giving currentResult variable value
   * operand1 represents the first operand of the calculation
   * operand2 represents the second operand of the calculation
   * status integer can only be 4 integers,
   * 0, meaning we are now in operand1,
   * 1, meaning we are now in operator,
   * 2, meaning we are in operand2,
   * and 3, meaning we are in result.
   * operator integer can only be 4 integers,
   * 0, meaning no operator assigned.
   * 1, meaning operator + will be used.
   * 2, meaning operator - will be used.
   * and finally 3, meaning operator * will be used.
   */
  // calculation store currentResult(0 position), operand1(1 position),
  // operand2(2 position), status(3 position), operator(4 position) in order
  // in an integer array
  protected int[] calculation;

  public Mutator(String stringResult, int[] calculation) {
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
      throw new NegativeOperandException();
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
      if (isNumber(c) || isOperator(c) || c == '=') {
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
      // in case if c is neither number nor operator
      else {
        throw new InvalidCharacterException(c);
      }
    }
  }


  // handleResult method that is decided based on which type of mutator it belongs to
  // a smart calculator will be able to accept multiple = sign by calculating with
  // the second operand multiple times.
  protected abstract void handleResult(char c);

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
    long returnNum = 0;
    returnNum = (oldDigit * 10) + newDigit;
    if (returnNum > Integer.MAX_VALUE){
      throw new OverFlowException();
    }
    return returnNum;
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
  protected abstract void handleOperand1(char c);

  // this method handle the second operand
  protected abstract void handleOperand2(char c);

  // this method handle the operator
  protected void handleOperator(char c) {
    switch (c) {
      case '+':
        this.calculation[4] = 1;
        this.stringResult += "+";
        break;
      case '-':
        this.calculation[4] = 2;
        this.stringResult += "-";
        break;
      case '*':
        this.calculation[4] = 3;
        this.stringResult += "*";
        break;
      default:
        throw new InvalidCharacterException(c);
    }
    this.calculation[3] = 2;
  }
}
