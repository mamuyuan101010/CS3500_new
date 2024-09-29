package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by vidojemihajlovikj on 6/11/24.
 */
public interface IView {
  String getData();
  void setData(String data);
  void addViewListener(IViewListener viewListener);
  void requestFocus();
  void setVisible(boolean value);
}
