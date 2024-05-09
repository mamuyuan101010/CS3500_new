package vehicle;

/**
 * interface of manual transmission
 */
public interface ManualTransmission {
  /**
   * @return Return the status of this transmission
   * as a String without any additional parameters.
   */
  String getStatus();

  /**
   * @return the current speed of the vehicle as a whole number.
   * <ul>
   *   <li>
   *     "OK: everything is OK.":
   *     this is the status if the speed was changed successfully without changing gears,
   *     or the gear was changed successfully without changing speed.
   *   </li>
   * </ul>
   */
  int getSpeed();

  /**
   * @return the current gear of the vehicle as a whole number.
   */
  int getGear();

  /**
   * Increase the speed by a fixed amount without changing gears
   *
   * @return return the resulting transmission object.
   * <ul>
   *   <li>
   *     If the speed cannot be increased,
   *     it return an object with the same speed.
   *   </li>
   *   <li>
   *     The speed change amount is up to the implementation
   *     and is not an argument of this method.
   *   </li>
   * </ul>
   */
  Object increaseSpeed();

  /**
   * @return
   */
  Object decreaseSpeed();

  /**
   *
   * @return
   */
  Object increaseGear();

  /**
   *
   * @return
   */
  Object decreaseGear();
}
