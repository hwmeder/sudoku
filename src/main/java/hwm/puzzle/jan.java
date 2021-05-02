package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class jan extends PuzzleDescImpl {
  public jan() {
    String[] puzzleStr = { //
    "6.......8", //
        ".3..9..7.", //
        ".471.569.", //
        "..86.71..", //
        ".6.....2.", //
        "..24.98..", //
        ".293.178.", //
        ".7..6..1.", //
        "1.......9", //
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = { //
        "691734258", "835296471", "247185693", "958627134", "764813925", "312459867", "529341786", "473968512", "186572349"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
