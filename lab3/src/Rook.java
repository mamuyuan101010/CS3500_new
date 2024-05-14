/**
 * class Rook represents the Rook chess.
 */
public class Rook extends ChessAbs {
  /**
   * constructor of Rook.
   * @param row is the row number
   * @param col is the col number
   * @param color is the color
   */
  Rook(int row, int col, Color color) {
    super(row, col, color);
  }

  /**
   * check whether Rook can move to the given position.
   * @param row the row to which this chess piece can be moved
   * @param col the col to which this chess piece can be moved
   * @return
   */
  @Override
  public boolean canMove(int row, int col) {
    if (!inGoodRange(row, col)) {
      return false;
    }
    return ((this.row == row) || (this.col == col));
  }
}
