package cs3500.animator.provider.utils;

import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.ActionsHolder;
import cs3500.animator.util.Coord;
import cs3500.animator.model.IShape;
import cs3500.animator.provider.view.Keyframe;

/**
 * Represents an adapter from an IShape to an AShape. Methods below were obtained from methods
 * called in the provider view.
 */
public class AShapeAdapter implements AShape {
  ActionsHolder actions;
  IShape shape;

  /**
   * In order to fulfill the functionality specified by the methods called in the provider view, we
   * constructed an adapter that contained the shape, as well as its associated ActionHolder.
   */
  public AShapeAdapter(ActionsHolder actions, IShape shape) {
    this.actions = actions;
    this.shape = shape;
  }

  /**
   * Color getter through pass by delegation.
   *
   * @return the color.
   */
  public Color getColor() {
    return this.shape.getColor().getAWTColor();
  }

  /**
   * Type getter through delegation and a conditional.
   *
   * @return the type of the shape.
   */
  public ShapeType getType() {
    if (this.shape.isRectangle()) {
      return ShapeType.RECTANGLE;
    } else {
      return ShapeType.OVAL;
    }
  }

  /**
   * Returns the position of the shape through delegation.
   *
   * @return the position of the shape.
   */
  public Coord getPos() {
    return this.shape.getLocation();
  }

  /**
   * Getter for the width of the shape through delegation.
   *
   * @return the width of the shape.
   */
  public double getWidth() {
    return this.shape.getWidth();
  }

  /**
   * Getter for the height of the shape through delegation.
   *
   * @return the height of the shape.
   */
  public double getHeight() {
    return this.shape.getHeight();
  }

  /**
   * Constructs and returns the list of keyframes associated with the shape.
   *
   * @return the list of keyframes.
   */
  public List<Keyframe> getKeyframes() {
    List<Keyframe> keys = new ArrayList<>();
    for (String s : actions.getKeyFrames()) {
      int time = Integer.parseInt(s);
      Coord curr = actions.getCurrentLoc(time);
      Point p = new Point((int) curr.getX(), (int) curr.getY());
      int width = (int) actions.getCurrentWidth(time);
      int height = (int) actions.getCurrentHeight(time);
      Color color = actions.getCurrentColor(time).getAWTColor();
      keys.add(new Keyframe(time, p, width, height, color));
    }
    return keys;
  }

  /**
   * Returns a shape with the appropriate properties for the specified tick.
   *
   * @param tick represents the time of the given shape.
   * @return the shape a the given tick.
   */
  public AShape getShapeAt(int tick) {
    Coord curLoc;
    cs3500.animator.util.Color curColor;
    double curHeight;
    double curWidth;

      curLoc = actions.getCurrentLoc(tick);
      curColor = actions.getCurrentColor(tick);
      curHeight = actions.getCurrentHeight(tick);
      curWidth = actions.getCurrentWidth(tick);


    return new AShapeAdapter(this.actions, this.shape.from(curColor, curLoc, curHeight, curWidth));
  }

  /**
   * Getter for the delegate IShape.
   * @return the IShape field.
   */
  public IShape getShape() {
    return this.shape;
  }
}
