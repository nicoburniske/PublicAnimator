package cs3500.animator.util;

import java.util.Objects;

/**
 * Represents a 2 dimensional coordinate.
 */
public class Coord {
  private double x;
  private double y;

  /**
   * Coordinate constructor taking in two double.
   *
   * @param x represents x coordinate.
   * @param y represents y coordinate.
   */
  public Coord(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Coordiate constructor taking in a Coordinate.
   *
   * @param c given coordinate.
   */
  public Coord(Coord c) {
    this.x = c.getX();
    this.y = c.getY();
  }

  /**
   * Gets x value.
   *
   * @return the x value.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets Y value.
   *
   * @return the Y value.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Sets x value.
   *
   * @param x the x value.
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Sets y value.
   *
   * @param y the y value.
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Determines equality.
   *
   * @param o object being compared to a Coord.
   * @return true if equal.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (!(o instanceof Coord)) {
      return false;
    } else {
      return (Math.abs(this.x - ((Coord) o).x) < 0.01 &&
              Math.abs(this.y - ((Coord) o).y) < 0.01);
    }

  }

  /**
   * Overriden hashCode.
   *
   * @return the hasCode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(x + y);
  }

}