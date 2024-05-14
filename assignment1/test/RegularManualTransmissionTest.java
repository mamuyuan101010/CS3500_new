import org.junit.Before;
import org.junit.Test;

import vehicle.RegularManualTransmission;

import static org.junit.Assert.*;

public class RegularManualTransmissionTest {

  RegularManualTransmission Maunal1;
  @Before
  public void setUp() throws Exception {
    Maunal1 = new RegularManualTransmission(0,15,
            10,25,20,35,30,45,40,55);
  }

  @Test
  public void testValidManual() {
    // test with valid input, should build a valid RegularManualTransmission object
    try {
      RegularManualTransmission validManual1
              = new RegularManualTransmission(0,10,10,20,
              20,30,30,40,40,50);
    } catch (Exception e) {
      fail("should not fail");
    }
    // test with valid input, should build a valid RegularManualTransmission object
    try {
      RegularManualTransmission validManual2
              = new RegularManualTransmission(0,15,10,25,
              20,35,30,45,40,55);
    } catch (Exception e) {
      fail("should not fail");
    }
    // test with valid input, should build a valid RegularManualTransmission object
    try {
      RegularManualTransmission validManual2
              = new RegularManualTransmission(0,15,10,25,
              20,35,30,55,40,55);
    } catch (Exception e) {
      fail("should not fail");
    }
  }

  @Test
  public void testInvalidManual() {
    // test for invalid input due to lower speed of gear 1 is not less than that of gear 2
    try {
      RegularManualTransmission validManual3
              = new RegularManualTransmission(0,10,0,30,
              20,50,45,60,55,70);
    } catch (Exception e) {
      assertEquals("lower speed for gear x should be strictly " +
              "lesser than that of gear x+1.", e.getMessage());
    }
  }
}