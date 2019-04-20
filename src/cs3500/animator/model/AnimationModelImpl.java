package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.Color;
import cs3500.animator.util.Coord;

/**
 * Mutable implementation of model interfaces that represents the data necessary.
 */
public class AnimationModelImpl implements AnimationModel, ReadAnimationModel {

  private Map<String, IShape> shapes;
  private Map<String, ActionsHolder> actions;
  private BoundHolder bounds;

  /**
   * Embedded static class created for the means of containing and sharing data about this models
   * bounds.
   */
  public static class BoundHolder {
    private int boundX;
    private int boundY;
    private int boundHeight;
    private int boundWidth;

    /**
     * Main constructor for a Boundholder.
     *
     * @param boundX      the x value for the bound.
     * @param boundY      th y value for a bound.
     * @param boundWidth  the width for a bound.
     * @param boundHeight the height for a bound.
     */
    public BoundHolder(int boundX, int boundY, int boundWidth, int boundHeight) {
      this.boundX = boundX;
      this.boundY = boundY;
      this.boundHeight = boundHeight;
      this.boundWidth = boundWidth;
    }

    /**
     * Getter for the x value of this models bound.
     *
     * @return int representing said value.
     */
    public int getBoundX() {
      return this.boundX;
    }

    /**
     * Getter for the y value of this models bound.
     *
     * @return int representing said value.
     */
    public int getBoundY() {
      return this.boundY;
    }

    /**
     * Getter for the height value of this models bound.
     *
     * @return int representing said value.
     */
    public int getBoundHeight() {
      return this.boundHeight;
    }

    /**
     * Getter for the width value of this models bound.
     *
     * @return int representing said value.
     */
    public int getBoundWidth() {
      return this.boundWidth;
    }

  }

  /**
   * default constructor that initializes data structures within this model for keeping, shapes and
   * their respective actions.
   */
  public AnimationModelImpl() {
    shapes = new LinkedHashMap<>();
    actions = new HashMap<>();
  }

  public boolean noActions(String id) {
    return this.actions.get(id).isEmpty();
  }


  /**
   * Builder class for constructing read only models using the builder pattern for the purpose of
   * passing to the view.
   */
  public static final class Builder implements AnimationBuilder<ReadAnimationModel> {
    private AnimationModelImpl m;

    /**
     * Default constructor for Builder that initializes a empty mutable model.
     */
    public Builder() {
      m = new AnimationModelImpl();
    }

    /**
     * A method that returns a read only version of the model that has been built. This is read only
     * because after the model is built there is no reason for allowing mutable access to the
     * model.
     *
     * @return a built read only model.
     */
    @Override
    public ReadAnimationModel build() {
      return m;
    }

    /**
     * Sets the bounds of the model being built.
     *
     * @param x      The leftmost x value.
     * @param y      The topmost y value.
     * @param width  The width of the bounding box.
     * @param height The height of the bounding box.
     * @return model builder being built.
     */
    @Override
    public AnimationBuilder<ReadAnimationModel> setBounds(int x, int y, int width, int height) {
      m.setBounds(new BoundHolder(x, y, width, height));
      return this;
    }

    /**
     * Method for adding a shape to this model.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return model builder being built.
     */
    @Override
    public AnimationBuilder<ReadAnimationModel> declareShape(String name, String type) {
      IShape s;
      if (type.equals("rectangle")) {
        s = new Rectangle();
      } else if (type.equals("ellipse")) {
        s = new Oval();
      } else {
        throw new IllegalArgumentException("not a shape");
      }
      m.addObject(s, name);
      return this;
    }

    /**
     * Method for adding a motion actions that allows the user to specify any changes in the shape
     * with the given id's color, position, or size.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return Animation builder being built.
     */
    @Override
    public AnimationBuilder<ReadAnimationModel> addMotion(String name, int t1, int x1,
                                                          int y1, int w1, int h1,
                                                          int r1, int g1, int b1,
                                                          int t2, int x2,
                                                          int y2, int w2, int h2,
                                                          int r2, int g2, int b2) {
      m.move(name, t1, t2, new Coord(x1, y1), new Coord(x2, y2));
      m.changeColor(name, t1, t2, new Color(r1, g1, b1), new Color(r2, g2, b2));
      m.changeSize(name, t1, t2, h1, w1, h2, w2);
      return this;
    }

