/**
 * class Bishop represents the Bishop chess.
 */
public class Bishop extends ChessAbs {

  /**
   * constructor of Bishop.
   *
   * @param row   is the row number
   * @param col   is the col number
   * @param color is the color info
   */
  Bishop(int row, int col, Color color) {
    super(row, col, color);
  }

  /**
   * check whether bishop can be moved to the given position.
   *
   * @param row the row to which this chess piece can be moved.
   * @param col the col to which this chess piece can be moved.
   * @return
   */
  public boolean canMove(int row, int col) {
    if (!inGoodRange(row, col)) {
      return false;
    }
    return (Math.abs(this.row - row) == Math.abs(this.col - col));
  }
}
