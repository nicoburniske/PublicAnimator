package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JFrame;

import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.ReadAnimationModel;

/**
 * Our Main implementation of the visual view interface. Extends JFrame and creates a visual
 * animation using the java swing framework.
 */
public class GraphicsView extends JFrame implements IView {

  private AnimationPanel panel;
  private int speed;

  /**
   * Constructor for the graphics view implementation that takes in read only access model and a
   * speed in ticks per second to display the animation.
   *
   * @param r     the given model.
   * @param speed the speed for the given model.
   */
  public GraphicsView(ReadAnimationModel r, int speed, ActionListener l) {
    if (r == null) {
      throw new IllegalArgumentException("model must not be null");
    }
    this.setTitle("Animation");
    AnimationModelImpl.BoundHolder b = r.getBounds();
    this.setBounds(b.getBoundX(), b.getBoundY(), b.getBoundWidth(), b.getBoundHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new AnimationPanel(Objects.requireNonNull(r), speed, l);
    this.add(this.panel);
    this.speed = speed;

  }

  /**
   * This operation  is not supported by this view so throw an exception.
   */
  @Override
  public String toSVG() {
    throw new UnsupportedOperationException("operation not supported");

  }

  /**
   * This operation  is not supported by this view so throw an exception.
   */
  @Override
  public String animationState() {
    throw new UnsupportedOperationException("operation not supported");
  }

  /**
   * Sets the visibility of this view to true.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * refreshes and increments the tick of the inner panel.
   */
  @Override
  public void refresh() {
    panel.setTick(panel.getTick() + speed);
    panel.repaint();
  }
}
