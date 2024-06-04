package spreadsheet;

import java.util.Scanner;

public class MacroSpreadSheetController extends SpreadSheetController{
  /**
   * Create a controller to work with the specified sheet (model),
   * readable (to take inputs) and appendable (to transmit output).
   *
   * @param sheet      the sheet to work with (the model)
   * @param readable   the Readable object for inputs
   * @param appendable the Appendable objects to transmit any output
   */
  public MacroSpreadSheetController(SpreadSheetWithMacro sheet, Readable readable, Appendable appendable) {
    super(sheet, readable, appendable);
  }

  @Override
  protected void processCommand(String userInstruction, Scanner sc, SpreadSheetWithMacro sheetMacro) {
    if (userInstruction.equals("bulk-assign-value")) {
      int rowBegin;
      int rowEnd;
      int colBegin;
      int colEnd;
      double value;
      bulkAssignMacro macro = null;
      try {
        rowBegin = getRowNum(sc.next()); //get in the row string
        colBegin = sc.nextInt(); //get in the column number, starting with 1
        rowEnd = getRowNum(sc.next()); // get in the row string
        colEnd = getRowNum(sc.next());//get in the col number, starting with 1
        value = sc.nextDouble();
        System.out.println("Setting cell range from (" + rowBegin + "," + (colBegin - 1)
        + ") to (" + rowEnd + "," + colEnd + ")");
        macro = new bulkAssignMacro(rowBegin, colBegin, rowEnd, colEnd, value);
      } catch (IllegalArgumentException e) {
        writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
      sheetMacro.execute(macro);
    }
    else {
      super.processCommand(userInstruction, sc, sheetMacro);
    }
  }
}
