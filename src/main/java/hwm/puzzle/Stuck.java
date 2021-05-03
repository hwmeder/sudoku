package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Stuck extends PuzzleDescImpl {
  public Stuck() {
    String[] puzzleStr = {
        "8.7.9..15",
        ".567....8",
        "...8.57..",
        ".8..4.157",
        "574128.9.",
        "619573..4",
        "498.51.72",
        "7.5.....1",
        ".6..875..",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "837692415",
        "156734928",
        "942815763",
        "283946157",
        "574128396",
        "619573284",
        "498351672",
        "725469831",
        "361287549"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
