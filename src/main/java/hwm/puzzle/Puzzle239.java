package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle239 extends PuzzleDescImpl {

  public Puzzle239() {
    String[] puzzleStr = {
        ".47..1..2",
        "5.....4..",
        "9...4..15",
        ".3..52...",
        "...7.4...",
        "...18..9.",
        "27..6...1",
        "..1.....4",
        "3..2..57.",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        ".475.1..2",
        "51..2.4..",
        "9...4..15",
        "73..5214.",
        "1..7.4.5.",
        "4..18..9.",
        "27.46...1",
        "..1.7..24",
        "3.421.57."
    };
    setSolutionStrArray(solutionStrArray);
  }
}
