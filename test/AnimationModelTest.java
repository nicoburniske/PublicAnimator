import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Oval;
import cs3500.animator.util.Coord;
import cs3500.animator.model.ChangeColor;
import cs3500.animator.model.ChangeSize;
import cs3500.animator.model.Move;
import cs3500.animator.model.AAction;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import cs3500.animator.util.Color;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Class to test an animationModel.
 */
public class AnimationModelTest {
  AnimationModel a1;
  AnimationModel a2;
  AnimationModel a3;

  IShape r;
  IShape o;

  @Before
  public void initialize() {
    a1 = new AnimationModelImpl();
    a2 = new AnimationModelImpl();
    a3 = new AnimationModelImpl();

    r = new Rectangle();
    o = new Oval();

    a1.addObject(r, "r1");
    a1.move("r1", 0, 10, new Coord(10, 10), new Coord(20, 20));
    a1.move("r1", 10, 20, new Coord(20, 20), new Coord(30, 30));
    a1.changeColor("r1", 0, 20, new Color(255, 0, 0), new Color(0, 0, 255));
    a1.changeSize("r1", 0, 20, 5, 5, 15, 15);

    a2.addObject(r, "r1");
    a2.addObject(o, "o1");
    a2.move("r1", 0, 10, new Coord(10, 10), new Coord(20, 20));
    a2.changeColor("r1", 0, 10, new Color(255, 0, 0), new Color(0, 0, 255));
    a2.changeSize("r1", 0, 10, 5, 5, 15, 15);
    a2.move("o1", 0, 20, new Coord(50, 50), new Coord(20, 20));
    a2.changeColor("o1", 0, 20, new Color(0, 255, 0), new Color(0, 0, 255));
    a2.changeSize("o1", 0, 20, 10, 10, 30, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddObjectAlreadyExists() {
    a1.addObject(new Rectangle(), "r1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveToNonExistentShape() {
    a1.move("r3", 5, 5, new Coord(20, 2), new Coord(2, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddColorToNonExistentShape() {
    a1.changeColor("r3", 5, 5, new Color(0, 0, 255), new Color(0, 0, 255));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddSizeToNonExistentShape() {
    a1.changeSize("r3", 5, 5, 5, 5, 10, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMoveToOccupiedInterval() {
    a1.move("r1", 1, 3, new Coord(10, 10), new Coord(20, 20));
  }

  @Test
  public void sizeOfAnimations() {
    assertEquals(0, a3.getIDSet().size());
    a3.addObject(new Rectangle(), "r1");
    assertEquals(1, a3.getIDSet().size());
    a3.addObject(new Rectangle(), "r2");
    assertEquals(2, a3.getIDSet().size());
    a3.addObject(new Rectangle(), "r3");
    assertEquals(3, a3.getIDSet().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void matchStartEndState() {
    a1.move("r1", 19, 40, new Coord(30, 30), new Coord(40, 40));
  }

  @Test(expected = IllegalArgumentException.class)
  public void gapInInterval() {
    a1.move("r1", 30, 40, new Coord(30, 30), new Coord(40, 40));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetMovesNonExistingID() {
    a1.getMoves("!");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSizesNonExistingID() {
    a1.getSizes("!");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetColorsNonExistingID() {
    a1.getColors("!");
  }

  @Test
  public void testMoveColorSize1Shape() {
    List<IShape> current = a1.currentFrame(5);
    assertEquals(current.size(), 1);
    assertEquals(15, current.get(0).getLocation().getX(), 0.001);
    assertEquals(15, current.get(0).getLocation().getY(), 0.001);
    assertEquals(7.5, current.get(0).getHeight(), 0);
    assertEquals(7.5, current.get(0).getWidth(), 0);

    assertEquals(0.75, current.get(0).getColor().getRed(), 0.001);
    assertEquals(0, current.get(0).getColor().getGreen(), 0.001);
    assertEquals(0.25, current.get(0).getColor().getBlue(), 0.001);

    current = a1.currentFrame(10);
    assertEquals(current.size(), 1);
    assertEquals(20, current.get(0).getLocation().getX(), 0.001);
    assertEquals(20, current.get(0).getLocation().getY(), 0.001);
    assertEquals(10, current.get(0).getHeight(), 0);
    assertEquals(10, current.get(0).getWidth(), 0);

    assertEquals(0.5, current.get(0).getColor().getRed(), 0.001);
    assertEquals(0.0, current.get(0).getColor().getGreen(), 0.001);
    assertEquals(0.5, current.get(0).getColor().getBlue(), 0.001);

    current = a1.currentFrame(20);
    assertEquals(30, current.get(0).getLocation().getX(), 0.001);
    assertEquals(30, current.get(0).getLocation().getY(), 0.001);
    assertEquals(15, current.get(0).getHeight(), 0);
    assertEquals(15, current.get(0).getWidth(), 0);

    assertEquals(0, current.get(0).getColor().getRed(), 0.001);
    assertEquals(0.0, current.get(0).getColor().getGreen(), 0.001);
    assertEquals(1, current.get(0).getColor().getBlue(), 0.001);

    current = a1.currentFrame(25);
    assertEquals(30, current.get(0).getLocation().getX(), 0.001);
    assertEquals(30, current.get(0).getLocation().getY(), 0.001);
    assertEquals(15, current.get(0).getHeight(), 0);
    assertEquals(15, current.get(0).getWidth(), 0);

    assertEquals(0, current.get(0).getColor().getRed(), 0.001);
    assertEquals(0.0, current.get(0).getColor().getGreen(), 0.001);
    assertEquals(1, current.get(0).getColor().getBlue(), 0.001);
  }

  @Test
  public void testMoveColorSize2Shapes() {
    List<IShape> current = a2.currentFrame(5);

    assertEquals(current.size(), 2);
    assertEquals(15, current.get(0).getLocation().getX(), 0.001);
    assertEquals(15, current.get(0).getLocation().getY(), 0.001);
    assertEquals(10, current.get(0).getHeight(), 0);
    assertEquals(10, current.get(0).getWidth(), 0);
    assertEquals(0.5, current.get(0).getColor().getRed(), 0.001);
    assertEquals(0, current.get(0).getColor().getGreen(), 0.001);
    assertEquals(0.5, current.get(0).getColor().getBlue(), 0.001);

    assertEquals(42.5, current.get(1).getLocation().getX(), 0.001);
    assertEquals(42.5, current.get(1).getLocation().getY(), 0.001);
    assertEquals(15, current.get(1).getHeight(), 0);
    assertEquals(15, current.get(1).getWidth(), 0);
    assertEquals(0, current.get(1).getColor().getRed(), 0.001);
    assertEquals(0.75, current.get(1).getColor().getGreen(), 0.001);
    assertEquals(0.25, current.get(1).getColor().getBlue(), 0.001);

    current = a2.currentFrame(10);

    assertEquals(current.size(), 2);
    assertEquals(20, current.get(0).getLocation().getX(), 0.001);
    assertEquals(20, current.get(0).getLocation().getY(), 0.001);
    assertEquals(15, current.get(0).getHeight(), 0);
    assertEquals(15, current.get(0).getWidth(), 0);
    assertEquals(0, current.get(0).getColor().getRed(), 0.001);
    assertEquals(0, current.get(0).getColor().getGreen(), 0.001);
    assertEquals(1, current.get(0).getColor().getBlue(), 0.001);

    assertEquals(35, current.get(1).getLocation().getX(), 0.001);
    assertEquals(35, current.get(1).getLocation().getY(), 0.001);
    assertEquals(20, current.get(1).getHeight(), 0);
    assertEquals(20, current.get(1).getWidth(), 0);
    assertEquals(0, current.get(1).getColor().getRed(), 0.001);
    assertEquals(0.5, current.get(1).getColor().getGreen(), 0.001);
    assertEquals(0.5, current.get(1).getColor().getBlue(), 0.001);

    current = a2.currentFrame(20);

    assertEquals(current.size(), 2);
    assertEquals(20, current.get(0).getLocation().getX(), 0.001);
    assertEquals(20, current.get(0).getLocation().getY(), 0.001);
    assertEquals(15, current.get(0).getHeight(), 0);
    assertEquals(15, current.get(0).getWidth(), 0);
    assertEquals(0, current.get(0).getColor().getRed(), 0.001);
    assertEquals(0, current.get(0).getColor().getGreen(), 0.001);
    assertEquals(1, current.get(0).getColor().getBlue(), 0.001);

    assertEquals(20, current.get(1).getLocation().getX(), 0.001);
    assertEquals(20, current.get(1).getLocation().getY(), 0.001);
    assertEquals(30, current.get(1).getHeight(), 0);
    assertEquals(30, current.get(1).getWidth(), 0);
    assertEquals(0, current.get(1).getColor().getRed(), 0.001);
    assertEquals(0, current.get(1).getColor().getGreen(), 0.001);
    assertEquals(1, current.get(1).getColor().getBlue(), 0.001);
  }

  @Test
  public void testCurrentFrame() {
    assertEquals(1, a1.currentFrame(0).size());
    assertEquals(2, a2.currentFrame(0).size());
    assertEquals(0, a3.currentFrame(0).size());

    assertEquals(new Coord(10, 10), a1.currentFrame(0).get(0).getLocation());
    assertEquals(5, a1.currentFrame(0).get(0).getHeight(), 0.001);
    assertEquals(5, a1.currentFrame(0).get(0).getWidth(), 0.001);
    assertEquals(1, a1.currentFrame(0).get(0).getColor().getRed(), 0.001);
    assertEquals(0, a1.currentFrame(0).get(0).getColor().getGreen(), 0.001);
    assertEquals(0, a1.currentFrame(0).get(0).getColor().getBlue(), 0.001);


    assertEquals(new Coord(20, 20), a1.currentFrame(10).get(0).getLocation());
    assertEquals(10, a1.currentFrame(10).get(0).getHeight(), 0.001);
    assertEquals(10, a1.currentFrame(10).get(0).getWidth(), 0.001);
    assertEquals(0.5, a1.currentFrame(10).get(0).getColor().getRed(), 0.001);
    assertEquals(0, a1.currentFrame(10).get(0).getColor().getGreen(), 0.001);
    assertEquals(0.5, a1.currentFrame(10).get(0).getColor().getBlue(), 0.001);

    assertEquals(new Coord(30, 30), a1.currentFrame(20).get(0).getLocation());
    assertEquals(15, a1.currentFrame(20).get(0).getHeight(), 0.001);
    assertEquals(15, a1.currentFrame(20).get(0).getWidth(), 0.001);
    assertEquals(0, a1.currentFrame(20).get(0).getColor().getRed(), 0.001);
    assertEquals(0, a1.currentFrame(20).get(0).getColor().getGreen(), 0.001);
    assertEquals(1, a1.currentFrame(20).get(0).getColor().getBlue(), 0.001);

    assertEquals(new Coord(10, 10), a2.currentFrame(0).get(0).getLocation());
    assertEquals(new Coord(20, 20), a2.currentFrame(10).get(0).getLocation());
    assertEquals(new Coord(20, 20), a2.currentFrame(20).get(0).getLocation());

    assertEquals(new Coord(50, 50), a2.currentFrame(0).get(1).getLocation());
    assertEquals(new Coord(35, 35), a2.currentFrame(10).get(1).getLocation());
    assertEquals(new Coord(20, 20), a2.currentFrame(20).get(1).getLocation());
  }

  @Test
  public void testCurrentShape() {
    assertEquals(10, a1.currentShape(0, "r1").getLocation().getX(), 0.001);
    assertEquals(10, a1.currentShape(0, "r1").getLocation().getY(), 0.001);
    assertEquals(5, a1.currentShape(0, "r1").getHeight(), 0);
    assertEquals(5, a1.currentShape(0, "r1").getWidth(), 0);

    assertEquals(1, a1.currentShape(0, "r1").getColor().getRed(), 0.001);
    assertEquals(0, a1.currentShape(0, "r1").getColor().getGreen(), 0.001);
    assertEquals(0, a1.currentShape(0, "r1").getColor().getBlue(), 0.001);

    assertEquals(20, a1.currentShape(10, "r1").getLocation().getX(), 0.001);
    assertEquals(20, a1.currentShape(10, "r1").getLocation().getY(), 0.001);
    assertEquals(10, a1.currentShape(10, "r1").getHeight(), 0);
    assertEquals(10, a1.currentShape(10, "r1").getWidth(), 0);

    assertEquals(0.5, a1.currentShape(10, "r1").getColor().getRed(), 0.001);
    assertEquals(0.0, a1.currentShape(10, "r1").getColor().getGreen(), 0.001);
    assertEquals(0.5, a1.currentShape(10, "r1").getColor().getBlue(), 0.001);
    assertEquals(20, a1.currentShape(10, "r1").getLocation().getX(), 0.001);
    assertEquals(20, a1.currentShape(10, "r1").getLocation().getY(), 0.001);
    assertEquals(10, a1.currentShape(10, "r1").getHeight(), 0);
    assertEquals(10, a1.currentShape(10, "r1").getWidth(), 0);

    assertEquals(0, a1.currentShape(20, "r1").getColor().getRed(), 0.001);
    assertEquals(0, a1.currentShape(20, "r1").getColor().getGreen(), 0.001);
    assertEquals(1, a1.currentShape(20, "r1").getColor().getBlue(), 0.001);

    assertEquals(30, a1.currentShape(20, "r1").getLocation().getX(), 0.001);
    assertEquals(30, a1.currentShape(20, "r1").getLocation().getY(), 0.001);
    assertEquals(15, a1.currentShape(20, "r1").getHeight(), 0);
    assertEquals(15, a1.currentShape(20, "r1").getWidth(), 0);

    assertEquals(0, a1.currentShape(30, "r1").getColor().getRed(), 0.001);
    assertEquals(0.0, a1.currentShape(30, "r1").getColor().getGreen(), 0.001);
    assertEquals(1, a1.currentShape(30, "r1").getColor().getBlue(), 0.001);

    assertEquals(50, a2.currentShape(0, "o1").getLocation().getX(), 0.001);
    assertEquals(50, a2.currentShape(0, "o1").getLocation().getY(), 0.001);
    assertEquals(10, a2.currentShape(0, "o1").getHeight(), 0);
    assertEquals(10, a2.currentShape(0, "o1").getWidth(), 0);
    assertEquals(0, a2.currentShape(0, "o1").getColor().getRed(), 0.001);
    assertEquals(1, a2.currentShape(0, "o1").getColor().getGreen(), 0.001);
    assertEquals(0, a2.currentShape(0, "o1").getColor().getBlue(), 0.001);
  }

  @Test
  public void testStartEnd() {
    Map<String, Map<Integer, Integer>> startEnd = a1.startEnd();
    Map<Integer, Integer> shape1History = startEnd.get("r1");

    assertEquals(Integer.valueOf(10), shape1History.get(0));
    assertEquals(Integer.valueOf(20), shape1History.get(10));

    startEnd = a2.startEnd();
    shape1History = startEnd.get("r1");
    Map<Integer, Integer> shape2History = startEnd.get("o1");

    assertEquals(Integer.valueOf(10), shape1History.get(0));
    assertEquals(Integer.valueOf(10), shape1History.get(0));

    startEnd = a3.startEnd();
    assertEquals(true, startEnd.isEmpty());
  }

  @Test
  public void testRemoveObject() {
    assertEquals(1, a1.getIDSet().size());
    a1.removeObject("r1");
    assertEquals(0, a1.getIDSet().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveException() {
    a3.removeObject("1234");
  }

  @Test
  public void testAddKeyFrame() {
    this.a1 = new AnimationModelImpl();
    a1.addObject(new Rectangle(), "R");
    a1.move("R", 0, 10, new Coord(0, 0), new Coord(10, 10));
    a1.changeColor("R", 0, 10, new Color(255, 0, 0), new Color(0, 0, 255));
    a1.changeSize("R", 0, 10, 0, 0, 10, 10);
    IShape current = a1.currentShape(5, "R");
    assertEquals(5, current.getLocation().getX(), 0.01);
    assertEquals(5, current.getLocation().getY(), 0.01);
    assertEquals(5, current.getHeight(), 0.01);
    assertEquals(5, current.getWidth(), 0.01);
    assertEquals(128, current.getColor().getRedInt(), 0.01);
    assertEquals(0, current.getColor().getGreenInt(), 0.01);
    assertEquals(128, current.getColor().getBlueInt(), 0.01);

    a1.addKeyFrame("R", 5, 20, 20, 20, 20, 0, 255, 0);

    current = a1.currentShape(5, "R");
    assertEquals(20, current.getLocation().getX(), 0.01);
    assertEquals(20, current.getLocation().getY(), 0.01);
    assertEquals(20, current.getHeight(), 0.01);
    assertEquals(20, current.getWidth(), 0.01);
    assertEquals(0, current.getColor().getRedInt(), 0.01);
    assertEquals(255, current.getColor().getGreenInt(), 0.01);
    assertEquals(0, current.getColor().getBlueInt(), 0.01);

    a1.deleteKeyFrame("R", 5);

    current = a1.currentShape(5, "R");
    assertEquals(20, current.getLocation().getX(), 0.01);
    assertEquals(20, current.getLocation().getY(), 0.01);
    assertEquals(20, current.getHeight(), 0.01);
    assertEquals(20, current.getWidth(), 0.01);
    assertEquals(0, current.getColor().getRedInt(), 0.01);
    assertEquals(255, current.getColor().getGreenInt(), 0.01);
    assertEquals(0, current.getColor().getBlueInt(), 0.01);
  }

  @Test
  public void testRemoveKeyFrame() {
    Coord current;
    a3.addObject(r, "r1");
    a3.move("r1", 0, 10, new Coord(10, 10), new Coord(20, 20));
    a3.move("r1", 10, 20, new Coord(20, 20), new Coord(40, 40));
    a3.changeColor("r1", 0, 10, new Color(255, 0, 0), new Color(0, 0, 255));
    a3.changeColor("r1", 10, 20, new Color(0, 0, 255), new Color(0, 255, 0));
    a3.changeSize("r1", 0, 10, 5, 5, 15, 15);
    a3.changeSize("r1", 10, 20, 15, 15, 50, 50);

    current = a3.currentShape(10, "r1").getLocation();

    a3.deleteKeyFrame("r1", 10);

    current = a3.currentShape(10, "r1").getLocation();

    assertEquals(20, current.getX(), 0.001);
    assertEquals(20, current.getY(), 0.001);

    System.out.println(a3.getKeyFrames("r1"));


  }

  @Test
  public void testGetIDSet() {
    assertEquals(new HashSet<String>(Arrays.asList("r1")), a1.getIDSet());
    assertEquals(new HashSet<String>(Arrays.asList("r1", "o1")), a2.getIDSet());
  }

  @Test
  public void testGetKeyFrame() {
    String[] a1Frames = {"0", "10", "20"};
    assertArrayEquals(a1Frames, a1.getKeyFrames("r1"));

    String[] a2Frames = {"0", "10"};
    assertArrayEquals(a2Frames, a2.getKeyFrames("r1"));

    String[] a2Frames2 = {"0", "20"};
    assertArrayEquals(a2Frames2, a2.getKeyFrames("o1"));
  }

  @Test
  public void testGetActionsAsList() {
    String[] a1Keys = {"r1"};
    assertArrayEquals(a1Keys, a1.getActionsIdAsList());

    String[] a2Keys = {"o1", "r1"};
    assertArrayEquals(a2Keys, a2.getActionsIdAsList());
  }

  @Test
  public void testGetMoves() {
    Move move1 = new Move(0, 10, new Coord(10, 10), new Coord(20, 20));
    Move move2 = new Move(10, 20, new Coord(20, 20), new Coord(30, 30));

    List<AAction> getMoves1 = a1.getMoves("r1");
    assertEquals(2, getMoves1.size());


    Move current = (Move) getMoves1.get(0);

    assertEquals((move1).getStartLoc(), current.getStartLoc());
    assertEquals((move1).getEndLoc(), current.getEndLoc());
    assertEquals((move1).getStartTime(), current.getStartTime());
    assertEquals((move1).getEndTime(), current.getEndTime());

    current = (Move) getMoves1.get(1);

    assertEquals((move2).getStartLoc(), current.getStartLoc());
    assertEquals((move2).getEndLoc(), current.getEndLoc());
    assertEquals((move2).getStartTime(), current.getStartTime());
    assertEquals((move2).getEndTime(), current.getEndTime());
  }

  @Test
  public void testGetSizes() {
    ChangeSize size1 = new ChangeSize(0, 20, 5, 5, 15, 15);

    ChangeSize current = (ChangeSize) a1.getSizes("r1").get(0);
    assertEquals(size1.getStartTime(), current.getStartTime());
    assertEquals(size1.getEndTime(), current.getEndTime());
    assertEquals(size1.getStartHeight(), current.getStartHeight(), 0.001);
    assertEquals(size1.getStartWidth(), current.getStartWidth(), 0.001);
    assertEquals(size1.getEndHeight(), current.getEndHeight(), 0.001);
    assertEquals(size1.getEndWidth(), current.getEndWidth(), 0.001);
  }

  @Test
  public void testGetColors() {
    ChangeColor c1 = new ChangeColor(0, 20, new Color(255, 0, 0), new Color(0, 0, 255));
    ChangeColor current = (ChangeColor) a1.getColors("r1").get(0);

    assertEquals(c1.getStartTime(), current.getStartTime());
    assertEquals(c1.getEndTime(), current.getEndTime());

    assertEquals(c1.getFromColor().getRedInt(), current.getFromColor().getRedInt());
    assertEquals(c1.getFromColor().getGreenInt(), current.getFromColor().getGreenInt());
    assertEquals(c1.getFromColor().getBlueInt(), current.getFromColor().getBlueInt());

    assertEquals(c1.getToColor().getRedInt(), current.getToColor().getRedInt());
    assertEquals(c1.getToColor().getGreenInt(), current.getToColor().getGreenInt());
    assertEquals(c1.getToColor().getBlueInt(), current.getToColor().getBlueInt());
  }
}
