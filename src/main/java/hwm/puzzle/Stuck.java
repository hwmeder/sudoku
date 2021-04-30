package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public class Stuck extends PuzzleDescImpl {
  public Stuck() {
    String[] puzzleStr = { //
    "8.7.9..15", //
    ".567....8", //
    "...8.57..", //
    ".8..4.157", //
    "474128.9.", //
    "", //
    "", //
    "", //
    "", //
    };
    setPuzzleStrArray(puzzleStr);
  }
}
