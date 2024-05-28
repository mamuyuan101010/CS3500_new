package Mutator;

import calculator.Calculator;
import calculator.SimpleCalculator;

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
public class SimpleMutator extends Mutator {

  /**
   * Mutator constructor that takes an array value of result, operand1,
   * operand2, status, operator and a stringResult value.
   * @param calculation is the array of int that include all info for calculation
   * @param stringResult is the result in string
   * @throws IllegalArgumentException throw exception when input an invalid number
   */
  public SimpleMutator(String stringResult, int[] calculation) throws Exception {
    super(stringResult, calculation.clone());
  }

  /**
   * buildCalculator method return a Calculator object based on
   * the mutator's current field values.
   *
   * @return a Calculator object based on the mutator's current field values
   */
  @Override
  public Calculator buildCalculator() {
    return new SimpleCalculator(this.stringResult, this.calculation);
  }

  // handle operand method takes three arguments, a character c which
  // given from input, the operand which is either operand1 or operand2,
  // finally the isFirstOperand which is the determinant of operand1,
  // if the given operand is operand1, then isFirstOperand is true,
  // else it's false.
  @Override
  protected void handleOperand(Character c, int operand, boolean isFirstOperand) {
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

  // this method handle result stage by calculate out the result
  // based on the previous given operand1, operand2, and operator
  @Override
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

  // this method handle the operator
  @Override
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
        throw new IllegalArgumentException("should not type any character other "
                +
                "than number or operator here");
    }
    this.calculation[3] = 2;
  }
}
