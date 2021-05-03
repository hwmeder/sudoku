package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Expert20210421 extends PuzzleDescImpl {
  public Expert20210421() {
    String[] puzzleStr = {
        ".......3.",
        "..8....4.",
        ".....4196",
        "1..7.....",
        "96.......",
        "3...2.5..",
        "..1.7...4",
        "64...1...",
        "..75.....",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "496157832",
        "218396745",
        "753284196",
        "185763429",
        "962415378",
        "374928561",
        "531672984",
        "649831257",
        "827549613"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
