package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle20210520 extends PuzzleDescImpl {
  public Puzzle20210520() {
    String[] puzzleStr = {
        "..63.1.84",
        "1..4...57",
        "94.287...",
        "..3..8...",
        "4.....1..",
        "...9.6..2",
        ".........",
        "3.7....29",
        "8....4.6.",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "..6351984","138469257","945287613","6.31.87.5","48.57.1.6","..19.68.2",".1469..78","367815429","8..7.4.61"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
