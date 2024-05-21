package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public class CheesePizza implements ObservablePizza{
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;
  @Override
  public double cost() {
    return 0;
  }

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return null;
  }

  protected CheesePizza(Size s, Crust c) {
    // case that the given argument is null
    if (size == null || crust == null || toppings == null) {
      throw new IllegalArgumentException("arguments cannot be null");
    }
    else {
      this.size = s;
      this.crust = c;
      this.toppings = new HashMap<ToppingName, ToppingPortion>();
      this.toppings.put(ToppingName.Cheese, ToppingPortion.Full);
      this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
    }
  }
}
