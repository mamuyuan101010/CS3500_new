package vehicle;

public interface ManualTransmission {
  String getStatus();
  int getSpeed();
  int getGear();
  Object increaseSpeed();
  Object decreaseSpeed();
  Object increaseGear();
  Object decreaseGear();
}
