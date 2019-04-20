import cs3500.animator.util.Coord;
import cs3500.animator.model.IShape;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.util.Color;
import cs3500.animator.view.TextView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class to test a textView.
 */
public class TextViewTest {
  TextView t1;
  TextView t2;
  TextView mtText;

  AnimationModelImpl a1;
  AnimationModelImpl a2;
  AnimationModelImpl a3;

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
    a1.changeColor("r1", 0, 20, new Color(255, 0, 0), new Color(0, 0, 255));
    a1.changeSize("r1", 0, 20, 5, 5, 15, 15);
    a1.move("r1", 0, 1, new Coord(10, 10), new Coord(11, 11));
    a1.move("r1", 1, 2, new Coord(11, 11), new Coord(12, 12));
    a1.move("r1", 2, 3, new Coord(12, 12), new Coord(13, 13));
    a1.move("r1", 3, 4, new Coord(13, 13), new Coord(14, 14));
    a1.move("r1", 4, 5, new Coord(14, 14), new Coord(15, 15));
    a1.move("r1", 5, 6, new Coord(15, 15), new Coord(16, 16));
    a1.move("r1", 6, 7, new Coord(16, 16), new Coord(17, 17));
    a1.move("r1", 7, 8, new Coord(17, 17), new Coord(18, 18));
    a1.move("r1", 8, 9, new Coord(18, 18), new Coord(19, 19));
    a1.move("r1", 9, 10, new Coord(19, 19), new Coord(20, 20));
    a1.move("r1", 10, 20, new Coord(20, 20), new Coord(30, 30));
    a1.move("r1", 20, 30, new Coord(30, 30), new Coord(99, 99));

    a2.addObject(r, "r1");
    a2.addObject(o, "o1");
    a2.move("r1", 0, 10, new Coord(10, 10), new Coord(20, 20));
    a2.changeColor("r1", 0, 10, new Color(255, 0, 0), new Color(0, 0, 255));
    a2.changeSize("r1", 0, 10, 5, 5, 15, 15);
    a2.move("o1", 0, 40, new Coord(50, 50), new Coord(20, 20));
    a2.changeColor("o1", 0, 20, new Color(255, 0, 0), new Color(0, 0, 255));
    a2.changeSize("o1", 0, 20, 10, 10, 30, 30);

    t1 = new TextView(100, 100, a1);
    t2 = new TextView(100, 100, a2);
    mtText = new TextView(100, 200, a3);
  }

  @Test
  public void testAnimationState() {
    assertEquals("canvas 100 100\n" +
            "            T     X     Y     W     H      R      G      B      " +
            "   T     X     Y     W     H      R      G      B\n" +
            "Motion r1     0    10    10   5.0   5.0    255      0      0     " +
            "    1    11    11   5.5   5.5    242      0     13\n" +
            "Motion r1     1    11    11   5.5   5.5    242      0     13     " +
            "    2    12    12   6.0   6.0    230      0     26\n" +
            "Motion r1     2    12    12   6.0   6.0    230      0     26     " +
            "    3    13    13   6.5   6.5    217      0     38\n" +
            "Motion r1     3    13    13   6.5   6.5    217      0     38    " +
            "     4    14    14   7.0   7.0    204      0     51\n" +
            "Motion r1     4    14    14   7.0   7.0    204      0     51     " +
            "    5    15    15   7.5   7.5    191      0     64\n" +
            "Motion r1     5    15    15   7.5   7.5    191      0     64     " +
            "    6    16    16   8.0   8.0    179      0     77\n" +
            "Motion r1     6    16    16   8.0   8.0    179      0     77     " +
            "    7    17    17   8.5   8.5    166      0     89\n" +
            "Motion r1     7    17    17   8.5   8.5    166      0     89     " +
            "    8    18    18   9.0   9.0    153      0    102\n" +
            "Motion r1     8    18    18   9.0   9.0    153      0    102    " +
            "     9    19    19   9.5   9.5    140      0    115\n" +
            "Motion r1     9    19    19   9.5   9.5    140      0    115    " +
            "    10    20    20  10.0  10.0    128      0    128\n" +
            "Motion r1    10    20    20  10.0  10.0    128      0    128    " +
            "    20    30    30  15.0  15.0      0      0    255\n" +
            "Motion r1    20    30    30  15.0  15.0      0      0    255    " +
            "    30    99    99  15.0  15.0      0      0    255\n" +
            "\n" +
            "\n", t1.animationState());

    /**
     * tests for text view with only one motion.
     */
    assertEquals("canvas 100 100\n" +
            "            T     X     Y     W     H      R      G      B      " +
            "   T     X     Y     W     H      R      G      B\n" +
            "Motion r1     0    10    10   5.0   5.0    255      0      0    " +
            "    10    20    20  15.0  15.0      0      0    255\n" +
            "\n" +
            "\n" +
            "Motion o1     0    50    50  10.0  10.0    255      0      0    " +
            "    40    20    20  30.0  30.0      0      0    255\n\n\n", t2.animationState());
    /**
     * tests for empty text view.
     */
    assertEquals("canvas 100 200\n" +
            "            T     X     Y     W     H      R      G      B    " +
            "     T     X     Y     W     H      R      G      B\n", mtText.animationState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    new TextView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModeTwol() {
    new TextView(10, 10, null);
  }

  /**
   * The following two tests ensure exceptions are thrown when unsupported methods are called.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void toSVG() {
    t1.toSVG();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testMakeVisible() {
    t1.makeVisible();
  }

}