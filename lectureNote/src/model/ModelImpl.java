package model;

import java.util.Objects;

/**
 * Created by vidojemihajlovikj on 6/10/24.
 */
public class ModelImpl implements IModel {
  private String data;

  public ModelImpl(){
    this.data = "";
  }

  @Override
  public String getData() {
    return this.data.toUpperCase();
  }

  @Override
  public void setData(String data) {
    this.data = Objects.requireNonNull(data);
  }
}
