package controller.commands;

/**
 * Created by vidojemihajlovikj on 5/20/24.
 */
public class ReadState {
  private final int result;
  private final InputResult inputResult;

  public ReadState(InputResult inputResult, int result){
    this.inputResult = inputResult;
    this.result = result;
  }

  InputResult getInputResult(){
    return this.inputResult;
  }

  int getResult(){
    return this.result;
  }
}
