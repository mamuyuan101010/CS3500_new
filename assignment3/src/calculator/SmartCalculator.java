package calculator;

import Mutator.IMutator;
import Mutator.SmartMutator;

public class SmartCalculator extends CalculatorAbs {
  public SmartCalculator() {
    super("", new int[]{0, -1, -1, 0, 0});
  }

  public SmartCalculator(String result, int[] calculation) {
    super(result, calculation.clone());
  }

  /**
   * Input method for smartCalculator that return a SmartCalculator.
   * after input a character c
   *
   * @param c is a single character
   * @return a updated SmartCalculator
   * @throws Exception if any exception occur when calling constructor
   */
  @Override
  public Calculator input(char c) throws Exception {
    IMutator m = new SmartMutator(this.currentResult, this.calculation.clone());
    m.input(c);
    return new SmartCalculator();
  }

  @Override
  public String getResult() {
    return this.currentResult;
  }

  // remember to delete before submitting
  @Override
  public String getArray() {
    return "" + this.calculation[0]
            + "/" + this.calculation[1]
            + "/" + this.calculation[2]
            + "/" + this.calculation[3]
            + "/" + this.calculation[4];
  }

}
