package vehicle;

/**
 *
 */
public class RegularManualTransmission implements ManualTransmission{
  // integer to store the speed increase amount
  private final int speedIncreaseAmount;
  // integer to store the current speed
  private int currentSpeed;

  /**
   * constructor of Regular manual transmission
   */
  public RegularManualTransmission() {
    this.speedIncreaseAmount = 1;
    this.currentSpeed = 0;
  }

  /**
   *
   * @param speed
   */
  private RegularManualTransmission(int speed) {
    if (speed < 0) {
      throw new IllegalArgumentException("Speed cannot be negative.");
    }
  }

  /**
   *
   * @return
   */
  @Override
  public String getStatus() {
    return null;
  }

  /**
   *
   * @return
   */
  @Override
  public int getSpeed() {
    return 0;
  }

  /**
   *
   * @return
   */
  @Override
  public int getGear() {
    return 0;
  }

  /**
   *
   * @return
   */
  @Override
  public Object increaseSpeed() {
    return null;
  }

  /**
   *
   * @return
   */
  @Override
  public Object decreaseSpeed() {
    return null;
  }

  /**
   *
   * @return
   */
  @Override
  public Object increaseGear() {
    return null;
  }

  /**
   *
   * @return
   */
  @Override
  public Object decreaseGear() {
    return null;
  }
}
