package calculator;

/**
 * Mutator class is the helper class for taking the value in each fields in SimpleCalculator class
 * , taking inputs of character c for mutating its own fields, and re-declaring
 * the value in each field in SimpleCalculator class.
 * <ul>
 *   <li>
 *     Mutator class takes 6 parameters of operand1/2, operator, status, result, and stringResult
 *     values and assign those in its own fields.
 *   </li>
 *   <li>
 *     the constructor is public because SimpleCalculator class need to call Mutator class
 *   </li>
 *   <li>
 *     Mutator's fields are the same as SimpleCalculator's fields,and each variable in that field
 *     has the same rules as the one in SimpleCalculator's variables.
 *   </li>
 *   <li>
 *     eg: operand1/2 cannot over 32 bits, operator and status can only be 0-3(see SimpleCalculator
 *     's class comments for more detail on what 0, 1, 2, 3 means), result is the most
 *     recent calculated result, and cannot over 32 bits as well, stringResult is the result that
 *     can be print out in String type.
 *   </li>
 * </ul>
 */
public class Mutator {
  private String stringResult;
  private int[] calculation;

  /**
   * Mutator constructor that takes an array value of result, operand1,
   * operand2, status, operator and a stringResult value.
   * @param calculation is the array of int that include all info for calculation
   * @param stringResult is the result in string
   * @throws IllegalArgumentException throw exception when input an invalid number
   */
  public Mutator(String stringResult, int[] calculation) throws IllegalArgumentException {
    // condition that status is not in 0 - 3 range
    if (calculation[3] > 3 || calculation[3] < 0) {
      throw new IllegalArgumentException("illegal status number input");
    }
    // condition that operator is not in 0 to 3 range
    else if (calculation[4] > 3 || calculation[4] < 0) {
      throw new IllegalArgumentException("illegal operator number input");
    }
    // condition that string contains characters other than digits or operator
    else if (! StringChecker.onlyContainDigitOrOpr(stringResult)) {
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
   * @param c is a character intake to proceed the program
   */
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

  /**
   * buildCalculator method return a Calculator object based on
   * the mutator's current field values.
   *
   * @return a Calculator object based on the mutator's current field values
   */
  public Calculator buildCalculator() {
    return new SimpleCalculator(this.stringResult, this.calculation);
  }

  // this method handle the first operand
  private void handleOperand1(char c) {
    handleOperand(c, this.calculation[1], true);
  }

  // this method handle the second operand
  private void handleOperand2(char c) {
    handleOperand(c, this.calculation[2], false);
  }

  // this method will take a character c, an integer operand, and a boolean isFirstOperand
  // and will auto assign value for operand1 or 2 and update the currentResult variable
  // at the same time for preparing getResult method.
  private void assignOperand(Character c, int operand, boolean isFirstOperand) {
    this.stringResult += c;
    if (isFirstOperand) {
      this.calculation[1] = operand;
    } else {
      this.calculation[2] = operand;
    }
  }

  // handle operand method takes three arguments, a character c which
  // given from input, the operand which is either operand1 or operand2,
  // finally the isFirstOperand which is the determinant of operand1,
  // if the given operand is operand1, then isFirstOperand is true,
  // else it's false.
  private void handleOperand(Character c, int operand, boolean isFirstOperand) {
    // case that operand1 is -1, meaning it's the first pos of the first operand
    if (operand == -1) {
      // case that the input c is a number
      if (isNumber(c)) {
        operand = Character.getNumericValue(c);
        assignOperand(c, operand, isFirstOperand);
      }
      // condition that input c is a -, meaning the user wants to input a negative
      // value, which should not be allowed here.
      else if (c == '-') {
        throw new IllegalArgumentException("cannot handle negative numbers");
      }
      // case that the input c is neither a number nor a negative operator
      else {
        int numO;
        if (isFirstOperand) {
          numO = 1;
        } else {
          numO = 2;
        }
        throw new IllegalArgumentException("cannot handle character "
                + c
                + " other than number "
                + "in the first position of the operand " + numO);
      }
    }
    // case that operand is not -1, meaning it's not the first pos of the first operand
    else {
      // case that c is still a 0 to 9 number
      if (isNumber(c)) {
        long advValue = digitAdv(operand, Character.getNumericValue(c));
        if (advValue <= Integer.MAX_VALUE) {
          operand = (int) advValue;
        } else {
          throw new IllegalArgumentException("cannot work with whole numbers longer than 32 bits");
        }
        assignOperand(c, operand, isFirstOperand);
      }
      // case that c is not a number, we need to handle this c to handleOperator method
      else {
        if (isFirstOperand) {
          this.calculation[3] = 1;
        } else {
          this.calculation[3] = 3;
        }
        input(c);
      }
    }
  }

  // this method handle the operator
  private void handleOperator(char c) {
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
        throw new IllegalArgumentException("should not type any character other "
                +
                "than number or operator here");
    }
    this.calculation[3] = 2;
  }


  // take the old digit and new digit, make the old digit advance for 1 digit
  private long digitAdv(long oldDigit, int newDigit) {
    return (oldDigit * 10) + newDigit;
  }

  // this method handle result stage by calculate out the result
  // based on the previous given operand1, operand2, and operator
  private void handleResult(char c) {
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
  private int plus(int a, int b) {
    if (((long) a + b) > Integer.MAX_VALUE) {
      return 0;
    }
    return (a + b);
  }

  // minus method takes two integers a and b, and
  // return the difference between the
  // two integer inputs
  private int minus(int a, int b) {
    if (((long) a - b) < (long) Integer.MIN_VALUE) {
      return 0;
    }
    return (a - b);
  }

  // multiply method takes two integers a and b, and
  // return the product of the
  // two integer inputs
  private int multiply(int a, int b) {
    if (((long) a * b) > Integer.MAX_VALUE
            || ((long) a * b) < Integer.MIN_VALUE) {
      return 0;
    }
    return (a * b);
  }

  // this method check whether the given character c is an integer from 0 to 9
  private boolean isNumber(char c) {
    return Character.isDigit(c);
  }

  // this method check whether the given character c is an operator
  private boolean isOperator(char c) {
    return (c == '+' || c == '-' || c == '*');
  }

  // this method will make all number back into its default value,
  // just like no inputs are given
  private void backIntoDefault() {
    this.stringResult = "";
    this.calculation = new int[]{0, -1, -1, 0, 0};
  }


}
