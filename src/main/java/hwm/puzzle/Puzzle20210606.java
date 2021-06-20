package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle20210606 extends PuzzleDescImpl {
  public Puzzle20210606() {
    String[] puzzleStr = {
        "..6.324..",
        "...9...21",
        "....5....",
        "2...9..78",
        ".1..8..9.",
        "78..1...2",
        "....4....",
        "84..73...",
        "..362.5..",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "196832457", "357964821", "428751936", "235496178", "614287395", "789315642", "562149783", "841573269", "973628514"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
