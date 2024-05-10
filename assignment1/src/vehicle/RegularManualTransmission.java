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

  /**
   * @param low
   * @param high
   */
  private RegularManualTransmission(int low, int high) {
    this.speedIncreaseAmount = 1;
    if (low < 0 || high < 0) {
      throw new IllegalArgumentException("Speed cannot be negative.");
    } else if (low > high) {
      throw new IllegalArgumentException("lower speed cannot" +
              " greater than higher speed");
    }
  }

  /**
   * @return
   */
  @Override
  public String getStatus() {
    return null;
  }

  /**
   * @return
   */
  @Override
  public int getSpeed() {
    return 0;
  }

  /**
   * @return
   */
  @Override
  public int getGear() {
    return 0;
  }

  /**
   * @return
   */
  @Override
  public Object increaseSpeed() {
    return null;
  }

  /**
   * @return
   */
  @Override
  public Object decreaseSpeed() {
    return null;
  }

  /**
   * @return
   */
  @Override
  public Object increaseGear() {
    return null;
  }

  /**
   * @return
   */
  @Override
  public Object decreaseGear() {
    return null;
  }
}
