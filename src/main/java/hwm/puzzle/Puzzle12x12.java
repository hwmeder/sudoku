package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle12x12 extends PuzzleDescImpl {
  public Puzzle12x12() {
    setNRecsInRow(3);
    setNRecsInCol(4);
    setPossibleCharacterStr("123456789abc");

    String[] puzzleStr = {
        "...58a.....6",
        "83b9....c..7",
        ".6.4..978..3",
        "bc6.5..94..1",
        ".5.8..12.67.",
        "..4a.c73....",
        "....a18.b7..",
        ".b8.96..5.c.",
        "a..23..c.468",
        "5..cb9..6.8.",
        "9..b....3ca4",
        "4.....c87...",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "c1758a3b2946",
        "83b92461ca57",
        "26a4c5978b13",
        "bc6758a94231",
        "35984b12a67c",
        "124a6c7398b5",
        "64c3a185b729",
        "7b81962453ca",
        "a95237bc1468",
        "573cb94a6182",
        "981b72563ca4",
        "4a2613c8759b"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
