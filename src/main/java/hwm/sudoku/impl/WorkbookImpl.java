package hwm.sudoku.impl;

import hwm.Reporter;
import hwm.sudoku.Cell;
import hwm.sudoku.Container;
import hwm.sudoku.Puzzle;
import hwm.sudoku.PuzzleDesc;
import hwm.sudoku.Strategy;
import hwm.sudoku.Workbook;

import java.util.HashSet;
import java.util.Set;

public class WorkbookImpl implements Workbook {

  private int count;

  private final Reporter reporter;

  private final String puzzleName;

  private final Set<Container> nodeSet = new HashSet<>();

  private final Puzzle puzzle;

  public WorkbookImpl(PuzzleDesc puzzleDesc, Reporter reporter) {
    Puzzle puzzle = new PuzzleImpl(puzzleDesc);
    this.puzzleName = puzzle.getName();
    this.reporter = reporter;
    final Set<Cell> cellSet = new HashSet<>();
    puzzle.build(reporter, nodeSet, cellSet);
    this.puzzle = puzzle;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.Workbook#displayCells()
   */
  public void displayCells() {
    puzzle.displayCells();
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.Workbook#displayPuzzleName()
   */
  public void displayPuzzleName() {
    reporter.println(puzzleName);
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.Workbook#execute(java.util.List)
   */
  public void execute(Strategy strategy) {
    boolean found = true;
    while (found) {
      found = executeOnce(strategy);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.Workbook#executeOnce(java.util.List)
   */
  public boolean executeOnce(Strategy strategy) {
    boolean found;
    reporter.setCount(++count);
    found = strategy.execute();
    reporter.printDone();
    return found;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.Workbook#getNodes()
   */
  public Set<Container> getNodes() {
    return nodeSet;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.Workbook#getCell(int, int, int, int)
   */
  public Cell getCell(int irr, int irc, int icr, int icc) {
    return puzzle.getCell(irr, irc, icr, icc);
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.Workbook#setCellChar(java.lang.String, int, int, int,
   *      int)
   */
  public void setCellChar(String ch, int irr, int irc, int icr, int icc) {
    Cell cell = getCell(irr, irc, icr, icc);
    cell.setC(ch.toCharArray()[0]);
  }

  public String[] getSolutionStr() {
    return puzzle.getSolutionStr();
  }
}
