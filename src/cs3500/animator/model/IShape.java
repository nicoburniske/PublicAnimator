package cs3500.animator.model;

import cs3500.animator.util.Color;
import cs3500.animator.util.Coord;

/**
 * Interface representing a shape to be animated.
 */
public interface IShape {
  /**
   * Coord getter.
   *
   * @return the object's current position.
   */
  Coord getLocation();

  /**
   * Color getter.
   *
   * @return the object's current color.
   */
  Color getColor();

  /**
   * Height getter.
   *
   * @return the height of the given shape.
   */
  double getHeight();

  /**
   * Width getter.
   *
   * @return the width of the given shape.
   */
  double getWidth();

  /**
   * factory method for constructing shapes of the same type.
   * @param c color of new shape.
   * @param loc location of new shape.
   * @param height height of new shape.
   * @param width width of new shape.
   * @return IShape of same type constructed with given values.
   */
  IShape from(Color c, Coord loc, double height, double width);

  /**
   * Method for determining whether this IShape is a rectangle.
   * @return boolean true if it is indeed a rectangle.
   */
  boolean isRectangle();

}
