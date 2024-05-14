/**
 * class ChessAbs is the abstract class implement ChessPiece Interface
 * and each kind of chess class can extend it.
 */
public abstract class ChessAbs implements ChessPiece {
  int row;
  public int col;

  Color color;

  /**
   * constructor of ChessAbs class.
   * @param row is the row number.
   * @param col is the column number.
   * @param color is the color info.
   * @throws IllegalArgumentException if the given row or col is negative.
   */
  public ChessAbs(int row, int col, Color color) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Illegal position");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  // check if the row and col number is in good range(0-8)
  boolean inGoodRange(int row, int col) {
    return !((row < 0) || (col < 0) || (row >= 8) || (col >= 8));
  }


  public int getRow() {
    return row;
  }


  public int getColumn() {
    return col;
  }


  public Color getColor() {
    return color;
  }

  public boolean canKill(ChessPiece piece) {
    return (this.getColor() != piece.getColor()) && canMove(
            piece.getRow(),
            piece.getColumn());
  }

}
