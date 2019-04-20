package cs3500.animator.model;

/**
 * Object that represents a size change action to be animated.
 */
public class ChangeSize extends AAction {
  private final double startHeight;
  private final double startWidth;
  private final double endHeight;
  private final double endWidth;
  private final double dHeight;
  private final double dWidth;

  /**
   * Constructor for creating a new size chang object.
   *
   * @param startTime   time where this action starts.
   * @param endTime     time where this actions ends.
   * @param startHeight height to start at.
   * @param startWidth  width to start at.
   * @param endHeight   height to end at.
   * @param endWidth    width to end at.
   */
  public ChangeSize(int startTime, int endTime, double startHeight, double startWidth, double endHeight,
                    double endWidth) {
    super(startTime, endTime);
    if (endWidth < 0 || endHeight < 0 || startHeight < 0 || startWidth < 0) {
      throw new IllegalArgumentException("Invalid Input. Dimensions cannot be negative");
    }
    this.startHeight = startHeight;
    this.startWidth = startWidth;
    this.endHeight = endHeight;
    this.endWidth = endWidth;
    this.dHeight = (endHeight - startHeight) / (endTime - startTime);
    this.dWidth = (endWidth - startWidth) / (endTime - startTime);
  }

  /**
   * Getter for start height.
   *
   * @return double that is start height.
   */
  public double getStartHeight() {
    return this.startHeight;
  }

  /**
   * Getter for start width.
   *
   * @return double that is start width
   */
  public double getStartWidth() {
    return this.startWidth;
  }

  /**
   * Getter for end height.
   *
   * @return double that is end height.
   */
  public double getEndHeight() {
    return this.endHeight;
  }

  /**
   * Getter for end width.
   *
   * @return double that is end width.
   */
  public double getEndWidth() {
    return this.endWidth;
  }

  /**
   * Getter for dHeight.
   *
   * @return double that is the change per tick in height.
   */
  public double getdHeight() {
    return this.dHeight;
  }

  /**
   * Getter for dWidth.
   *
   * @return double that represents the change in width per tick.
   */
  public double getdWidth() {
    return this.dWidth;
  }
}
