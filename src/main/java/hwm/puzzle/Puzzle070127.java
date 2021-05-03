package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle070127 extends PuzzleDescImpl {
  public Puzzle070127() {
    String[] puzzleStr = {
        "7....65..",
        "8.41.7...",
        "5.....2..",
        "1.....3..",
        ".4.9.5.8.",
        "..8.....6",
        "..6.....3",
        "...2.84.5",
        "..54....7",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "739826514",
        "824157639",
        "561349278",
        "197684352",
        "642935781",
        "358712946",
        "476591823",
        "913278465",
        "285463197"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
