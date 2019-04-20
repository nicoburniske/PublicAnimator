package cs3500.animator.util;

/**
 * Class to represent RGB values.
 */
public class Color {
  private double red;
  private double green;
  private double blue;

  /**
   * Constructor for building a color with double values.
   *
   * @param red   double value representing the red component.
   * @param green double value representing the green value.
   * @param blue  double value representing the blue value.
   */
  public Color(double red, double green, double blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Constructor for building a color with integer values.
   *
   * @param red   double value representing the red component.
   * @param green double value representing the green value.
   * @param blue  double value representing the blue value.
   */
  public Color(int red, int green, int blue) {
    this.red = red / 255.0;
    this.green = green / 255.0;
    this.blue = blue / 255.0;
  }

  /**
   * getter for double red value.
   *
   * @return the red value as a double.
   */
  public double getRed() {
    return this.red;
  }

  /**
   * getter for double green value.
   *
   * @return the green value as a double.
   */
  public double getGreen() {
    return this.green;
  }

  /**
   * getter for double blue value.
   *
   * @return the blue value as a double.
   */
  public double getBlue() {
    return this.blue;
  }

  /**
   * getter for int red value.
   *
   * @return the red value as an int.
   */
  public int getRedInt() {
    return convertTo255(this.red);
  }

  /**
   * getter for int green value.
   *
   * @return the green value as an int.
   */
  public int getGreenInt() {
    return convertTo255(this.green);
  }

  /**
   * getter for int blue value.
   *
   * @return the blue value as an int.
   */
  public int getBlueInt() {
    return convertTo255(this.blue);
  }

  public java.awt.Color getAWTColor() {
    return new java.awt.Color(this.getRedInt(), this.getGreenInt(), this.getBlueInt());
  }

  /**
   * converts a double color value into an integer color value.
   *
   * @param c the double color value.
   * @return the integer color value.
   */
  private int convertTo255(Double c) {
    return (int) Math.round(c * 255);
  }


}
