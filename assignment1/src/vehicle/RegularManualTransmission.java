package vehicle;

/**
 * RegularManualTransmission interface
 * implements the ManualTransmission interface
 * that has the following characteristics.
 * <ul>
 *   <li>
 *     The speed changes by 1 at a time.
 *   </li>
 *   <li>
 *     The transmission supports exactly 5 gears, numbered 1 through 5.
 *     Gear 1 has the lowest speed range.
 *   </li>
 *   <li>
 *     The class offers a constructor that takes the speed ranges for the 5 gears
 *     as two integral numbers each: low and high.
 *     it takes arguments as l1,h1,l2,h2,...,l5,h5.
 *   </li>
 *   <li>
 *     Return the status messages
 *     depending on the state of the transmission.
 *     (more details explained in getStatus method).
 *   </li>
 * </ul>
 */
public class RegularManualTransmission implements ManualTransmission {
  // integer to store the speed increase amount
  private final int speedIncreaseAmount;
  // integer to store the current speed
  private int currentSpeed;
  // integer to store the gear
  private int gear;
  // string for storing the status message
  private String statusLog;
  private final int l1;
  private final int h1;
  private final int l2;
  private final int h2;
  private final int l3;
  private final int h3;
  private final int l4;
  private final int h4;
  private final int l5;
  private final int h5;

  /**
   * RegularManualTransmission constructor implements
   * the ManualTransmission interface, and takes the
   * speed ranges for the 5 gears as two integral numbers each: low and high.
   *
   * @param l1 is the lower speed for gear 1.
   * @param h1 is the higher speed for gear 1.
   * @param l2 is the lower speed for gear 2.
   * @param h2 is the higher speed for gear 2.
   * @param l3 is the lower speed for gear 3.
   * @param h3 is the higher speed for gear 3.
   * @param l4 is the lower speed for gear 4.
   * @param h4 is the higher speed for gear 4.
   * @param l5 is the lower speed for gear 5.
   * @param h5 is the higher speed for gear 5.
   */
  public RegularManualTransmission(int l1, int h1, int l2, int h2,
                                   int l3, int h3, int l4, int h4,
                                   int l5, int h5) {
    if (h1 < l1 || h2 < l2 || h3 < l3 || h4 < l4 || h5 < l5) {
      throw new IllegalArgumentException("lx should be less than or equal to hx.");
    } else if (l1 < 0 || l2 < 0 || l3 < 0 || l4 < 0 || l5 < 0) {
      throw new IllegalArgumentException("speed cannot be negative.");
    } else if (l1 >= l2 || l2 >= l3 || l3 >= l4 || l4 >= l5) {
      throw new IllegalArgumentException("lower speed for gear x should be strictly"
              + " lesser than that of gear x+1.");
    } else if (h1 < l2 || h2 < l3 || h3 < l4 || h4 < l5) {
      throw new IllegalArgumentException("ranges cannot be non-overlapping");
    } else if (moreThanTwoRangeCover()) {
      throw new IllegalArgumentException("only adjacent-gear ranges may overlap");
    } else if (l1 != 0) {
      throw new IllegalArgumentException("The lower speed of the first gear should be 0");
    } else if (h4 > h5) {
      throw new IllegalArgumentException("the h5 is the speed limit of the vehicle"
              +
              " and it cannot go faster than this");
    } else {
      this.speedIncreaseAmount = 1;
      this.currentSpeed = 0;
      this.gear = 1;
      this.l1 = l1;
      this.h1 = h1;
      this.l2 = l2;
      this.h2 = h2;
      this.l3 = l3;
      this.h3 = h3;
      this.l4 = l4;
      this.h4 = h4;
      this.l5 = l5;
      this.h5 = h5;
      this.statusLog = "OK: everything is OK.";
    }
  }

