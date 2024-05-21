package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

public class VeggiePizza implements ObservablePizza{
  @Override
  public double cost() {
    return 0;
  }

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return null;
  }
}
