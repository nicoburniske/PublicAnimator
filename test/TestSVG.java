import org.junit.Before;
import org.junit.Test;

import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.util.Coord;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.util.Color;
import cs3500.animator.view.SVGView;

import static junit.framework.TestCase.assertEquals;

/**
 * Class to test svgview.
 */
public class TestSVG {

  AnimationModelImpl m1;
  AnimationModelImpl m2;
  AnimationModelImpl m3;
  SVGView svg1;
  SVGView svg2;
  SVGView svg3;
  SVGView svg4;
  SVGView mtSVG;

  @Before
  public void init() {
    m1 = new AnimationModelImpl();
    m2 = new AnimationModelImpl();
    m3 = new AnimationModelImpl();

    m1.addObject(new Oval(), "O");
    m1.move("O", 0, 20, new Coord(20, 20), new Coord(30, 30));
    m1.changeColor("O", 0, 20, new Color(0, 0, 0), new Color(1, 1, 1));
    m1.changeSize("O", 0, 20, 20, 20, 40, 40);

    m1.addObject(new Rectangle(), "R");
    m1.move("R", 0, 20, new Coord(20, 20), new Coord(30, 30));
    m1.changeColor("R", 0, 20, new Color(0, 0, 0), new Color(1, 1, 1));
    m1.changeSize("R", 0, 20, 20, 20, 40, 40);

    svg1 = new SVGView(m1, 50, 50);

    m2.addObject(new Oval(), "O1");
    m2.addObject(new Oval(), "O2");

    m2.move("O1", 0, 20, new Coord(20, 20), new Coord(30, 30));
    m2.changeColor("O1", 0, 20,
            new Color(0.0, 0.0, 0.0), new Color(1, 1, 1));
    m2.changeSize("O1", 0, 20, 20, 20, 40, 40);

    m2.move("O2", 0, 10, new Coord(20, 20), new Coord(30, 30));
    m2.move("O2", 10, 15, new Coord(30, 30), new Coord(40, 40));
    m2.move("O2", 15, 20, new Coord(40, 40), new Coord(0, 0));
    m2.changeColor("O2", 0, 10, new Color(0.0, 0.0, 0), new Color(1.0, 0.5, 0.5));
    m2.changeColor("O2", 10, 20, new Color(0.0, 0, 0), new Color(0, 1, 1));
    m2.changeSize("O2", 0, 10, 20, 20, 40, 40);
    m2.changeSize("O2", 10, 20, 40, 40, 80, 80);

    svg2 = new SVGView(m2, 500, 500);

    mtSVG = new SVGView(m3, 1000, 1000);

    m1.setBounds(new AnimationModelImpl.BoundHolder(100, 100, 100, 100));
    svg3 = new SVGView(m1, 20);

    svg4 = new SVGView(m1, 10);

  }

  @Test
  public void testSVGThree() {
    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "</svg>", mtSVG.toSVG());

    assertEquals("<svg width=\"50\" height=\"50\" version=\"1.1\" xmlns=\"http://www" +
            ".w3.org/2000/svg\">\n" +
            "<ellipse id=\"O\" cx=\"20.0\" cy=\"20.0\" rx=\"20.0\" ry=\"20.0\" fill=\"rgb(0,0,0" +
            ")\" visibility=\"visible\" >\n" +
            "      <animate attributeType=\"css\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"" +
            "fill\" from=\"rgb(0,0,0)\" to=\"rgb(1,1,1)\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"" +
            "rx\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"" +
            "ry\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"" +
            "cx\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"c" +
            "y\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "<rect id=\"R\" x=\"20.0\" y=\"20.0\" width=\"20.0\" height=\"20.0\" fill=\"rgb(0,0,0" +
            ")\" visibility=\"visible\" >\n" +
            "      <animate attributeType=\"css\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"f" +
            "ill\" from=\"rgb(0,0,0)\" to=\"rgb(1,1,1)\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"w" +
            "idth\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"h" +
            "eight\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"" +
            "x\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"" +
            "y\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>", svg1.toSVG());

