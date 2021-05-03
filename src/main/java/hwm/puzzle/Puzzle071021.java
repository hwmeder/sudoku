package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle071021 extends PuzzleDescImpl {
  public Puzzle071021() {
    String[] puzzleStr = {
        "...792...",
        "9.......5",
        "..8...2..",
        ".3.9.7.6.",
        "1.6.4.5.7",
        ".7.1.5.3.",
        "..9...1..",
        "3.......4",
        "...374...",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "514792386",
        "923486715",
        "768531249",
        "835927461",
        "196843527",
        "472165938",
        "249658173",
        "387219654",
        "651374892"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
