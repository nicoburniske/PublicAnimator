package cs3500.animator.model;

import cs3500.animator.util.Color;
import cs3500.animator.util.Coord;

/**
 * Represents a rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor that takes in all parameters for a rectangle.
   * @param color color of rectangle.
   * @param location locations of rectangle.
   * @param width of rectangle.
   * @param length of rectangle.
   */
  public Rectangle(Color color, Coord location, double width, double length) {
    super(color, location, width, length);
  }

  /**
   * Default constructor for rectangle.
   */
  public Rectangle() {
    super();
  }

  /**
   * Rectangle constructor taking in a rectangle.
   * @param o takes in another rectangle for YOUR convenience!!!
   */
  public Rectangle(Rectangle o) {
    super(o.getColor(), o.getLocation(), o.getWidth(), o.getHeight());
  }

  /**
   * Factory method for creating rectangles.
   * @param c of new rectangle.
   * @param loc of new rectangle.
   * @param height of new rectangle.
   * @param width of new rectangle.
   * @return new Instance of rectangle with given parameters.
   */
  @Override
  public IShape from(Color c, Coord loc, double height, double width) {
    return new Rectangle(c, loc, width, height);
  }

  /**
   * Identifier for an instance of a rectangle.
   * @return true.
   */
  @Override
  public boolean isRectangle() {
    return true;
  }


}