    /**
     * IGNORE THIS METHOD AS IT IS NOT REQUIRED FOR THIS ASSIGNMENT.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t    The time for this keyframe
     * @param x    The x-position of the shape
     * @param y    The y-position of the shape
     * @param w    The width of the shape
     * @param h    The height of the shape
     * @param r    The red color-value of the shape
     * @param g    The green color-value of the shape
     * @param b    The blue color-value of the shape
     */
    @Override
    public AnimationBuilder<ReadAnimationModel> addKeyframe(String name, int t,
                                                            int x, int y, int w,
                                                            int h, int r,
                                                            int g, int b) {
      return null;
    }
  }

  /**
   * Setter for bounds of this model.
   *
   * @param bounds holds all the necessary data for representing the bounds of this model.
   */
  public void setBounds(BoundHolder bounds) {
    this.bounds = bounds;
  }

  /**
   * Getter for the bounds of this model.
   */
  public BoundHolder getBounds() {
    return this.bounds;
  }

  /**
   * Adds and object to collection of objects that this animation has and animates over. Takes in a
   * int id to ensure each animation object is unique and easily accessible.
   *
   * @param s  object to be animated.
   * @param id unique id to identify the new object.
   */
  @Override
  public void addObject(IShape s, String id) {
    if (!shapes.containsKey(id)) {
      shapes.put(id, s);
      actions.put(id, new ActionsHolder());
    } else {
      throw new IllegalArgumentException("Object already exists");
    }
  }

  /**
   * Commands the object with the given string id to begin moving.
   *
   * @param id        of shape to be moved.
   * @param startTime time of which it must begin its move.
   * @param endTime   time of which the move must end.
   * @param startLoc  location where the move starts.
   * @param endLoc    location where the move ends.
   */
  @Override
  public void move(String id, int startTime, int endTime, Coord startLoc, Coord endLoc) {
    if (shapes.containsKey(id)) {
      if (startTime != endTime) {
        Move m = new Move(startTime, endTime, startLoc, endLoc);
        actions.get(id).addMove(m);
      }
    } else {
      throw new IllegalArgumentException("Object does not exist");
    }
  }

  /**
   * Command an object to start changing color.
   *
   * @param id         of shape to change color.
   * @param startTime  time to start changing color.
   * @param endTime    time to end changing color.
   * @param startColor color to start as.
   * @param endColor   color to end as.
   */
  @Override
  public void changeColor(String id, int startTime, int endTime, Color startColor, Color endColor) {
    if (shapes.containsKey(id)) {
      if (startTime != endTime) {
        ChangeColor c = new ChangeColor(startTime, endTime, startColor, endColor);
        actions.get(id).addColor(c);
      }
    } else {
      throw new IllegalArgumentException("Object does not exist");
    }
  }

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
  @Override
  public void changeSize(String id, int startTime, int endTime, double startHeight,
                         double startWidth, double endHeight, double endWidth) {
    if (shapes.containsKey(id)) {
      if (startTime != endTime) {
        ChangeSize s = new ChangeSize(startTime, endTime, startHeight, startWidth, endHeight, endWidth);
        actions.get(id).addSize(s);
      }
    } else {
      throw new IllegalArgumentException("Object does not exist");
    }
  }

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
  @Override
  public void addKeyFrame(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    if (this.actions.containsKey(name)) {
      this.actions.get(name).actionsKeyFrame(t, x, y, w, h, r, g, b);
    }
  }

  /**
   * Deletes a key frame of a given shape at given time and combined the two actions on either
   * side.
   *
   * @param name of shape to delete key frame of.
   * @param t    time at which to delete the key frame.
   */
  @Override
  public void deleteKeyFrame(String name, int t) {
    if (this.actions.containsKey(name)) {
      this.actions.get(name).deleteKeyFrame(t);
    }
  }

  /**
   * Get the current value of every shape in the animation at given tick.
   *
   * @param tick the given the tick of the animation.
   * @return the list of shapes with the properties appropriate for the given tick.
   */
  @Override
  public List<IShape> currentFrame(int tick) {
    ArrayList<IShape> currentFrame = new ArrayList<>();
    Set<String> set = shapes.keySet();
    for (String s : set) {
      if (actions.get(s).startAndEndTime().entrySet().iterator().hasNext()
              && actions.get(s).startAndEndTime().entrySet().iterator().next().getKey() <= tick) {
        currentFrame.add(currentShape(tick, s));
      }
    }
    return currentFrame;
  }


