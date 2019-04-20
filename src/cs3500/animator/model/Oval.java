package cs3500.animator.model;

import cs3500.animator.util.Color;
import cs3500.animator.util.Coord;

/**
 * Represents an Oval.
 */
public class Oval extends AbstractShape {

  /**
   * Oval constructor that takes in individual parameters.
   * @param color color for the oval.
   * @param location coordinate for the oval.
   * @param width width of the oval.
   * @param length length of the oval.
   */
  public Oval(Color color, Coord location, double width, double length) {
    super(color, location, width, length);
  }

  /**
   * Oval constructor that generates an empty oval.
   */
  public Oval() {
    super();
  }

  /**
   * Oval constructor that takes in an oval.
   * @param o the oval to be replicated.
   */
  public Oval(Oval o) {
    super(o.getColor(), o.getLocation(), o.getWidth(), o.getHeight());
  }


  @Override
  public IShape from(Color c, Coord loc, double height, double width) {
    return new Oval(c, loc, width, height);
  }

  /**
   * Identifier for an instance of an Oval.
   * @return false.
   */
  @Override
  public boolean isRectangle() {
    return false;
  }

}
