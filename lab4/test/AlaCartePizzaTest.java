import org.junit.Before;
import org.junit.Test;

import betterpizza.AlaCartePizza;
import betterpizza.ObservablePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * AlaCartePizzaTest class is the class that handle the test towards
 * Alacarte pizzas.
 */
public class AlaCartePizzaTest {
  ObservablePizza alacarte;

  @Before
  public void setUp() {
    alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();
  }

  @Test
  public void testHasTopping() {
    assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.Sauce));
    assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.GreenPepper));
    assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.Onion));
    assertEquals(ToppingPortion.LeftHalf, alacarte.hasTopping(ToppingName.Jalapeno));
    assertNull(alacarte.hasTopping(ToppingName.Ham));
    assertNull(alacarte.hasTopping(ToppingName.Chicken));
  }

  @Test
  public void testCost() {
    assertEquals(8.25, alacarte.cost(), 0.01);
  }

  @Test
  public void testNoSize() {
    try {
      alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
              .crust(Crust.Classic)
              .addTopping(ToppingName.Cheese, ToppingPortion.Full)
              .addTopping(ToppingName.Sauce, ToppingPortion.Full)
              .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
              .addTopping(ToppingName.Onion, ToppingPortion.Full)
              .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
              .build();
    } catch (Exception e) {
      assertEquals("size cannot be null", e.getMessage());
    }
  }
}