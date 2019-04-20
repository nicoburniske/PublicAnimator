package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cs3500.animator.util.Color;
import cs3500.animator.util.Coord;


/**
 * Represents an object that stores all the intervals and actions for a given object. This class
 * contains tweening used to calculate the current properties of the object.
 */
public class ActionsHolder {

  private ArrayList<AAction> moves;
  private ArrayList<AAction> sizes;
  private ArrayList<AAction> colors;

  /**
   * Constructor that initializes field to be new ArrayLists.
   */
  public ActionsHolder() {
    moves = new ArrayList<>();
    sizes = new ArrayList<>();
    colors = new ArrayList<>();
  }

  public boolean isEmpty() {
    return this.moves.size() == 0;
  }


  /**
   * Adds a move to the given list of moves.
   *
   * @param move to add.
   */
  public void addMove(Move move) {
    if (overLappingInterval(moves, move)) {
      throw new IllegalArgumentException("Action has overlapping interval" +
              " with a previous action of the same type");
    }
    if ((moves.size() == 0)
            || (move.getStartTime() == moves.get(moves.size() - 1).getEndTime())) {
      moves.add(move);
    } else {
      throw new IllegalArgumentException("Start time does not " +
              "match the previous action's end time");
    }
  }

  /**
   * Adds a size action to list of size actions.
   *
   * @param size to add.
   */
  public void addSize(ChangeSize size) {
    if (overLappingInterval(sizes, size)) {
      throw new IllegalArgumentException("Action has overlapping interval with" +
              " a previous action of the same type");
    }
    if ((sizes.size() == 0)
            || (size.getStartTime() == sizes.get(sizes.size() - 1).getEndTime())) {
      sizes.add(size);
    } else {
      throw new IllegalArgumentException("Start time does not" +
              " match the previous action's end time");
    }

  }


  /**
   * Adds a color change action to list of color change actions.
   *
   * @param color to add.
   */
  public void addColor(ChangeColor color) {
    if (overLappingInterval(colors, color)) {
      throw new IllegalArgumentException("Action has overlapping interval " +
              "with a previous action of the same type");
    }
    if ((colors.size() == 0)
            || (color.getStartTime() == colors.get(colors.size() - 1).getEndTime())) {
      colors.add(color);
    } else {
      throw new IllegalArgumentException("Start time does not match" +
              " the previous action's end time");
    }
  }

