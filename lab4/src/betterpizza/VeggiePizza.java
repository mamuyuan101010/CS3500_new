package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public class VeggiePizza extends AlaCartePizza {
  /**
   * Create a pizza given its crust type, size and toppings.
   *
   * @param size is the Size Enum, representing the size of pizza
   * @param crust is a Crust Enum, representing the crust type
   */
  protected VeggiePizza(Size size, Crust crust) {
    Map<ToppingName, ToppingPortion> toppings = new HashMap<ToppingName, ToppingPortion>();

    toppings.put(ToppingName.Cheese,ToppingPortion.Full);
    toppings.put(ToppingName.Sauce,ToppingPortion.Full);
    toppings.put(ToppingName.BlackOlive,ToppingPortion.Full);
    toppings.put(ToppingName.GreenPepper,ToppingPortion.Full);
    toppings.put(ToppingName.Onion,ToppingPortion.Full);
    toppings.put(ToppingName.Jalapeno,ToppingPortion.Full);
    toppings.put(ToppingName.Tomato,ToppingPortion.Full);
    super(size, crust, toppings);
  }

  public static class VeggiePizzaBuilder extends AlaCartePizzaBuilder {
    @Override
    public ObservablePizza build() {
      return new VeggiePizza(this.s1, this.c1);
    }
  }
}
