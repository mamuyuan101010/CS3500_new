package vehicle;

/**
 * This is the interface of the simulator of manual transmission.
 * that offers the following operations:
 * <ul>
 *   <li>
 *     Increase the speed by a certain fixed amount.
 *   </li>
 *   <li>
 *     Decrease the speed by a certain fixed amount.
 *   </li>
 *   <li>
 *     Increase the gear by one.
 *   </li>
 *   <li>
 *     Decrease the gear by one.
 *   </li>
 *   <li>
 *     Report the status of the vehicle
 *     (as a result of any of the above operations).
 *   </li>
 * </ul>
 */
public interface ManualTransmission {

  /**
   * @return Return the status of this transmission
   * as a String without any additional parameters.
   */
  String getStatus();

  /**
   * @return the current speed of the vehicle as a whole number.
   */
  int getSpeed();

  /**
   * @return the current gear of the vehicle as a whole number.
   */
  int getGear();

  /**
   * Increase the speed by a fixed amount without changing gears.
   * @return return the resulting transmission object.
   * If the speed cannot be increased,
   * then it should return an object with the same speed as before.
   * The speed change amount is up to the implementation
   * and is not an argument of this method.
   */
  Object increaseSpeed();

  /**
   * Decrease the speed by a fixed amount without changing gears.
   * @return return the resulting transmission object.
   *
   * If the speed cannot be decreased,
   * then it should return an object with the same speed as before.
   * The speed change amount is up to the implementation
   * and is not an argument of this method.
   */
  Object decreaseSpeed();

  /**
   *Increase the gear by one without changing speed.
   * @return return the resulting transmission object.
   *
   * If the gear cannot be increased,
   * then it should return an object with the same gear as before.
   */
  Object increaseGear();

  /**
   *Decrease the gear by one without changing speed.
   * @return return the resulting transmission object.
   *
   * If the gear cannot be decreased,
   * then it should return an object with the same gear as before.
   */
  Object decreaseGear();
}
