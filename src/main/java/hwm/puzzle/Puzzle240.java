package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public class Puzzle240 extends PuzzleDescImpl {
  public Puzzle240() {
    String[] puzzleStrArray = { //
    "6.3...2.4", //
    "...9.2...", //
    ".1.....6.", //
    "..16897..", //
    ".........", //
    "..62475..", //
    ".9.....5.", //
    "...5.8...", //
    "5.4...6.1", //
    };
    setPuzzleStrArray(puzzleStrArray);
    String[] solutionStrArray = { //
    "673851294",//
    "..59621..",//
    ".1.374865",//
    ".516897..",//
    "7..135..6",//
    ".3624751.",//
    "19.42635.",//
    "36.518...",//
    "5.47936.1",//
    };
    setSolutionStrArray(solutionStrArray);
    init();
  }
}
