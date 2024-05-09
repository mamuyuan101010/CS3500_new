public interface IDuration {
  public long inSeconds();
  public String asHMS();
  public IDuration plus(IDuration other);
  int compare(IDuration other);
}
