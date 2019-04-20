package cs3500.animator.view;

import java.util.Objects;

import cs3500.animator.model.AAction;
import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Move;
import cs3500.animator.model.ReadAnimationModel;
import cs3500.animator.model.ChangeSize;

/**
 * Implementation of SVG view that will create an animation in SVG form.
 */
public class SVGView implements IView {
  private int width;
  private int height;
  private int speed;
  private ReadAnimationModel model;

  /**
   * Default constructor for SVGview that initializes bounds from the data in the model.
   *
   * @param r read only model that contains the necessary data for the animation.
   */
  public SVGView(ReadAnimationModel r) {
    if (r == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    speed = 1000;
    model = Objects.requireNonNull(r);
    this.width = model.getBounds().getBoundWidth();
    this.height = model.getBounds().getBoundHeight();
  }

  /**
   * Secondary Constructor for initializing SVGview's.
   *
   * @param r      read only model to be hold the necessary animation data.
   * @param width  given width of animation.
   * @param height given height of animation.
   */
  public SVGView(ReadAnimationModel r, int width, int height) {
    if (r == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    speed = 1000;
    model = Objects.requireNonNull(r);
    this.width = width;
    this.height = height;
  }

  /**
   * Tertiary constructor for initializing SVGView.
   *
   * @param r     read only model to be hold the necessary animation data.
   * @param speed speed that he given animation should operate at.
   */
  public SVGView(ReadAnimationModel r, int speed) {
    if (r == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    model = Objects.requireNonNull(r);
    this.width = model.getBounds().getBoundWidth();
    this.height = model.getBounds().getBoundHeight();
    this.speed = 1000 / speed;
  }

  /**
   * A method for creating an svg compatible string from the data in this views model.
   *
   * @return a string that can be read as an SVG file.
   */
  public String toSVG() {

    StringBuilder toReturn = new StringBuilder();
    toReturn.append("<svg width=" + "\"" + Integer.toString(this.width)
            + "\"" + " height=" + "\"" + Integer.toString(this.height)
            + "\"" + " version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"); // add website
    String tag;
    for (String s : model.getIDSet()) {

      IShape sh = model.currentShape(0, s);
      if (sh.isRectangle()) {
        toReturn.append("<rect id=" + "\"" + s + "\"" + " ");
        tag = "</rect>";
        toReturn.append("x=\"" + sh.getLocation().getX() + "\" y=\""
                + sh.getLocation().getY() + "\" width=\"" + sh.getWidth() + "\" height=\""
                + sh.getHeight() + "\" fill=\"rgb(" + sh.getColor().getRedInt() + "," +
                sh.getColor().getGreenInt() + ","
                + sh.getColor().getBlueInt() + ")\" visibility=\"visible\" >\n");
      } else {
        toReturn.append("<ellipse id=" + "\"" + s + "\"" + " ");
        toReturn.append("cx=\"" + sh.getLocation().getX() + "\" cy=\"" + sh.getLocation().getY()
                + "\" rx=\"" + sh.getWidth() + "\" ry=\"" + sh.getHeight() + "\" fill=\"rgb("
                + sh.getColor().getRedInt() + "," + sh.getColor().getGreenInt() + ","
                + sh.getColor().getBlueInt() + ")\" visibility=\"visible\" >\n");
        tag = "</ellipse>";
      }

      toReturn.append(svgColors(s));

      toReturn.append(svgSizes(s));

      toReturn.append(svgMoves(s));

      toReturn.append(tag + "\n");

    }
    toReturn.append("</svg>");
    return toReturn.toString();
  }

  /**
   * This operation is not supported by this implementation, so throw an exception.
   */
  @Override
  public String animationState() {
    throw new UnsupportedOperationException("operation not supported");
  }

  /**
   * This operation is not supported by this implementation, so throw an exception.
   */
  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("operation not supported");
  }

  /**
   * This operation is not supported by this implementation, so throw an exception.
   */
  @Override
  public void refresh() {
    throw new UnsupportedOperationException("operation not supported");
  }

  /**
   * A helper method for creating all of the animation strings in svg of just the color change
   * actions in this models data.
   *
   * @param id id of shape to change color.
   * @return a String representing this data in SVG form.
   */
  private String svgColors(String id) {
    StringBuilder toReturn = new StringBuilder();
    for (AAction a : model.getColors(id)) {
      ChangeColor c = (ChangeColor) a;
      toReturn.append("      ");
      toReturn.append("<animate attributeType=\"css\" begin=\"" + c.getStartTime() * speed
              + "ms\"" + " dur=\"" + speed * (c.getEndTime() - c.getStartTime()) +
              "ms" + "\" attributeName=\"" + "fill" + "\"" +
              " from=\"" + "rgb(" + c.getFromColor().getRedInt() + "," +
              c.getFromColor().getGreenInt() + ","
              + c.getFromColor().getBlueInt() + ")" + "\" to=\"" +
              "" + "rgb(" + c.getToColor().getRedInt() + ","
              + c.getToColor().getGreenInt() + ","
              + c.getToColor().getBlueInt() + ")\" fill=\"freeze\" />\n");
    }
    return toReturn.toString();
  }

  /**
   * A helper method for creating all of the animation strings in svg of just the size change
   * actions in this models data.
   *
   * @param id id of shape to change size.
   * @return a String representing this data in SVG form.
   */
  private String svgSizes(String id) {
    StringBuilder toReturn = new StringBuilder();
    String w;
    String h;
    for (AAction a : model.getSizes(id)) {
      if (model.currentShape(0, id).isRectangle()) {
        w = "width";
        h = "height";
      } else {
        w = "rx";
        h = "ry";
      }
      ChangeSize sz = (ChangeSize) a;
      toReturn.append("      ");
      toReturn.append("<animate attributeType=\"xml\" begin=\""
              + sz.getStartTime() * speed + "ms\"" +
              " dur=\"" + speed * (sz.getEndTime() - sz.getStartTime())
              + "ms" + "\" attributeName=\"" + w + "\"" +
              " from=\"" + sz.getStartWidth() + "\" to=\"" +
              "" + sz.getEndWidth() + "\" fill=\"freeze\" />\n");
      toReturn.append("      ");
      toReturn.append("<animate attributeType=\"xml\" begin=\""
              + sz.getStartTime() * speed + "ms\"" +
              " dur=\"" + speed * (sz.getEndTime() - sz.getStartTime())
              + "ms" + "\" attributeName=\"" + h + "\"" +
              " from=\"" + sz.getStartHeight() + "\" to=\"" +
              "" + sz.getEndHeight() + "\" fill=\"freeze\" />\n");

    }
    return toReturn.toString();
  }

  /**
   * A helper method for creating all of the animation strings in svg of just the color change
   * actions in this models data.
   *
   * @param id id of shape to change color.
   * @return a String representing this data in SVG form.
   */
  private String svgMoves(String id) {
    StringBuilder toReturn = new StringBuilder();
    String x;
    String y;
    if (model.currentShape(0, id).isRectangle()) {
      x = "x";
      y = "y";
    } else {
      x = "cx";
      y = "cy";
    }
    for (AAction a : model.getMoves(id)) {
      Move m = (Move) a;
      toReturn.append("      ");
      toReturn.append("<animate attributeType=\"xml\" begin=\""
              + m.getStartTime() * speed + "ms\"" +
              " dur=\"" + speed * (m.getEndTime() - m.getStartTime())
              + "ms" + "\" attributeName=\"" + x + "\"" +
              " from=\"" + m.getStartLoc().getX() + "\" to=\"" +
              "" + m.getEndLoc().getX() + "\" fill=\"freeze\" />\n");
      toReturn.append("      ");
      toReturn.append("<animate attributeType=\"xml\" begin=\""
              + m.getStartTime() * speed + "ms\"" +
              " dur=\"" + speed * (m.getEndTime() - m.getStartTime())
              + "ms" + "\" attributeName=\"" + y + "\"" +
              " from=\"" + m.getStartLoc().getY() + "\" to=\"" +
              "" + m.getEndLoc().getY() + "\" fill=\"freeze\" />\n");
    }
    return toReturn.toString();
  }
}
