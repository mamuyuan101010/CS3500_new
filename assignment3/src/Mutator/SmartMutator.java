package Mutator;

import calculator.Calculator;
import calculator.SmartCalculator;

/**
 * SmartMutator class is the mutator for calculating with SmartCalculator
 * that extends the Mutator abstract class
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

  @Override
  public Calculator buildCalculator() {
    return new SmartCalculator(this.stringResult, this.calculation);
  }

  @Override
  protected void handleOperator(char c) {

  }

  @Override
  protected void handleOperand(Character c, int operand, boolean isFirstOperand) {

  }

  @Override
  protected void handleResult(char c) {

  }
}