  /**
   * Determines if any of the AActions in the given list have overlapping intervals with the given
   * action.
   *
   * @param list   the list of AActions to be searched.
   * @param action the individual AAction that will be compared.
   * @return true if there exists an overlap, else otherwise.
   */
  private boolean overLappingInterval(ArrayList<AAction> list, AAction action) {
    for (AAction a : list) {
      if (((a.getStartTime() < action.getStartTime()) && (a.getEndTime() > action.getStartTime()))
              || ((a.getStartTime() < action.getEndTime())
              && (a.getEndTime() > action.getEndTime()))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Calculates the location of the object based on a given tick.
   *
   * @param tick the given tick of the animation.
   * @return the current 2-d coordinate representing the Object's position.
   */
  public Coord getCurrentLoc(int tick) {
    Move m = (Move) this.curAction(this.moves, tick);

    double curX;
    double curY;

    if (tick < m.getStartTime()) {
      curX = m.getStartLoc().getX();
      curY = m.getStartLoc().getY();
    } else if (tick > m.getEndTime()) {
      curX = m.getEndLoc().getX();
      curY = m.getEndLoc().getY();
    } else {
      curX = m.getStartLoc().getX() + m.getDx() * (tick - m.getStartTime());
      curY = m.getStartLoc().getY() + m.getDy() * (tick - m.getStartTime());
    }

    return new Coord(curX, curY);

  }

  /**
   * Calculates the current width of the object based on a given tick.
   *
   * @param tick the given tick of the animation.
   * @return the current width of the shape.
   */
  public double getCurrentWidth(int tick) {

    ChangeSize s = (ChangeSize) this.curAction(sizes, tick);

    double curWidth;

    if (tick < s.getStartTime()) {
      curWidth = s.getStartWidth();
    } else if (tick > s.getEndTime()) {
      curWidth = s.getEndWidth();
    } else {
      curWidth = s.getStartWidth() + s.getdWidth() * (tick - s.getStartTime());
    }
    return curWidth;

  }

  /**
   * Calculates the current height of the object based on a given tick.
   *
   * @param tick the given tick of the animation.
   * @return the current height of the shape.
   */
  public double getCurrentHeight(int tick) {
    ChangeSize s = (ChangeSize) this.curAction(sizes, tick);

    double curHeight;

    if (tick < s.getStartTime()) {
      curHeight = s.getStartHeight();
    } else if (tick > s.getEndTime()) {
      curHeight = s.getEndHeight();
    } else {
      curHeight = s.getStartHeight() + s.getdHeight() * (tick - s.getStartTime());
    }
    return curHeight;

  }

  /**
   * Calculates the current color of the object based on a given tick.
   *
   * @param tick the given tick of the animation.
   * @return the current color of the shape.
   */
  public Color getCurrentColor(int tick) {
    ChangeColor c = (ChangeColor) this.curAction(colors, tick);

    double curRed;
    double curGreen;
    double curBlue;

    if (tick < c.getStartTime()) {
      return c.getFromColor();
    } else if (tick > c.getEndTime()) {
      return c.getToColor();
    } else {
      curRed = c.getFromColor().getRed() + c.getdRed() * (tick - c.getStartTime());
      curGreen = c.getFromColor().getGreen() + c.getdGreen() * (tick - c.getStartTime());
      curBlue = c.getFromColor().getBlue() + c.getdBlue() * (tick - c.getStartTime());
    }

    return new Color(curRed, curGreen, curBlue);

  }

  /**
   * Constructs a map of start time to end time for each action contained in the arraylist of
   * moves.
   *
   * @return a map that pairs starting time to end time for each move action.
   */
  public Map<Integer, Integer> startAndEndTime() {
    Map<Integer, Integer> t = new TreeMap<>();
    for (AAction a : moves) {
      t.put(a.getStartTime(), a.getEndTime());
    }
    return t;
  }

  /**
   * Calculates the current interval in the list of actions.
   *
   * @param a    the given ArrayList of action to be searched.
   * @param tick the current tick of the animation.
   * @return the action corresponding to the given tick.
   */
  private AAction curAction(ArrayList<AAction> a, int tick) {
    if (a.size() == 0) {
      throw new IllegalArgumentException("Given list of actions is empty");
    }
    for (AAction ac : a) {
      if (tick >= ac.getStartTime() && tick <= ac.getEndTime()) {
        return ac;
      }
    }
    return a.get(a.size() - 1); //instead return the last one in the list.
  }

  /**
   * Getter method for the list of moves.
   *
   * @return the list of moves.
   */
  public List<AAction> getMoves() {
    return this.moves;
  }

  /**
   * Getter method for the list of ChangeSize.
   *
   * @return the list of ChangeSize.
   */
  public List<AAction> getSizes() {
    return (ArrayList) this.sizes.clone();
  }

  /**
   * Getter method for the list of ChangeColor.
   *
   * @return the list of ChangeColor.
   */
  public List<AAction> getColors() {
    return this.colors;
  }

  /**
   *  Getter method for getting each of the KeyFrame Times.
   * @return an array of String that contains all the keyFrame times.
   */
  public String[] getKeyFrames() {
    String[] keyframes;
    if (moves.size() == 0) {
      keyframes = new String[0];
    } else {
      keyframes = new String[(moves.size()) + 1];
      int i = 0;
      for (AAction a : moves) {
        keyframes[i] = (String.valueOf(a.getStartTime()));
        i++;
        if (moves.indexOf(a) == moves.size() - 1) {
          keyframes[i] = (String.valueOf(a.getEndTime()));
        }
      }
    }
    return keyframes;
  }

  /**
   * A method for adding a key frame with specified values to this animation. This method will
   * mutate the model and tween any changes between the new key frame.
   *
   * @param t time to add at.
   * @param x posn of shape at new key frame.
   * @param y posn of shape at new key frame.
   * @param w width of shape at new key frame.
   * @param h height of shape at new key frame.
   * @param r red of shape at new key frame.
   * @param g green of shape at new key frame.
   * @param b blue of shape at new key frame.
   */
  public void actionsKeyFrame(int t, int x, int y, int w, int h, int r, int g, int b) {
    boolean alreadyExists = false;
    for (AAction m : moves) {
      if (m.getStartTime() == t || m.getEndTime() == t) {
        alreadyExists = true;
      }
    }
    if (!alreadyExists) {
      moveKeyFrame(t, x, y);
      sizeKeyFrame(t, w, h);
      colorKeyFrame(t, r, g, b);
    }
  }

  /**
   * Helper method of actions key frame that mutates the list of actions moves to add a key frame.
   *
   * @param t time to insert new key frame.
   * @param x x of shape at new key frame.
   * @param y y of shape at new key frame.
   */
  private void moveKeyFrame(int t, int x, int y) {
    if (moves.size() == 0) {
      this.moves.add(new Move(t, t, new Coord(x, y), new Coord(x, y)));
    } else if ((t > this.moves.get(this.moves.size() - 1).getEndTime())) {
      Move curMove = (Move) curAction(this.moves, t);
      this.moves.add(new Move(curMove.getStartTime(), t, curMove.getEndLoc(), new Coord(x, y)));
    } else if ((t < this.moves.get(0).getStartTime())) {
      Move curMove = (Move) this.moves.get(0);
      this.moves.add(new Move(t, curMove.getEndTime(), new Coord(x, y), curMove.getStartLoc()));
    } else {
      Move curMove = (Move) curAction(this.moves, t);
      this.moves.add(new Move(curMove.getStartTime(), t, curMove.getStartLoc(), new Coord(x, y)));
      this.moves.add(new Move(t, curMove.getEndTime(), new Coord(x, y), curMove.getEndLoc()));
      this.moves.remove(curMove);
    }
    Collections.sort(this.moves, Comparator.comparingInt(m -> m.getStartTime()));
  }

  /**
   * Helper method of actions key frame that mutates the list of actions size changes to add a key
   * frame.
   *
   * @param t time to insert new key frame.
   * @param w width of shape at new key frame.
   * @param h height of shape at new key frame.
   */
  private void sizeKeyFrame(int t, int w, int h) {
    if (sizes.size() == 0) {
      this.sizes.add(new ChangeSize(t, t, h, w, h, w));
    } else if ((t > this.sizes.get(this.sizes.size() - 1).getEndTime())) {
      ChangeSize curSize = (ChangeSize) curAction(this.sizes, t);
      this.sizes.add(new ChangeSize(curSize.getStartTime(),
              t, curSize.getEndHeight(), curSize.getEndWidth(), h, w));
    } else if ((t < this.sizes.get(0).getStartTime())) {
      ChangeSize curSize = (ChangeSize) this.sizes.get(0);
      this.sizes.add(new ChangeSize(t, curSize.getStartTime(),
              h, w, curSize.getStartHeight(), curSize.getStartWidth()));
    } else {
      ChangeSize curSize = (ChangeSize) curAction(this.sizes, t);
      this.sizes.add(new ChangeSize(curSize.getStartTime(),
              t, curSize.getStartHeight(), curSize.getStartWidth(), h, w));
      this.sizes.add(new ChangeSize(t, curSize.getEndTime(),
              h, w, curSize.getEndHeight(), curSize.getEndWidth()));
      this.sizes.remove(curSize);
    }
    Collections.sort(this.sizes, Comparator.comparingInt(s -> s.getStartTime()));
  }

  /**
   * Helper method of color key frame that mutates the list of actions size changes to add a key
   * frame.
   *
   * @param t time to insert new key frame.
   * @param r red of shape at new key frame.
   * @param g green of shape at new key frame.
   * @param b blue of shape at new key frame.
   */
  private void colorKeyFrame(int t, int r, int g, int b) {
    if (this.colors.size() == 0) {
      this.colors.add(new ChangeColor(t, t, new Color(r, g, b),
              new Color(r, g, b)));
    } else if (t > this.colors.get(this.colors.size() - 1).getEndTime()) {
      ChangeColor curColor = (ChangeColor) curAction(this.colors, t);
      this.colors.add(new ChangeColor(curColor.getEndTime(),
              t, curColor.getToColor(), new Color(r, g, b)));
    } else if (t < this.colors.get(0).getStartTime()) {
      ChangeColor curColor = (ChangeColor)this.colors.get(0);
      this.colors.add(new ChangeColor(t, curColor.getStartTime(),
              new Color(r,g,b), curColor.getFromColor()));
    } else {
      ChangeColor curColor = (ChangeColor) curAction(this.colors, t);
      this.colors.add(new ChangeColor(curColor.getStartTime(),
              t, curColor.getFromColor(), new Color(r, g, b)));
      this.colors.add(new ChangeColor(t, curColor.getEndTime(),
              new Color(r, g, b), curColor.getToColor()));
      this.colors.remove(curColor);
      Collections.sort(this.colors, Comparator.comparingInt(s -> s.getStartTime()));
    }
  }

  /**
   * Deletes a key frame from each of the list of actions at given time.
   *
   * @param t tine at which to delete key frame.
   */
  public void deleteKeyFrame(int t) {
    moveDeleteKeyFrame(t);
    sizeDeleteKeyFrame(t);
    colorDeleteKeyFrame(t);
  }

  /**
   * Deletes key frame at given time from list of color actions.
   *
   * @param t time at which to delete key frame.
   */
  private void colorDeleteKeyFrame(int t) {
    ChangeColor curColor = (ChangeColor) curAction(this.colors, t);

    if (colors.indexOf(curColor) == 0) {
      colors.remove(curColor);
    } else if (colors.indexOf(curColor) == colors.size() - 1) {
      colors.remove(curColor);
    } else {
      ChangeColor nextColor = (ChangeColor) this.colors.get(this.colors.indexOf(curColor) + 1);

      ChangeColor newColor = new ChangeColor(curColor.getStartTime(), nextColor.getEndTime(),
              curColor.getFromColor(), nextColor.getToColor());
      int index = this.colors.indexOf(curColor);
      this.colors.add(index, newColor);

      this.colors.remove(curColor);
      this.colors.remove(nextColor);
    }
  }

  /**
   * Deletes key frame at given time from list of size actions.
   *
   * @param t time at which to delete key frame.
   */
  private void sizeDeleteKeyFrame(int t) {
    ChangeSize curSize = (ChangeSize) curAction(this.sizes, t);

    if (sizes.indexOf(curSize) == 0) {
      sizes.remove(curSize);
    } else if (sizes.indexOf(curSize) == sizes.size() - 1) {
      sizes.remove(curSize);
    } else {
      ChangeSize nextSize = (ChangeSize) this.sizes.get(this.sizes.indexOf(curSize) + 1);

      ChangeSize newSize = new ChangeSize(curSize.getStartTime(), nextSize.getEndTime(),
              curSize.getStartHeight(), curSize.getStartWidth(),
              nextSize.getEndHeight(), nextSize.getEndWidth());

      int index = this.sizes.indexOf(curSize);
      this.sizes.add(index, newSize);

      this.sizes.remove(curSize);
      this.sizes.remove(nextSize);
    }
  }

  /**
   * Deletes key frame at given time from list of move actions.
   *
   * @param t time at which to delete key frame.
   */
  private void moveDeleteKeyFrame(int t) {
    Move curMove = (Move) curAction(this.moves, t);
    if (moves.indexOf(curMove) == 0) {
      moves.remove(curMove);
    } else if (moves.indexOf(curMove) == moves.size() - 1) {
      moves.remove(curMove);
    } else {
      Move nextMove = (Move) this.moves.get(this.moves.indexOf(curMove) + 1);

      Move newMove = new Move(curMove.getStartTime(), nextMove.getEndTime(),
              curMove.getStartLoc(), nextMove.getEndLoc());

      int index = this.moves.indexOf(curMove);
      this.moves.add(index, newMove);

      this.moves.remove(curMove);
      this.moves.remove(nextMove);
    }
  }
}
