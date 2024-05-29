package Mutator;

import calculator.Calculator;
import calculator.SmartCalculator;

/**
 * SmartMutator class is the mutator for calculating with SmartCalculator
 * that extends the Mutator abstract class.
 */
public class SmartMutator extends Mutator{
  /**
   * SmartMutator constructor builder.
   * @param stringResult is the result in string.
   * @param calculation is the calculation infos, including the operand1/2, operator,
   *                    and status of the calculation.
   * @throws Exception when the calculation input is invalid.
   */
  public SmartMutator(String stringResult, int[] calculation) throws Exception {
    super(stringResult, calculation);
  }

  /**
   * buildCalculator method returns a SmartCalculator with the info from current fields.
   * @return the updated SmartCalculator object.
   */
  @Override
  public Calculator buildCalculator() {
    return new SmartCalculator(this.stringResult, this.calculation.clone());
  }

  @Override
  protected void handleOperand(Character c, int operand, boolean isFirstOperand) {
    // handle the case that the given character is an operator
    if (isOperator(c)) {
      // if it's first operand, then - is not allowed, and + will be ignored
      if (isFirstOperand) {
        if (c == '-') {
          throw new CalculatorException();
        }
      }
    }
  }

  @Override
  protected void handleResult(char c) {

  }
}
