package cs3500.animator.provider.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * An EditorView extends the AnimatorView class to allow for its functionality, but adds the ability
 * to pause, restart, enable/disable looping, speed up/down, add shapes, remove shapes, add
 * keyframes to shapes, remove keyframes from shapes, and change keyframes of shapes. An EditorView
 * has the controller as an ActionListener, which allows for this new functionality to relay to the
 * model.
 */
public class EditorView extends AnimatorView implements View {

  private JButton startButton;
  private JTextField textField;
  private ActionListener listener;
  private Point topLeftCorner;

  /**
   * To create an instance of an EditorView.
   */
  public EditorView() {
    this.type = ViewType.EDITOR;
  }

  /**
   * Displays the animation. Creates the frame, panel, and buttons necessary for the EditorView to
   * run and function.
   */
  @Override
  public void display() {
    setEndTick(findEndTick());
    frame.setLayout(new FlowLayout());
    frame.setPreferredSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
            + height + 200));
    frame.setMinimumSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
            + height + 200));
    frame.setMaximumSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
            + height + 200));
    // frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new AnimationJPanel(shapes, tempo);
    panel.setEndTick(this.endTick);
    panel.setPreferredSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
            + height));
    panel.setMinimumSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
            + height));
    panel.setMaximumSize(new Dimension(topLeftCorner.x + width + 1, topLeftCorner.y
            + height + 1));
    frame.add(panel);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    frame.add(buttonPanel, BorderLayout.EAST);

    startButton = new JButton("Start/Stop");
    buttonPanel.add(startButton);

    JButton restartButton = new JButton("Restart");
    buttonPanel.add(restartButton);

    JButton loopButton = new JButton("Enable/Disable looping");
    buttonPanel.add(loopButton);

    JButton speedUpButton = new JButton("Speed up");
    buttonPanel.add(speedUpButton);

    JButton speedDownButton = new JButton("Slow down");
    buttonPanel.add(speedDownButton);

    JPanel editorPanel = new JPanel();
    editorPanel.setLayout(new FlowLayout());
    frame.add(editorPanel, BorderLayout.EAST);

    JPanel textPanel = new JPanel();
    textPanel.setLayout(new FlowLayout());
    frame.add(textPanel, BorderLayout.EAST);

    textField = new JTextField("Type input here", 50);
    textPanel.add(textField);

    JButton addShapeButton = new JButton("Add Shape");
    editorPanel.add(addShapeButton);

    JButton removeShapeButton = new JButton("Remove Shape");
    editorPanel.add(removeShapeButton);

    JButton addKeyframeButton = new JButton("Add Keyframe");
    editorPanel.add(addKeyframeButton);

    JButton removeKeyframeButton = new JButton("Remove Keyframe");
    editorPanel.add(removeKeyframeButton);

    JButton changeKeyframeButton = new JButton("Change Keyframe");
    editorPanel.add(changeKeyframeButton);

    JPanel instructionPanel = new JPanel();
    JLabel label = new JLabel("Instructions: separate each parameter by a space.");
    label.setText("To add a shape input the shape name and shape type. To remove a shape input the"
            + "shape name. To add/remove/change a keyframe " +
            " input the name of shape, time, x position, "
            + "y position, width, height, red value, green value, blue value.");

    instructionPanel.add(label);
    instructionPanel.setLayout(new FlowLayout());
    frame.add(instructionPanel);

    startButton.setActionCommand("start");
    startButton.addActionListener(listener);
    restartButton.setActionCommand("restart");
    restartButton.addActionListener(listener);
    loopButton.setActionCommand("looping");
    loopButton.addActionListener(listener);
    speedUpButton.setActionCommand("speedup");
    speedUpButton.addActionListener(listener);
    speedDownButton.setActionCommand("speeddown");
    speedDownButton.addActionListener(listener);
    addShapeButton.setActionCommand("addshape");
    addShapeButton.addActionListener(listener);
    removeShapeButton.setActionCommand("removeshape");
    removeShapeButton.addActionListener(listener);
    addKeyframeButton.setActionCommand("addkeyframe");
    addKeyframeButton.addActionListener(listener);
    removeKeyframeButton.setActionCommand("removekeyframe");
    removeKeyframeButton.addActionListener(listener);
    changeKeyframeButton.setActionCommand("changekeyframe");
    changeKeyframeButton.addActionListener(listener);

    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void setListener(ActionListener listener) {
    this.listener = listener;
  }

  @Override
  public JTextField getTextField() {
    return textField;
  }

  @Override
  public Point getTopLeftCorner() {
    return topLeftCorner;
  }

  @Override
  public void setTopLeftCorner(Point topLeftCorner) {
    this.topLeftCorner = topLeftCorner;
  }

  public JButton getStartButton() {
    return startButton;
  }
}
