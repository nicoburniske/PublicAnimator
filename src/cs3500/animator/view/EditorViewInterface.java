package cs3500.animator.view;

import javax.swing.event.ChangeListener;

/**
 * Public facing functionality of an editable animation.
 */
public interface EditorViewInterface extends IView {

  /**
   * Pauses the current animation in place.
   */
  void pause();

  /**
   * Resumes the current animation.
   */
  void play();

  /**
   * Plays the current animation in reverse from this point in time.
   */
  void rewind();

  /**
   * Restarts this animation from the beginning.
   */
  void restart();

  /**
   * Increases the speed at which this animation is played.
   */
  void speedUp();

  /**
   * Slows down the speed at which this animation is played.
   */
  void slowDown();

  /**
   * Repaints the current objects in this animation.
   */
  void refresh();

  /**
   * Getter for the text in the user inputted file path.
   *
   * @return a string that is the user input.
   */
  String getReadFile();

  /**
   * Sets the visibility of this view depending on boolean parameter.
   *
   * @param b set visibility to true or false.
   */
  void visible(Boolean b);

  /**
   * Disposes this view.
   */
  void disp();

  /**
   * Getter for file type to be saved by user.
   *
   * @return String which is the type of file extension.
   */
  String getOutType();

  /**
   * Getter for the selected shape ID.
   *
   * @return selected shapeID.
   */
  String getUserId();

  /**
   * Getter for the user inputted x value.
   *
   * @return inputted x value.
   */
  String getUserX();

  /**
   * Getter for the user inputted y value.
   *
   * @return inputted y value.
   */
  String getUserY();

  /**
   * Getter for the user inputted width value.
   *
   * @return inputted width value.
   */
  String getUserW();

  /**
   * Getter for the user inputted height value.
   *
   * @return inputted height value.
   */
  String getUserHeight();

  /**
   * Getter for the user inputted red value.
   *
   * @return inputted red value.
   */
  String getUserR();

  /**
   * Getter for the user inputted green value.
   *
   * @return inputted green value.
   */
  String getUserG();

  /**
   * A method for deleting a an id that has been removed from the text box shown to the user.
   */
  void deleteId();

  /**
   * Getter for the user inputted blue value.
   *
   * @return inputted blue value.
   */
  String getUserBlue();

  /**
   * A getter for the shape type text box in the view.
   *
   * @return string selected by the user.
   */
  String getShapeType();

  /**
   * Getter for the string value in the add new shape text field entered by a users.
   *
   * @return a string entered by the user.
   */
  String getNewId();

  /**
   * A method that adds a new string id to the to text box shown to the user.
   *
   * @param id id to add to the box.
   */
  void refreshId(String id);

  /**
   * Getter for the user inputted time value.
   *
   * @return inputted time value.
   */
  String getUserTime();

  /**
   * reset all of the text box texts and display an informative message to the use.
   */
  void resetOnBadInput();

  /**
   * Updates the values of the displayed key frames.
   */
  void updateKeyFrames();

  /**
   * Getter for the selected value in user inputted key frame.
   */
  String getKeyFrame();

  void engageThrusters();

  public void setChangeListener(ChangeListener c);
}
