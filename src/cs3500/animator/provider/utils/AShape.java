package cs3500.animator.provider.utils;

import java.util.List;

import cs3500.animator.util.Coord;
import cs3500.animator.model.IShape;
import cs3500.animator.provider.view.Keyframe;

import java.awt.Color;

/**
 * Represents an adaptor interface for the delegate IShape class to the AShape interface type.
 * Methods below were obtained from methods called in the provider view.
 */
public interface AShape {

  /**
   * Getter for the color of the shape.
   *
   * @return the color of the shape.
   */
  Color getColor();

  /**
   * Gets the enum type tagged class.
   */
  ShapeType getType();

  /**
   * Gets current Position of the shape.
   *
   * @return the current position as a Coord.
   */
  Coord getPos();

  /**
   * Gets the width of the shape.
   *
   * @return the width of the shape.
   */
  double getWidth();

  /**
   * Gets the height of the shape.
   *
   * @return the height of the shape.
   */
  double getHeight();

  /**
   * Gets all the keyframes associated with the given shape.
   *
   * @return the List of Keyframes that is associated with the shape.
   */
  List<Keyframe> getKeyframes();

  /**
   * Returns the shape with all the characteristics appropriate to the given tick.
   *
   * @param tick represents the time of the given shape.
   * @return the shape at the given tick.
   */
  AShape getShapeAt(int tick);

  /**
   * Returns the delegate field IShape.
   */
  IShape getShape();
}
