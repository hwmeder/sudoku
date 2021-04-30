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

  private final int ncols;

  private final int nrows;

  private Cell[][] cells;

  private final Character[][] charGrid;

  private Reporter reporter;

  public PuzzleImpl(PuzzleDesc puzzleDesc) {
    this.puzzle = puzzleDesc;
    ncols = puzzleDesc.getNCellsInRecCol() * puzzleDesc.getNRecsInCol();
    nrows = puzzleDesc.getNCellsInRecRow() * puzzleDesc.getNRecsInRow();
    Set<Character> set = new HashSet<>();

    for (char c : puzzleDesc.getPossibleCharacterStr().toCharArray()) {
      set.add(c);
    }
    possibleCharacters = Collections.unmodifiableSet(set);
    charGrid = puzzleDesc.getCharGrid();
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#getCharacter(int, int, int, int)
   */
  public Character getCharacter(int irr, int irc, int icr, int icc) {
    return charGrid //
    [irr * puzzle.getNCellsInRecCol() + icr] //
    [irc * puzzle.getNCellsInRecRow() + icc];
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#getNullCharacter()
   */
  public Character getNullCharacter() {
    return puzzle.getNullCharacter();
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#getPossibleCharacters()
   */
  public Set<Character> getPossibleCharacters() {
    return possibleCharacters;
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#numCellsInRecCol()
   */
  public int numCellsInRecCol() {
    return puzzle.getNCellsInRecCol();
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#numCellsInRecRow()
   */
  public int numCellsInRecRow() {
    return puzzle.getNCellsInRecRow();
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#numRecCols()
   */
  public int numRecCols() {
    return puzzle.getNRecsInRow();
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#numRecRows()
   */
  public int numRecRows() {
    return puzzle.getNRecsInCol();
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#getName()
   */
  public String getName() {
    return puzzle.getClass().getName();
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#build(hwm.Reporter, java.util.Set, java.util.Set)
   */
  public void build(Reporter reporter, Set<Container> nodeSet, Set<Cell> cellSet) {
    this.reporter = reporter;
    int nrrows = numRecRows();
    int nrcols = numRecCols();
    int ncrows = numCellsInRecCol();
    int nccols = numCellsInRecRow();
    int ncols = nrcols * nccols;
    int nrows = nrrows * ncrows;

    Container[] rows = new ContainerImpl[nrows];
    for (int ir = 0; ir < rows.length; ir++) {
      String label = "Row" + "(" + (ir + 1) + ")";
      Container row = new ContainerImpl(getPossibleCharacters(), reporter,
          "Row", label);
      rows[ir] = row;
      nodeSet.add(row);
    }
    Container[] cols = new ContainerImpl[ncols];
    for (int ic = 0; ic < cols.length; ic++) {
      String label = "Col" + "(" + (ic + 1) + ")";
      Container col = new ContainerImpl(getPossibleCharacters(), reporter,
          "Column", label);
      cols[ic] = col;
      nodeSet.add(col);
    }
    cells = new CellImpl[ncols][nrows];
    for (int irr = 0; irr < nrrows; irr++) {
      for (int irc = 0; irc < nrcols; irc++) {
        String label = "Box" + "(" + (irr + 1) + "," + (irc + 1) + ")";
        Container rec = new ContainerImpl(getPossibleCharacters(), reporter,
            "Boxe", label);
        nodeSet.add(rec);
        for (int icr = 0; icr < ncrows; icr++) {
          int ir = irr * ncrows + icr;
          Container row = rows[ir];
          for (int icc = 0; icc < nccols; icc++) {
            int ic = irc * nccols + icc;
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
            ContainerImpl.checkRowColRec(cell);
          }
        }
      }
    }

    for (Cell cell : cellSet) {
      cell.remove();
    }
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#displayCells()
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
          reporter.println(cell.getName() + possibilities);
        }
      }
    }
    reporter.println();
  }

  /* (non-Javadoc)
   * @see hwm.sudoku.impl.Puzzle#getCell(int, int, int, int)
   */
  public Cell getCell(int irr, int irc, int icr, int icc) {
    int ir = irr * puzzle.getNCellsInRecCol() + icr;
    int ic = irc * puzzle.getNCellsInRecRow() + icc;
//    System.out.println(ir+" "+irr+" "+ncrows+" "+icr+" "+ic+" "+irc+" "+nccols+" "+icc);
    return cells[ir][ic];
  }

  public String[] getSolutionStr() {
    String[] solutionStrArray = new String [nrows];
    for (int ir = 0; ir < nrows; ir++) {
      StringBuilder rowStr = new StringBuilder();
      for (int ic = 0; ic < ncols; ic++) {
        Character c = cells[ir][ic].getC();
        char ch = c == null ? '.' : c;
        rowStr.append(ch);
      }
      solutionStrArray[ir] = rowStr.toString();
    }
    return solutionStrArray;
  }

}