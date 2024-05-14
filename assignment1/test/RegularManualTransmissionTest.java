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

  ManualTransmission Manual1;
  ManualTransmission Manual2;
  ManualTransmission Manual3;
  ManualTransmission Manual4;
  ManualTransmission Manual5;
  ManualTransmission Manual6;
  ManualTransmission Manual7;

  // setup method that pre-build 7
  // different regularManualTransmission object
  @Before
  public void setUp() throws Exception {
    Manual1 = new RegularManualTransmission(0,15,
            10,25,20,35,30,45,40,55);
    Manual2 = new RegularManualTransmission(0,20,
            15,30,25,40,35,50,45,60);
    Manual3 = new RegularManualTransmission(0,30,
            25,40,35,50,45,60,55,70);
    Manual4 = new RegularManualTransmission(0,28,28,
            34,34,55,55,60,60,70);
    Manual5 = new RegularManualTransmission(0,10,
            10,20,20,30,30,40,40,50);
    Manual6 = new RegularManualTransmission(0,40,
            40,50,50,60,60,70,70,80);
    Manual7 = new RegularManualTransmission(0,5,5,
            10,10,15,15,20,20,25);
  }

  @Test
  public void testValidManual() {
    // test with valid input, should build a valid RegularManualTransmission object
    try {
      ManualTransmission validManual1
              = new RegularManualTransmission(0,10,10,20,
              20,30,30,40,40,50);
    } catch (Exception e) {
      fail("should not fail");
    }
    // test with valid input, should build a valid RegularManualTransmission object
    try {
      ManualTransmission validManual2
              = new RegularManualTransmission(0,15,10,25,
              20,35,30,45,40,55);
    } catch (Exception e) {
      fail("should not fail");
    }
    // test with valid input, should build a valid RegularManualTransmission object
    try {
      ManualTransmission validManual3
              = new RegularManualTransmission(0,15,10,25,
              20,35,30,55,40,55);
    } catch (Exception e) {
      fail("should not fail");
    }
    // test with an extreme case where h5 less than h4
    try {
      ManualTransmission validManual4
              = new RegularManualTransmission(0,15,10,25,
              20,35,30,60,40,55);
    } catch (Exception e) {
      fail("should not fail");
    }
  }

  // test for any invalid parameter of constructor
  @Test
  public void testInvalidManual() {
    // test for invalid input due to lx greater than hx
    try {
      ManualTransmission inValidManual1
              = new RegularManualTransmission(0,10,10,30,
              30,25,30,40,40,50);
    } catch (Exception e) {
      assertEquals("lx should be less than or equal to hx.", e.getMessage());
    }
    // test for invalid input due to negative
    try {
      ManualTransmission inValidManual2
              = new RegularManualTransmission(0, 5, -3, 6,
              6, 7, 7, 8, 8, 9);
    } catch (Exception e) {
      assertEquals("speed cannot be negative.", e.getMessage());
    }
    try {
      ManualTransmission inValidManual3
              = new RegularManualTransmission(0, 5, 3, 6,
              6, 7, -7, 8, 8, 9);
    } catch (Exception e) {
      assertEquals("speed cannot be negative.", e.getMessage());
    }
    // test for invalid input due to lower speed of gear 1 is not less than that of gear 2
    try {
      ManualTransmission inValidManual4
              = new RegularManualTransmission(0,10,0,30,
              20,50,45,60,55,70);
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
                      20,30,30,40,40,50);
    } catch (Exception e) {
      assertEquals("ranges cannot be non-overlapping", e.getMessage());
    }
    // test for invalid input due to whole speed of range not been fully overlapped
    try {
      ManualTransmission inValidManual6 =
              new RegularManualTransmission(0, 10, 10, 20,
                      20,25,30,40,40,50);
    } catch (Exception e) {
      assertEquals("ranges cannot be non-overlapping", e.getMessage());
    }
    // test for non-adjacent-gear ranges overlap
    try {
      ManualTransmission inValidManual7 =
              new RegularManualTransmission(0, 10, 10, 20,
                      11,30,30,40,40,50);
    } catch (Exception e) {
      assertEquals("only adjacent-gear ranges may overlap", e.getMessage());
    }
    // test for non-adjacent-gear ranges overlap
    try {
      ManualTransmission inValidManual7 =
              new RegularManualTransmission(0, 10, 10, 20,
                      11,30,12,40,13,50);
    } catch (Exception e) {
      assertEquals("only adjacent-gear ranges may overlap", e.getMessage());
    }
    // test for first gear not 0 invalid input
    try {
      ManualTransmission inValidManual7 =
              new RegularManualTransmission(5, 10, 10, 20,
                      20,30,30,40,40,50);
    } catch (Exception e) {
      assertEquals("The lower speed of the first gear should be 0", e.getMessage());
    }
    try {
      ManualTransmission inValidManual7 =
              new RegularManualTransmission(8, 15, 10, 20,
                      20,30,30,40,40,50);
    } catch (Exception e) {
      assertEquals("The lower speed of the first gear should be 0", e.getMessage());
    }
  }

  @Test
  public void testGetStatus() {
    assertEquals("OK: everything is OK.", Manual1.getStatus());
    Manual2.increaseSpeed();
    assertEquals("OK: everything is OK.", Manual2.getStatus());
    Manual2.increaseSpeed();
    Manual2.increaseSpeed();
    Manual2.increaseSpeed();
    Manual2.increaseSpeed();
    Manual2.increaseSpeed();
    Manual2.increaseSpeed();
    Manual2.increaseSpeed();
    Manual2.increaseSpeed();
    Manual2.increaseSpeed();

  }

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