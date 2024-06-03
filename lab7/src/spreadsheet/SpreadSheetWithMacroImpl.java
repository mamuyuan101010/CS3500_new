package spreadsheet;

public class SpreadSheetWithMacroImpl implements SpreadSheetWithMacro {
  private SparseSpreadSheet sparseSpreadSheet;

  public SpreadSheetWithMacroImpl(SparseSpreadSheet spreadSheet) {
    this.sparseSpreadSheet = spreadSheet;
  }


  @Override
  public void execute(SpreadSheetMacro m) {
    m.execute(this);
  }

  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    return sparseSpreadSheet.get(row, col);
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    sparseSpreadSheet.set(row, col, value);
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    return sparseSpreadSheet.isEmpty(row, col);
  }

  @Override
  public int getWidth() {
    return sparseSpreadSheet.getWidth();
  }

  @Override
  public int getHeight() {
    return sparseSpreadSheet.getHeight();
  }
}
