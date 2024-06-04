package controller;

import java.util.Objects;

import model.ICalculator;

/**
 * Created by vidojemihajlovikj on 5/20/24.
 */
public class MockModel implements ICalculator {
  private final StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }


  @Override
  public int add(int a, int b) {
    log.append(a +  " " + b);
    return 0;
  }

  @Override
  public int subtract(int a, int b) {
    log.append(a +  " " + b);
    return 0;
  }

  @Override
  public int multiply(int a, int b) {
    log.append(a +  " " + b);
    return 0;
  }

  @Override
  public int devide(int a, int b) {
    log.append(a +  " " + b);
    return 0;
  }
}