  /**
   * getStatus message return the state of the transmission and
   * the last thing that was attempted.
   *
   * @return one of the following possibilities.
   * <ul>
   *   <li>
   *     "OK: everything is OK.": this is the status if the speed was changed
   *     successfully without changing gears, or the gear was changed successfully
   *     without changing speed.
   *   </li>
   *   <li>
   *     "OK: you may increase the gear.": this is the status if the speed was increased
   *     successfully, but it is now within the range of the next gear (remember that
   *     adjacent gear ranges may overlap)
   *   </li>
   *   <li>
   *     "OK: you may decrease the gear.": this is the status if the speed was decreased
   *     successfully, but it is now within the range of the previous gear (remember that
   *     adjacent gear ranges may overlap)
   *   </li>
   *   <li>
   *     "Cannot increase speed, increase gear first.": this is the status if the speed
   *     cannot be increased more unless the gear is increased first. This implies that
   *     the intended speed is too high for the current gear.
   *   </li>
   *   <li>
   *     "Cannot decrease speed, decrease gear first.": this is the status if the speed
   *     cannot be decreased more unless the gear is decreased first. This implies that
   *     the intended speed is too low for the current gear.
   *   </li>
   *   <li>
   *     "Cannot increase gear, increase speed first.": this is the status if the gear
   *     cannot be increased more unless the speed is increased first. This implies that
   *     the current speed will be too low for the next gear.
   *   </li>
   *   <li>
   *     "Cannot decrease gear, decrease speed first.": this is the status if the gear
   *     cannot be decreased more unless the speed is decreased first. This implies that
   *     the current speed will be too high for the previous gear.
   *   </li>
   *   <li>
   *     "Cannot increase speed. Reached maximum speed.": this is the status if the speed
   *     cannot be increased as it will go beyond the speed limit of the vehicle
   *   </li>
   *   <li>
   *     "Cannot decrease speed. Reached minimum speed.": this is the status if the speed
   *     cannot be decreased as it is already 0
   *   </li>
   *   <li>
   *     "Cannot increase gear. Reached maximum gear.": this is the status if the gear cannot
   *     be increased as it is already in gear 5
   *   </li>
   *   <li>
   *     "Cannot decrease gear. Reached minimum gear.": this is the status if the gear cannot
   *     be decreased as it is already in gear 1
   *   </li>
   * </ul>
   */
  @Override
  public String getStatus() {
    return this.statusLog;
  }


  @Override
  public int getSpeed() {
    return this.currentSpeed;
  }


  @Override
  public int getGear() {
    return this.gear;
  }

  /**
   * Increase the speed by 1 without changing gears.
   * If the speed cannot be increased, then it returns an object with the
   * same speed as before.
   *
   * @return the resulting transmission object with the speed increased by 1
   *     if it can be increased.
   */
  @Override
  public ManualTransmission increaseSpeed() {
    // condition that current speed reach the maximum speed of gear 5
    if (currentSpeed == h5) {
      this.statusLog = "Cannot increase speed. Reached maximum speed.";
    }
    // condition that current speed reach the maximum in current gear
    else if (currentSpeed + speedIncreaseAmount > getGearHighSpd(this.gear)) {
      this.statusLog = "Cannot increase speed, increase gear first.";
    }
    // condition that current speed increase successfully
    else {
      currentSpeed += speedIncreaseAmount;
      // condition if already in gear 5
      if (this.getGear() == 5) {
        this.statusLog = "OK: everything is OK.";
      }
      // condition if gear is in 1, 2, 3, or 4
      else {
        // condition that speed reach the min speed of the next gear
        if (this.getSpeed() >= getGearLowSpd(this.getGear() + 1)) {
          this.statusLog = "OK: you may increase the gear.";
        }
        // condition that speed not reach the min speed of the next gear
        else {
          this.statusLog = "OK: everything is OK.";
        }
      }
    }
    return this;
  }

  /**
   * Decrease the speed by 1 without changing gears.
   * If the speed cannot be decreased, then it returns an object with the
   * same speed as before.
   *
   * @return the resulting transmission object with the speed decreased by 1
   *     if it can be decreased.
   */
  @Override
  public ManualTransmission decreaseSpeed() {
    // condition that current speed reach 0
    if (currentSpeed == 0) {
      this.statusLog = "Cannot decrease speed. Reached minimum speed.";
    }
    // condition that current speed reach the lower speed of the current gear
    else if (currentSpeed - speedIncreaseAmount < getGearLowSpd(this.gear)) {
      this.statusLog = "Cannot decrease speed, decrease gear first.";
    }
    // condition that current speed decrease successfully
    else {
      currentSpeed -= speedIncreaseAmount;
      // condition that current gear is in gear 1
      if (this.getGear() == 1) {
        this.statusLog = "OK: everything is OK.";
      }
      // condition that in gear 2, 3, 4, or 5
      else {
        if (this.getSpeed() <= getGearHighSpd(this.getGear() - 1)) {
          this.statusLog = "OK: you may decrease the gear.";
        } else {
          this.statusLog = "OK: everything is OK.";
        }
      }
    }
    return this;
  }


  @Override
  public ManualTransmission increaseGear() {
    // condition that gear is at 5
    if (this.gear == 5) {
      this.statusLog = "Cannot increase gear. Reached maximum gear.";
    }
    // condition that gear is in 1, 2, 3, or 4
    else {
      // condition that current speed not reach the min speed of the next gear
      if (currentSpeed < getGearLowSpd(this.gear + 1)) {
        this.statusLog = "Cannot increase gear, increase speed first.";
      }
      // condition that gear increase successfully
      else {
        this.gear += 1;
        this.statusLog = "OK: everything is OK.";
      }
    }
    return this;
  }