    assertEquals("<svg width=\"500\" height=\"500\" version=\"1.1\" xmlns=\"http://www." +
            "w3.org/2000/svg\">\n" +
            "<ellipse id=\"O1\" cx=\"20.0\" cy=\"20.0\" rx=\"20.0\" ry=\"20.0\" fill=\"rgb(0,0,0)" +
            "\" visibility=\"visible\" >\n" +
            "      <animate attributeType=\"css\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"f" +
            "ill\" from=\"rgb(0,0,0)\" to=\"rgb(1,1,1)\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"" +
            "rx\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"" +
            "ry\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"c" +
            "x\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"c" +
            "y\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "<ellipse id=\"O2\" cx=\"20.0\" cy=\"20.0\" rx=\"20.0\" ry=\"20.0\" fill=\"rgb(0,0,0)" +
            "\" visibility=\"visible\" >\n" +
            "      <animate attributeType=\"css\" begin=\"0ms\" dur=\"10000ms\" attributeName=\"f" +
            "i" +
            "ll\" from=\"rgb(0,0,0)\" to=\"rgb(255,128,128)\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"css\" begin=\"10000ms\" dur=\"10000ms\" attributeNam" +
            "e=" +
            "\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(0,1,1)\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"10000ms\" attributeName=\"r" +
            "x\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"10000ms\" attributeN" +
            "ame=\"ry" +
            "\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"10000ms\" attributeName" +
            "=\"rx\" from=\"40.0\" to=\"80.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"10000ms\" attribut" +
            "eName=\"ry\" from=\"40.0\" to=\"80.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"10000ms\" attributeNa" +
            "me=\"cx\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"10000ms\" attributeN" +
            "ame=\"cy\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"5000ms\" attributeNa" +
            "me=\"cx\" from=\"30.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"5000ms\" attributeNa" +
            "me=\"cy\" from=\"30.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"15000ms\" dur=\"5000ms\" attributeN" +
            "ame=\"cx\" from=\"40.0\" to=\"0.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"15000ms\" dur=\"5000ms\" attributeNa" +
            "me=\"cy\" from=\"40.0\" to=\"0.0\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "</svg>", svg2.toSVG());
  }

  @Test
  public void testSVGSpeed20() {
    assertEquals("<svg width=\"100\" height=\"100\" version=\"1.1\" xmlns=\"http://www." +
            "w3.org/2000/svg\">\n" +
            "<ellipse id=\"O\" cx=\"20.0\" cy=\"20.0\" rx=\"20.0\" ry=\"20.0\" fill=\"rgb" +
            "(0,0,0)\"" +
            " visibility=\"visible\" >\n" +
            "      <animate attributeType=\"css\" begin=\"0ms\" dur=\"1000ms\" attribute" +
            "Name=\"fi" +
            "ll\" from=\"rgb(0,0,0)\" to=\"rgb(1,1,1)\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"" +
            "0ms\" dur=\"1000ms\" attributeName=\"rx\"" +
            " from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"" +
            "1000ms\" attributeName=\"ry\"" +
            " from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1" +
            "000ms\" attributeName=\"cx\" " +
            "from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"10" +
            "00ms\" attributeName=\"cy\"" +
            " from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "<rect id=\"R\" x=\"20.0\" y=\"20.0\" width=\"20.0\" height=\"20.0\" fill=" +
            "\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
            "      <animate attributeType=\"css\" begin=\"0ms\" dur=\"1000ms\" attributeN" +
            "ame=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(1,1,1)\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" attribute" +
            "Name=\"width\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" attribute" +
            "Name=\"height\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" attributeN" +
            "ame=\"x\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\" attributeN" +
            "ame=\"y\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>", svg3.toSVG());
  }

  @Test
  public void testSVGSpeed10() {
    assertEquals("<svg width=\"100\" height=\"100\" version=\"1.1\" xmlns=\"http://ww" +
            "w.w3.org/2000/svg\">\n" +
            "<ellipse id=\"O\" cx=\"20.0\" cy=\"20.0\" rx=\"20.0\" ry=\"20.0\" fill=\"rgb(0,0,0)" +
            "\" visibility=\"visible\" >\n" +
            "      <animate attributeType=\"css\" begin=\"0ms\" dur=\"2000ms\" attributeName=\"" +
            "fill\" from=\"rgb(0,0,0)\" to=\"rgb(1,1,1)\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"2000ms\" attributeName=\"" +
            "rx\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"2000ms\" attributeName=\"" +
            "ry\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"2000ms\" attributeName=\"" +
            "cx\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"2000ms\" attributeName=\"" +
            "cy\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "<rect id=\"R\" x=\"20.0\" y=\"20.0\" width=\"20.0\" height=\"20.0\" fill=\"rgb(0,0" +
            ",0)\" visibility=\"visible\" >\n" +
            "      <animate attributeType=\"css\" begin=\"0ms\" dur=\"2000ms\" attributeName=\"f" +
            "ill\" from=\"rgb(0,0,0)\" to=\"rgb(1,1,1)\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"2000ms\" attributeName=\"wi" +
            "dth\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"2000ms\" attributeName=\"he" +
            "ight\" from=\"20.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"2000ms\" attributeName=\"x" +
            "\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "      <animate attributeType=\"xml\" begin=\"0ms\" dur=\"2000ms\" attributeName=\"y" +
            "\" from=\"20.0\" to=\"30.0\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>", svg4.toSVG());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    new SVGView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModelTwo() {
    new SVGView(null, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModelThree() {
    new SVGView(null, 10);
  }


  @Test(expected = UnsupportedOperationException.class)
  public void testMakeVisible() {
    svg1.makeVisible();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void animationState() {
    svg1.animationState();
  }
}
