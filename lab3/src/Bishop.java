
public class Bishop extends ChessAbs {

  Bishop(int row, int col, Color color) {
    super(row, col, color);
  }

  public boolean canMove(int row, int col) {
    if (!inGoodRange(row, col)) {
      return false;
    }
    return (Math.abs(this.row - row) == Math.abs(this.col - col));
  }
}