  @Override
  public ManualTransmission decreaseGear() {
    // condition that gear is at 1
    if (this.gear == 1) {
      this.statusLog = "Cannot decrease gear. Reached minimum gear.";
    }
    // condition that gear is in 2, 3, 4, or 5
    else {
      // condition that current speed not decelerate the max speed of the previous gear
      if (currentSpeed > getGearHighSpd(this.gear - 1)) {
        this.statusLog = "Cannot decrease gear, decrease speed first.";
      }
      // condition that gear decrease successfully
      else {
        this.gear -= 1;
        this.statusLog = "OK: everything is OK.";
      }
    }
    return this;
  }

  // the numberOfRangeCover method return the number of
  // times the given speed is covered by each range
  // it is a helper method to check whether any speed is non-overlapped
  // or overlapped for more than 2 times
  private int numberOfRangeCover(int speed) {
    int returnNum = 0;
    if (speed >= l1 && speed <= h1) {
      returnNum += 1;
    }
    if (speed >= l2 && speed <= h2) {
      returnNum += 1;
    }
    if (speed >= l3 && speed <= h3) {
      returnNum += 1;
    }
    if (speed >= l4 && speed <= h4) {
      returnNum += 1;
    }
    if (speed >= l5 && speed <= h5) {
      returnNum += 1;
    }
    return returnNum;
  }

  // this helper function helps to check if any speed is overlapped by more than 2 gears
  // meaning non-adjacent gear ranges overlap
  private boolean moreThanTwoRangeCover() {
    for (int i = 0; i < h5; i++) {
      if (numberOfRangeCover(i) > 2) {
        return true;
      }
    }
    return false;
  }

  // return the lowest speed bound of the given gear
  private int getGearLowSpd(int gear) {
    if (gearCheckerFail()) {
      throw new IllegalArgumentException("gear not in good range");
    }
    if (gear == 1) {
      return this.l1;
    } else if (gear == 2) {
      return this.l2;
    } else if (gear == 3) {
      return this.l3;
    } else if (gear == 4) {
      return this.l4;
    } else if (gear == 5) {
      return this.l5;
    } else {
      throw new IllegalArgumentException("unable to reach");
    }
  }

  // return the highest speed bound of the given gear
  private int getGearHighSpd(int gear) {
    if (gearCheckerFail()) {
      throw new IllegalArgumentException("gear not in good range");
    }
    if (gear == 1) {
      return this.h1;
    } else if (gear == 2) {
      return this.h2;
    } else if (gear == 3) {
      return this.h3;
    } else if (gear == 4) {
      return this.h4;
    } else if (gear == 5) {
      return this.h5;
    } else {
      throw new IllegalArgumentException("unable to reach");
    }
  }

  // this helper method check if gear is in the correct range
  private boolean gearCheckerFail() {
    return this.gear < 1 || this.gear > 5;
  }

  /**
   * equals method compare two ManualTransmission object.
   * @param o is the object to compare with
   * @return whether the two ManualTransmission object are the same.
   */
  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) {
      //backward compatibility with default equals
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (!(o instanceof RegularManualTransmission)) {
      return false;
    }
    // compare if each field is the same
    RegularManualTransmission compareObject = (RegularManualTransmission) o;
    return (this.getGear() == compareObject.getGear())
            && (this.getSpeed() == compareObject.getSpeed())
            && (this.getStatus().equals(compareObject.getStatus()))
            && lowHighSpeedOfGearSame(compareObject);
  }

  // helper method to compare if the given RegularManualTransmission
  // object's lx and hx are
  // the same as the current object's lx and hx
  boolean lowHighSpeedOfGearSame(RegularManualTransmission compare) {
    return (this.h1 == compare.h1) && (this.h2 == compare.h2)
            && (this.h3 == compare.h3) && (this.h4 == compare.h4)
            && (this.h5 == compare.h5) && (this.l1 == compare.l1)
            && (this.l2 == compare.l2) && (this.l3 == compare.l3)
            && (this.l4 == compare.l4) && (this.l5 == compare.l5);
  }

  /**
   * hashCode method generate the hashcode of the object.
   * @return the hashcode of the ManualTransmission object.
   */
  @Override
  public int hashCode() {
    return Long.hashCode(this.getGear())
            + Long.hashCode(this.getSpeed())
            + this.getStatus().hashCode()
            + Long.hashCode(this.l1)
            + Long.hashCode(this.l2)
            + Long.hashCode(this.l3)
            + Long.hashCode(this.l4)
            + Long.hashCode(this.l5)
            + Long.hashCode(this.h1)
            + Long.hashCode(this.h2)
            + Long.hashCode(this.h3)
            + Long.hashCode(this.h4)
            + Long.hashCode(this.h5);
  }
}
