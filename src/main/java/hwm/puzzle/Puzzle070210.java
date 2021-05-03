package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle070210 extends PuzzleDescImpl {
  public Puzzle070210() {
    String[] puzzleStr = {
        "....5.3..",
        ".538.....",
        "9.2.16.5.",
        ".7...52.6",
        "......5..",
        "2.5....7.",
        ".8.63.9.5",
        ".....483.",
        "..1.9....",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "816457392",
        "753829164",
        "942316758",
        "378945216",
        "194762583",
        "265183479",
        "487631925",
        "629574831",
        "531298647"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
