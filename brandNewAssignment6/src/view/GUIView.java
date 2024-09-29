package view;
import java.awt.event.ActionListener;

import controller.Features;

public interface GUIView extends IView {

  void show();
  void setButtonListener(ActionListener listener);

  void addFeatures(Features features);
  void showMessage(String message, Panels panels);
}
