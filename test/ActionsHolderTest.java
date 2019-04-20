import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import cs3500.animator.model.ActionsHolder;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.ChangeColor;
import cs3500.animator.util.Coord;
import cs3500.animator.model.Move;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ChangeSize;
import cs3500.animator.util.Color;

import static org.junit.Assert.assertEquals;

/**
 * Class to test an acitonholder.
 */
public class ActionsHolderTest {
  AnimationModelImpl a1;
  ActionsHolder action1;
  ChangeSize s1;
  ChangeSize s2;
  Move m1;
  Move m2;
  ChangeColor c1;
  ChangeColor c2;

  @Before
  public void initialize() {
    action1 = new ActionsHolder();
    s1 = new ChangeSize(0, 5, 5, 5, 10, 10);
    s2 = new ChangeSize(5, 10, 10, 10, 40, 44);

    m1 = new Move(0, 5, new Coord(0, 0), new Coord(10, 10));
    m2 = new Move(5, 10, new Coord(10, 10), new Coord(-5, -5));

    c1 = new ChangeColor(0, 5, new Color(0, 0, 255), new Color(255, 0, 0));
    c2 = new ChangeColor(5, 10, new Color(255, 0, 0), new Color(0, 0, 0));

    a1 = new AnimationModelImpl();
    a1.addObject(new Rectangle(), "R");

    a1.move("R", 0, 5, new Coord(0, 0), new Coord(10, 10));
    a1.move("R", 5, 10, new Coord(10, 10), new Coord(20, 20));

    a1.changeColor("R", 0, 5, new Color(255, 0, 0), new Color(0, 0, 255));
    a1.changeColor("R", 5, 10, new Color(0, 0, 255), new Color(128, 0, 128));

    a1.changeSize("R", 0, 5, 5, 5, 10, 10);
    a1.changeSize("R", 5, 10, 10, 10, 20, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addMoveException() {
    action1.addMove(m1);
    action1.addMove(m1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addSizeException() {
    action1.addSize(s1);
    action1.addSize(s1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addColorException() {
    action1.addColor(c1);
    action1.addColor(c1);
  }

  @Test
  public void addMove() {
    action1.addMove(m1);
    assertEquals(1, action1.getMoves().size());
  }

  @Test
  public void addSize() {
    action1.addSize(s1);
    assertEquals(1, action1.getSizes().size());
  }

  @Test
  public void addColor() {
    action1.addColor(c1);
    assertEquals(1, action1.getColors().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyMoves() {
    action1.getCurrentLoc(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyHeight() {
    action1.getCurrentHeight(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyWidth() {
    action1.getCurrentWidth(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void intervalOccupiedMove() {
    m2 = new Move(2, 4, new Coord(10, 10), new Coord(-5, -5));
    action1.addMove(m1);
    action1.addMove(m2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void intervalOccupiedSize() {
    s2 = new ChangeSize(2, 4, 10, 10, 40, 44);
    action1.addSize(s1);
    action1.addSize(s2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void intervalOccupiedColor() {
    c2 = new ChangeColor(2, 3, new Color(255, 0, 0), new Color(0, 0, 0));
    action1.addColor(c1);
    action1.addColor(c2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyColor() {
    action1.getCurrentColor(0);
  }

  @Test
  public void getCurrentLoc() {
    action1.addMove(m1);
    action1.addMove(m2);
    assertEquals(0, action1.getCurrentLoc(0).getX(), 0.001);
    assertEquals(0, action1.getCurrentLoc(0).getY(), 0.001);

    assertEquals(6, action1.getCurrentLoc(3).getX(), 0.001);
    assertEquals(6, action1.getCurrentLoc(3).getY(), 0.001);

    assertEquals(10, action1.getCurrentLoc(5).getX(), 0.001);
    assertEquals(10, action1.getCurrentLoc(5).getY(), 0.001);

    assertEquals(4, action1.getCurrentLoc(7).getX(), 0.001);
    assertEquals(4, action1.getCurrentLoc(7).getY(), 0.001);

    assertEquals(-5, action1.getCurrentLoc(10).getX(), 0.001);
    assertEquals(-5, action1.getCurrentLoc(10).getY(), 0.001);
  }

  @Test
  public void getCurrentHeightWidth() {
    action1.addSize(s1);
    action1.addSize(s2);

    assertEquals(5, action1.getCurrentHeight(0), 0.001);
    assertEquals(5, action1.getCurrentWidth(0), 0.001);

    assertEquals(8, action1.getCurrentHeight(3), 0.001);
    assertEquals(8, action1.getCurrentWidth(3), 0.001);

    assertEquals(28, action1.getCurrentHeight(8), 0.001);
    assertEquals(30.4, action1.getCurrentWidth(8), 0.001);

    assertEquals(40, action1.getCurrentHeight(10), 0.001);
    assertEquals(44, action1.getCurrentWidth(10), 0.001);
  }

  @Test
  public void getCurrentColor() {
    action1.addColor(c1);
    action1.addColor(c2);

    assertEquals(0, action1.getCurrentColor(0).getRed(), 0.01);
    assertEquals(0, action1.getCurrentColor(0).getGreen(), 0.01);
    assertEquals(1, action1.getCurrentColor(0).getBlue(), 0.01);

    assertEquals(0.6, action1.getCurrentColor(3).getRed(), 0.01);
    assertEquals(0, action1.getCurrentColor(3).getGreen(), 0.01);
    assertEquals(0.4, action1.getCurrentColor(3).getBlue(), 0.01);

    assertEquals(1, action1.getCurrentColor(5).getRed(), 0.01);
    assertEquals(0, action1.getCurrentColor(5).getGreen(), 0.01);
    assertEquals(0, action1.getCurrentColor(5).getBlue(), 0.01);

    assertEquals(0.6, action1.getCurrentColor(7).getRed(), 0.01);
    assertEquals(0, action1.getCurrentColor(7).getGreen(), 0.01);
    assertEquals(0, action1.getCurrentColor(7).getBlue(), 0.01);

    assertEquals(0, action1.getCurrentColor(10).getRed(), 0.01);
    assertEquals(0, action1.getCurrentColor(10).getGreen(), 0.01);
    assertEquals(0, action1.getCurrentColor(10).getBlue(), 0.01);
  }

  @Test
  public void startAndEndTime() {
    Map<Integer, Integer> m = action1.startAndEndTime();
    assertEquals(0, m.size());

    action1.addMove(m1);
    action1.addMove(m2);
    m = action1.startAndEndTime();

    assertEquals(Integer.valueOf(5), m.get(0));
    assertEquals(Integer.valueOf(10), m.get(5));
  }

  @Test
  public void testActionsKeyFrame() {
    action1.actionsKeyFrame(0, 5, 5, 10, 10, 255, 0, 0);

    assertEquals(new Coord(5, 5), action1.getCurrentLoc(5));
    assertEquals(10, action1.getCurrentWidth(5), 0.001);
    assertEquals(10, action1.getCurrentHeight(5), 0.001);
    assertEquals(255, action1.getCurrentColor(5).getRedInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getGreenInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getBlueInt(), 0.001);

    action1.actionsKeyFrame(0, 100, 115, 100, 110, 0, 10, 10);

    assertEquals(new Coord(5, 5), action1.getCurrentLoc(5));
    assertEquals(10, action1.getCurrentWidth(5), 0.001);
    assertEquals(10, action1.getCurrentHeight(5), 0.001);
    assertEquals(255, action1.getCurrentColor(5).getRedInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getGreenInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getBlueInt(), 0.001);

    action1.actionsKeyFrame(5, 50, 50, 100, 100, 0, 255, 0);

    assertEquals(new Coord(50, 50), action1.getCurrentLoc(5));
    assertEquals(100, action1.getCurrentWidth(5), 0.001);
    assertEquals(100, action1.getCurrentHeight(5), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getRedInt(), 0.001);
    assertEquals(255, action1.getCurrentColor(5).getGreenInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getBlueInt(), 0.001);
  }

  @Test
  public void testDeleteKeyFrame() {
    action1.actionsKeyFrame(0, 5, 5, 10, 10, 255, 0, 0);
    action1.actionsKeyFrame(5, 50, 50, 100, 100, 0, 255, 0);

    assertEquals(14, action1.getCurrentLoc(1).getX(), 0.001);
    assertEquals(14, action1.getCurrentLoc(1).getY(), 0.001);

    assertEquals(28, action1.getCurrentWidth(1), 0.001);
    assertEquals(28, action1.getCurrentHeight(1), 0.001);
    assertEquals(204, action1.getCurrentColor(1).getRedInt(), 0.001);
    assertEquals(51, action1.getCurrentColor(1).getGreenInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(1).getBlueInt(), 0.001);

    assertEquals(new Coord(50, 50), action1.getCurrentLoc(5));
    assertEquals(100, action1.getCurrentWidth(5), 0.001);
    assertEquals(100, action1.getCurrentHeight(5), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getRedInt(), 0.001);
    assertEquals(255, action1.getCurrentColor(5).getGreenInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getBlueInt(), 0.001);

    action1.deleteKeyFrame(5);

    assertEquals(new Coord(5, 5), action1.getCurrentLoc(1));
    assertEquals(10, action1.getCurrentWidth(1), 0.001);
    assertEquals(10, action1.getCurrentHeight(1), 0.001);
    assertEquals(255, action1.getCurrentColor(1).getRedInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(1).getGreenInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(1).getBlueInt(), 0.001);
  }

  @Test
  public void testAddKeyFrameBetween() {

    action1.actionsKeyFrame(0, 5, 5, 10, 10, 255, 0, 0);
    action1.actionsKeyFrame(5, 50, 50, 100, 100, 0, 255, 0);

    assertEquals(14, action1.getCurrentLoc(1).getX(), 0.001);
    assertEquals(14, action1.getCurrentLoc(1).getY(), 0.001);

    assertEquals(28, action1.getCurrentWidth(1), 0.001);
    assertEquals(28, action1.getCurrentHeight(1), 0.001);
    assertEquals(204, action1.getCurrentColor(1).getRedInt(), 0.001);
    assertEquals(51, action1.getCurrentColor(1).getGreenInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(1).getBlueInt(), 0.001);

    assertEquals(new Coord(50, 50), action1.getCurrentLoc(5));
    assertEquals(100, action1.getCurrentWidth(5), 0.001);
    assertEquals(100, action1.getCurrentHeight(5), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getRedInt(), 0.001);
    assertEquals(255, action1.getCurrentColor(5).getGreenInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getBlueInt(), 0.001);

    action1.actionsKeyFrame(3, 5, 5, 10000, 1000, 255, 0, 0);
    assertEquals(5050.0, action1.getCurrentWidth(4), 0.001);
    assertEquals(550.0, action1.getCurrentHeight(4), 0.001);

  }

  @Test
  public void deleteFirstKeyFrame() {

    action1.actionsKeyFrame(0, 5, 5, 10, 10, 255, 0, 0);
    action1.actionsKeyFrame(5, 50, 50, 100, 100, 0, 255, 0);

    assertEquals(14, action1.getCurrentLoc(1).getX(), 0.001);
    assertEquals(14, action1.getCurrentLoc(1).getY(), 0.001);

    assertEquals(28, action1.getCurrentWidth(1), 0.001);
    assertEquals(28, action1.getCurrentHeight(1), 0.001);
    assertEquals(204, action1.getCurrentColor(1).getRedInt(), 0.001);
    assertEquals(51, action1.getCurrentColor(1).getGreenInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(1).getBlueInt(), 0.001);

    assertEquals(new Coord(50, 50), action1.getCurrentLoc(5));
    assertEquals(100, action1.getCurrentWidth(5), 0.001);
    assertEquals(100, action1.getCurrentHeight(5), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getRedInt(), 0.001);
    assertEquals(255, action1.getCurrentColor(5).getGreenInt(), 0.001);
    assertEquals(0, action1.getCurrentColor(5).getBlueInt(), 0.001);

    action1.deleteKeyFrame(1);

  }
}