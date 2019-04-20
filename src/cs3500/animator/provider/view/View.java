package cs3500.animator.provider.view;

import cs3500.animator.provider.utils.AShape;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 * An interface to represent an Animation View. A View is either a SVGView, TextView, or
 * AnimatorView. An SVGView will represent a SVG format and take in a model, tempo, and output. A
 * TextView represents a text format, and includes a model, tempo, and output. An AnimatorView
 * represents a JSwing animation and includes a model and tempo.
 */
public interface View {

  /**
   * Displays either a SVGView, TextView, AnimatorView, or EditorView. For SVGViews and TextViews
   * this display will depend on the output.
   */
  void display();

  int getTick();

  ViewType getType();

  int getWidth();

  void setWidth(int width);

  int getHeight();

  void setHeight(int height);

  ArrayList<AShape> getShapes();

  void setShapes(ArrayList<AShape> shapes);

  int getTempo();

  void setTempo(int tempo);

  void setListener(ActionListener listener);

  AnimationJPanel getPanel();

  JTextField getTextField();

  Point getTopLeftCorner();

  void setTopLeftCorner(Point topLeftCorner);
}
