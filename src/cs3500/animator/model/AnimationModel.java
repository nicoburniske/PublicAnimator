package cs3500.animator.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cs3500.animator.util.Color;
import cs3500.animator.util.Coord;

/**
 * Represents a simple animation.
 */
public interface AnimationModel {

  /**
   * Setter for bounds of this model.
   *
   * @param bounds holds all the necessary data for representing the bounds of this model.
   */
  void setBounds(AnimationModelImpl.BoundHolder bounds);

  /**
   * Getter for the bounds of this model.
   */
  AnimationModelImpl.BoundHolder getBounds();

  /**
   * Adds and object to collection of objects that this animation has and animates over. Takes in a
   * int id to ensure each animation object is unique and easily accessible.
   *
   * @param s  object to be animated.
   * @param id unique id to identify the new object.
   */
  void addObject(IShape s, String id);

  /**
   * Commands the object with the given int id to begin moving.
   *
   * @param id        of shape to be moved.
   * @param startTime time of which it must begin its move.
   * @param endTime   time of which the move must end.
   * @param startLoc  location where the move starts.
   * @param endLoc    location where the move ends.
   */
  void move(String id, int startTime, int endTime, Coord startLoc, Coord endLoc);

  /**
   * Command an object to start changing color.
   *
   * @param id         of shape to change color.
   * @param startTime  time to start changing color.
   * @param endTime    time to end changing color.
   * @param startColor color to start as.
   * @param endColor   color to end as.
   */
  void changeColor(String id, int startTime, int endTime, Color startColor, Color endColor);

  /**
   * Command an object to change size.
   *
   * @param id          of shape who must change size.
   * @param startTime   time to start changing size.
   * @param endTime     time to send changing size.
   * @param startHeight height to start.
   * @param startWidth  width to start.
   * @param endHeight   height to end.
   * @param endWidth    width to end.
   */
  void changeSize(String id, int startTime, int endTime, double startHeight, double startWidth,
                  double endHeight, double endWidth);

  /**
   * Adds a key frame to this model and tween changes on either side of new key frame.
   *
   * @param name id of what shape to add key frame to.
   * @param t    time at which to add a key frame.
   * @param x    coord of shape to add at new key frame.
   * @param y    coord of shape to add at new key frame.
   * @param w    width of shape at new key frame.
   * @param h    height of shape at new key frame.
   * @param r    red of shape at new key frame.
   * @param g    green of shape at new key frame.
   * @param b    blue of shape at new key frame.
   */
  void addKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b);

  /**
   * Deletes a key frame of a given shape at given time and combined the two actions on either
   * side.
   *
   * @param name of shape to delete key frame of.
   * @param t    time at which to delete the key frame.
   */
  void deleteKeyFrame(String name, int t);

  /**
   * Get the current value of every shape in the animation at given tick.
   *
   * @param tick the given the tick of the animation.
   * @return the list of shapes with the properties appropriate for the given tick.
   */
  List<IShape> currentFrame(int tick);

  /**
   * Get the current value for a specified shape in the animation at a given tick.
   *
   * @param tick the given tick of the animation.
   * @param id   the id corresponding to a shape.
   * @return a shape with all the appropriate properties for the given tick.
   */
  IShape currentShape(int tick, String id);


  /**
   * Constructs a hashmap of shape IDs to another ordered map (treemap) of start time to end time.
   *
   * @return all the intervals for each motion for each shape in the animation.
   */
  Map<String, Map<Integer, Integer>> startEnd();

  boolean noActions(String id);

  /**
   * Removes an object from the animation.
   *
   * @param id the id of the object to be removed.
   */
  void removeObject(String id);


  /**
   * Getter method for the set of ids.
   *
   * @return the set of keys for all the shapes in the animation.
   */
  Set<String> getIDSet();

  /**
   * A getter for the list of key frames of shape with given ID.
   *
   * @param id of shape to get key frames of.
   * @return array of strings representing the key frames.
   */
  String[] getKeyFrames(String id);

  /**
   * Similar to getIDSet but will return the set of ids in an array rather than a hashset.
   *
   * @return an array of the key values of the mpa of moves in this model.
   */
  String[] getActionsIdAsList();

  /**
   * A getter for the list of moves for the shape with given id in this model.
   *
   * @param id given id to get moves of.
   * @return list of actions for given shape.
   * @throws IllegalArgumentException if id for given shape does not exist in this model.
   */
  List<AAction> getMoves(String id) throws IllegalArgumentException;

  /**
   * A getter for the list of size changes for the shape with given id in this model.
   *
   * @param id given id to get size changes of.
   * @return list of actions for given shape.
   * @throws IllegalArgumentException if id for given shape does not exist in this model.
   */
  List<AAction> getSizes(String id) throws IllegalArgumentException;

  /**
   * A getter for the list of color changes for the shape with given id in this model.
   *
   * @param id given id to get color changes of.
   * @return list of actions for given shape.
   * @throws IllegalArgumentException if id for given shape does not exist in this model.
   */
  List<AAction> getColors(String id) throws IllegalArgumentException;

  /**
   * Returns the actionHolder associated with the given shape id.
   * Required for implementing provider view.
   *
   * @param id id of the shape.
   * @return the associated actionHolder.
   */
  ActionsHolder getActions(String id);

  /**
   * Returns all the shape type. Required for implementing the provider view.
   * @param name id of the shape.
   * @return the shape with the associated shape id.
   */
  IShape getShape(String name);
}
