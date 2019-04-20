package cs3500.animator.provider.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import cs3500.animator.provider.utils.AShape;
import cs3500.animator.provider.utils.ShapeType;

/**
 * Class representing our JPanel that will be inside of the JFrame in our AnimatorView class. An
 * instance of a JPanel will paint all of the shapes given to it and will initialize a timer that is
 * used to specify the delay in between ticks. Contains methods for painting shapes, and repainting
 * the panel at every tick.
 */
public class AnimationJPanel extends javax.swing.JPanel implements ActionListener {

  private ArrayList<AShape> shapes;
  private Timer t;
  private Boolean isPaused;
  private Boolean looping;
  private int tick = 0;
  private int endTick;
  private int tempo;
  private static int ONE_SECOND = 1000;

  /**
   * Constructor for creating a JPanel given a model.
   *
   * @param shapes list of shapes to be drawn.
   * @param tempo speed of animation.
   */
  public AnimationJPanel(ArrayList<AShape> shapes, int tempo) {
    this.tempo = tempo;
    this.shapes = shapes;
    t = new Timer(ONE_SECOND / tempo, this);
    this.isPaused = false;
    this.looping = true;
  }

  /**
   * Paints shapes depending on their shape type.
   *
   * @param g represents given Graphic.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (AShape s : getShapesAt(tick)) {
      g.setColor(s.getColor());
      if (s.getType().equals(ShapeType.OVAL)) {
        g.fillOval((int) s.getPos().getX(), (int) s.getPos().getY(), (int) s.getWidth(),
            (int) s.getHeight());
      } else if (s.getType().equals(ShapeType.RECTANGLE)) {
        g.fillRect((int) s.getPos().getX(), (int) s.getPos().getY(), (int) s.getWidth(),
            (int) s.getHeight());
      }
    }
    if (tick >= endTick) {
      if (looping) {
        this.tick = 0;
      } else {
        t.stop();
      }
    }
  }

  private ArrayList<AShape> getShapesAt(int tick) {
    ArrayList<AShape> shapesAt = new ArrayList();
    for (AShape s : shapes) {
      AShape s1 = s.getShapeAt(tick);
      if (s1 != null) {
        shapesAt.add(s1);
      }
    }
    return shapesAt;
  }

  /**
   * Repaints the panel every tick.
   *
   * @param e represents given ActionEvent.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    this.repaint();
    tick++;
  }

  public ArrayList<AShape> getShapes() {
    return shapes;
  }

  public void setShapes(ArrayList<AShape> shapes) {
    this.shapes = shapes;
  }

  public Timer getT() {
    return t;
  }

  public void setT(Timer t) {
    this.t = t;
  }

  public Boolean getPaused() {
    return isPaused;
  }

  public void setPaused(Boolean paused) {
    isPaused = paused;
  }

  public Boolean getLooping() {
    return looping;
  }

  public void setLooping(Boolean looping) {
    this.looping = looping;
  }

  public int getTick() {
    return tick;
  }

  public void setTick(int tick) {
    this.tick = tick;
  }

  public int getTempo() {
    return tempo;
  }

  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  public int getEndTick() {
    return endTick;
  }

  public void setEndTick(int endTick) {
    this.endTick = endTick;
  }
}
