package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle239a extends PuzzleDescImpl {

  public Puzzle239a() {
    String[] puzzleStr = {
        ".47.31..2",
        "5.....4..",
        "9...4..15",
        ".3..52...",
        "...7.4...",
        "...18..9.",
        "27..6...1",
        "..1.....4",
        "3..2..57.",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "647531982",
        "518927463",
        "923846715",
        "739652148",
        "182794356",
        "456183297",
        "275469831",
        "891375624",
        "364218579"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
