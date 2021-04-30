package hwm.sudoku.impl;

import hwm.sudoku.Cell;
import hwm.sudoku.Node;

import java.util.Set;

public abstract class NodeBaseImpl implements Node {

  private final String label;

  private Object nodeType;

  private static boolean continueProcessing = false;

  private static boolean eliminateFoundC = true;

  public NodeBaseImpl(Object nodeType, String label) {
    this.label = label;
    this.nodeType = nodeType;
  }

  public static boolean checkRowColRec(Set<CellImpl> cells) {
    boolean changed = false;
    if (isContinueProcessing()) {
      for (Cell cell : cells) {
        changed |= cell.checkRowColRec();
        if (changed && !isContinueProcessing())
          return true;
      }
    }
    return changed;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.Node#getCoords()
   */
  public String getName() {
    return label;
  }

  protected static boolean isContinueProcessing() {
    return continueProcessing;
  }

  public static void setContinueProcessing(boolean continueProcessing) {
    NodeBaseImpl.continueProcessing = continueProcessing;
  }

  protected static void checkRowColRec(Cell cell) {
    if (isContinueProcessing()) {
      cell.checkRowColRec();
    }
  }

  public static boolean isEliminateFoundC() {
    return eliminateFoundC;
  }

  public static void setEliminateFoundC(boolean eliminateFoundC) {
    NodeBaseImpl.eliminateFoundC = eliminateFoundC;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.Node#getNodeType()
   */
  public Object getNodeType() {
    return nodeType;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.Node#getPossibilities()
   */
  public abstract Set<Character> getPossibilities();
}