
public class Queen extends ChessAbs {
  Queen(int row, int col, Color color) {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (!inGoodRange(row, col)) {
      return false;
    }
    return ((this.row == row) || (this.col == col)
            || (Math.abs(this.row - row) == Math.abs(this.col - col)));
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return false;
  }
}
