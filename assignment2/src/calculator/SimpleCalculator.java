package calculator;

public class SimpleCalculator implements Calculator{
  @Override
  public Calculator input(char c) {
    return null;
  }

  @Override
  public String getResult() {
    return null;
  }

  // plus method takes two integers a and b, and
  // return the sum of two integer inputs
  int plus(int a, int b) {
    return (a + b);
  }

  // minus method takes two integers a and b, and
  // return the difference between the
  // two integer inputs
  int minus(int a, int b) {
    return (a - b);
  }

  // multiply method takes two integers a and b, and
  // return the product of the
  // two integer inputs
  int multiply(int a, int b) {
    return (a * b);
  }
}
