package model;

/**
 * Created by vidojemihajlovikj on 5/20/24.
 */
public class CalculatorImpl  implements ICalculator{
  @Override
  public int add(int a, int b) {
    return a+b;
  }

  @Override
  public int subtract(int a, int b) {
    return a-b;
  }

  @Override
  public int multiply(int a, int b) {
    return a * b;
  }

  @Override
  public int devide(int a, int b) {
    if ( b == 0 ){
      throw new IllegalArgumentException("Cannot divide by zero");
    }
    return a / b;
  }
}
