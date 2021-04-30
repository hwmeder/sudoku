package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public class Puzzle12x12 extends PuzzleDescImpl {
  public Puzzle12x12() {
    setNRecsInRow(3);
    setNRecsInCol(4);
    setPossibleCharacterStr("123456789abc");
    
    String[] puzzleStr = {//
    "...58a.....6", //
    "83b9....c..7", //
    ".6.4..978..3", //
    "bc6.5..94..1", //
    ".5.8..12.67.", //
    "..4a.c73....", //
    "....a18.b7..", //
    ".b8.96..5.c.", //
    "a..23..c.468", //
    "5..cb9..6.8.", //
    "9..b....3ca4", //
    "4.....c87...", //
    };
    setPuzzleStrArray(puzzleStr);
    init();
  }
}
