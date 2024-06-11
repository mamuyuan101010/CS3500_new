package listadt;

import java.util.function.Function;

public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {
  ListADT<T> list;

  private ImmutableListADTImpl(ListADT<T> list) {
    this.list = list;
  }

  @Override
  public MutableListADT<T> getMutableList() {
    return null;
  }

  @Override
  public void addFront(T b) {

  }

  @Override
  public void addBack(T b) {

  }

  @Override
  public void add(int index, T b) {

  }

  @Override
  public void remove(T b) {

  }

  @Override
  public <R> ImmutableListADTImpl map(Function<T, R> converter) {
    ListADT<R> newList = list.map(converter);
    ImmutableListADTBuilder<R> builder = new ImmutableListADTBuilder<>();
    for (int i = 0; i < newList.getSize(); i ++) {
      builder.addBack(newList.get(i));
    }
    return builder.build();
  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    return null;
  }

  private void addBack() {

  }

  private static class ImmutableListADTBuilder<R> {
    private final ListADT<R> list;

    private ImmutableListADTBuilder() {
      this.list = new ListADTImpl<>();
    }

    void addBack(R item) {
      list.addBack(item);
    }

    ImmutableListADTImpl<R> build() {
      return new ImmutableListADTImpl<R>(list);
    }
  }

}
