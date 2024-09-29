package view;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
/**
 * Created by vidojemihajlovikj on 6/11/24.
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
  private final List<Point> clickedPoints;

  public Canvas(int prefferedDimensionX, int prefferedDimensionY, Color color){
    setPreferredSize(new Dimension(prefferedDimensionX, prefferedDimensionY));
    setBackground(color);
    addMouseListener(this);
    addMouseMotionListener(this);
    this.clickedPoints = new ArrayList<>();
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);

    g.setColor(Color.RED);
    g.fillOval(200,200, 50,50);
    g.setColor(Color.GREEN);
    g.fillOval(400,200, 50,50);

    for ( Point p : clickedPoints ){
      g.fillOval((int)p.getX(), (int)p.getY(), 40, 40);
    }


  }

  @Override
  public void mouseClicked(MouseEvent e) {
//    this.clickedPoints.add( e.getPoint() );
//    this.repaint();

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    this.clickedPoints.add( e.getPoint() );
    this.repaint();
  }

  @Override
  public void mouseMoved(MouseEvent e) {

  }
}
