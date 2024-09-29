package view;
import java.awt.event.ActionListener;

import controller.Features;

public interface GUIView extends IView {

  void show();

  void addFeatures(Features features);
}
