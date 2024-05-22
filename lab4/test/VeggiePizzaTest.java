import org.junit.Before;
import org.junit.Test;

import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class VeggiePizzaTest {
  ObservablePizza veggie;

  @Before
  public void setUp() throws Exception {
    veggie = new VeggiePizza
            .VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large).build();

  }

  @Test
  public void testVeggiePizza() {
    assertEquals(11.5,veggie.cost(), 0.01);
  }

  @Test
  public void testHasToppings() {
    assertEquals(ToppingPortion.Full, veggie.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.Full, veggie.hasTopping(ToppingName.Sauce));
    assertEquals(ToppingPortion.Full, veggie.hasTopping(ToppingName.BlackOlive));
    assertEquals(ToppingPortion.Full, veggie.hasTopping(ToppingName.GreenPepper));
    assertEquals(ToppingPortion.Full, veggie.hasTopping(ToppingName.Onion));
    assertEquals(ToppingPortion.Full, veggie.hasTopping(ToppingName.Jalapeno));
    assertEquals(ToppingPortion.Full, veggie.hasTopping(ToppingName.Tomato));
    assertNull(veggie.hasTopping(ToppingName.Ham));
  }
}