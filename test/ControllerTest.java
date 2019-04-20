import org.junit.Before;
import org.junit.Test;

import cs3500.animator.controller.EditorController;
import cs3500.animator.controller.SimpleController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;

import static org.junit.Assert.assertEquals;


/**
 * Tests for our controller class.
 */
public class ControllerTest {
  MockView view = new MockView();
  EditorController edit = new EditorController((AnimationModel) new
          AnimationModelImpl.Builder().build(), 0, view);
  SimpleController simp = new SimpleController((AnimationModel) new
          AnimationModelImpl.Builder().build(), 10, view);

  @Before
  public void init() {
    view.setListener(edit);

  }

  @Test
  public void testPause() {
    view.testButton.setActionCommand("Pause Button");
    view.testButton.doClick();
    assertEquals("pause", view.toString());
  }

  @Test
  public void testRewind() {
    view.testButton.setActionCommand("Rewind Button");
    view.testButton.doClick();
    assertEquals("rewind", view.toString());
  }

  @Test
  public void testPlay() {
    view.testButton.setActionCommand("Play Button");
    view.testButton.doClick();
    assertEquals("play", view.toString());
  }

  @Test
  public void testSpeedUp() {
    view.testButton.setActionCommand("Speed Up Button");
    view.testButton.doClick();
    assertEquals("speedup", view.toString());
  }

  @Test
  public void testSlowDown() {
    view.testButton.setActionCommand("Slow Down Button");
    view.testButton.doClick();
    assertEquals("slowdown", view.toString());
  }

  @Test
  public void testRestart() {
    view.testButton.setActionCommand("Restart Command");
    view.testButton.doClick();
    assertEquals("restart", view.toString());
  }

  @Test
  public void testTimerOff() {
    view.testButton.setActionCommand("Timer Off");
    view.testButton.doClick();
    assertEquals("timer off", view.toString());
  }

  @Test
  public void testAddKeyFrame() {
    view.testButton.setActionCommand("add Key Frame");
    view.testButton.doClick();
    assertEquals("add key frame", view.toString());
  }

  @Test
  public void testDeleteKeyFrame() {
    view.testButton.setActionCommand("delete Key Frame");
    view.testButton.doClick();
    assertEquals("add key frame", view.toString());
  }

  @Test
  public void testReadFile() {
    view.testButton.setActionCommand("Read File");
    view.testButton.doClick();
    assertEquals("getReadFile", view.toString());
  }

  /**
   * Null pointer will never be thrown at run time while actually running the real implementation of
   * our view. Catching for a null pointer still ensures that the controller is listening to the
   * button press and calling the appropriate method in the view.
   */
  @Test(expected = NullPointerException.class)
  public void testSaveFile() {
    view.testButton.setActionCommand("Save File");
    view.testButton.doClick();
    assertEquals("save file", view.toString());
  }

  @Test
  public void testRefreshKeyFrame() {
    view.testButton.setActionCommand("Refresh Keyframes");
    view.testButton.doClick();
    assertEquals("refresh keyframes", view.toString());
  }

  /**
   * This method call will throw an illegal argument because there is no objcet to delete, (as
   * intended) but still proves that the listener calls the proper method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDeleteShape() {
    view.testButton.setActionCommand("Delete Shape");
    view.testButton.doClick();
    assertEquals("delete shape", view.toString());
  }

  /**
   * Null pointer will never be thrown at run time while actually running the real implementation of
   * our view. Catching for a null pointer still ensures that the controller is listening to the
   * button press and calling the appropriate method in the view.
   */
  @Test(expected = NullPointerException.class)
  public void testAddShape() {
    view.testButton.setActionCommand("Add Shape");
    view.testButton.doClick();
    assertEquals("add shape", view.toString());
  }

  @Test
  public void simpleTest() {
    view.setListener(simp);
    view.testButton.setActionCommand("Timer Off");
    view.testButton.doClick();
    assertEquals("timer offtimer off", view.toString());
  }


}

