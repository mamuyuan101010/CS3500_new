import org.junit.Before;
import org.junit.Test;

import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * CheesePizzaTest class is the class
 * that specifically handle the test for all cheese pizzas.
 */
public class CheesePizzaTest {
  ObservablePizza cheese;
  ObservablePizza cheese1;
  ObservablePizza cheese2;

  @Before
  public void setUp() {
    cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();
    cheese1 = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Medium)
            .build();
    cheese2 = new CheesePizza.CheesePizzaBuilder()
            .size(Size.Medium).crust(Crust.Thin).leftHalfCheese().build();
  }

  @Test
  public void testCost() {
    assertEquals(9, cheese.cost(), 0.01);
    assertEquals(7, cheese1.cost(), 0.01);
    assertEquals(6.5, cheese2.cost(), 0.01);
  }

  @Test
  public void testHasTopping() {
    assertEquals(ToppingPortion.Full, cheese.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.Full, cheese.hasTopping(ToppingName.Sauce));
    assertNull(cheese.hasTopping(ToppingName.Onion));
    assertEquals(ToppingPortion.LeftHalf, cheese2.hasTopping(ToppingName.Cheese));
  }

}