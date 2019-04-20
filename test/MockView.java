import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.event.ChangeListener;

import cs3500.animator.view.EditorViewInterface;

/**
 * A mock view that appends method calls to a string builder for the purposes of testing.
 */
class MockView implements EditorViewInterface {

  StringBuilder app = new StringBuilder();

  public JButton testButton = new JButton("test");

  public void setListener(ActionListener l) {
    testButton.addActionListener(l);
  }


  @Override
  public void pause() {
    app.append("pause");

  }

  @Override
  public void play() {
    app.append("play");

  }

  @Override
  public void rewind() {

    app.append("rewind");

  }

  @Override
  public void restart() {

    app.append("restart");

  }

  @Override
  public void speedUp() {

    app.append("speedup");

  }

  @Override
  public void slowDown() {

    app.append("slowdown");

  }

  @Override
  public void refresh() {

    app.append("timer off");

  }

  @Override
  public String getReadFile() {
    app.append("getReadFile");
    return null;
  }

  public void visible(Boolean b) {
    return;
  }

  @Override
  public void disp() {
    app.append("dispose");
    return;

  }

  @Override
  public String getOutType() {
    return "TXT";
  }

  @Override
  public String getUserId() {
    app.append("add key frame");
    return null;
  }

  @Override
  public String getUserX() {
    return null;
  }

  @Override
  public String getUserY() {
    return null;
  }

  @Override
  public String getUserW() {
    return null;
  }

  @Override
  public String getUserHeight() {
    return null;
  }

  @Override
  public String getUserR() {
    return null;
  }

  @Override
  public String getUserG() {
    return null;
  }

  @Override
  public void deleteId() {

    app.append("delete shape");
  }

  @Override
  public String getUserBlue() {
    return null;
  }

  @Override
  public String getShapeType() {
    app.append("add shape");
    return null;
  }

  @Override
  public String getNewId() {
    app.append("add shape");
    return null;
  }

  @Override
  public void refreshId(String id) {
    return;
  }

  @Override
  public String getUserTime() {
    return null;
  }

  @Override
  public void resetOnBadInput() {
    return;
  }

  @Override
  public void updateKeyFrames() {
    app.append("refresh keyframes");
  }

  @Override
  public String getKeyFrame() {
    return null;
  }

  @Override
  public void engageThrusters() {
    return;
  }

  @Override
  public void setChangeListener(ChangeListener c) {
    return;
  }


  @Override
  public String toSVG() {

    return null;
  }

  @Override
  public String animationState() {

    return null;
  }

  @Override
  public void makeVisible() {
    return;
  }

  public String toString() {
    return app.toString();
  }
}