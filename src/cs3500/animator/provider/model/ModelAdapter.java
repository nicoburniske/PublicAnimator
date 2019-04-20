package cs3500.animator.provider.model;

import java.awt.Point;
import java.util.ArrayList;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.provider.utils.AShape;
import cs3500.animator.provider.utils.AShapeAdapter;

/**
 * Adapter for AnimationModelImpl to the model specified by the methods called on in the provider
 * view.
 */
public class ModelAdapter implements AnimatorModel {
  AnimationModel m;

  /**
   * Constructor that takes in an AnimationModel.
   *
   * @param m AnimationModel. Methods called by delegation onto model 'm'.
   */
  public ModelAdapter(AnimationModel m) {
    this.m = m;
  }

  /**
   * Creates the appropriate AShapeAdapter.
   *
   * @param name the name of the shape in the model.
   * @return the AShapeAdapter.
   */
  public AShape getShape(String name) {
    return new AShapeAdapter(m.getActions(name), m.getShape(name));
  }

  /**
   * Adds the given shape to the model. Uses pass through delegation.
   *
   * @param s    Given Shape.
   * @param name the name of the shape to be added to the model.
   */
  public void addShape(AShape s, String name) {
    this.m.addObject(s.getShape(), name);
  }

  /**
   * Removes the given shape from the model.
   *
   * @param name the name of the shape.
   */
  public void removeShape(String name) {
    this.m.removeObject(name);
  }

  /**
   * Gets the list of shapes and converts them to AShapeAdapters.
   *
   * @return the list of AShapes.
   */
  public ArrayList<AShape> getShapes() {
    ArrayList<AShape> shapes = new ArrayList<AShape>();
    for (String s : m.getIDSet()) {
      if (!this.m.getActions(s).isEmpty()){
        shapes.add(this.getShape(s));
      }
    }
    return shapes;
  }

  /**
   * Getter for the width of the window.
   *
   * @return the width of the window
   */
  public int getWidth() {
    return m.getBounds().getBoundWidth();
  }

  /**
   * Getter for the height of the window.
   *
   * @return the height of the window
   */
  public int getHeight() {
    return m.getBounds().getBoundHeight();
  }

  /**
   * Getter for the origin of the window.
   *
   * @return the origin of the window as a Point.
   */
  public Point getTopLeftCorner() {
    Point p;
    p = new Point(m.getBounds().getBoundX(), m.getBounds().getBoundY());
    return p;
  }

  /**
   * Adds the given Keyframe to the model.
   *
   * @param name the name of the shape.
   * @param t    the time of the given keyframe.
   * @param x    the x position of the shape.
   * @param y    the y poistion of the shape.
   * @param w    the width of the shape.
   * @param h    the height of the shape.
   * @param r    the red value of the shape.
   * @param g    the green value of the shape.
   * @param b    te blue value of the shape.
   */
  public void addKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    m.addKeyFrame(name, t, x, y, w, h, r, g, b);
  }

  /**
   * Deletes the given keyframe from the model.
   *
   * @param name name of the shape.
   * @param t    the time of the shape.
   */
  public void deleteKeyFrame(String name, int t) {
    m.deleteKeyFrame(name, t);
  }

}
