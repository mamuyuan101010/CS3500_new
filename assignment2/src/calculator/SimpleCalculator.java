package calculator;

/**
 * The SimpleCalculator implements Calculator interface
 * it takes straightforward inputs and work as a calculator
 * that handle with whole numbers shorter than 32 bits
 * it has the following characteristics:
 * <ul>
 *   <li>
 *     A correct basic sequence of inputs is the first operand, followed by the operator, followed
 *     by the second operand, followed by "=" (e.g. 3 2 + 2 4 = ). Note that each operand may have
 *     multiple digits.
 *   </li>
 *   <li>
 *     A valid sequence can contain a sequence of operands and operators (e.g. 3 2 + 2 4 - 1 0 = ,
 *     3 2 + 2 4 = - 10 = , etc.).
 *   </li>
 *   <li>
 *     The result at any point should show either what was entered thus far, or the result. For
 *     example, for the sequence of inputs 3 2 + 2 4 = the result after each input should be "3",
 *     "32", "32+", "32+2", "32+24", "56" in that order. For the sequence of inputs 3 2 + 2 4 - 1 0
 *     = the result after each input should be "3", "32", "32+", "32+2", "32+24", "56-", "56-1",
 *     "56-10", "46" in that order. Before entering any inputs, the result should be the blank
 *     string.
 *   </li>
 *   <li>
 *     The only valid operand characters are 0-9, and the only valid operators are +, - and *.
 *   </li>
 *   <li>
 *     The input 'C' will clear the calculator inputs. The result after clearing should be
 *     he blank string.
 *   </li>
 *   <li>
 *     The calculator does not "infer" any missing inputs. For example, although 32+= , +12+3 ,
 *     etc. is valid input on a normal calculator, this calculator will report that as an error.
 *   </li>
 *   <li>
 *     The calculator does allow inputting "=" multiple times. In this case it will return the
 *     same result. For example the result after 3 2 + 2 4 = and 3 2 + 2 4 = = = is the same: 56 .
 *     This is not what a normal calculator will do.
 *   </li>
 *   <li>
 *     The calculator does not allow inputting negative numbers, although it can handle negative
 *     results.
 *   </li>
 *   <li>
 *     If an operand overflows, it should throw an IllegalArgumentException and the operand's
 *     value before the input that caused it to overflow should be retained. For example,
 *     let us assume that an operand 1111111111 is valid (10 1s), but 11111111111 overflows
 *     (11 1s). When the eleventh 1 is entered as an input, the method should throw the exception,
 *     but the current operand should still be the 10 1s (so that the client may next enter an
 *     operator and use that operand correctly).
 *   </li>
 *   <li>
 *     It throws an IllegalArgumentException for all invalid inputs and sequences. There is one
 *     unique situation: If the operand does not overflow but the result of the arithmetic does,
 *     then the result reported should be 0 (but no exception will be thrown). For example,
 *     a + b - 1 0 = should result in -10 if a+b will overflow, but no exception is thrown when
 *     - was entered.
 *   </li>
 *   <li>
 *     The input method is not expected to change the calling object. This is part of the expected
 *     specification, and not up to the choice of the implementor!
 *   </li>
 * </ul>
 */
public class SimpleCalculator implements Calculator {
  // currentResult stores the result in string and prepare
  // for getResult method to return
  private String currentResult;
  // resultInInteger only store the calculated result in integer and prepare
  // for giving currentResult variable value
  private int resultInInteger;
  private int operand1;
  private int operand2;
  // status integer can only be 4 integers,
  // 0, meaning we are now in operand1,
  // 1, meaning we are now in operator,
  // 2, meaning we are in operand2,
  // and 3, meaning we are in result.
  private int status;
  // operator integer can only be 4 integers,
  // 0, meaning no operator assigned.
  // 1, meaning operator + will be used.
  // 2, meaning operator - will be used.
  // and finally 3, meaning operator * will be used.
  private int operator;

  public String testingLog;

  /**
   * constructor of SimpleCalculator
   * <ul>
   *   <li>
   *     current result should be an empty String
   *     as getResult should return empty string if
   *     nothing is given
   *   </li>
   *   <li>
   *     resultInInteger is zero because no operand 1 and 2 is given
   *   </li>
   *   <li>
   *     operand1 is -1, this is for enabling the handle methods know
   *     whether this operand is given a value yet, so that handle methods
   *     can determine whether an operator "-". "+", "*" is legal here or not
   *     for example, if first input is +, this is illegal, and if operator knows that
   *     operand1 is -1, so it can throw an exception
   *     if first input is a 0-9 number, and second input is +, this should be legal,
   *     so operand1 here is not -1 as it has been assigned value from the previous input,
   *     so handle method knows that + is legal here, and move to next stage:
   *     operator detection
   *   </li>
   *   <li>
   *     operand2 is -1 for the same reason of operand1 to determine whether the operand2
   *     has been assigned a value or not yet
   *   </li>
   *   <li>
   *     status is 0 in default, meaning that it's in the first stage of handling
   *     the first operand
   *   </li>
   *   <li>
   *     operator is 0, meaning no operator is assigned yet
   *   </li>
   * </ul>
   */
  public SimpleCalculator() {
    this.currentResult = "";
    this.resultInInteger = 0;
    this.operand1 = -1;
    this.operand2 = -1;
    this.status = 0;
    this.operator = 0;
    this.testingLog = "";
  }

