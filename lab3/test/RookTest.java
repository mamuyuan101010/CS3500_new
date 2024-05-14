import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * test for Rook class.
 */
public class RookTest {
  private boolean[][] results;

  // setup method
  @Before
  public void setup() {
    results = new boolean[8][8];

  }

  // make sure the chess move
  private void verifyMoveResults(ChessPiece piece) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }

        assertEquals("Piece at :" + piece.getRow() + "," + piece.getColumn()
                        +
                        ", Unexpected canMove result "
                        + "for "
                        + "i=" + i + " j="
                        +
                        j + "",
                results[i][j], piece.canMove(i, j));

      }
    }
  }

  // make sure the chess in good range
  private void verifyKillResults(ChessPiece piece) {

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {

        if ((i == piece.getRow()) && (j == piece.getColumn())) {
          continue;
        }
        ChessPiece another = new Rook(i, j,
                Color.values()[(piece.getColor().ordinal() + 1)
                        % Color.values().length]);


        assertEquals("Unexpected canKill result for "
                        + "i=" + i + " j="
                        +
                        j + "",
                results[i][j], piece.canKill(another));

      }
    }
  }


  // make sure row, col, and color match
  @Test(timeout = 500)
  public void testGetters() {
    ChessPiece piece;


    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        for (Color c : Color.values()) {
          piece = new Rook(row, col, c);


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

  // test if invalid input in constructor will throw an exception
  @Test(timeout = 500)
  public void testInvalidConstructions() {
    ChessPiece piece;

    for (Color c : Color.values()) {
      for (int i = 0; i < 8; i++) {


        try {
          piece = new Rook(i, -1, c);
          fail("Did not throw an exception when rook is created with invalid "
                  +
                  "row");
        } catch (IllegalArgumentException e) {
          //passes
        }

        try {
          piece = new Rook(-1, i, c);
          fail("Did not throw an exception when rook is created with invalid "
                  +
                  "column");
        } catch (IllegalArgumentException e) {
          //passes
        }
      }
    }
  }

  // make sure Rook's moving path is in the correct pattern
  @Test(timeout = 500)
  public void testRookMoves() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        initializeResults();
        ChessPiece piece = new Rook(row, col, Color.BLACK);

        setupResults(row, col);
        verifyMoveResults(piece);
      }
    }
  }

  // make sure the Rook can kill other chess
  @Test(timeout = 500)
  public void testRookKills() {

    for (Color c : Color.values()) {
      for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
          initializeResults();
          ChessPiece piece = new Rook(row, col, c);

          setupResults(row, col);
          verifyKillResults(piece);
        }
      }
    }
  }


  // set up the test data
  private void initializeResults() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        results[row][col] = false;
      }
    }
  }

  // set up the test data
  private void setupResults(int row, int col) {
    //check if canMove works
    for (int i = 0; i < 8; i++) {
      results[i][col] = true;
      results[row][i] = true;

    }
  }

}