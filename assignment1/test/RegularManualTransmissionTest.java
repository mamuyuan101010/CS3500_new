import org.junit.Before;
import org.junit.Test;

import vehicle.ManualTransmission;
import vehicle.RegularManualTransmission;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
  ManualTransmission manual8;
  String testing1;

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
    manual8 = new RegularManualTransmission(0, 5, 5,
            10, 10, 15, 15, 20, 20, 25);
    testing1 = "testing";
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
    // test with the case where h5 equals to h4
    try {
      ManualTransmission validManual4
              = new RegularManualTransmission(0, 15, 10, 25,
              20, 35, 30, 55, 40, 55);
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
      assertEquals("lower speed for gear x should be strictly "
              +
              "lesser than that of gear x+1.", e.getMessage());
    }
    // test for invalid input due to l2 equals to l3
    try {
      ManualTransmission inValidManual5 =
              new RegularManualTransmission(0, 10, 10, 10,
                      10, 20, 20, 30, 30, 40);
    } catch (Exception e) {
      assertEquals("lower speed for gear x should be strictly "
              +
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
    // test the case where h4 greater than h5
    // test with the case where h5 less than h4
    try {
      ManualTransmission validManual4
              = new RegularManualTransmission(0, 15, 10, 25,
              20, 35, 30, 60, 40, 55);
    } catch (Exception e) {
      assertEquals("the h5 is the speed limit of the vehicle "
              +
              "and it cannot go faster than this", e.getMessage());
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
    assertEquals(15, manual2.getSpeed());
    assertEquals("OK: you may increase the gear.",
            manual2.getStatus());
    // decrease speed to 13 and test getStatus message
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals(13, manual2.getSpeed());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // increase speed back to 15
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    assertEquals(15, manual2.getSpeed());
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
    assertEquals(20, manual2.getSpeed());
    assertEquals(2, manual2.getGear());
    // increase speed to 24
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    assertEquals(24, manual2.getSpeed());
    // current speed should be less than min speed of gear 3
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // attempt to increase gear, and should fail,
    // speed should stay the same
    manual2.increaseGear();
    assertEquals("Cannot increase gear, increase speed first.",
            manual2.getStatus());
    assertEquals(2, manual2.getGear());
    assertEquals(24, manual2.getSpeed());
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
    // now try to increase gear, and should fail because
    // it's already in the highest gear
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
    assertEquals(51, manual2.getSpeed());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now attempt to decrease gear, and should fail
    // speed should stay in 51
    manual2.decreaseGear();
    assertEquals("Cannot decrease gear, decrease speed first.",
            manual2.getStatus());
    assertEquals(51, manual2.getSpeed());
    // now decrease speed to 50
    manual2.decreaseSpeed();
    assertEquals("OK: you may decrease the gear.",
            manual2.getStatus());
    // decrease speed to 48
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals("OK: you may decrease the gear.",
            manual2.getStatus());
    // decrease speed to 45
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals(45, manual2.getSpeed());
    assertEquals("OK: you may decrease the gear.",
            manual2.getStatus());
    // attempt to decrease speed and should fail
    manual2.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.",
            manual2.getStatus());
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
    assertEquals(4, manual2.getGear());
    assertEquals("OK: you may decrease the gear.", manual2.getStatus());
    // attempt to decrease speed after decreased to 35, and should fail
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.",
            manual2.getStatus());
    // decrease to gear 3
    manual2.decreaseGear();
    assertEquals(3, manual2.getGear());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // decrease speed to 31, above the min speed of gear 2
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals(31, manual2.getSpeed());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now decrease speed to 30, equals to the min speed of gear 2
    manual2.decreaseSpeed();
    assertEquals(30, manual2.getSpeed());
    assertEquals("OK: you may decrease the gear.", manual2.getStatus());
    // decrease speed to 25, and then try to decrease, and should fail
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals(25, manual2.getSpeed());
    assertEquals("OK: you may decrease the gear.", manual2.getStatus());
    manual2.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.",
            manual2.getStatus());
    // now decrease to gear 2
    manual2.decreaseGear();
    assertEquals(2, manual2.getGear());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now decrease speed to 22
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals(22, manual2.getSpeed());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now decrease to 20, and should be able to decrease gear
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals(20, manual2.getSpeed());
    assertEquals("OK: you may decrease the gear.", manual2.getStatus());
    // now increase to 22, and getStatus should return to "OK: everything is OK"
    manual2.increaseSpeed();
    manual2.increaseSpeed();
    assertEquals(22, manual2.getSpeed());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now decrease to 21, and getStatus should stay the same
    manual2.decreaseSpeed();
    assertEquals(21, manual2.getSpeed());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now decrease to 20, and getStatus should generate the message
    // that allows me to decrease gear
    manual2.decreaseSpeed();
    assertEquals(20, manual2.getSpeed());
    assertEquals("OK: you may decrease the gear.",
            manual2.getStatus());
    // now decrease gear to gear 1, speed should stay the same
    manual2.decreaseGear();
    assertEquals(1, manual2.getGear());
    assertEquals(20, manual2.getSpeed());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now decrease speed to 1, getStatus should stay the same
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    manual2.decreaseSpeed();
    assertEquals(1, manual2.getSpeed());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // try to decrease gear, and should fail because it's in gear 1
    manual2.decreaseGear();
    assertEquals("Cannot decrease gear. Reached minimum gear.",
            manual2.getStatus());
    // now decrease speed to 0, get status should stay the same
    manual2.decreaseSpeed();
    assertEquals(0, manual2.getSpeed());
    assertEquals("OK: everything is OK.", manual2.getStatus());
    // now attempt to decrease speed after reaching 0, and should fail
    manual2.decreaseSpeed();
    assertEquals(0, manual2.getSpeed());
    assertEquals("Cannot decrease speed. Reached minimum speed.",
            manual2.getStatus());
  }

  // test for getSpeed method that return the current speed
  @Test
  public void testGetSpeed() {
    assertEquals(0, manual1.getSpeed());
    manual1.increaseSpeed();
    manual1.increaseSpeed();
    manual1.increaseSpeed();
    assertEquals(3, manual1.getSpeed());
    manual1.decreaseSpeed();
    assertEquals(2, manual1.getSpeed());
    manual1.decreaseSpeed();
    assertEquals(1, manual1.getSpeed());
    manual1.decreaseSpeed();
    assertEquals(0, manual1.getSpeed());
    manual1.decreaseSpeed();
    assertEquals(0, manual1.getSpeed());
  }

  // test getGear method that return the current gear
  // of the RegularManualTransmission object
  @Test
  public void testGetGear() {
    assertEquals(1, manual3.getGear());
    // increase speed to 25
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    manual3.increaseSpeed();
    assertEquals(25, manual3.getSpeed());
    manual3.increaseGear();
    // now test the getGear method which
    // should be 2 in this case
    assertEquals(2, manual3.getGear());
    // now decrease gear to 1
    manual3.decreaseGear();
    assertEquals(1, manual3.getGear());
  }

  // test increaseSpeed method that increase speed
  // by 1 if its speed is less than the current gear's highest speed
  @Test
  public void testIncreaseSpeed() {
    assertEquals(0, manual4.getSpeed());
    manual4.increaseSpeed();
    assertEquals(1, manual4.getSpeed());
    manual4.increaseSpeed();
    manual4.increaseSpeed();
    manual4.increaseSpeed();
    manual4.increaseSpeed();
    assertEquals(5, manual4.getSpeed());
    // now test increaseSpeed method return the correct updated
    // ManualTransmission object.
    ManualTransmission testReturn = manual4.increaseSpeed();
    assertEquals(6, testReturn.getSpeed());
    assertEquals(1, testReturn.getGear());
  }

  // test decreaseSpeed method that decrease speed
  // by 1 if its speed is higher than the current gear's lowest speed
  @Test
  public void testDecreaseSpeed() {
    assertEquals(0, manual5.getSpeed());
    manual5.increaseSpeed();
    assertEquals(1, manual5.getSpeed());
    manual5.increaseSpeed();
    manual5.increaseSpeed();
    manual5.increaseSpeed();
    manual5.increaseSpeed();
    assertEquals(5, manual5.getSpeed());
    // now decrease the speed by 1 each time
    manual5.decreaseSpeed();
    assertEquals(4, manual5.getSpeed());
    manual5.decreaseSpeed();
    assertEquals(3, manual5.getSpeed());
    manual5.decreaseSpeed();
    assertEquals(2, manual5.getSpeed());
    manual5.decreaseSpeed();
    assertEquals(1, manual5.getSpeed());
    manual5.decreaseSpeed();
    assertEquals(0, manual5.getSpeed());
    // now test decreaseSpeed method return the correct
    // updated ManualTransmission object.
    ManualTransmission testReturn2 = manual5.increaseSpeed();
    assertEquals(1, testReturn2.getSpeed());
    assertEquals(1, testReturn2.getGear());
    ManualTransmission testReturn3 = manual5.decreaseSpeed();
    assertEquals(0, testReturn2.getSpeed());
    assertEquals(1, testReturn2.getGear());
  }

  // test increaseGear method that increase the gear by one if
  // the gear is not at 5 and if the current speed reach the minimum
  // speed of the next gear
  @Test
  public void testIncreaseGear() {
    assertEquals(1, manual6.getGear());
    // now increase speed to 39, min speed of gear 2
    // test increase gear should fall before reach 40
    for (int i = 1; i < 40; i++) {
      manual6.increaseSpeed();
      manual6.increaseGear();
      assertEquals(1, manual6.getGear());
      assertEquals(i, manual6.getSpeed());
      assertEquals("Cannot increase gear, increase speed first.",
              manual6.getStatus());
    }
    // now increase speed to 40, and gear should be able to increase
    manual6.increaseSpeed();
    manual6.increaseGear();
    assertEquals(2, manual6.getGear());
    assertEquals(40, manual6.getSpeed());
    // now increase speed to 49
    manual6.increaseSpeed().increaseSpeed().increaseSpeed()
            .increaseSpeed().increaseSpeed().increaseSpeed()
            .increaseSpeed().increaseSpeed().increaseSpeed()
            .increaseSpeed();
    // now test whether increaseGear method return the correct updated
    // ManualTransmission object
    ManualTransmission testReturn4 = manual6.increaseGear();
    assertEquals(50, testReturn4.getSpeed());
    assertEquals(3, testReturn4.getGear());
  }

  // test decreaseGear method that decrease the gear by one if
  // the gear is not at 1 and if the current speed decrease to the
  // maximum speed of the previous gear
  @Test
  public void testDecreaseGear() {
    // first accelerate to speed of 10 and change gear to 2
    for (int i = 1; i < 6; i++) {
      manual7.increaseSpeed();
      assertEquals(1, manual7.getGear());
      assertEquals(i, manual7.getSpeed());
    }
    manual7.increaseGear();
    assertEquals(2, manual7.getGear());
    for (int i = 6; i < 11; i++) {
      manual7.increaseSpeed();
      assertEquals(2, manual7.getGear());
      assertEquals(i, manual7.getSpeed());
    }
    // now try to decrease gear everytime
    // when decelerate from 10 to 6,
    // and should fail
    for (int i = 9; i > 5; i--) {
      manual7.decreaseSpeed();
      manual7.decreaseGear();
      assertEquals(2, manual7.getGear());
      assertEquals(i, manual7.getSpeed());
      assertEquals("Cannot decrease gear, decrease speed first.",
              manual7.getStatus());
    }
    // now decrease the speed by 1, and should
    // succeed in decreasing gear
    manual7.decreaseSpeed();
    manual7.decreaseGear();
    assertEquals(5, manual7.getSpeed());
    assertEquals(1, manual7.getGear());
    assertEquals("OK: everything is OK.",
            manual7.getStatus());
    // now increase gear back to 2 without changing speed
    manual7.increaseGear();
    assertEquals(2, manual7.getGear());
    assertEquals(5, manual7.getSpeed());
    // now check if the decreaseGear method return the correct updated object
    ManualTransmission testReturn5 = manual7.decreaseGear();
    assertEquals(5, testReturn5.getSpeed());
    assertEquals(1, testReturn5.getGear());
  }

  // test equals method I overwrite, should return true
  // if all their fields are the same, or they are just
  // the same object. return false if at least one of
  // their fields are different or the given object
  // is not a RegularManualTransmission object
  @Test
  public void testEquals() {
    // test different RegularManualTransmission object
    // but fields are the same
    assertTrue(manual7.equals(manual8));
    // test same RegularManualTransmission object
    assertTrue(manual7.equals(manual7));
    // test different RegularManualTransmission object
    // that their fields are different
    assertFalse(manual7.equals(manual6));
    // test RegularManualTransmission object
    // comparing with a non-RegularManualTransmission object
    assertFalse(manual7.equals(testing1));
  }

  // test the hashCode method I overwrite.
  // hashcode should be the same if all their fields are equals.
  // hashcode should be different if any of their fields are different.
  // hashcode should always be the same if the given objects are the same.
  @Test
  public void testHashCode() {
    assertEquals(2111299237, manual7.hashCode());
    assertEquals(2111299237, manual8.hashCode());
    assertEquals(2111299632, manual6.hashCode());
    // test hashcode are the same if their field are the same
    assertTrue(manual7.hashCode() == manual8.hashCode());
    // test hashcode are the same if they are
    // the same RegularManualTransmission object
    assertTrue(manual7.hashCode() == manual7.hashCode());
    // test hashcode are different if at least one of
    // their fields are different
    assertFalse(manual7.hashCode() == manual6.hashCode());
    // test hashcode are different if they are not the same type of object
    assertFalse(manual7.hashCode() == testing1.hashCode());
    // test hashcode being different once speed increase
    manual7.increaseSpeed();
    assertFalse(manual7.hashCode() == manual8.hashCode());
    // test hashcode are the same if the field are the same
    manual7.decreaseSpeed();
    assertTrue(manual7.hashCode() == manual8.hashCode());
  }
}