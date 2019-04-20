package cs3500.animator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import cs3500.animator.controller.EditorController;
import cs3500.animator.controller.SimpleController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.ReadAnimationModel;
import cs3500.animator.provider.controller.ProviderController;
import cs3500.animator.provider.model.ModelAdapter;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;

/**
 * Main class that serves as entry point to program.
 */
public class Main {

  /**
   * Main method that allows commands to be specified by the user. These commands allow the user to
   * determine the input source, output location as well as which view to use and the speed at which
   * to display the animation.
   *
   * @param args command lines arguments.
   * @throws FileNotFoundException if given file path cannot be found.
   */
  public static void main(String[] args) throws FileNotFoundException {

    File input = new File(Objects.requireNonNull(findNextOrDefault("-in", args, null)));
    AnimationBuilder<ReadAnimationModel> modelBuilder = new AnimationModelImpl.Builder();
    BufferedReader inputReader;
    String speed = findNextOrDefault("-speed", args, "1");
    inputReader = new BufferedReader(new FileReader(input));
    String outputPath = (findNextOrDefault("-out", args, null));

    String newFile = null;

    switch (Objects.requireNonNull(findNextOrDefault("-view", args, null))) {

      case "visual":
        new SimpleController((AnimationModel)
                AnimationReader.parseFile(inputReader, modelBuilder),
                Integer.parseInt(speed)).gogoGadget();
        break;
      case "text":
        TextView tv = new TextView(AnimationReader.parseFile(inputReader, modelBuilder));
        newFile = tv.animationState();
        break;
      case "svg":
        SVGView svg = new SVGView(AnimationReader.parseFile(inputReader, modelBuilder),
                Integer.parseInt(speed));
        newFile = svg.toSVG();
        break;
      case "edit":
        ReadAnimationModel model =
                Objects.requireNonNull(AnimationReader.parseFile(inputReader, modelBuilder));
        new EditorController((AnimationModel) model, Integer.parseInt(speed)).gogoGadget();
        break;
      case "provider":
        AnimationModel m =
                (AnimationModel) Objects.requireNonNull(
                        AnimationReader.parseFile(inputReader, modelBuilder));
        cs3500.animator.provider.view.EditorView v = new cs3500.animator.provider.view.EditorView();
        ModelAdapter ma = new ModelAdapter(m);
        new ProviderController(ma, v);
        v.setShapes(ma.getShapes());
        v.setWidth(ma.getWidth() + 200);
        v.setHeight(ma.getHeight());
        v.setTopLeftCorner(ma.getTopLeftCorner());
        v.display();
        break;
      default:
        break;
    }
    if (newFile != null) {
      if (outputPath == null || outputPath.equals("out")) {
        System.out.println(newFile);
      } else {
        bufferedWriter(outputPath, newFile);
      }
    }

  }

  /**
   * Finds the String in given string array that is directly after given String. If cannot be found
   * return the given default value.
   *
   * @param s    string to find.
   * @param args array to look for string.
   * @param def  default value to return.
   * @return Either the element after found string or default if that string does not occur
   */
  public static String findNextOrDefault(String s, String[] args, String def) {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals(s)) {
        return args[i + 1];
      }
    }
    return def;

  }

  /**
   * A method for writing files to a specified output location.
   *
   * @param filePath     location to output to.
   * @param fileContents contents of file to be written.
   */
  public static void bufferedWriter(String filePath, String fileContents) {
    try {
      PrintWriter writer = new PrintWriter(
              filePath, "UTF-8");
      writer.write(fileContents);
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("cannot find file");
    }

  }
}
