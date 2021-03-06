package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle071027 extends PuzzleDescImpl {
  public Puzzle071027() {
    String[] puzzleStr = {
        "..6.5.7..",
        ".5.....2.",
        "7..8.9..4",
        ".2.4.1.7.",
        "....9....",
        ".6.5.8.1.",
        "2..7.6..3",
        ".3.....9.",
        "..4.2.1..",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "986254731",
        "453617928",
        "712839654",
        "825461379",
        "147392865",
        "369578412",
        "291786543",
        "638145297",
        "574923186"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
