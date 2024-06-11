package listadt;

public interface MutableListADT<T> extends ListADT<T> {
  ImmutableListADT<T> getImmutableList();
}
