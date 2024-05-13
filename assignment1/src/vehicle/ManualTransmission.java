package vehicle;

/**
 * This is the interface of the simulator of manual transmission.
 * that offers the following operations.
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
   * this method return the status of this transmission.
   * @return Return the status of this transmission.
   */
  String getStatus();

  /**
   * this method Get the current speed of the vehicle.
   * @return the current speed of the vehicle as a whole number.
   */
  int getSpeed();

  /**
   * this method Get the current gear of the vehicle.
   * @return the current gear of the vehicle as a whole number.
   */
  int getGear();

  /**
   * this method Increase the speed by a fixed amount without changing gears.
   * If the speed cannot be increased,
   * then it should return an object with the same speed as before.
   * The speed change amount is up to the implementation
   * and is not an argument of this method.
   * @return return the resulting transmission object.
   */
  ManualTransmission increaseSpeed();

  /**
   * Decrease the speed by a fixed amount without changing gears.
   * If the speed cannot be decreased,
   * then it should return an object with the same speed as before.
   * The speed change amount is up to the implementation
   * and is not an argument of this method.
   * @return return the resulting transmission object.
   */
  ManualTransmission decreaseSpeed();

  /**
   * Increase the gear by one without changing speed.
   * If the gear cannot be increased,
   * then it should return an object with the same gear as before.
   * @return return the resulting transmission object.
   */
  ManualTransmission increaseGear();

  /**
   * Decrease the gear by one without changing speed.
   * If the gear cannot be decreased,
   * then it should return an object with the same gear as before.
   * @return return the resulting transmission object.
   */
  ManualTransmission decreaseGear();
}
