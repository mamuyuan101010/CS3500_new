/**
 * class Knight represent the Knight chess
 */
public class Knight extends ChessAbs{
  /**
   * constructor of Knight.
   * @param row is the row number
   * @param col is the col number
   * @param color is the color info
   * @throws IllegalArgumentException if given row or col is invalid
   */
  public Knight(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * canMove method of Knight class check whether the Knight
   * can move to the given row and col pos.
   * @param row the row to which this chess piece can be moved
   * @param col the col to which this chess piece can be moved
   * @return whether the Knight can move to the given row and col pos.
   */
  @Override
  public boolean canMove(int row, int col) {
    if (!inGoodRange(row, col)) {
      return false;
    }
    return (Math.abs(this.row - row) == 1 && Math.abs(this.col - col) == 2)
            || (Math.abs(this.row - row) == 2 && Math.abs(this.col - col) == 1);
  }
}
