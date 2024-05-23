package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * CheesePizza class is the class that represent types of cheese pizza,
 * cheese pizza is one kinds of Alacarte pizza, so extends AlaCartePizza class.
 */
public class CheesePizza extends AlaCartePizza {

  /**
   * Create a pizza given its crust type, size and toppings.
   *
   * @param cheeseBuilder is the cheeseBuilder
   */
  protected CheesePizza(CheesePizzaBuilder cheeseBuilder) {
    super(cheeseBuilder.s1, cheeseBuilder.c1, cheeseBuilder.toppings1);
  }

  /**
   * CheesePizzaBuilder class is an inner class of CheesePizza that
   * helps to build the CheesePizza object but let user has minumum access
   * to the inner fields.
   */
  public static class CheesePizzaBuilder extends PizzaBuilder<CheesePizzaBuilder> {

    /**
     * Constructor of Cheese Pizza Builder.
     */
    public CheesePizzaBuilder() {
      this.toppings1.put(ToppingName.Cheese, ToppingPortion.Full);
      this.toppings1.put(ToppingName.Sauce, ToppingPortion.Full);
    }

    @Override
    public ObservablePizza build() {
      return new CheesePizza(this);
    }

    /**
     * no cheese method will remove the cheese.
     *
     * @return itself with no cheese updated
     */
    public CheesePizzaBuilder noCheese() {
      this.toppings1.remove(ToppingName.Cheese);
      return returnBuilder();
    }

    /**
     * left half cheese will only keep the cheese in left half.
     *
     * @return itself with only left half cheese
     */
    public CheesePizzaBuilder leftHalfCheese() {
      this.toppings1.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
      return returnBuilder();
    }

    /**
     * right half cheese will only keep the cheese in right half.
     *
     * @return itself with only right half cheese
     */
    public CheesePizzaBuilder rightHalfCheese() {
      this.toppings1.put(ToppingName.Cheese, ToppingPortion.RightHalf);
      return returnBuilder();
    }

    @Override
    protected CheesePizzaBuilder returnBuilder() {
      return this;
    }
  }
}
