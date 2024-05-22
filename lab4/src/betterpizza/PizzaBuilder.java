package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public abstract class PizzaBuilder {
  protected Crust c1;
  protected Size s1;
  protected Map<ToppingName, ToppingPortion> toppings1;
  public abstract PizzaBuilder crust(Crust c);
  public abstract PizzaBuilder size(Size s);
  public abstract PizzaBuilder addTopping(ToppingName tn, ToppingPortion tp);
  public abstract ObservablePizza build();
}
