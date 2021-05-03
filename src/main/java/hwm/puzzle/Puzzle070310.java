package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Puzzle070310 extends PuzzleDescImpl {
  public Puzzle070310() {
    String[] puzzleStr = {
        "..8....93",
        "....7.4.2",
        ".7.6.....",
        "1.3.5....",
        "7..3.1..8",
        "....8.1.9",
        ".....8.2.",
        "8.4.1....",
        "56....8..",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "658124793",
        "931875462",
        "472693581",
        "183952647",
        "796341258",
        "245786139",
        "317468925",
        "824519376",
        "569237814"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
