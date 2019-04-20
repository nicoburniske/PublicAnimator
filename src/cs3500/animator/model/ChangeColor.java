package cs3500.animator.model;

import cs3500.animator.util.Color;

/**
 * Represents an object changing color over a given time frame.
 */
public class ChangeColor extends AAction {
  private final Color fromColor;
  private final Color toColor;
  private final double dRed;
  private final double dGreen;
  private final double dBlue;

  /**
   * Constructor for ChangeColor that initializes the necessary data for a color change operation.
   *
   * @param startTime time where the animations starts.
   * @param endTime   time where the animation ends.
   * @param fromColor color from which to start from.
   * @param toColor   color of to which to change to.
   */
  public ChangeColor(int startTime, int endTime, Color fromColor, Color toColor) {
    super(startTime, endTime);
    this.fromColor = fromColor;
    this.toColor = toColor;
    this.dRed = (toColor.getRed() - fromColor.getRed()) / (endTime - startTime);
    this.dGreen = (toColor.getGreen() - fromColor.getGreen()) / (endTime - startTime);
    this.dBlue = (toColor.getBlue() - fromColor.getBlue()) / (endTime - startTime);
  }

  /**
   * Getter for the from color of this change color action.
   *
   * @return from color to get.
   */
  public Color getFromColor() {
    return new Color(fromColor.getRed(), fromColor.getGreen(), fromColor.getBlue());
  }

  /**
   * Getter for the to color of the change color action.
   *
   * @return to color to get.
   */
  public Color getToColor() {
    return new Color(toColor.getRed(), toColor.getGreen(), toColor.getBlue());
  }

  /**
   * Getter for the change in red per tick value.
   *
   * @return double representing said value.
   */
  public double getdRed() {
    return dRed;
  }

  /**
   * Getter for the change in green per tick value.
   *
   * @return double representing said value.
   */
  public double getdGreen() {
    return dGreen;
  }

  /**
   * Getter for the change in green per tick value.
   *
   * @return double representing said value.
   */
  public double getdBlue() {
    return dBlue;
  }

}
