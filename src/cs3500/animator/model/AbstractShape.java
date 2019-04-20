package cs3500.animator.model;

import cs3500.animator.util.Color;
import cs3500.animator.util.Coord;

/**
 * Represents a shape.
 */
public abstract class AbstractShape implements IShape {
  private Coord location;
  private Color color;
  private double height;
  private double width;

  /**
   * Constructor for abstract class Shape.
   *
   * @param color    the initial color for the object.
   * @param location the initial location for the object.
   * @param width    the initial width for the object.
   * @param height   the initial height for the object.
   */
  public AbstractShape(Color color, Coord location, double width, double height) {
    if (location == null || color == null) {
      throw new IllegalArgumentException("Location and color cannot be null.");
    } else {
      this.color = color;
      this.location = location;
      this.width = width;
      this.height = height;
    }
  }

  /**
   * Empty constructor to initialize an empty shape. Acts as a placeholder upon shape
   * initialization.
   */
  public AbstractShape() {
    this.location = null;
    this.color = null;
    this.height = -1;
    this.width = -1;
  }


  /**
   * Color getter.
   *
   * @return the object's current color.
   */
  public Color getColor() {
    return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  /**
   * Coord getter.
   *
   * @return the object's current position.
   */
  public Coord getLocation() {
    return new Coord(this.location);
  }

  /**
   * height getter.
   *
   * @return the object's current height dimension.
   */
  public double getHeight() {
    return height;
  }

  /**
   * Width getter.
   *
   * @return the object's current width dimension.
   */
  public double getWidth() {
    return width;
  }

  /**
   * Factory method for returning a copy of a given shape.
   */
  public abstract IShape from(Color c, Coord loc, double height, double width);

  /**
   * Identifier for an instance of a shape.
   *
   * @return true if the shape is a rectangle, and false otherwise.
   */
  public abstract boolean isRectangle();
}
