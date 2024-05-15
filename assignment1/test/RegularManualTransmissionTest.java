import org.junit.Before;
import org.junit.Test;

import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * RegularManualTransmissionTest class is the class for
 * testing RegularManualTransmission class.
 */
public class RegularManualTransmissionTest {

  ManualTransmission manual1;
  ManualTransmission manual2;
  ManualTransmission manual3;
  ManualTransmission manual4;
  ManualTransmission manual5;
  ManualTransmission manual6;
  ManualTransmission manual7;

  // setup method that pre-build 7
  // different regularManualTransmission object
  @Before
  public void setUp() throws Exception {
    manual1 = new RegularManualTransmission(0, 15,
            10, 25, 20, 35, 30, 45, 40, 55);
    manual2 = new RegularManualTransmission(0, 20,
            15, 30, 25, 40, 35, 50, 45, 60);
    manual3 = new RegularManualTransmission(0, 30,
            25, 40, 35, 50, 45, 60, 55, 70);
    manual4 = new RegularManualTransmission(0, 28, 28,
            34, 34, 55, 55, 60, 60, 70);
    manual5 = new RegularManualTransmission(0, 10,
            10, 20, 20, 30, 30, 40, 40, 50);
    manual6 = new RegularManualTransmission(0, 40,
            40, 50, 50, 60, 60, 70, 70, 80);
    manual7 = new RegularManualTransmission(0, 5, 5,
            10, 10, 15, 15, 20, 20, 25);
  }

  // test for building the RegularManualTransmission constructor
  // by giving it the valid arguments.
  @Test
  public void testValidManual() {
    // test with valid input, should build a valid RegularManualTransmission object
    try {
      ManualTransmission validManual1
              = new RegularManualTransmission(0, 10, 10, 20,
              20, 30, 30, 40, 40, 50);
    } catch (Exception e) {
      fail("should not fail");
    }
    // test with valid input, should build a valid RegularManualTransmission object
    try {
      ManualTransmission validManual2
              = new RegularManualTransmission(0, 15, 10, 25,
              20, 35, 30, 45, 40, 55);
    } catch (Exception e) {
      fail("should not fail");
    }
    // test with valid input, should build a valid RegularManualTransmission object
    try {
      ManualTransmission validManual3
              = new RegularManualTransmission(0, 15, 10, 25,
              20, 35, 30, 55, 40, 55);
    } catch (Exception e) {
      fail("should not fail");
    }
    // test with an extreme case where h5 less than h4
    try {
      ManualTransmission validManual4
              = new RegularManualTransmission(0, 15, 10, 25,
              20, 35, 30, 60, 40, 55);
    } catch (Exception e) {
      fail("should not fail");
    }
  }

  // test for any invalid parameter of constructor
  @Test
  public void testInvalidManual() {
    // test for invalid input due to lx greater than hx
    try {
      ManualTransmission inValidmanual1
              = new RegularManualTransmission(0, 10, 10, 30,
              30, 25, 30, 40, 40, 50);
    } catch (Exception e) {
      assertEquals("lx should be less than or equal to hx.", e.getMessage());
    }
    // test for invalid input due to negative
    try {
      ManualTransmission inValidmanual2
              = new RegularManualTransmission(0, 5, -3, 6,
              6, 7, 7, 8, 8, 9);
    } catch (Exception e) {
      assertEquals("speed cannot be negative.", e.getMessage());
    }
    try {
      ManualTransmission inValidmanual3
              = new RegularManualTransmission(0, 5, 3, 6,
              6, 7, -7, 8, 8, 9);
    } catch (Exception e) {
      assertEquals("speed cannot be negative.", e.getMessage());
    }
    // test for invalid input due to lower speed of gear 1 is not less than that of gear 2
    try {
      ManualTransmission inValidManual4
              = new RegularManualTransmission(0, 10, 0, 30,
              20, 50, 45, 60, 55, 70);
    } catch (Exception e) {
      assertEquals("lower speed for gear x should be strictly " +
              "lesser than that of gear x+1.", e.getMessage());
    }
    // test for invalid input due to l2 equals to l3
    try {
      ManualTransmission inValidManual5 =
              new RegularManualTransmission(0, 10, 10, 10,
                      10, 20, 20, 30, 30, 40);
    } catch (Exception e) {
      assertEquals("lower speed for gear x should be strictly " +
              "lesser than that of gear x+1.", e.getMessage());
    }
    // test for invalid input due to whole speed of range not been fully overlapped
    try {
      ManualTransmission inValidManual6 =
              new RegularManualTransmission(0, 9, 10, 20,
                      20, 30, 30, 40, 40, 50);
    } catch (Exception e) {
      assertEquals("ranges cannot be non-overlapping", e.getMessage());
    }
    // test for invalid input due to whole speed of range not been fully overlapped
    try {
      ManualTransmission inValidManual6 =
              new RegularManualTransmission(0, 10, 10, 20,
                      20, 25, 30, 40, 40, 50);
    } catch (Exception e) {
      assertEquals("ranges cannot be non-overlapping", e.getMessage());
    }
    // test for non-adjacent-gear ranges overlap
    try {
      ManualTransmission inValidManual7 =
              new RegularManualTransmission(0, 10, 10, 20,
                      11, 30, 30, 40, 40, 50);
    } catch (Exception e) {
      assertEquals("only adjacent-gear ranges may overlap", e.getMessage());
    }
    // test for non-adjacent-gear ranges overlap
    try {
      ManualTransmission inValidManual7 =
              new RegularManualTransmission(0, 10, 10, 20,
                      11, 30, 12, 40, 13, 50);
    } catch (Exception e) {
      assertEquals("only adjacent-gear ranges may overlap", e.getMessage());
    }
    // test for first gear not 0 invalid input
    try {
      ManualTransmission inValidManual7 =
              new RegularManualTransmission(5, 10, 10, 20,
                      20, 30, 30, 40, 40, 50);
    } catch (Exception e) {
      assertEquals("The lower speed of the first gear should be 0", e.getMessage());
    }
    try {
      ManualTransmission inValidManual7 =
              new RegularManualTransmission(8, 15, 10, 20,
                      20, 30, 30, 40, 40, 50);
    } catch (Exception e) {
      assertEquals("The lower speed of the first gear should be 0", e.getMessage());
    }
  }

