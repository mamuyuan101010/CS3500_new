public interface ManualTransmission {
  String getStatus();
  int getSpeed();
  int getGear();
  Object increaseSpeed(int speed);
  Object decreaseSpeed(int speed);
  Object increaseGear();
  Object decreaseGear();
}
