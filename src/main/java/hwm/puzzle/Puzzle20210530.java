package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle20210530 extends PuzzleDescImpl {
  public Puzzle20210530() {
    String[] puzzleStr = {
        "..6.987..",
        "..9.71.4.",
        ".......61",
        ".....7..8",
        "7..359..6",
        "6..1.....",
        "32.......",
        ".6.74.3..",
        "..893....",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "..6351984","138469257","945287613","6.31.87.5","48.57.1.6","..19.68.2",".1469..78","367815429","8..7.4.61"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
