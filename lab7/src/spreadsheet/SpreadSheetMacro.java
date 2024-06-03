package spreadsheet;

/**
 * This is our "command" interface.
 */
public interface SpreadSheetMacro {

  /**
   * executes this macro on it.
   * @param spreadSheet is a taken spreadSheet object.
   */
  void execute(SpreadSheet spreadSheet);
}
