import junit.framework.TestCase;

import org.junit.Before;

import betterpizza.AlaCartePizza;
import betterpizza.ObservablePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

public class AlaCartePizzaTest extends TestCase {
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

  public void testHasTopping() {
    assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.Sauce));
    assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.GreenPepper));
    assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.Onion));
    assertEquals(ToppingPortion.LeftHalf, alacarte.hasTopping(ToppingName.Jalapeno));
    assertEquals(null, alacarte.hasTopping(ToppingName.Ham));
    assertEquals(null, alacarte.hasTopping(ToppingName.Chicken));
  }

  public void testCost() {
    assertEquals(8.25, alacarte.cost());
  }
}