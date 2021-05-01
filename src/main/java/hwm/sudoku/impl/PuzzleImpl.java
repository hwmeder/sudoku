package hwm.sudoku.impl;

import hwm.Reporter;
import hwm.sudoku.Cell;
import hwm.sudoku.Container;
import hwm.sudoku.Node;
import hwm.sudoku.Puzzle;
import hwm.sudoku.PuzzleDesc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PuzzleImpl implements Puzzle{
  private final PuzzleDesc puzzle;

  private final Set<Character> possibleCharacters;

  private final int nCols;

  private final int nRows;

  private Cell[][] cells;

  private final Character[][] charGrid;

  private Reporter reporter;

  /**
   * @param puzzleDesc describes the puzzle
   */
  public PuzzleImpl(PuzzleDesc puzzleDesc) {
    this.puzzle = puzzleDesc;
    nCols = puzzleDesc.getNCellsInRecCol() * puzzleDesc.getNRecsInCol();
    nRows = puzzleDesc.getNCellsInRecRow() * puzzleDesc.getNRecsInRow();
    Set<Character> set = new HashSet<>();

    for (char c : puzzleDesc.getPossibleCharacterStr().toCharArray()) {
      set.add(c);
    }
    possibleCharacters = Collections.unmodifiableSet(set);
    charGrid = puzzleDesc.getCharGrid();
  }

  /**
   * TODO What does this do?
   */
  public void build(Reporter reporter, Set<Container> nodeSet, Set<Cell> cellSet) {
    this.reporter = reporter;
    int nRRows = numRecRows();
    int nRCols = numRecCols();
    int nRColCells = numCellsInRecCol();
    int nRRowCells = numCellsInRecRow();
    int nCols = nRCols * nRRowCells;
    int nRows = nRRows * nRColCells;

    Container[] rows = new ContainerImpl[nRows];
    for (int ir = 0; ir < rows.length; ir++) {
      String label = "Row" + "(" + (ir + 1) + ")";
      Container row = new ContainerImpl(getPossibleCharacters(), "Row", label);
      rows[ir] = row;
      nodeSet.add(row);
    }
    Container[] cols = new ContainerImpl[nCols];
    for (int ic = 0; ic < cols.length; ic++) {
      String label = "Col" + "(" + (ic + 1) + ")";
      Container col = new ContainerImpl(getPossibleCharacters(), "Column", label);
      cols[ic] = col;
      nodeSet.add(col);
    }
    cells = new CellImpl[nCols][nRows];
    for (int irr = 0; irr < nRRows; irr++) {
      for (int irc = 0; irc < nRCols; irc++) {
        String label = "Box" + "(" + (irr + 1) + "," + (irc + 1) + ")";
        Container rec = new ContainerImpl(getPossibleCharacters(), "Box", label);
        nodeSet.add(rec);
        for (int icr = 0; icr < nRColCells; icr++) {
          int ir = irr * nRColCells + icr;
          Container row = rows[ir];
          for (int icc = 0; icc < nRRowCells; icc++) {
            int ic = irc * nRRowCells + icc;
            Container col = cols[ic];
            Character c = getCharacter(irr, irc, icr, icc);
            label = "Cell(" + (ir + 1) + "," + (ic + 1) + ")";
            CellImpl cell = new CellImpl(c, getPossibleCharacters(), row, col,
                rec, reporter, "Cell", label);
            cells[ir][ic] = cell;
            cellSet.add(cell);
            row.addCell(cell);
            col.addCell(cell);
            rec.addCell(cell);
            RowColRecAnalysisDriver.checkCell(cell);
          }
        }
      }
    }

    for (Cell cell : cellSet) {
      cell.removeFromContainers();
    }
  }

  /**
   * @return the character to be used to represent an unknown cell
   */
  public Character getNullCharacter() {
    return puzzle.getNullCharacter();
  }

  /**
   * @return the set of possible characters
   */
  public Set<Character> getPossibleCharacters() {
    return possibleCharacters;
  }

  /**
   * @return the number rows within a rectangle
   */
  public int numCellsInRecCol() {
    return puzzle.getNCellsInRecCol();
  }

  /**
   * @return the number columns within a rectangle
   */
  public int numCellsInRecRow() {
    return puzzle.getNCellsInRecRow();
  }

  /**
   * @return the number columns of rectangles
   */
  public int numRecCols() {
    return puzzle.getNRecsInRow();
  }

  /**
   * @return the number rows of rectangles
   */
  public int numRecRows() {
    return puzzle.getNRecsInCol();
  }

  /**
   * @return the name of the Puzzle
   */
  public String getName() {
    return puzzle.getClass().getName();
  }

  /**
   * @param recRow rectangle row
   * @param recCol rectangle columns
   * @param cellRow cell row within rectangle
   * @param cellCol cell column within rectangle
   * @return the character for the specified Cell
   */
  public Character getCharacter(int recRow, int recCol, int cellRow, int cellCol) {
    return charGrid //
        [recRow * puzzle.getNCellsInRecCol() + cellRow] //
        [recCol * puzzle.getNCellsInRecRow() + cellCol];
  }

  /**
   * @param recRow rectangle row
   * @param recCol rectangle columns
   * @param cellRow cell row within rectangle
   * @param cellCol cell column within rectangle
   * @return the specified Cell
   */
  public Cell getCell(int recRow, int recCol, int cellRow, int cellCol) {
    int ir = recRow * puzzle.getNCellsInRecCol() + cellRow;
    int ic = recCol * puzzle.getNCellsInRecRow() + cellCol;
    return cells[ir][ic];
  }

  /**
   * Displays the known Cells and then lists the possibilities for the remaining Cells.
   */
  public void displayCells() {
    reporter.clear();
    reporter.println();
    for (final Cell[] item : cells) {
      for (int ic = 0; ic < cells.length; ic++) {
        Character c = item[ic].getC();
        char ch = c == null ? '.' : c;
        reporter.print(" " + ch + ' ');
      }
      reporter.println();
    }
    for (final Cell[] value : cells) {
      for (int ic = 0; ic < cells.length; ic++) {
        Node cell = value[ic];
        Set<Character> possibilities = cell.getPossibilities();
        if (possibilities.size() > 0) {
          reporter.println(cell.getLabel() + possibilities);
        }
      }
    }
    reporter.println();
  }

  /**
   * @return a String array describing the final solution
   */
  public String[] getSolutionStr() {
    String[] solutionStrArray = new String [nRows];
    for (int ir = 0; ir < nRows; ir++) {
      StringBuilder rowStr = new StringBuilder();
      for (int ic = 0; ic < nCols; ic++) {
        Character c = cells[ir][ic].getC();
        char ch = c == null ? '.' : c;
        rowStr.append(ch);
      }
      solutionStrArray[ir] = rowStr.toString();
    }
    return solutionStrArray;
  }

}