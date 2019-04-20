package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs3500.animator.Main;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.model.ReadAnimationModel;
import cs3500.animator.model.Rectangle;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.EditorViewInterface;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;

/**
 * A controller implementation that implements the actions listener interface. This class will
 * listen for user interactions that occur in the view and call the appropriate methods.
 */
public class EditorController implements ActionListener, ChangeListener {
  private EditorViewInterface editView;
  private Map<String, Runnable> commandMap = new HashMap<>();
  private AnimationModel model;
  int speed;


  /**
   * A constructor for building a new controller implementation.
   *
   * @param model representing the data of the animation.
   * @param speed speed at which the animation will run in ticks per second.
   */
  public EditorController(AnimationModel model, int speed) {
    this.editView = new EditorView((ReadAnimationModel) model, speed, this); //
    this.editView.setChangeListener(this);
    this.speed = speed;
    this.model = model;
    commandMap.put("Pause Button", () -> editView.pause());
    commandMap.put("Rewind Button", () -> editView.rewind());
    commandMap.put("Play Button", () -> editView.play());
    commandMap.put("Speed Up Button", () -> editView.speedUp());
    commandMap.put("Slow Down Button", () -> editView.slowDown());
    commandMap.put("Restart Command", () -> editView.restart());
    commandMap.put("Timer Off", () -> editView.refresh());
    commandMap.put("add Key Frame", () -> this.addKeyFrame());
    commandMap.put("delete Key Frame", () -> this.deleteKeyFrame());
    commandMap.put("Read File", () -> this.readFile());
    commandMap.put("Save File", () -> this.saveFile());
    commandMap.put("Refresh Keyframes", () -> editView.updateKeyFrames());
    commandMap.put("Delete Shape", () -> this.deleteShape());
    commandMap.put("Add Shape", () -> this.addShape());
  }

  /**
   * A second Controller for testing for testing only PLEASE IGNORE.
   *
   * @param model representing the data of the animation.
   * @param speed speed at which the animation will run in ticks per second.
   * @param view  implementation of editor view to be shown.
   */
  public EditorController(AnimationModel model, int speed, EditorViewInterface view) {
    this.editView = view;
    this.speed = speed;
    this.model = model;
    commandMap.put("Pause Button", () -> editView.pause());
    commandMap.put("Rewind Button", () -> editView.rewind());
    commandMap.put("Play Button", () -> editView.play());
    commandMap.put("Speed Up Button", () -> editView.speedUp());
    commandMap.put("Slow Down Button", () -> editView.slowDown());
    commandMap.put("Restart Command", () -> editView.restart());
    commandMap.put("Timer Off", () -> editView.refresh());
    commandMap.put("add Key Frame", () -> this.addKeyFrame());
    commandMap.put("delete Key Frame", () -> this.deleteKeyFrame());
    commandMap.put("Read File", () -> this.readFile());
    commandMap.put("Save File", () -> this.saveFile());
    commandMap.put("Refresh Keyframes", () -> editView.updateKeyFrames());
    commandMap.put("Delete Shape", () -> this.deleteShape());
    commandMap.put("Add Shape", () -> this.addShape());
  }

  /**
   * A method that mutates given model and adds a keyframe. This method is called when the user
   * preses the "addKeyFrame" button in the view. This method works by getting the appropriate
   * values from text boxes from the view and calling a mutating model method accordingly.
   */
  private void addKeyFrame() {
    String id = editView.getUserId();
    String timeString = editView.getUserTime();

    try {
      int x = 0;
      int y = 0;
      int w = 0;
      int h = 0;
      int r = 0;
      int g = 0;
      int b = 0;
      int t;
      t = Integer.parseInt(timeString);
      if (!model.noActions(id)) {
        IShape curShape = model.currentShape(t, id);

        x = (int) curShape.getLocation().getX();
        y = (int) curShape.getLocation().getX();
        w = (int) curShape.getWidth();
        h = (int) curShape.getHeight();
        r = curShape.getColor().getRedInt();
        g = curShape.getColor().getGreenInt();
        b = curShape.getColor().getBlueInt();
      }

      if (!editView.getUserX().equals("x")) {
        x = Integer.parseInt(editView.getUserX());
      }
      if (!editView.getUserY().equals("y")) {
        y = Integer.parseInt(editView.getUserY());
      }
      if (!editView.getUserR().equals("red")) {
        r = Integer.parseInt(editView.getUserR());
      }
      if (!editView.getUserG().equals("green")) {
        g = Integer.parseInt(editView.getUserG());
      }
      if (!editView.getUserBlue().equals("blue")) {
        b = Integer.parseInt(editView.getUserBlue());
      }
      if (!editView.getUserW().equals("width")) {
        w = Integer.parseInt(editView.getUserW());
      }
      if (!editView.getUserHeight().equals("height")) {
        h = Integer.parseInt(editView.getUserHeight());
      }

      this.model.addKeyFrame(id, t, x, y, w, h, r, g, b);
      editView.updateKeyFrames();

    } catch (NumberFormatException e) {
      editView.resetOnBadInput();
    }
  }

