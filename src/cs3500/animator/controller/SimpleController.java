package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ReadAnimationModel;
import cs3500.animator.view.GraphicsView;
import cs3500.animator.view.IView;

/**
 * A controller implementation that implements the actions listener interface. This class will
 * listen for user interactions that occur in the view and call the appropriate methods. This
 * controller is from the previous assignment and only handles timer actions for the graphical
 * view.
 */
public class SimpleController implements ActionListener {
  IView view;
  Map<String, Runnable> cmd = new HashMap<>();

  /**
   * A constructor for building a new controller implementation.
   *
   * @param model representing the data of the animation.
   * @param speed speed at which the animation will run in ticks per second.
   */
  public SimpleController(AnimationModel model, int speed) {
    this.view = new GraphicsView((ReadAnimationModel) model, speed, this);

    cmd.put("Timer Off", () -> view.refresh());

  }

  /**
   * A second constructor for testing.
   *
   * @param model representing the data of the animation.
   * @param speed speed at which the animation will run in ticks per second.
   * @param view  view implementation to display animation.
   */
  public SimpleController(AnimationModel model, int speed, IView view) {
    this.view = view;

    cmd.put("Timer Off", () -> view.refresh());

  }

  /**
   * Completes a command when an action whose listener is this controller happens.
   *
   * @param e actions event listened for.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    cmd.get(e.getActionCommand()).run();
  }

  /**
   * A method for making the view visible.
   */
  public void gogoGadget() {
    this.view.makeVisible();
  }
}
