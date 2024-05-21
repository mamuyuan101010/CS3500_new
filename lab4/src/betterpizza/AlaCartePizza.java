package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Pizza;
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
    public PizzaBuilder crust(Crust c) {
      this.c1 = c;
      return this;
    }

    @Override
    public PizzaBuilder size(Size s) {
      this.s1 = s;
      return this;
    }

    @Override
    public PizzaBuilder addTopping(ToppingName tn, ToppingPortion tp) {
      if (this.toppings1 == null) {
        this.toppings1 = new HashMap<ToppingName, ToppingPortion>();
      }
      this.toppings1.put(tn, tp);
      return this;
    }

    @Override
    public ObservablePizza build() {
      ObservablePizza returnPizza = new AlaCartePizza(this.s1, this.c1, this.toppings1);
      return returnPizza;
    }
  }

  protected AlaCartePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    // case that the given argument is null
    if (size == null || crust == null || toppings == null) {
      throw new IllegalArgumentException("arguments cannot be null");
    }
    // case that the given argument is non-null
    else {
      this.size = size;
      this.crust = crust;
      this.toppings = toppings;
    }
  }
}