  /**
   * Similar to add key frame this method will scrape the neccesary data from text boxes in the view
   * and pass that information on to a mutating method in the model.
   */
  private void deleteKeyFrame() {
    int t;
    String id = editView.getUserId();
    String keyFrame = editView.getKeyFrame();

    try {
      t = Integer.parseInt(keyFrame);
    } catch (NumberFormatException e) {
      System.out.print("debug");
      return;
    }
    model.deleteKeyFrame(id, t);
    editView.updateKeyFrames();

  }

  /**
   * Completes a command when an action whose listener is this controller happens.
   *
   * @param e actions event listened for.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    Runnable runner = commandMap.getOrDefault(cmd, null);
    if (runner != null) {
      runner.run();
    }
  }

  /**
   * A method for deleting a shape entered by the user from the animation.
   */
  private void deleteShape() {
    this.model.removeObject(editView.getUserId());
    editView.deleteId();
  }

  /**
   * EXTRA CREDIT IMPLEMENTATION This method is called when a user presses the read file button.
   * This method scrapes data from the appropriate text box in the view and subsequently
   * re-initializes the view with a model created from reading in the given file path.
   */
  private void readFile() {
    String read = this.editView.getReadFile();

    try {
      File f = new File(read);
      AnimationBuilder<ReadAnimationModel> modelBuilder = new AnimationModelImpl.Builder();
      BufferedReader inputReader = new BufferedReader(new FileReader(f));
      ReadAnimationModel model = AnimationReader.parseFile(inputReader, modelBuilder);
      this.model = (AnimationModel) model;
      this.editView.visible(false);
      this.editView.disp();
      this.editView = new EditorView(model, speed, this);
      this.gogoGadget();

    } catch (FileNotFoundException | NullPointerException e) {
      // in this case nullpointer occurs when a user passes in no file path
      // so there for is equivalent to file not found
      editView.resetOnBadInput();
    }

  }

  /**
   * EXTRA CREDIT IMPLEMENTATION This method allows for the saving of model states that have been
   * mutated using the add and delete key frame methods through the UI. This method will save the
   * given model state in either svg or text form to the specified file path.
   */
  private void saveFile() {
    String out = this.editView.getReadFile();
    String type = this.editView.getOutType();
    String file;
    switch (type) {
      case "TXT":
        file = new TextView((ReadAnimationModel) this.model).animationState();
        break;
      case "SVG":
        file = new SVGView((ReadAnimationModel) this.model).toSVG();
        break;
      default:
        file = null;
        break;
    }
    if (file != null) {
      Main.bufferedWriter(out, file);
    }

  }

  /**
   * A method for adding a shape of a type entered by the user to model.
   */
  private void addShape() {
    String type = editView.getShapeType();
    String id = editView.getNewId();
    IShape shape;

    if (type.equals("Rectangle")) {
      shape = new Rectangle();
    } else {
      shape = new Oval();
    }

    model.addObject(shape, id);
    editView.refreshId(id);
  }

  /**
   * A method for making the view visible.
   */
  public void gogoGadget() {
    editView.makeVisible();
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    editView.pause();
    editView.engageThrusters();
  }
}
