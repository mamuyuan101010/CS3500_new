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
  @Override
  public void execute(SpreadSheet spreadSheet) {
    for (int i = heightStart; i <= heightEnd; i++) {
      for (int j = widthStart; j <= widthEnd; j++) {
        spreadSheet.set(i, j, value);
      }
    }
  }
}
