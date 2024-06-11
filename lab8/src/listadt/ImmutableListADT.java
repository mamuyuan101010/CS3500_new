package listadt;

public interface ImmutableListADT<T> extends CommonListADT<T> {
  MutableListADT<T> getMutableList();
}
