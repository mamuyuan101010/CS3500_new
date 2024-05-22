package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents an ala carte pizza (i.e. a pizza that can
 * have an arbitrary number of ingredients.
 */
public class AlaCartePizza implements ObservablePizza {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    };
    return cost + this.size.getBaseCost();
  }

  public static class AlaCartePizzaBuilder extends PizzaBuilder {

    @Override
    public AlaCartePizzaBuilder crust(Crust c) {
      this.c1 = c;
      return returnBuilder();
    }

    @Override
    public AlaCartePizzaBuilder size(Size s) {
      this.s1 = s;
      return returnBuilder();
    }

    @Override
    public AlaCartePizzaBuilder addTopping(ToppingName tn, ToppingPortion tp) {
      if (this.toppings1 == null) {
        this.toppings1 = new HashMap<ToppingName, ToppingPortion>();
      }
      this.toppings1.put(tn, tp);
      return returnBuilder();
    }

    @Override
    public ObservablePizza build() {
      return new AlaCartePizza(this.s1, this.c1, this.toppings1);
    }

    protected AlaCartePizzaBuilder returnBuilder() {
      return this;
    }
  }

  protected AlaCartePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    // case that the given argument is null
    if (size == null) {
      throw new IllegalArgumentException("size cannot be null");
    }
    else if (crust == null) {
      throw new IllegalArgumentException("crust cannot be null");
    }
    else if (toppings == null) {
      throw new IllegalArgumentException("toppings cannot be null");
    }
    // case that the given argument is non-null
    else {
      this.size = size;
      this.crust = crust;
      this.toppings = toppings;
    }
  }
}
