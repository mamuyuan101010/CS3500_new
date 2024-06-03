package calculator;

/**
 *  interface calculator.Calculator that represents a single calculator
 *  This interface have the following methods:
 *  <ul>
 *    <li>
 *      Method input that takes a single character as its only argument and
 *      return a calculator.Calculator object as a result of processing the input.
 *    </li>
 *    <li>
 *      method getResult that does not take any arguments and
 *      returns the current "result" of the calculator as a String object.
 *    </li>
 *  </ul>
 */
public interface Calculator {

  /**
   * takes a single character and
   * return a calculator.Calculator object as
   * a result of processing the input.
   * @param c is a single character
   * @return a calculator.Calculator object as a result of processing the input
   */
  Calculator input(char c);

  /**
   * does not take any arguments and return the current
   * "result" of the calculator.
   * @return the current "result" of the calculator as a String.
   */
  String getResult();
}
