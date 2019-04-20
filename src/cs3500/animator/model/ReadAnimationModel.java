package cs3500.animator.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface of a model that does not support mutable access. Only supports observation methods.
 */
public interface ReadAnimationModel {

  /**
   * A getter method for the bounds information of this model.
   *
   * @return boundHolder that hold all the bound information.
   */
  AnimationModelImpl.BoundHolder getBounds();

  /**
   * Gets a list of shapes that represents the state of all objects in this animation at given
   * tick.
   *
   * @param tick time to get list of shapes and their current values.
   * @return a list of shapes that represents the state of all objects in this animation at
   *        giventick
   */
  List<IShape> currentFrame(int tick);

  /**
   * Gets a shape with given id that represents a copy of the shapes value at given point in time.
   *
   * @param tick given point in  time to get the value of shape.
   * @param id   of shape to get value of.
   * @return copy of shape representing shape with given id at given point in time.
   */
  IShape currentShape(int tick, String id);

  /**
   * Gets for each shape id a map of the start and end time of each movement action.
   *
   * @return a map of map that maps a string id to a map of start and end times.
   */
  Map<String, Map<Integer, Integer>> startEnd();

  /**
   * Getter for the set of unique IDs in this models set of shapes.
   *
   * @return set of strings for each id.
   */
  Set<String> getIDSet();

  /**
   * A getter for the list of key frames of shape with given ID.
   * @param id of shape to get key frames of.
   * @return array of strings representing the key frames.
   */
  String[] getKeyFrames(String id);

  /**
   * Similar to getIDSet but will return the set of ids in an array rather than a hashset.
   * @return an array of the key values of the mpa of moves in this model.
   */
  String[] getActionsIdAsList();


  /**
   * Getter for the list of moves of shape with given string id.
   *
   * @param id id of shape to get.
   * @return list of moves.
   */
  List<AAction> getMoves(String id);

  /**
   * Getter for the list of size changes of shape with given string id.
   *
   * @param id id of shape to get.
   * @return list of size changes
   */
  List<AAction> getSizes(String id);

  /**
   * Getter for the list of color changes of shape with given string id.
   *
   * @param id id of shape to get.
   * @return list of color changes.
   */
  List<AAction> getColors(String id);



}
