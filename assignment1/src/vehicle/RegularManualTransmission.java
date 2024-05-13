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

  private RegularManualTransmission(int l1, int h1, int l2, int h2, int l3, int h3, int l4, int h4, int l5, int h5) {
    if (h1 < l1 || h2 < l2 || h3 < l3 || h4 < l4 || h5 < l5) {
      throw new IllegalArgumentException("lx should be less than or equal to hx.");
    } else if (l1 < 0 || l2 < 0 || l3 < 0 || l4 < 0 || l5 < 0) {
      throw new IllegalArgumentException("speed cannot be negative.");
    } else if (l1 >= l2 || l2 >= l3 || l3 >= l4 || l4 >= l5) {
      throw new IllegalArgumentException("lower speed for gear 1 should be strictly" +
              " lesser than that of gear 2, and so on.");
    } else if (h1 < l2 || h2 < l3 || h3 < l4 || h4 < l5) {
      throw new IllegalArgumentException("ranges cannot be non-overlapping");
    } else if (!NoMoreThanTwoRangeCover()) {
      throw new IllegalArgumentException("only adjacent-gear ranges may overlap");
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
    }
  }

  /**
   * @return one of the following possibilities
   * (depending on the state of the transmission
   * and the last thing that was attempted):
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
   * @return the resulting transmission object with the speed increased by 1.
   * If the speed cannot be decreased, then it returns an object with the
   * same speed as before.
   */
  @Override
  public Object increaseSpeed() {
    if (currentSpeed + speedIncreaseAmount >= h5) {

    }
    currentSpeed += speedIncreaseAmount;
    return this;
  }


  @Override
  public Object decreaseSpeed() {
    return null;
  }


  @Override
  public Object increaseGear() {
    return null;
  }


  @Override
  public Object decreaseGear() {
    return null;
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
  private boolean NoMoreThanTwoRangeCover() {
    for (int i = 0; i < h5; i++) {
      if (numberOfRangeCover(i) >= 2) {
        return false;
      }
    }
    return true;
  }

}
