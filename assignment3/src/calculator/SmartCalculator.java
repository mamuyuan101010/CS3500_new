package calculator;

/**
 * SmartCalculator class is a class extends CalculatorAbs that has more advance feature than
 * SimpleCalculator.
 * it is able to handle the following situations different from SimpleCalculator:
 * <ul>
 *   <li>
 *     Input "=" multiple times: 3 2 + 2 4 = produces 56 as before. However 3 2 + 2 4 = = and
 *     3 2 + 2 4 = = = are also valid input sequences, and produce 80 and 104 respectively.
 *   </li>
 *   <li>
 *     Skipping the second operand: the input 3 2 + = produces 64. The input 3 2 + = = produces
 *     96, and so on. The state at the end of each "=" is the result of the computation thus far.
 *   </li>
 *   <li>
 *     Two consecutive operators: 3 2 + - 2 4 = ignores the first operator,
 *     and produces 8 as the result.
 *   </li>
 *   <li>
 *     Begin with operator: + 3 2 - 2 4 = ignores the "+" and produces 8 as the result.
 *     Note that this only applies to the '+' operator as it has a mathematical meaning
 *     when it comes before an operand. All other operators before operands are invalid.
 *   </li>
 * </ul>
 */
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
