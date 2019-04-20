package cs3500.animator.view;

import cs3500.animator.model.IShape;
import cs3500.animator.model.ReadAnimationModel;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * A View implementation that with display animation data as a String representing the state of the
 * data at start and end times of each move action.
 */
public class TextView implements IView {
  private int width;
  private int height;
  private ReadAnimationModel animationModel;
  private Set<String> shapeID;

  /**
   * Constructor that takes in a read only access model and specified width and height.
   *
   * @param w width of animation.
   * @param h height of animation.
   * @param m model for the view.
   */
  public TextView(int w, int h, ReadAnimationModel m) {
    if (m == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.width = w;
    this.height = h;
    this.animationModel = Objects.requireNonNull(m);
    this.shapeID = m.getIDSet();
  }

  /**
   * Constructor for initializing a text view with bound data from model.
   *
   * @param m read only access model.
   */
  public TextView(ReadAnimationModel m) {
    if (m == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.width = m.getBounds().getBoundWidth();
    this.height = m.getBounds().getBoundHeight();
    this.animationModel = Objects.requireNonNull(m);
    this.shapeID = m.getIDSet();
  }

  /**
   * This operation is not supported by this implementation, so throw an exception.
   */
  @Override
  public String toSVG() {
    throw new UnsupportedOperationException("operation not supported");
  }

  /**
   * The Main method for this v
   * iew that returns a string representing a textual view of this
   * animation.
   *
   * @return a String of text representing this animation.
   */
  public String animationState() {
    StringBuilder state = new StringBuilder();
    Map<String, Map<Integer, Integer>> motion = animationModel.startEnd();
    state.append("canvas " + this.width + " " + this.height + '\n');
    state.append("        " + String.format("%5s %5s %5s %5s %5s %6s %6s %6s",
            "T", "X", "Y", "W", "H", "R", "G", "B"));
    state.append("     " + String.format("%5s %5s %5s %5s %5s %6s %6s %6s",
            "T", "X", "Y", "W", "H", "R", "G", "B") + "\n");
    for (String i : shapeID) {
      state.append(objectHistory(i, motion.get(i)));
    }
    return state.toString();
  }

  /**
   * This operation is not supported by this view so throw an exception.
   */
  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("operation not supported");
  }

  /**
   * This operation is not supported by this implementation, so throw an exception.
   */
  @Override
  public void refresh() {
    throw new UnsupportedOperationException("operation not supported");
  }

  /**
   * A helper of animation state that gets all of the text animation information of one shape with
   * given ID.
   *
   * @param id        of shape to get info.
   * @param intervals time intervals of information to get.
   * @return a String of all the text information of shape of given id.
   */
  private String objectHistory(String id, Map<Integer, Integer> intervals) {
    IShape start;
    IShape end;
    int startTime;
    int endTime;

    StringBuilder state = new StringBuilder();
    for (Map.Entry<Integer, Integer> range : intervals.entrySet()) {
      startTime = range.getKey();
      endTime = range.getValue();
      start = animationModel.currentShape(startTime, id);
      end = animationModel.currentShape(endTime, id);
      state.append("Motion " + id + " ");
      state.append(objectInterval(start, startTime));
      state.append("     ");
      state.append(objectInterval(end, endTime));
      state.append("\n");
    }
    state.append("\n\n");
    return state.toString();
  }

  /**
   * A helper method for formatting one line representing one actions completed by given shape, at
   * given start time.
   *
   * @param s    shape to make text line of.
   * @param time start time of line to create.
   * @return one line to of text data of one actions of given shape at given time.
   */
  private String objectInterval(IShape s, int time) {
    StringBuilder state = new StringBuilder();
    state.append(String.format("%5s %5s %5s %5s %5s %6s %6s %6s",
            time, Math.round(s.getLocation().getX())
            , Math.round(s.getLocation().getY()), s.getWidth(), s.getHeight(),
            s.getColor().getRedInt(), s.getColor().getGreenInt(),
            s.getColor().getBlueInt()));

    return state.toString();
  }

}

