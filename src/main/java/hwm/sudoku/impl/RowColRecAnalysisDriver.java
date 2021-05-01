package hwm.sudoku.impl;

import java.util.Set;

import hwm.sudoku.Cell;

public class RowColRecAnalysisDriver {
  /**
   * continue processing through to the solution, or stop after taking one step towards the solution
   */
  private static boolean continueProcessing = false;

  public static void checkCells(Set<Cell> cells) {
    if (continueProcessing) {
      for (Cell cell : cells) {
        cell.setCIfPossible();
      }
    }
  }

  protected static void checkCell(Cell cell) {
    if (continueProcessing) {
      cell.setCIfPossible();
    }
  }

  public static void setContinueProcessing(boolean continueProcessing) {
    RowColRecAnalysisDriver.continueProcessing = continueProcessing;
  }

}
