package cs3500.animator.provider.model;

import java.awt.Point;
import java.util.ArrayList;

import cs3500.animator.provider.utils.AShape;

/**
 * Interface to represent our Animator Model. An instance of an AnimatorModel would contain all of
 * the necessary information to run an animation in any of our three views. Contains methods that
 * allow for adding/removing shapes to the shapelist, adding and removing motions from shapes, and
 * observing shapes and motions.
 */
public interface AnimatorModel {

  /**
   * Searches list of shapes for given shapes name.
   */
  AShape getShape(String name);


  /**
   * Takes in a shape and adds it to the model's current list of shapes.
   *
   * @param s Given Shape.
   */
  void addShape(AShape s, String name);

  /**
   * Takes in a shape and removes said shape from the model's current list of shapes. If no such
   * shape exists in the list then this method does nothing.
   *
   * @param name Given shape.
   */
  void removeShape(String name);

  /**
   * Obtains a list of all the shapes in the current animation.
   *
   * @return the list of AShape.
   */
  ArrayList<AShape> getShapes();

  /**
   * Gets the width of the window of the animation.
   *
   * @return the width of the window of the animation.
   */
  int getWidth();

  /**
   * Gets the height of the window of the animation.
   *
   * @return the height of the window of the animation.
   */
  int getHeight();

  /**
   * Gets the origin of the window (top left corner).
   *
   * @return the top left corner of a 2d space represented by a point.
   */
  Point getTopLeftCorner();

  /**
   * Adds a keyframe to the given shape.
   *
   * @param name the name of the shape.
   * @param t    the time of the given keyframe.
   * @param x    the x position of the shape.
   * @param y    the y poistion of the shape.
   * @param w    the width of the shape.
   * @param h    the height of the shape.
   * @param r    the red value of the shape.
   * @param g    the green value of the shape.
   * @param b    te blue value of the shape.
   */
  void addKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Deletes the keyframe at the given time for the shape.
   *
   * @param name name of the shape.
   * @param t    the time of the shape.
   */
  void deleteKeyFrame(String name, int t);

}