  /**
   * Get the current value for a specified shape in the animation at a given tick.
   *
   * @param tick the given tick of the animation.
   * @param id   the id corresponding to a shape.
   * @return a shape with all the appropriate properties for the given tick.
   */
  public IShape currentShape(int tick, String id) {
    Coord curLoc;
    Color curColor;
    double curHeight;
    double curWidth;

    ActionsHolder a = actions.get(id);
    IShape s = shapes.get(id);

    curLoc = a.getCurrentLoc(tick);
    curColor = a.getCurrentColor(tick);
    curHeight = a.getCurrentHeight(tick);
    curWidth = a.getCurrentWidth(tick);

    return s.from(curColor, curLoc, curHeight, curWidth);
  }

  /**
   * Constructs a hashmap of shape IDs to another ordered map (treemap) of start time to end time.
   *
   * @return all the intervals for each motion for each shape in the animation.
   */
  public Map<String, Map<Integer, Integer>> startEnd() {
    Map<String, Map<Integer, Integer>> intervals = new HashMap<>();
    Set<String> set = shapes.keySet();
    for (String s : set) {
      intervals.put(s, actions.get(s).startAndEndTime());
    }
    return intervals;
  }

  /**
   * Removes an object from the animation.
   *
   * @param id the id of the object to be removed.
   */
  public void removeObject(String id) {
    if (shapes.containsKey(id)) {
      shapes.remove(id);
      actions.remove(id);
    } else {
      throw new IllegalArgumentException("Object does not exist");
    }
  }

  /**
   * Getter method for the set of ids.
   *
   * @return the set of keys for all the shapes in the animation.
   */
  public Set<String> getIDSet() {
    return this.shapes.keySet();
  }

  /**
   * A getter for the list of key frames of shape with given ID.
   *
   * @param id of shape to get key frames of.
   * @return array of strings representing the key frames.
   */
  public String[] getKeyFrames(String id) {
    return actions.get(id).getKeyFrames();
  }

  /**
   * Similar to getIDSet but will return the set of ids in an array rather than a hashset.
   *
   * @return an array of the key values of the mpa of moves in this model.
   */
  @Override
  public String[] getActionsIdAsList() {
    String[] toReturn = new String[this.actions.size()];
    int i = 0;
    for (String s : actions.keySet()) {
      toReturn[i] = s;
      i++;
    }
    return toReturn;
  }

  /**
   * A getter for the list of moves for the shape with given id in this model.
   *
   * @param id given id to get moves of.
   * @return list of actions for given shape.
   * @throws IllegalArgumentException if id for given shape does not exist in this model.
   */
  public List<AAction> getMoves(String id) throws IllegalArgumentException {
    if (this.actions.containsKey(id)) {
      return this.actions.get(id).getMoves();
    } else {
      throw new IllegalArgumentException("Object does not exist");
    }
  }

  /**
   * A getter for the list of size changes for the shape with given id in this model.
   *
   * @param id given id to get size changes of.
   * @return list of actions for given shape.
   * @throws IllegalArgumentException if id for given shape does not exist in this model.
   */
  public List<AAction> getSizes(String id) throws IllegalArgumentException {
    if (this.actions.containsKey(id)) {
      return this.actions.get(id).getSizes();
    } else {
      throw new IllegalArgumentException("Object does not exist");
    }
  }

  /**
   * A getter for the list of color changes for the shape with given id in this model.
   *
   * @param id given id to get color changes of.
   * @return list of actions for given shape.
   * @throws IllegalArgumentException if id for given shape does not exist in this model.
   */
  public List<AAction> getColors(String id) throws IllegalArgumentException {
    if (this.actions.containsKey(id)) {
      return this.actions.get(id).getColors();
    } else {
      throw new IllegalArgumentException("Object does not exist");
    }
  }

  /**
   * Returns the actionHolder associated with the given shape id.
   * Required for implementing provider view.
   *
   * @param id id of the shape.
   * @return the associated actionHolder.
   */
  public ActionsHolder getActions(String id) {
    return this.actions.get(id);
  }

  /**
   * Returns all the shape type. Required for implementing the provider view.
   *
   * @param name id of the shape.
   * @return the shape with the associated shape id.
   */
  public IShape getShape(String name) {
    return this.shapes.get(name);
  }
}
