package hwm.sudoku;

import java.util.Set;

import hwm.sudoku.impl.CellImpl;

public interface Container extends Node {

  /**
   * Remove this Cell from all other sets of possible Cells for the other undetermined characters in this container.
   * Then analyse the container for any decisions that might result from this update.
   *
   * @param cell that has been determined and can be removed from sets of possible cells for other characters
   */
  void remove(Cell cell);

  /**
   * @param cell to be added to this container
   */
  void addCell(CellImpl cell);

  /**
   * @return the set of cells contained by this container
   */
  Set<Cell> getCells();

  /**
   * @return name describing the type of node
   */
  Object getNodeType();

  /**
   * @return TODO What does this do?
   */
  boolean checkShares();

}