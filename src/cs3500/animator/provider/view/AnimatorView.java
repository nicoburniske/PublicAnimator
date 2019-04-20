package cs3500.animator.provider.view;

import cs3500.animator.provider.utils.AShape;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Class representing a visual view of our model. An instance of this class will contain a frame
 * contains panels inside of it that have all of the information to paint shapes on each tick
 * therefore visually showing the animations.
 */
public class AnimatorView implements View {

  protected AnimationJPanel panel;
  protected JFrame frame = new JFrame("Animation");
  protected int tempo = 1;
  protected ArrayList<AShape> shapes;
  protected int width;
  protected int height;
  protected int endTick;
  protected ViewType type;
  private Point topLeftCorner;

  /**
   * Constructor to pass in a model to our class.
   */
  public AnimatorView() {
    this.type = ViewType.ANIMATOR;
  }

  /**
   * finds the tick the animation should end on.
   *
   * @return int representing a last tick.
   */
  public int findEndTick() {
    ArrayList<Integer> lastTicks = new ArrayList<>();
    for (AShape s : shapes) {
      Keyframe lastKeyframe = s.getKeyframes().get(s.getKeyframes().size() - 1);
      lastTicks.add(lastKeyframe.getTime());
    }
    return Collections.max(lastTicks);
  }

  /**
   * Displays the animation as a visual.
   */
  @Override
  public void display() {
    frame.setLayout(new FlowLayout());
    frame.setPreferredSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
        + height));
    frame.setMinimumSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
        + height));
    frame.setMaximumSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
        + height));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new AnimationJPanel(shapes, tempo);
    panel.setEndTick(findEndTick());
    panel.getT().start();
    panel.setPreferredSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
        + height));
    panel.setMinimumSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
        + height));
    panel.setMaximumSize(new Dimension(topLeftCorner.x + width, topLeftCorner.y
        + height));
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public ViewType getType() {
    return type;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public ArrayList<AShape> getShapes() {
    return null;
  }

  @Override
  public void setShapes(ArrayList<AShape> shapes) {
    this.shapes = shapes;
  }

  @Override
  public int getTick() {
    return panel.getTick();
  }

  public JFrame getFrame() {
    return this.frame;
  }

  public void setFrame(JFrame frame) {
    this.frame = frame;
  }

  public AnimationJPanel getPanel() {
    return panel;
  }

  @Override
  public JTextField getTextField() {
    throw new UnsupportedOperationException("Operation is not supported.");
  }

  @Override
  public Point getTopLeftCorner() {
    return topLeftCorner;
  }

  @Override
  public void setTopLeftCorner(Point topLeftCorner) {
    this.topLeftCorner = topLeftCorner;
  }

  public void setPanel(AnimationJPanel panel) {
    this.panel = panel;
  }

  public int getTempo() {
    return tempo;
  }

  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public void setListener(ActionListener listener) {
    throw new UnsupportedOperationException("Operation is not supported.");
  }

  public int getEndTick() {
    return endTick;
  }

  public void setEndTick(int endTick) {
    this.endTick = endTick;
  }
}