  // tests for getStatus method in each case.
  @Test
  public void testGetStatus() {
    // test if getStatus message is correct by
    // build a new RegularManualTransmission object
    assertEquals("OK: everything is OK.", manual1.getStatus());
    // test if getStatus message is correct by
    // increase speed but not reach the min speed 15
    // of the 2nd gear
    manual2.increaseSpeed();
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // test if getStatus message is correct by
    // increase speed but reach the min speed 15
    // of the 2nd gear
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    assertEquals("OK: you may increase the gear.",
            manual2.getStatus());
    // test if getStatus message is correct by fail to increase the speed because it
    // reaches the reach the maximum speed(20) of the first gear
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    assertEquals("Cannot increase speed, increase gear first.",
            manual2.getStatus());
    // test if getStatus message is correct after increasing to the 2nd gear
    manual2.increaseGear();
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now speed should be 20 in gear 2
    // increase gear to 24
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    // current speed should be less than min speed of gear 3
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now increase speed to 25, min speed to gear 3
    manual2.increaseSpeed();
    assertEquals("OK: you may increase the gear.", manual2.getStatus());
    // increase speed to 28
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    assertEquals(28, manual2.getSpeed());
    assertEquals("OK: you may increase the gear.", manual2.getStatus());
    // now increase to gear 3
    manual2.increaseGear();
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now increase speed to 40
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    assertEquals(40, manual2.getSpeed());
    // speed should now be unable to be increased beyond 40
    manual2.increaseSpeed();
    assertEquals(40, manual2.getSpeed());
    assertEquals("Cannot increase speed, increase gear first.",
            manual2.getStatus());
    // increase to gear 4
    manual2.increaseGear();
    // accelerate to 45
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    assertEquals(45, manual2.getSpeed());
    assertEquals("OK: you may increase the gear.",
            manual2.getStatus());
    // increase to gear 5
    manual2.increaseGear();
    // accelerate to 60
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    assertEquals(60, manual2.getSpeed());
    manual2.increaseSpeed();
    assertEquals("Cannot increase speed. Reached maximum speed.",
            manual2.getStatus());
    manual2.increaseGear();
    assertEquals("Cannot increase gear. Reached maximum gear.",
            manual2.getStatus());
    // decrease speed to 51
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // decrease speed to 50
    manual2.decreaseSpeed();
    assertEquals("OK: you may decrease the gear."
            , manual2.getStatus());
    // decrease speed to 48
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals("OK: you may decrease the gear."
            , manual2.getStatus());
    // decrease speed to 45
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals(45, manual2.getSpeed());
    assertEquals("OK: you may decrease the gear."
            , manual2.getStatus());
    // attempt to decrease speed and should fail
    manual2.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first."
            , manual2.getStatus());
    // decrease to gear 4
    manual2.decreaseGear();
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // decrease speed to 42
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    // decrease to 40
    assertEquals("OK: everything is OK.", manual2.getStatus());
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals("OK: you may decrease the gear.", manual2.getStatus());
    // decrease to 36
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals(36, manual2.getSpeed());
    assertEquals(4,manual2.getGear());
    assertEquals("OK: you may decrease the gear.", manual2.getStatus());
    // attempt to decrease speed after decreased to 35, and should fail
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.",
            manual2.getStatus());
  }

  // test for getSpeed method that return the current speed
  @Test
  public void testGetSpeed() {
  }

  @Test
  public void testGetGear() {
  }

  @Test
  public void testIncreaseSpeed() {
  }

  @Test
  public void testDecreaseSpeed() {
  }

  @Test
  public void testIncreaseGear() {
  }

  @Test
  public void testDecreaseGear() {
  }
}