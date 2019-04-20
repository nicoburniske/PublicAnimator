package cs3500.animator.model;

import cs3500.animator.util.Coord;

/**
 * A class that represents a move action to be performed.
 */
public class Move extends AAction {
  private final Coord startLoc;
  private final Coord endLoc;
  private final double dx;
  private final double dy;

  /**
   * Constructor for creating new moves actions.
   *
   * @param startTime time where the move starts.
   * @param endTime   time where the move ends.
   * @param startLoc  location where the moves starts.
   * @param endLoc    location where the move ends.
   */
  public Move(int startTime, int endTime, Coord startLoc, Coord endLoc) {
    super(startTime, endTime);
    this.startLoc = startLoc;
    this.endLoc = endLoc;
    this.dx = (endLoc.getX() - startLoc.getX()) / (endTime - startTime);
    this.dy = (endLoc.getY() - startLoc.getY()) / (endTime - startTime);
  }

  /**
   * Getter for the start location of this move.
   *
   * @return a copy of Coord that represents this moves start.
   */
  public Coord getStartLoc() {
    return new Coord(this.startLoc);
  }

  /**
   * Getter for the end location of this move.
   *
   * @return a copy of Coord that represents this moves end.
   */
  public Coord getEndLoc() {
    return new Coord(this.endLoc);
  }

  /**
   * Getter for getting change in x pertick value for this move.
   *
   * @return double representing the change per tick.
   */
  public double getDx() {
    return this.dx;
  }

  /**
   * Getter for getting change in y pertick value for this move.
   *
   * @return double representing the change per tick.
   */
  public double getDy() {
    return this.dy;
  }
}
