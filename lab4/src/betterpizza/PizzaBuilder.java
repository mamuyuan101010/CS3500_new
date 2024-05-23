package betterpizza;

import java.util.HashMap;
import java.util.Map;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * PizzaBuilder class is the class that taking a T object, which
 * T must be a subclass from PizzaBuilder.
 * @param <T> is any subclass of PizzaBuilder
 */
public abstract class PizzaBuilder<T extends PizzaBuilder<T>> {
  protected Crust c1;
  protected Size s1;
  protected Map<ToppingName, ToppingPortion> toppings1 = new HashMap<ToppingName, ToppingPortion>();

  public T crust(Crust c) {
    this.c1 = c;
    return returnBuilder();
  }

  public T size(Size s) {
    this.s1 = s;
    return returnBuilder();
  }

  /**
   * addTopping method is the method that takes a toppingName and a ToppingPortion,
   * and add these two values into the toppings1 Map fields been declared above.
   * @param tn is the toppingName
   * @param tp is the toppingPortion
   * @return
   */
  public T addTopping(ToppingName tn, ToppingPortion tp) {
    if (this.toppings1 == null) {
      this.toppings1 = new HashMap<ToppingName, ToppingPortion>();
    }
    this.toppings1.put(tn, tp);
    return returnBuilder();
  }

  public abstract ObservablePizza build();

  protected abstract T returnBuilder();
}
