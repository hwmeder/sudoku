package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle20210619 extends PuzzleDescImpl {
  public Puzzle20210619() {
    String[] puzzleStr = {
        ".4.781926",
        "891526473",
        "726439851",
        "91.24..68",
        "68.195.4.",
        "2.486.19.",
        "..867..19",
        "162958734",
        ".7931.68.",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        ".4.781926",
        "891526473",
        "726439851",
        "91.24..68",
        "68.195.4.",
        "2.486.19.",
        "..867..19",
        "162958734",
        ".7931.68.",
    };
    setSolutionStrArray(solutionStrArray);
  }
}
