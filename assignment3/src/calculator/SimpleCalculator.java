package calculator;

/**
 * The SimpleCalculator implements Calculator interface.
 * it takes straightforward inputs and work as a calculator
 * that handle with whole numbers shorter than 32 bits.
 * it has the following characteristics.
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
 * <p>
 *   for each variable in this field representing:
 *   </p>
 * <ul>
 *   <li>
 *     currentResult represent the result in string and prepare for getResult method to return.
 *   </li>
 *   <li>
 *     resultInInteger only store the calculated result in integer.
 *   </li>
 *   <li>
 *     operand1 represents the first operand of the calculation.
 *   </li>
 *   <li>
 *     operand2 represents the second operand of the calculation.
 *   </li>
 *   <li>
 *     status integer can only be 4 integers:
 *     0, meaning we are now in operand1;
 *     1, meaning we are now in operator;
 *     2, meaning we are in operand2;
 *     3, meaning we are in result;
 *   </li>
 *   <li>
 *     operator integer can only be 4 integers,
 *     0, meaning no operator assigned.
 *     1, meaning operator + will be used.
 *     2, meaning operator - will be used.
 *     and finally 3, meaning operator * will be used.
 *   </li>
 *   <li>
 *     all variables in the fields are declared as final private,
 *     in purpose of keeping the variable inapproachable by outside classes,
 *     and make the fields immutable.
 *   </li>
 * </ul>
 */
public class SimpleCalculator extends CalculatorAbs {

  /**
   * SimpleCalculator Constructor that takes all value and assign those values to
   * its private fields.
   *
   * @param currentResult is the result in string
   * @param calculation   is the array of int that store all info
   * @throws IllegalArgumentException when invalid input is enter the parameters.
   */
  public SimpleCalculator(String currentResult, int[] calculation) {
    super(currentResult, calculation.clone());
  }

  /**
   * constructor of SimpleCalculator that takes nothing, and will generate default value to
   * all the fields.
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
    super("", new int[]{0, -1, -1, 0, 0});
  }

  /**
   * input method for SimpleCalculator objects.
   * Inputting = after +, inputting + after +, inputting + at the beginning is forbidden in
   * SimpleCalculator.
   * Inputting = multiple times will result in the same result.
   * @param c is a single character
   * @return a new updated SimpleCalculator after input the character c
   */
  @Override
  public Calculator input(char c) {
    IMutator m = new SimpleMutator(this.currentResult, this.calculation);
    m.input(c);
    return m.buildCalculator();
  }
}
