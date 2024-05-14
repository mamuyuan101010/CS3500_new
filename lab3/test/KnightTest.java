import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest {
  private boolean[][] results;

  @Before
  public void setup() {
    results = new boolean[8][8];
  }

  private void verifyMoveResults(ChessPiece piece) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }

        assertEquals("Piece at :" + piece.getRow() + "," + piece.getColumn() +
                        ", Unexpected canMove result "
                        + "for "
                        + "i=" + i + " j=" +
                        j + "",
                results[i][j], piece.canMove(i, j));

      }
    }
  }

  private void verifyKillResults(ChessPiece piece) {

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {

        if ((i == piece.getRow()) && (j == piece.getColumn()))
          continue;
        ChessPiece another = new Knight(i, j,
                Color.values()[(piece.getColor().ordinal() + 1)
                        % Color.values().length]);


        assertEquals("Unexpected canKill result for "
                        + "i=" + i + " j=" +
                        j + "",
                results[i][j], piece.canKill(another));

      }
    }
  }


  @Test(timeout = 500)
  public void testGetters() {
    ChessPiece piece;


    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        for (Color c : Color.values()) {
          piece = new Knight(row, col, c);
          assertEquals("Row number does not match what was initialized", row,
                  piece.getRow());
          assertEquals("Column number does not match what was initialized",
                  col, piece.getColumn());
          assertEquals("solution.Color does not match what was initialized",
                  c, piece.getColor());

        }
      }
    }

  }

  @Test(timeout = 500)
  public void testInvalidConstructions() {
    ChessPiece piece;

    for (Color c : Color.values()) {
      for (int i = 0; i < 8; i++) {


        try {
          piece = new Knight(i, -1, c);
          fail("Did not throw an exception when rook is created with invalid " +
                  "row");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Knight(-1, i, c);
          fail("Did not throw an exception when rook is created with invalid " +
                  "column");
        } catch (IllegalArgumentException e) {
          //passes
        }

      }
    }
  }


  @Test
  public void testKnightMoves() {
    ChessPiece chess1 = new Knight(4,4,Color.BLACK);
    assertTrue(chess1.canMove(5,6));
    assertTrue(chess1.canMove(6,5));
  }

  @Test
  public void testKnightKills() {
    ChessPiece chess1 = new Queen(4,4,Color.BLACK);
    ChessPiece chess2 = new Knight(5,6,Color.WHITE);
    assertFalse(chess1.canKill(chess2));
    assertTrue(chess2.canKill(chess1));
  }


  private void initializeResults() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        results[row][col] = false;
      }
    }
  }
}