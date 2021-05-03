package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle070908 extends PuzzleDescImpl {
  public Puzzle070908() {
    String[] puzzleStr = {
        "6..7..4..",
        "271......",
        "...6...1.",
        "....8.35.",
        "8..5.6..1",
        ".97.1....",
        ".4...9...",
        "......128",
        "..3..8..4",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "659721483",
        "271834569",
        "384695217",
        "416987352",
        "832546971",
        "597312846",
        "148269735",
        "965473128",
        "723158694"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
