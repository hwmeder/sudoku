package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle238 extends PuzzleDescImpl {
  public Puzzle238() {
    String[] puzzleStr = {
        "2..1.3.65",
        "9.3......",
        ".5...4...",
        "32.8.95..",
        ".........",
        "..14.5.76",
        "...2...5.",
        "......8.1",
        "14.5.7..9",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "284173965",
        "913658742",
        "756924183",
        "327869514",
        "465712398",
        "891435276",
        "638291457",
        "579346821",
        "142587639"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
