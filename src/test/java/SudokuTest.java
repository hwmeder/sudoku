import java.util.Arrays;
import java.util.Set;

import hwm.Reporter;
import hwm.puzzle.*;
import hwm.sudoku.Container;
import hwm.sudoku.PuzzleDesc;
import hwm.sudoku.Strategy;
import hwm.sudoku.Workbook;
import hwm.sudoku.impl.WorkbookImpl;
import hwm.sudoku.strategy.NodePossibilityEliminator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SudokuTest {
  @Test
  public void test() {
    PuzzleDesc puzzle = new Puzzle240();
    final Reporter reporter = new MyReporter();
    Workbook workbook = build(puzzle, reporter);
    final String[] problemStr = workbook.getSolutionStr();
    solve(workbook);
    final String[] solutionStr = workbook.getSolutionStr();
    reporter.println(Arrays.toString(problemStr));
    reporter.println(Arrays.toString(solutionStr));
    assertTrue(puzzle.checkSolution(solutionStr));
  }

  public static Workbook build(PuzzleDesc puzzleDesc, Reporter reporter) {
    return new WorkbookImpl(puzzleDesc, reporter);
  }

  public static void solve(Workbook workbook) {
    Set<Container> containers = workbook.getNodes();
    solve(workbook, new NodePossibilityEliminator(containers));
  }

  public static void solve(Workbook workbook, Strategy strategy) {
    workbook.displayCells();

    try {
      workbook.execute(strategy);
    } finally {
      workbook.displayCells();
      workbook.displayPuzzleName();
    }
  }

  private static class MyReporter extends Reporter {

    public MyReporter() {
      super(null);
    }

    @Override
    public void println() {
    }

    @Override
    public void println(final String string) {
    }

    @Override
    public void print(final Object o) {
    }

    @Override
    public void printDone() {
    }

    @Override
    public void setCount(final int count) {
    }

    @Override
    public void clear() {
    }

    @Override
    public void printWithPrefix(final String encodedReason, final String englishReason) {
    }
  }
}
