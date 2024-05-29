package calculator;

import Mutator.IMutator;
import Mutator.SmartMutator;
import Mutator.StringChecker;
import Exception.NegativeOperandException;

public class SmartCalculator extends CalculatorAbs {

  /**
   * SmartCalculator constructor that takes currentResult as String,
   * and calculation as an array of integer.
   * @param currentResult is the string result
   * @param calculation is the array of integer
   */
  public SmartCalculator(String currentResult, int[] calculation) {
    super(currentResult, calculation.clone());
  }

  /**
   * SmartCalculator constructor that takes no argument.
   * Set all its resultInteger and calculation fields in default.
   * result integer is an empty string, and calculation is a array of integer
   * exactly in [0,-1,-1,0,0]
   */
  public SmartCalculator() {
    super("",new int[]{0, -1, -1, 0, 0});
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
  public Calculator input(char c) {
    IMutator m = new SmartMutator(this.currentResult, this.calculation.clone());
    m.input(c);
    return m.buildCalculator();
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
