package cs3500.animator.provider.view;

import java.awt.Color;
import java.awt.Point;

/**
 * To represent a keyframe.
  */


public class Keyframe {

  private int time;
  private Point pos;
  private int width;
  private int height;
  private Color color;

  /**
   *    To create an instance of a keyframe.
   */


  public Keyframe(int time, Point pos, int width, int height, Color color) {
    this.time = time;
    this.pos = pos;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  public boolean hasDimensionChanged(Keyframe that) {
    return hasWidthChanged(that) || hasHeightChanged(that);
  }

  public boolean hasPositionChanged(Keyframe that) {
    return hasXChanged(that) || hasYChanged(that);
  }

  public boolean hasColorChanged(Keyframe that) {
    return this.color != that.color;
  }

  public boolean hasXChanged(Keyframe that) {
    return this.pos.x != that.pos.x;
  }

  public boolean hasYChanged(Keyframe that) {
    return this.pos.y != that.pos.y;
  }

  public boolean hasWidthChanged(Keyframe that) {
    return this.width != that.width;
  }

  public boolean hasHeightChanged(Keyframe that) {
    return this.height != that.height;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public Point getPos() {
    return pos;
  }

  public void setPos(Point pos) {
    this.pos = pos;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

}
