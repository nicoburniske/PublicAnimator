package cs3500.animator.view;

/**
 * A interface that represents a view, a way of visualizing and producing data of an animation.
 */
public interface IView {
  /**
   * A method for returning a String that represents an animation in SVG format.
   * @return a string in SVG format of all the data in an animation.
   */
  String toSVG();

  /**
   * A method for creating an textual representation of an animation.
   * @return a String representing a textual representation of an animation.
   */
  String animationState();

  /**
   * A method for making an animation visible if this the implementation supports,
   * graphical views.
   */
  void makeVisible();

  /**
   * Refreshes this view to the next frame if operation is supported.
   */
  void refresh();

}
