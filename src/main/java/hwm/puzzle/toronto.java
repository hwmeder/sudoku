package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class toronto extends PuzzleDescImpl {
  public toronto() {
    String[] puzzleStr = {
        ".8..3....",
        "4.7..8...",
        ".2..59...",
        "7.8...53.",
        "..3...6..",
        ".15...9.7",
        "...38..6.",
        "...6..7.2",
        "....4..9.",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "581436279",
        "497218356",
        "326759184",
        "768924531",
        "943571628",
        "215863947",
        "179382465",
        "834695712",
        "652147893"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
