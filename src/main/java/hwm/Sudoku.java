package hwm;

import hwm.puzzle.*;
import hwm.sudoku.Container;
import hwm.sudoku.PuzzleDesc;
import hwm.sudoku.Strategy;
import hwm.sudoku.Workbook;
import hwm.sudoku.impl.ContainerImpl;
import hwm.sudoku.impl.WorkbookImpl;
import hwm.sudoku.strategy.NodePossibilityEliminator;

import java.util.Set;

public class Sudoku {

  public static void main(String[] args) {
    PuzzleDesc puzzle = new Big();
    Workbook workbook = build(puzzle);
    solve(workbook);
  }

  public static void solve(Workbook workbook) {
    Set<Container> containers = workbook.getNodes();
    solve(workbook, new NodePossibilityEliminator(containers));
  }

  public static void finish(Workbook workbook) {
    ContainerImpl.setContinueProcessing(true);
    ContainerImpl.setEliminateFoundC(true);
    execute(workbook);
  }

  public static void step(Workbook workbook) {
    ContainerImpl.setContinueProcessing(false);
    ContainerImpl.setEliminateFoundC(true);
    executeOnce(workbook);
  }

  private static void executeOnce(Workbook workbook) {
    Set<Container> containers = workbook.getNodes();
    workbook.executeOnce(new NodePossibilityEliminator(containers));
  }

  private static void execute(Workbook workbook) {
    Set<Container> containers = workbook.getNodes();
    workbook.execute(new NodePossibilityEliminator(containers));
  }

  public static Workbook build(PuzzleDesc puzzleDesc) {
    final Reporter reporter = new Reporter(System.out);
    return build(puzzleDesc, reporter);
  }

  public static Workbook build(PuzzleDesc puzzleDesc, Reporter reporter) {
    ContainerImpl.setContinueProcessing(false);
    return new WorkbookImpl(puzzleDesc, reporter);
  }

  public static void solve(Workbook workbook, Strategy strategy) {
    ContainerImpl.setContinueProcessing(true);
    workbook.displayCells();

    try {
      workbook.execute(strategy);
    } finally {
      workbook.displayCells();
      workbook.displayPuzzleName();
    }
  }
}
