public class DurationImpl implements IDuration{
  private int seconds;
  private int minutes;
  private int hours;
  public DurationImpl(int seconds, int minutes, int hours) {
    if (seconds < 0 || minutes < 0 || hours < 0) {
      throw new IllegalArgumentException("cannot be negative");
    }

    this.seconds = seconds;
    this.hours = hours;
    this.minutes = minutes;
  }
  @Override
  public long inSeconds() {
    return 0;
  }

  @Override
  public String asHMS() {
    return null;
  }

  @Override
  public IDuration plus(IDuration other) {
    long tempHours = other.inSeconds() / 3600;
    long maxInt = Integer.MAX_VALUE;
    if ( tempHours > maxInt) {
      throw new IllegalStateException("Other duration cannot converted tto Duration Impl");
    }
    int otherHours = (int) (other.inSeconds() / 3600);
    int otherSeconds = (int) (other.inSeconds() % 3600);
    int otherMinutes = (int) (other.inSeconds() % 3600 / 60 % 60);
    return new DurationImpl();
  }

  @Override
  public int compare(IDuration o) {
    return Long.compare(this.inSeconds(), o.inSeconds());
  }
}
