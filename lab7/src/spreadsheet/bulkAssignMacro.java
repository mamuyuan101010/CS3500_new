package spreadsheet;

public class bulkAssignMacro implements SpreadSheetMacro {

  int heightStart;
  int widthStart;
  int heightEnd;
  int widthEnd;
  double value;


  /**
   * the range of cells to set,
   * and the value to set them to
   */
  public bulkAssignMacro (int heightStart, int widthStart, int heightEnd, int widthEnd, double value) {
    if (heightStart < 0 || widthStart < 0 || heightEnd < 0 || widthEnd < 0) {
      throw new IllegalArgumentException ("at least one of the given value is both negative");
    }
    if (heightStart > heightEnd || widthStart > widthEnd) {
      throw new IllegalArgumentException("invalid range");
    }
    this.heightEnd = heightEnd;
    this.widthEnd = widthEnd;
    this.heightStart = heightStart;
    this.widthStart = widthStart;
    this.value = value;
  }

  /**
   * set the range of specified cells in the spreadsheet
   * passed to it, to the specified value.
   * @param spreadSheetWithMacro is a taken spreadSheet object.
   */
  @Override
  public void execute(SpreadSheetWithMacro spreadSheetWithMacro) {
    for (int i = heightStart; i <= heightEnd; i++) {
      for (int j = widthStart; j <= widthEnd; j++) {
        spreadSheetWithMacro.set(i, j, value);
      }
    }
  }


}
