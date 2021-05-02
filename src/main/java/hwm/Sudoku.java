package hwm;

import java.util.Arrays;
import java.util.Set;

import hwm.puzzle.Stuck;
import hwm.sudoku.Container;
import hwm.sudoku.PuzzleDesc;
import hwm.sudoku.Strategy;
import hwm.sudoku.Workbook;
import hwm.sudoku.impl.WorkbookImpl;
import hwm.sudoku.strategy.NodePossibilityEliminator;

public class Sudoku {

  public static void main(String[] args) {
    PuzzleDesc puzzle = new Stuck();
    final Reporter reporter = new Reporter(System.out);
    Workbook workbook = build(puzzle, reporter);
    final String[] problemStr = workbook.getSolutionStr();
    solve(workbook);
    final String[] solutionStr = workbook.getSolutionStr();
    reporter.println(Arrays.toString(problemStr));
    reporter.println(Arrays.toString(solutionStr));
    reporter.println(Boolean.toString(puzzle.checkSolution(solutionStr)));
  }

  public static void solve(Workbook workbook) {
    Set<Container> containers = workbook.getNodes();
    solve(workbook, new NodePossibilityEliminator(containers));
  }

  @SuppressWarnings("unused") // Used by GUI clients
  public static void finish(Workbook workbook) {
    Set<Container> containers = workbook.getNodes();
    workbook.execute(new NodePossibilityEliminator(containers));
  }

  @SuppressWarnings("unused") // Used by GUI clients
  public static void step(Workbook workbook) {
    Set<Container> containers = workbook.getNodes();
    workbook.executeOnce(new NodePossibilityEliminator(containers));
  }

  @SuppressWarnings("unused")
  public static Workbook build(PuzzleDesc puzzleDesc) {
    final Reporter reporter = new Reporter(System.out);
    return build(puzzleDesc, reporter);
  }

  public static Workbook build(PuzzleDesc puzzleDesc, Reporter reporter) {
    return new WorkbookImpl(puzzleDesc, reporter);
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
}
