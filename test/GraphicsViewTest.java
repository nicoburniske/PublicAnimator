
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.ReadAnimationModel;
import cs3500.animator.view.GraphicsView;

/**
 * Class to test a GraphicsView.
 */
public class GraphicsViewTest {

  ReadAnimationModel r;
  GraphicsView g;

  @Before
  public void init() {
    r = new AnimationModelImpl.Builder().setBounds(10, 10, 10, 10).build();
    g = new GraphicsView(r, 20, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        return;
      }
    });
  }

  @Test(expected = UnsupportedOperationException.class)
  public void toSVG() {
    g.toSVG();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void animationState() {
    g.animationState();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    new GraphicsView(null, 10, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        return;
      }
    });
  }
}
