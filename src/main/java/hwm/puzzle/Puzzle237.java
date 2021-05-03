package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle237 extends PuzzleDescImpl {
  public Puzzle237() {
    String[] puzzleStr = {
        "9...61...",
        "......9..",
        "6.4....27",
        ".8...2..1",
        "..93.74..",
        "5..8...9.",
        "29....6.8",
        "..5......",
        "...67...5",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "9...6185.",
        "......96.",
        "6.4...127",
        ".86..25.1",
        "129357486",
        "54.816.92",
        "29....6.8",
        ".65.....9",
        "...6792.5"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
