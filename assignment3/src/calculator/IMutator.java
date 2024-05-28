package calculator;

/**
 * IMutator is the interface that represent all kinds of Mutators
 */
public interface IMutator {
  // input method that take an input and mutate the fields
  public void input(char c);
  // buildCalculator method return a calculator with
  // the correct given calculator infos such as operand1/2, operator and so on.
  public Calculator buildCalculator();
}
