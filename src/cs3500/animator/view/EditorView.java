package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.BoxLayout;

import javax.swing.event.ChangeListener;


import cs3500.animator.model.AAction;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.ReadAnimationModel;

/**
 * A editable and interactive animator visual view.
 */
public class EditorView extends JFrame implements EditorViewInterface {

  private EditorPanel editorPanel;
  private JPanel buttonPanel;
  private JTextField readIn;
  private JTextField time;
  private JTextField newId;
  private JTextField red;
  private JTextField green;
  private JTextField blue;
  private JTextField width;
  private JTextField height;
  private JTextField x;
  private JTextField y;
  private JComboBox<String> shapeMenu;
  private JComboBox<String> newShapeType;
  private JComboBox<String> keyFrameMenu;
  private JComboBox<String> outputMenu;
  private ReadAnimationModel model;
  private JSlider slider;

  /**
   * Constructor for the Editor view.
   *
   * @param mod   Read only model.
   * @param speed the speed of the timer.
   * @param l     the editor controller.
   */
  public EditorView(ReadAnimationModel mod, int speed, ActionListener l) {

    this.model = mod;
    this.setTitle("Animation");
    AnimationModelImpl.BoundHolder b = mod.getBounds();
    this.setBounds(b.getBoundX(), b.getBoundY(), b.getBoundWidth(), b.getBoundHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.editorPanel = new EditorPanel(Objects.requireNonNull(mod), speed, l);
    this.add(this.editorPanel);
    this.buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.Y_AXIS));
    this.add(buttonPanel, BorderLayout.EAST);
    this.initButtons(l);
    this.add(editorPanel.getInnerPanel());

  }

  /**
   * Unsupported operation for the editor view.
   *
   * @return new UnsupportedOperationException.
   */
  @Override
  public String toSVG() {
    throw new UnsupportedOperationException();
  }

  /**
   * Unsupported operation for the editor view.
   *
   * @return new UnsupportedOperationException.
   */
  @Override
  public String animationState() {
    throw new UnsupportedOperationException();
  }

  /**
   * Makes the JFrame View visible.
   */
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Initializes all the buttons, combo boxes that comprise the JFrame.
   *
   * @param l the controller that acts as an actionListener for the View.
   */
  private void initButtons(ActionListener l) {

    JButton pause = new JButton("Pause");
    pause.setActionCommand("Pause Button");
    pause.addActionListener(l);
    this.buttonPanel.add(pause);

    JButton play = new JButton("Play");
    play.setActionCommand("Play Button");
    play.addActionListener(l);
    this.buttonPanel.add(play);

    JButton rewind = new JButton("Rewind");
    rewind.setActionCommand("Rewind Button");
    rewind.addActionListener(l);
    this.buttonPanel.add(rewind);

    JButton restart = new JButton("Restart");
    restart.setActionCommand("Restart Command");
    restart.addActionListener(l);
    this.buttonPanel.add(restart);

    JButton speedUp = new JButton("Speed Up");
    speedUp.setActionCommand("Speed Up Button");
    speedUp.addActionListener(l);
    this.buttonPanel.add(speedUp);

    JButton slowDown = new JButton(("Slow Down"));
    slowDown.setActionCommand("Slow Down Button");
    slowDown.addActionListener(l);
    this.buttonPanel.add(slowDown);
    // ----------- this for key frame shit -----------------
    this.shapeMenu = new JComboBox<>(model.getActionsIdAsList());
    this.shapeMenu.setActionCommand("Refresh Keyframes");
    this.shapeMenu.addActionListener(l);
    this.buttonPanel.add(shapeMenu);

    JButton deleteShape = new JButton("delete shape");
    deleteShape.setActionCommand("Delete Shape");
    deleteShape.addActionListener(l);
    this.buttonPanel.add(deleteShape);

    this.time = new JTextField("time");
    this.buttonPanel.add(time);

    this.red = new JTextField("red");
    this.green = new JTextField("green");
    this.blue = new JTextField("blue");
    this.buttonPanel.add(red);
    this.buttonPanel.add(green);
    this.buttonPanel.add(blue);

    this.width = new JTextField("width");
    this.buttonPanel.add(width);

    this.height = new JTextField("height");
    this.buttonPanel.add(height);

    this.x = new JTextField("x");
    this.buttonPanel.add(x);

    this.y = new JTextField("y");
    this.buttonPanel.add(y);

    JButton addKeyButton = new JButton("add Key Frame");
    addKeyButton.setActionCommand("add Key Frame");
    addKeyButton.addActionListener(l);
    this.buttonPanel.add(addKeyButton);

    JButton deleteKeyFrame = new JButton("delete Key Frame");
    deleteKeyFrame.setActionCommand("delete Key Frame");
    deleteKeyFrame.addActionListener(l);
    this.buttonPanel.add(deleteKeyFrame);

    String id = (String) shapeMenu.getSelectedItem();
    this.keyFrameMenu = new JComboBox<>(this.model.getKeyFrames(id));
    this.buttonPanel.add(keyFrameMenu);

    JButton readButton = new JButton("Read new File");
    readButton.setActionCommand("Read File");
    readButton.addActionListener(l);
    buttonPanel.add(readButton);

    this.readIn = new JTextField("Enter file Path here");
    this.buttonPanel.add(readIn);

    JButton saveFile = new JButton("Save File");
    saveFile.setActionCommand("Save File");
    saveFile.addActionListener(l);
    this.buttonPanel.add(saveFile);

    String[] output = new String[2];
    output[0] = "SVG";
    output[1] = "TXT";
    this.outputMenu = new JComboBox<String>(output);
    this.buttonPanel.add(outputMenu);

    String[] shapeType = new String[2];
    shapeType[0] = "Ellipse";
    shapeType[1] = "Rectangle";
    this.newShapeType = new JComboBox(shapeType);

    this.buttonPanel.add(newShapeType);
    JButton addShape = new JButton("add shape");
    addShape.setActionCommand("Add Shape");
    addShape.addActionListener(l);
    this.buttonPanel.add(addShape);

    this.newId = new JTextField("new id");
    this.buttonPanel.add(newId);

    this.slider = new JSlider(0, maxTick()); //TODO must parameterize change listener
    this.buttonPanel.add(slider);

  }

  @Override
  public void engageThrusters() { //TODO add to interface
    //while(slider.getValueIsAdjusting()) {
    this.editorPanel.getInnerPanel().setTick(slider.getValue());
    this.refresh();
    //}
  }

  public void setChangeListener(ChangeListener c) {
    this.slider.addChangeListener(c);
  }

  /**
   * Getter for the value in the new shape type combo box.
   */

  public String getShapeType() {
    return (String) this.newShapeType.getSelectedItem();
  }

  /**
   * Getter for the string value in the add new shape text field entered by a users.
   *
   * @return a string entered by the user.
   */
  public String getNewId() {
    return newId.getText();
  }

  /**
   * Pauses the panel.
   */
  public void pause() {
    this.editorPanel.pause();
  }

  /**
   * Plays the given animation.
   */
  public void play() {
    this.editorPanel.play();
  }

  /**
   * Rewind the given animation.
   */
  public void rewind() {
    this.editorPanel.rewind();
  }

  /**
   * Restart the given animation.
   */
  public void restart() {
    this.editorPanel.restart();
  }

  /**
   * Speed up the speed of the given panel.
   */
  public void speedUp() {
    this.editorPanel.speedUp();
  }

  /**
   * slow down the speed of the given panel.
   */
  public void slowDown() {
    this.editorPanel.slowDown();
  }

  /**
   * refresh the state of the panel.
   */
  public void refresh() {
    this.editorPanel.refreshAndIncrement();
  }

  /**
   * Getter method for the ID of the shape currently selected.
   *
   * @return the ID of the shape.
   */
  public String getUserId() {
    return (String) shapeMenu.getSelectedItem();
  }

  /**
   * Getter method for the time of the keyframe that is currently selected.
   *
   * @return the selected keyframe.
   */
  public String getKeyFrame() {
    return (String) keyFrameMenu.getSelectedItem();
  }

  /**
   * Getter method for user input in the x text field.
   *
   * @return the X value as a string.
   */
  public String getUserX() {
    return this.x.getText();
  }

  /**
   * Getter method for the user input in the y text field.
   *
   * @return the Y value as a String.
   */
  public String getUserY() {
    return this.y.getText();
  }

  /**
   * Getter method for the user input in the Width text field.
   *
   * @return the Width value as a String.
   */
  public String getUserW() {
    return this.width.getText();
  }

  /**
   * Getter method for the user input in the Height text field.
   *
   * @return the Height value as a String.
   */
  public String getUserHeight() {
    return this.height.getText();
  }

  /**
   * Getter method for the user input in the red text field.
   *
   * @return the red value as a String.
   */
  public String getUserR() {
    return this.red.getText();
  }

  /**
   * Getter method for the user input in the green text field.
   *
   * @return the green value as a String.
   */
  public String getUserG() {
    return this.green.getText();
  }

  /**
   * Getter method for the user input in the blue text field.
   *
   * @return the blue value as a String.
   */
  public String getUserBlue() {
    return this.blue.getText();
  }

  /**
   * Getter method for the user input in the time text field.
   *
   * @return the time value as a String.
   */
  public String getUserTime() {
    return this.time.getText();
  }

  /**
   * A method for deleting a an id that has been removed from the text box shown to the user.
   */
  public void deleteId() {

    this.shapeMenu.removeItemAt(shapeMenu.getSelectedIndex());
  }

  /**
   * A method that adds a new string id to the to text box shown to the user.
   *
   * @param id id to add to the box.
   */
  public void refreshId(String id) {

    this.shapeMenu.addItem(id);
  }

  /**
   * Resets the text displayed in each of the text boxes to the specified defaults.
   */
  public void resetOnBadInput() {
    blue.setText("blue");
    red.setText("red");
    width.setText("width");
    height.setText("height");
    green.setText("green");
    this.x.setText("x");
    this.y.setText("y");
    this.time.setText("MUST BE VALID KEY FRAME");
  }

  /**
   * Updates the contents of the drop down menu containing the keyframes of the selected shape.
   */
  public void updateKeyFrames() {
    this.keyFrameMenu.removeAllItems();
    String[] keyframes = this.model.getKeyFrames(getUserId());

    for (int i = 0; i < keyframes.length; i++) {
      this.keyFrameMenu.addItem(keyframes[i]);
    }
  }


  /**
   * Getter method for user input of file path.
   *
   * @return the String of the input file path.
   */
  public String getReadFile() {

    return readIn.getText();

  }

  /**
   * Used to alter the visibility of the View.
   *
   * @param b set visibility to true or false.
   */
  public void visible(Boolean b) {

    this.setVisible(b);
  }

  /**
   * Used to dispose.
   */
  @Override
  public void disp() {
    this.dispose();
  }

  /**
   * Getter for the textfield of the type of file to be output.
   *
   * @return the String of the type of text file.
   */
  @Override
  public String getOutType() {
    return (String) outputMenu.getSelectedItem();
  }

  private int maxTick() {
    Set<Integer> set = new HashSet<>();
    for (String s : model.getIDSet()) {
      List<AAction> m = model.getMoves(s);
      set.add(m.get(m.size() - 1).getEndTime());
    }
    return Collections.max(set);
  }


}
