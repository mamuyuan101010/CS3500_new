package calculator;

public class Mutator{
int operand1;
int operand2;
int operator;
int status;
int result;
String stringResult;

public Mutator(int operand1, int operand2, int operator,
               int status, int result, String stringResult) {
  this.operand1 = operand1;
  this.operand2 = operand2;
  this.operator = operator;
  this.status = status;
  this.result = result;
  this.stringResult = stringResult;
}

  public void input(char c) {
    if (c == 'C') {
      backIntoDefault();
    } else {
      switch (this.status) {
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

  // this method handle the first operand
  private void handleOperand1(char c) {
    handleOperand(c, this.operand1, true);
  }

  // this method handle the second operand
  private void handleOperand2(char c) {
    handleOperand(c, this.operand2, false);
  }

  // this method will take a character c, an integer operand, and a boolean isFirstOperand
  // and will auto assign value for operand1 or 2 and update the currentResult variable
  // at the same time for preparing getResult method.
  private void assignOperand(Character c, int operand, boolean isFirstOperand) {
    this.stringResult += c;
    if (isFirstOperand) {
      this.operand1 = operand;
    } else {
      this.operand2 = operand;
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
        throw new IllegalArgumentException("cannot handle character " + c
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
          operand = (int)advValue;
        }
        else {
          throw new IllegalArgumentException("cannot work with whole numbers longer than 32 bits");
        }
        assignOperand(c, operand, isFirstOperand);
      }
      // case that c is not a number, we need to handle this c to handleOperator method
      else {
        if (isFirstOperand) {
          this.status = 1;
        } else {
          this.status = 3;
        }
        input(c);
      }
    }
  }

  // this method handle the operator
  private void handleOperator(char c) {
    switch (c) {
      case '+':
        this.operator = 1;
        this.stringResult += "+";
        break;
      case '-':
        this.operator = 2;
        this.stringResult += "-";
        break;
      case '*':
        this.operator = 3;
        this.stringResult += "*";
        break;
      default:
        throw new IllegalArgumentException("should not type any character other " +
                "than number or operator here");
    }
    this.status = 2;
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
      switch (this.operator) {
        // case that no operator is assigned properly
        case 0:
          throw new IllegalStateException("operator is not assigned");
          // case that previous operator is plus
        case 1:
          this.result = plus(this.operand1, this.operand2);
          break;
        // case that previous operator is minus
        case 2:
          this.result = minus(this.operand1, this.operand2);
          break;
        // case that previous operator is multiply
        case 3:
          this.result = multiply(this.operand1, this.operand2);
          break;
        // if any value other than 0, 1, 2, 3, should throw an illegal status exception
        default:
          throw new IllegalStateException("illegal operator status is assigned, " +
                  "given " + String.valueOf(this.operator) + " is not a valid status");
      }
      // now assign the calculated value in String
      // to the currentResult variable.
      this.stringResult = String.valueOf(this.result);
      // continue calculating.
      // condition that c is +, - or *
      if (isOperator(c)) {
        // now in order to continue calculation,
        // we need to return to status 1, operator status
        this.status = 1;
        // make the previous result be the first operand
        this.operand1 = this.result;
        // make the operand2 become -1, which is default value waiting for input
        this.operand2 = -1;
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
      throw new IllegalArgumentException("should not input any character " +
              "other than +, -, *, =, or digits, you put: " + c);
    }
  }

  // plus method takes two integers a and b, and
  // return the sum of two integer inputs
  private int plus(int a, int b) {
    if (((long)a + b) > Integer.MAX_VALUE) {
      return 0;
    }
    return (a + b);
  }

  // minus method takes two integers a and b, and
  // return the difference between the
  // two integer inputs
  private int minus(int a, int b) {
    if ( ((long)a - b ) < (long)Integer.MIN_VALUE) {
      return 0;
    }
    return (a - b);
  }

  // multiply method takes two integers a and b, and
  // return the product of the
  // two integer inputs
  private int multiply(int a, int b) {
    if (((long)a * b) > Integer.MAX_VALUE
            || ((long)a * b) < Integer.MIN_VALUE) {
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
    this.result = 0;
    this.operand1 = -1;
    this.operand2 = -1;
    this.status = 0;
    this.operator = 0;
  }

  /**
   * buildCalculator method return a Calculator object based on
   * the mutator's current field values.
   * @return a Calculator object based on the mutator's current field values
   */
  public Calculator buildCalculator() {
    return new SimpleCalculator(this.stringResult, this.result, this.operand1, this.operand2,
            this.status, this.operator);
  }
}
