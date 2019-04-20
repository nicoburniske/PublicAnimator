import org.junit.Test;

import cs3500.animator.util.Coord;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.util.Color;

import static org.junit.Assert.assertEquals;

/**
 * Class to test a shape.
 */
public class ShapeTest {
  Rectangle r1 = new Rectangle(new Color(255, 0, 0), new Coord(1, 1), 5,5);
  Oval o1 =  new Oval(new Color(255, 0, 0), new Coord(0, 0), 5,5);

  @Test
  public void from() {
    assertEquals(false, o1.from(new Color(255, 0, 0), new Coord(0, 0), 5,5).isRectangle());
    assertEquals(true, r1.from(new Color(255, 0, 0), new Coord(0, 0), 5,5).isRectangle());
  }

  @Test
  public void isRectangle() {
    assertEquals(true, r1.isRectangle());
    assertEquals(false, o1.isRectangle());
  }
}