package hwm.sudoku;

import hwm.sudoku.impl.CellImpl;

import java.util.Set;

public interface Container extends Node {

  void remove(Cell cell);

  void addCell(CellImpl cell);

  Set<CellImpl> getCells();

  Object getNodeType();

  boolean checkShares();

}