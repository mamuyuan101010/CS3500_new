package calculator;

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
