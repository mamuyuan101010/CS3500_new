package Mutator;

import calculator.Calculator;
import calculator.SmartCalculator;
import Exception.InvalidCharacterException;

/**
 * SmartMutator class is the mutator for calculating with SmartCalculator
 * that extends the Mutator abstract class.
 */
public class SmartMutator extends Mutator {
  /**
   * SmartMutator constructor builder.
   *
   * @param stringResult is the result in string.
   * @param calculation  is the calculation infos, including the operand1/2, operator,
   *                     and status of the calculation.
   * @throws Exception when the calculation input is invalid.
   */
  public SmartMutator(String stringResult, int[] calculation) {
    super(stringResult, calculation.clone());
  }

  /**
   * buildCalculator method returns a SmartCalculator with the info from current fields.
   *
   * @return the updated SmartCalculator object.
   */
  @Override
  public Calculator buildCalculator() {
    return new SmartCalculator(this.stringResult, this.calculation.clone());
  }

  @Override
  protected void handleResult(char c) {
    // in case a = sign is input
    if (c == '=') {
      switch (calculation[4]) {
        case 1:
          calculation[0] = plus(calculation[1], calculation[2]);
          break;
        case 2:
          calculation[0] = minus(calculation[1], calculation[2]);
          break;
        case 3:
          calculation[0] = multiply(calculation[1], calculation[2]);
          break;
      }
      // first assign the number to the resultString
      this.stringResult = "" + calculation[0];
      // then assign the result into first operand
      calculation[1] = calculation[0];
    }
    // in case a +/-/* sign is input, should take c as an operator and continue taking number as
    // the second operand
    else if (isOperator(c)) {
      calculation[2] = -1;
      calculation[3] = 1;
      input(c);
    }
    // in case a new number is input
    else if (isNumber(c)) {
      backIntoDefault();
      input(c);
    }
    // any other case
    else {
      throw new IllegalStateException("" + c + " causes an illegal state error");
    }
  }

  // handleOperand1 method is for handle the first operand for SmartMutator
  @Override
  protected void handleOperand1(char c) {
    // case that operand1 has not been assigned a value yet
    if (calculation[1] == -1) {
      // in case if c is a number
      if (isNumber(c)) {
        assignOperand(c, Character.getNumericValue(c), true);
      }
      // in case if c is other than numbers and +
      else if ((isOperator(c) || c == '=') && (c != '+')) {
        throw new InvalidCharacterException(c);
      }
    }
    // case that operand1 has already had a value
    else {
      // case that c is a number
      if (isNumber(c)) {
        assignOperand(c, (int) digitAdv(calculation[1], Character.getNumericValue(c)), true);
      }
      // case that c is =
      else if (c == '=') {
        throw new InvalidCharacterException(c);
      } else if (isOperator(c)) {
        // case that c is an operator
        calculation[3] = 1; // make status be now in operator
        input(c);
      } else {
        throw new IllegalStateException("" + c + " is neither an operator nor a number or =," +
                "illegal state error occur");
      }
    }
  }

  // for handling operand2
  @Override
  protected void handleOperand2(char c) {
    // case that no value has assign to operand2 yet
    if (calculation[2] == -1) {
      // case that c is =
      if (c == '=') {
        calculation[2] = calculation[1];
        calculation[3] = 2;
        input(c);
      }
      // case that c is operator
      else if (isOperator(c)) {
        switch(c) {
          case '+':
            calculation[4] = 1;
            stringResult = stringResult.substring(0,stringResult.length()-1) + '+';
            break;
          case '-':
            calculation[4] = 2;
            stringResult = stringResult.substring(0,stringResult.length()-1) + '-';
            break;
          case '*':
            calculation[4] = 3;
            stringResult = stringResult.substring(0,stringResult.length()-1) + '*';
            break;
        }
      }
      // case that c is number
      else if (isNumber(c)) {
        assignOperand(c, Character.getNumericValue(c), false);
      }
      else {
        throw new IllegalStateException("" + c + " is an illegal character here that cause " +
                "illegal state error");
      }
    }
    // case that operand2 has already had a value
    else {
      // case that c is a number
      if (isNumber(c)) {
        assignOperand(c, (int) digitAdv(calculation[2], Character.getNumericValue(c)), false);
      }
      // case that c is an operator
      else if (isOperator(c)) {
        input('=');
        input(c);
      }
      // case that c is =
      else if (c == '=') {
        calculation[3] = 3;
        input(c);
      }
    }
  }
}
