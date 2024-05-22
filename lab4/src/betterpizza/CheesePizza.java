package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public class CheesePizza extends AlaCartePizza {
  /**
   * Create a pizza given its crust type, size and toppings.
   *
   * @param size is the Size Enum, representing the size of pizza
   * @param crust is a Crust Enum, representing the crust type
   */
  protected CheesePizza(Size size, Crust crust) {
    Map<ToppingName, ToppingPortion> toppings = new HashMap<ToppingName, ToppingPortion>();
    toppings.put(ToppingName.Cheese, ToppingPortion.Full);
    toppings.put(ToppingName.Sauce, ToppingPortion.Full);
    super(size, crust, toppings);
  }
  public static class CheesePizzaBuilder extends AlaCartePizzaBuilder {
    @Override
    public ObservablePizza build() {
      return new CheesePizza(this.s1, this.c1);
    }

    /**
     * no cheese method will remove the cheese.
     * @return itself with no cheese updated
     */
    public CheesePizzaBuilder noCheese() {
      if (this.toppings1 == null) {
        this.toppings1 = new HashMap<ToppingName, ToppingPortion>();
      }
      this.toppings1.remove(ToppingName.Cheese);
      return returnBuilder();
    }

    /**
     * left half cheese will only keep the cheese in left half.
     * @return itself with only left half cheese
     */
    public CheesePizzaBuilder leftHalfCheese() {
      if (this.toppings1 == null) {
        this.toppings1 = new HashMap<ToppingName, ToppingPortion>();
      }
      this.toppings1.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
      return returnBuilder();
    }

    /**
     * right half cheese will only keep the cheese in right half
     * @return itself with only right half cheese
     */
    public CheesePizzaBuilder rightHalfCheese() {
      if (this.toppings1 == null) {
        this.toppings1 = new HashMap<ToppingName, ToppingPortion>();
      }
      this.toppings1.put(ToppingName.Cheese, ToppingPortion.RightHalf);
      return returnBuilder();
    }

    @Override
    public CheesePizzaBuilder crust(Crust c) {
      this.c1 = c;
      return returnBuilder();
    }

    @Override
    public CheesePizzaBuilder size(Size s) {
      this.s1 = s;
      return returnBuilder();
    }

    @Override
    public CheesePizzaBuilder addTopping(ToppingName tn, ToppingPortion tp) {
      if (this.toppings1 == null) {
        this.toppings1 = new HashMap<ToppingName, ToppingPortion>();
      }
      this.toppings1.put(tn, tp);
      return returnBuilder();
    }
    @Override
    protected CheesePizzaBuilder returnBuilder() {
      return this;
    }
  }
}
