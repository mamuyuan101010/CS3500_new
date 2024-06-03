package spreadsheet;

public interface SpreadSheetWithMacro extends SpreadSheet {
  void execute(SpreadSheetMacro m);
}
