public abstract class ChessAbs implements ChessPiece {
  int row;
  public int col;

  Color color;

  public ChessAbs(int row, int col, Color color) throws IllegalArgumentException {
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Illegal position");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }


  public boolean inGoodRange(int row, int col) {
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
