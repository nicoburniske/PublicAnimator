package cs3500.animator.view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import cs3500.animator.util.Coord;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ReadAnimationModel;

/**
 * Implementation of Action listener that represents a panel to be displayed by the JFrame in this
 * animation.
 */
public class AnimationPanel extends JPanel {
  private int tick;
  private ReadAnimationModel model;
  private Timer timer;

  /**
   * Constructor for initializing a new Animation Panel.
   *
   * @param model model to be used for animation. Read only access given.
   * @param speed speed to run the animation in ticks per second.
   */
  public AnimationPanel(ReadAnimationModel model, int speed, ActionListener l) {
    super();
    this.setBackground(Color.WHITE);
    this.tick = 0;
    this.model = model;
    timer = new Timer(1000 / speed, l);
    timer.setActionCommand("Timer Off");
    timer.start();
  }

  /**
   * Draws all of the animation shapes in the model on the panel for each tick.
   *
   * @param g graphics to be used for drawing.
   */
  @Override
  public void paintComponent(Graphics g) {
    List<IShape> shape = model.currentFrame(tick);
    super.paintComponent(g);
    for (IShape s : shape) {
      Coord loc = s.getLocation();
      Color col = new Color(s.getColor().getRedInt(),
             s.getColor().getGreenInt(),
              s.getColor().getBlueInt());
      double height = s.getHeight();
      double width = s.getWidth();
      g.setColor(new Color(col.getRed(), col.getGreen(), col.getBlue()));
      if (s.isRectangle()) {
        g.fillRect((int) loc.getX(), (int) loc.getY(), (int) width, (int) height);
      } else {
        g.fillOval((int) loc.getX(), (int) loc.getY(), (int) width, (int) height);
      }
    }
  }

  /**
   * Gettter for the timer of this panel.
   * @return this panels timer.
   */
  public Timer getTimer() {
    return this.timer;
  }

  /**
   * Setter for the tick field of this panel.
   * @param tick tick to set.
   */
  public void setTick(int tick) {
    this.tick = tick;
  }

  /**
   * getter for the tick value of this panel.
   * @return ticks.
   */
  public int getTick() {
    return tick;
  }
}
