package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle070915 extends PuzzleDescImpl {
  public Puzzle070915() {
    String[] puzzleStr = {
        "....4....",
        "95...6.3.",
        ".....5.84",
        ".8..9.2..",
        "7...5...1",
        "..5.7..6.",
        "17.3.....",
        ".3.4...78",
        "....6....",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "318742596",
        "954816732",
        "627935184",
        "481693257",
        "763258941",
        "295174863",
        "176389425",
        "539421678",
        "842567319"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
