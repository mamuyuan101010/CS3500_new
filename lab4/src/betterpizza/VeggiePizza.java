package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * VeggiePizza class represents all kinds of Veggie pizza,
 * and it is a subclass of AlaCartePizza.
 */
public class VeggiePizza extends AlaCartePizza {

  /**
   * Create a pizza given its crust type, size and toppings.
   *
   * @param veggiePizzaBuilder is the builder of Veggie.
   */
  protected VeggiePizza(VeggiePizzaBuilder veggiePizzaBuilder) {

    super(veggiePizzaBuilder.s1, veggiePizzaBuilder.c1, veggiePizzaBuilder.toppings1);
  }

  /**
   * VeggiePizzaBuilder is a static class that extends from alacartePizzaBuilder.
   * as it is static, we can directly call VeggiePizzaBuilder.method() outside this
   * class without build a real VeggiePizzaBuilder object.
   */
  public static class VeggiePizzaBuilder extends PizzaBuilder<VeggiePizzaBuilder> {

    /**
     * constructor of VeggiePizzaBuilder.
     */
    public VeggiePizzaBuilder() {
      toppings1.put(ToppingName.Cheese, ToppingPortion.Full);
      toppings1.put(ToppingName.Sauce, ToppingPortion.Full);
      toppings1.put(ToppingName.BlackOlive, ToppingPortion.Full);
      toppings1.put(ToppingName.GreenPepper, ToppingPortion.Full);
      toppings1.put(ToppingName.Onion, ToppingPortion.Full);
      toppings1.put(ToppingName.Jalapeno, ToppingPortion.Full);
      toppings1.put(ToppingName.Tomato, ToppingPortion.Full);
    }

    @Override
    public ObservablePizza build() {
      return new VeggiePizza(this);
    }

    public VeggiePizzaBuilder noJalapeno() {
      this.toppings1.remove(ToppingName.Jalapeno);
      return returnBuilder();
    }

    public VeggiePizzaBuilder noTomato() {
      this.toppings1.remove(ToppingName.Tomato);
      return returnBuilder();
    }

    public VeggiePizzaBuilder noSauce() {
      this.toppings1.remove(ToppingName.Sauce);
      return returnBuilder();
    }

    @Override
    protected VeggiePizzaBuilder returnBuilder() {
      return this;
    }

  }
}
