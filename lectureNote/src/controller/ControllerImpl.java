package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import javax.swing.text.View;

import model.IModel;
import view.IView;
import view.IViewListener;
import view.MyView;

/**
 * Created by vidojemihajlovikj on 6/10/24.
 */
public class ControllerImpl implements IController, IViewListener {
  private final IModel model;
  private final IView view;
  public ControllerImpl(IModel model, IView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.addViewListener(this);
  }

  @Override
  public void go() {
    view.setVisible(true);
  }
  //public void handleGetDataForStockAndDate(String ticker, String date){
  /*
      IStock stock = this.model.getStock(ticker);
      double price = stock.getPrice(date);
      view.displayStock(ticker, price);
   */
  public void handleSetData(){
    this.model.setData( this.view.getData() );
    this.view.requestFocus();
  }

  public void handleGetData(){
    this.view.setData( model.getData() );
    this.view.requestFocus();
  }
}
