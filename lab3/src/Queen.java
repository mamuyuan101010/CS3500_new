/**
 * class Queen represents the queen chess.
 */
public class Queen extends ChessAbs {
  /**
   * constructor of Queen.
   * @param row is the row number
   * @param col is the col number
   * @param color is the color info
   */
  Queen(int row, int col, Color color) {
    super(row, col, color);
  }

  /**
   * check whether Queen can be moved to the given position.
   * @param row the row to which this chess piece can be moved.
   * @param col the col to which this chess piece can be moved.
   * @return the boolean value of whether the Queen can move.
   */
  @Override
  public boolean canMove(int row, int col) {
    if (!inGoodRange(row, col)) {
      return false;
    }
    return ((this.row == row) || (this.col == col)
            || (Math.abs(this.row - row) == Math.abs(this.col - col)));
  }
}
