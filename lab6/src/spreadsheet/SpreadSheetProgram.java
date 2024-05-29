package spreadsheet;

import java.io.InputStreamReader;

/**
 * SpreadSheetProgram class.
 */
public class SpreadSheetProgram {
  /**
   * main method that let the user run.
   * @param args is the argument user input
   */
  public static void main(String[] args) {
    SpreadSheet model = new SparseSpreadSheet();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetController(model, rd, ap);
    controller.gos();
  }
}
