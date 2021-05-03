package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle237a extends PuzzleDescImpl {
  public Puzzle237a() {
    String[] puzzleStr = {
        "9...61...",
        "......9..",
        "6.4....27",
        ".8...2..1",
        "..93.74..",
        "5..8..79.",
        "29....6.8",
        "..5......",
        "...67...5",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "932761854",
        "871245963",
        "654983127",
        "786492531",
        "129357486",
        "543816792",
        "297534618",
        "465128379",
        "318679245"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