  @Override
  public Calculator input(char c) {
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
    return this;
  }

  @Override
  public String getResult() {
    return this.currentResult;
  }

  // this method handle the first operand
  private void handleOperand1(char c) {
    handleOperand(c, this.operand1, true);
  }

  // this method handle the second operand
  private void handleOperand2(char c) {
    handleOperand(c, this.operand2, false);
  }

  private void assignOperand(Character c, int operand, boolean isFirstOperand) {
    this.currentResult += c;
    if (isFirstOperand) {
      assignValueForO1ForOperand1(operand);
    } else {
      assignValueForO2ForOperand2(operand);
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
        assignOperand(c,operand,isFirstOperand);
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
        operand = digitAdv(operand, Character.getNumericValue(c));
        assignOperand(c,operand,isFirstOperand);
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
        this.currentResult += "+";
        break;
      case '-':
        this.operator = 2;
        this.currentResult += "-";
        break;
      case '*':
        this.operator = 3;
        this.currentResult += "*";
        break;
      default:
        throw new IllegalArgumentException("should not type any character other " +
                "than number or operator here");
    }
    this.status = 2;
  }


  // take the old digit and new digit, make the old digit advance for 1 digit
  private int digitAdv(int oldDigit, int newDigit) {
    return (oldDigit * 10) + newDigit;
  }

  // this method handle result stage by calculate out the result
  // based on the previous given operand1, operand2, and operator
  private void handleResult(char c) {
    // first calculate the result and store result in the resultInInteger variable
    // condition that c is =, +, -, or *
    if (c == '=' || isOperator(c)) {
      switch (this.operator) {
        // case that no operator is assigned properly
        case 0:
          throw new IllegalStateException("operator is not assigned");
          // case that previous operator is plus
        case 1:
          this.resultInInteger = plus(this.operand1, this.operand2);
          break;
        // case that previous operator is minus
        case 2:
          this.resultInInteger = minus(this.operand1, this.operand2);
          break;
        // case that previous operator is multiply
        case 3:
          this.resultInInteger = multiply(this.operand1, this.operand2);
          break;
        // if any value other than 0, 1, 2, 3, should throw an illegal status exception
        default:
          throw new IllegalStateException("illegal operator status is assigned, " +
                  "given " + String.valueOf(this.operator) + " is not a valid status");
      }
      // now assign the calculated value in String
      // to the currentResult variable.
      this.currentResult = String.valueOf(this.resultInInteger);
      // continue calculating.
      // condition that c is +, - or *
      if (isOperator(c)) {
        // now in order to continue calculation,
        // we need to return to status 1, operator status
        this.status = 1;
        // make the previous result be the first operand
        assignValueForO1ForAssignPrevRes(this.resultInInteger);
        // make the operand2 become -1, which is default value waiting for input
        assignValueForO2ForAssignminus1(-1);
        // now recall input again to enter operator stage
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
    // condition that c is not =, +, -, *.
    else {
      throw new IllegalArgumentException("should not input any character " +
              "other than +, -, *, or =, you put: " + c);
    }
  }

  // plus method takes two integers a and b, and
  // return the sum of two integer inputs
  private int plus(int a, int b) {
    return (a + b);
  }

  // minus method takes two integers a and b, and
  // return the difference between the
  // two integer inputs
  private int minus(int a, int b) {
    return (a - b);
  }

  // multiply method takes two integers a and b, and
  // return the product of the
  // two integer inputs
  private int multiply(int a, int b) {
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
    this.currentResult = "";
    this.resultInInteger = 0;
    this.operand1 = -1;
    this.operand2 = -1;
    this.status = 0;
    this.operator = 0;
  }

  /**
   * helper method for test, should delete after testing
   */
  public int getOperand1() {
    return this.operand1;
  }

  public int getOperand2() {
    return this.operand2;
  }
  /*
  remember to delete above before hand
   */


  private void assignValueForO1ForAssignPrevRes(int value) {
    this.operand1 = value;
    this.testingLog += ("\n value: " + value + " assignTo1: " + this.operand1 + " for giving prev result value");
  }

  private void assignValueForO1ForOperand1(int value) {
    this.operand1 = value;
    this.testingLog += ("\n value: " + value + " assignTo1: " + this.operand1 + " for giving operand1 value");
  }

  private void assignValueForO2ForAssignminus1(int value) {
    this.operand2 = value;
    this.testingLog += ("\n value: " + value + " assignTo2: " + this.operand2 + " for giving a -1 value");
  }

  private void assignValueForO2ForOperand2(int value) {
    this.operand2 = value;
    this.testingLog += ("\n value: " + value + " assignTo2: " + this.operand2 + " for giving operand2 value");
  }

  public String getLog() {
    return this.testingLog;
  }
}
