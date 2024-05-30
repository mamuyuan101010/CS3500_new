package calculator;

import exception.NegativeOperandException;

/**
 * CalculatorAbs is the abstract class implements Calculator interface
 * that can represent both SimpleCalculator and SmartCalculator.
 */
public abstract class CalculatorAbs implements Calculator {
  // currentResult stores the result in string and prepare
  // for getResult method to return
  protected final String currentResult;
  // calculation array stores the result in integer,
  // operand1, operand2, status, and operator values
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
  protected final int[] calculation;

  // CalculatorAbs constructor that takes a stringResult and an int array as calculation infos
  // that represent operand1/2, status, operator, resultInInt
  protected CalculatorAbs(String stringResult, int[] calculation) {
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
    this.currentResult = stringResult;
    this.calculation = calculation.clone();
  }

  @Override
  public String getResult() {
    return this.currentResult;
  }
}
