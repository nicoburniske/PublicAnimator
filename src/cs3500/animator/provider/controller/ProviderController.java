package cs3500.animator.provider.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import cs3500.animator.model.ActionsHolder;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.provider.model.AnimatorModel;
import cs3500.animator.provider.view.EditorView;
import cs3500.animator.provider.utils.AShape;
import cs3500.animator.provider.utils.AShapeAdapter;

/**
 * Represents a controller to be used with the provider view.
 */
public class ProviderController implements ActionListener {
  AnimatorModel model;
  EditorView view;
  Map<String, Runnable> cmds = new HashMap<>();

  /**
   * Constructor for a controller. Contains a Model and view.
   *
   * @param model Animation model.
   * @param edit  editor view from provider.
   */
  public ProviderController(AnimatorModel model, EditorView edit) {
    edit.setListener(this);
    this.model = model;
    this.view = edit;
    initCommands();
  }

  /**
   * Initializes map of commands.
   */
  private void initCommands() {
    cmds.put("start", () -> view.getPanel().getT().start());
    cmds.put("restart", () -> {
      view.getPanel().getT().restart();
      view.getPanel().setTick(0);
    });
    cmds.put("looping", () -> view.getPanel().setLooping(!view.getPanel().getLooping()));
    cmds.put("speedup", () -> {
      int temp = view.getPanel().getTempo();
      temp += 50;
      view.getPanel().setTempo(temp);
      view.getPanel().getT().setDelay(1000 / temp);
    });

    cmds.put("speeddown", () -> {
      int temp = view.getPanel().getTempo();
      if (temp - 50 > 0) {
        temp -= 50;
        view.getPanel().setTempo(temp);
      }
      view.getPanel().getT().setDelay(1000 / temp);
    });

    cmds.put("addshape", () -> {
      String[] inputs = view.getTextField().getText().split(" ");
      IShape i;
      if (inputs[1].equals("rectangle")) {
        i = new Rectangle();
      } else {
        i = new Oval();
      }
      AShape a = new AShapeAdapter(new ActionsHolder(), i);

      model.addShape(a, inputs[0]);

      view.getPanel().setShapes(model.getShapes());
    });
    cmds.put("removeshape", () -> {
      try {
        String s = view.getTextField().getText();
        model.removeShape(s);
        view.getPanel().setShapes(model.getShapes());
      } catch (IllegalArgumentException e) {
        System.out.println("ALREADY REMOVED");
      }
    });

    cmds.put("addkeyframe", () -> {
      try {
        String[] inputs = view.getTextField().getText().split(" ");
        int t = Integer.parseInt(inputs[1]);
        int x = Integer.parseInt(inputs[2]);
        int y = Integer.parseInt(inputs[3]);
        int w = Integer.parseInt(inputs[4]);
        int h = Integer.parseInt(inputs[5]);
        int r = Integer.parseInt(inputs[6]);
        int g = Integer.parseInt(inputs[7]);
        int b = Integer.parseInt(inputs[8]);

        model.addKeyFrame(inputs[0], t, x, y, w, h, r, g, b);
        view.getPanel().setShapes(model.getShapes());
      } catch (NumberFormatException e) {
        //Catch block is empty because the provider view doesn't supply a way
        //to show the input was incorrect. Therefore we choose not to do anything.
      }
    });

    cmds.put("removekeyframe", () -> {
      String[] inputs = view.getTextField().getText().split(" ");
      model.deleteKeyFrame(inputs[0], Integer.parseInt(inputs[1]));
      view.getPanel().setShapes(model.getShapes());
    });
  }

  /**
   * Performs the specified action.
   *
   * @param e the action performed by the view.
   */
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    Runnable go = cmds.getOrDefault(cmd, null);
    if (go != null) {
      go.run();
    }

  }
}