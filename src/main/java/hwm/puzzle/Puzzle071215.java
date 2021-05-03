package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle071215 extends PuzzleDescImpl {
  public Puzzle071215() {
    String[] puzzleStr = {
        ".2.3..6.9",
        ".......2.",
        "....87.5.",
        ".16..5..3",
        "...9.3...",
        "3..4..89.",
        ".8.73....",
        ".6.......",
        "4.5..9.7.",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "721354689",
        "548196327",
        "693287154",
        "916825743",
        "874963512",
        "352471896",
        "189732465",
        "267548931",
        "435619278"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
