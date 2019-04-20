package cs3500.animator.view;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import cs3500.animator.model.ReadAnimationModel;

/**
 * A panel class that composes our old panel (which handles graphics, and will be referred to as
 * "inner panel") and adds edit functionality.
 */
public class EditorPanel extends JPanel {
  private int speed;
  private int dTime;
  private AnimationPanel animationPanel;

  /**
   * A constructor for EditorPanel which initializes the inner panel with given model and given
   * action listener.
   *
   * @param model model that represents data of an animation.
   * @param speed speed in ticks per second to play the animation.
   * @param l     action listener that will listen for inner panels timer.
   */
  EditorPanel(ReadAnimationModel model, int speed, ActionListener l) {
    super();
    this.speed = speed;
    this.dTime = 1;
    animationPanel = new AnimationPanel(model, speed, l);
  }

  /**
   * A getter for the inner composed panel of this panel.
   */
  public AnimationPanel getInnerPanel() {
    return animationPanel;
  }

  /**
   * Sets the value of the timer in the inner panel of this panel.
   */
  public void setTimer() {
    this.animationPanel.getTimer().setDelay(1000 / this.speed);
  }

  /**
   * Pauses the timer in the inner panel of this panel.
   */
  public void pause() {
    this.animationPanel.getTimer().stop();
    this.dTime = 0;
  }

  /**
   * Starts the timer of this inner panel.
   */
  public void play() {
    this.animationPanel.getTimer().start();
    this.dTime = 1;
  }

  /**
   * Plays the timer in the inner panel in reverse.
   */
  public void rewind() {
    if (dTime == 1) {
      dTime = -1;
    }
  }

  /**
   * increases the speed of the timer in the inner panel.
   */
  public void speedUp() {
    this.speed += 50;
    this.setTimer();
  }

  /**
   * restarts the the animation at the beginning.
   */
  public void restart() {
    this.animationPanel.getTimer().restart();
    animationPanel.setTick(0);
  }

  /**
   * Reduces the timer in the inner panel.
   */
  public void slowDown() {
    if (this.speed - 50 > 0) {
      this.speed -= 50;
    }
    this.setTimer();
  }

  /**
   * Commands in the inner panel to repaint itself.
   */
  public void refresh() {
    animationPanel.repaint();
  }

  /**
   * Increases the tick of the inner panel and refreshes it.
   */
  public void refreshAndIncrement() {
    int curTick = animationPanel.getTick();
    if (dTime + curTick >= 0) {
      animationPanel.setTick(curTick + dTime);
    }
    this.refresh();
  }
}
