package cs3500.animator.model;

/**
 * represents an abstract action.
 */
public abstract class AAction {
  private final int startTime;
  private final int endTime;

  AAction(int startTime, int endTime) {
    if (startTime > endTime) {
      throw new IllegalArgumentException("Invalid input." +
              " Starting time cannot be greater than ending time.");
    }
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Start time getter.
   *
   * @return starting time of action.
   */
  public int getStartTime() {
    return startTime;
  }

  /**
   * End time getter.
   *
   * @return end time of action.
   */
  public int getEndTime() {
    return endTime;
  }

}
