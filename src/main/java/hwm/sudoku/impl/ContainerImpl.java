package hwm.sudoku.impl;

import hwm.Reporter;
import hwm.sudoku.Cell;
import hwm.sudoku.Container;
import hwm.sudoku.strategy.ClosedSetScanner;
import hwm.sudoku.strategy.CrossNodeAnalyser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainerImpl extends NodeBaseImpl implements Container {

  /**
   * The map of Cells in this container that possibly could be set to the specified character.
   */
  private final Map<Character, Set<CellImpl>> cmap = new HashMap<>();

  /**
   * The set of Cells contained in this container.
   */
  private final Set<Cell> cells = new HashSet<>();

  /**
   * A container of Cells
   *
   * @param possibilities full set of possible characters
   * @param nodeType the name for this type of container
   * @param label that identifies this container
   */
  ContainerImpl(Set<Character> possibilities, String nodeType, String label) {
    super(nodeType, label);

    for (Character c : possibilities) {
      cmap.put(c, new HashSet<>());
    }
  }

  @Override
  public void addCell(CellImpl cell) {
    cells.add(cell);
    for (Character c : cmap.keySet()) {
      cmap.get(c).add(cell);
    }
  }

  @Override
  public Set<Cell> getCells() {
    return cells;
  }

  @Override
  public boolean checkShares() {
    Map<Character, Set<Cell>> map = new HashMap<>();
    Map<Object, Map<Character, Set<Container>>> containerMaps = new HashMap<>();
    for (Cell cell : cells) {
      Set<Character> possibilities = cell.getPossibilities();
      for (Character c : possibilities) {
        Set<Cell> set = map.computeIfAbsent(c, k -> new HashSet<>());
        set.add(cell);

        for (Container container : cell.getContainers()) {
          Object containerType = container.getNodeType();
          if (!containerType.equals(this.getNodeType())) {
            Map<Character, Set<Container>> containerMap = containerMaps
                .computeIfAbsent(containerType, k -> new HashMap<>());
            Set<Container> containers = containerMap.computeIfAbsent(c, k -> new HashSet<>());
            containers.add(container);
          }
        }
      }
    }
    try {
      if ((new ClosedSetScanner(map, this)).execute())
        return true;
      for (Object containerType : containerMaps.keySet()) {
        if (!containerType.equals(this.getNodeType())) {
          Map<Character, Set<Container>> containerMap = containerMaps
              .get(containerType);
          if ((new CrossNodeAnalyser(containerMap, this)).execute())
            return true;
        }
      }
      return false;
    } catch (Exception e) {
      throw new RuntimeException(getLabel(), e);
    }
  }

  @Override
  public void remove(Cell cell) {
    if (cmap.remove(cell.getC()) != null) {
      for (Set<CellImpl> set : cmap.values()) {
        //noinspection SuspiciousMethodCalls
        set.remove(cell);
      }
      RowColRecAnalysisDriver.checkCells(cells);
    }
  }

  @Override
  public Set<Character> getPossibilities() {
    return cmap.keySet();
  }
}
