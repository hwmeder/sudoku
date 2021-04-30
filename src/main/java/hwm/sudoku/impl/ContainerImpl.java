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

  private Map<Character, Set<CellImpl>> cmap = new HashMap<>();

  private Set<CellImpl> cells = new HashSet<>();

  ContainerImpl(Set<Character> possibilities, Reporter reporter,
      Object nodeType, String label) {
    super(nodeType, label);

    for (Character c : possibilities) {
      cmap.put(c, new HashSet<>());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.Node#addCell(hwm.Cell)
   */
  public void addCell(CellImpl cell) {
    cells.add(cell);
    for (Character c : cmap.keySet()) {
      cmap.get(c).add(cell);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.Node#getCells()
   */
  public Set<CellImpl> getCells() {
    return cells;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.Node#checkShares(int, boolean)
   */
  public boolean checkShares() {
    Map<Character, Set<Cell>> map = new HashMap<>();
    // Map<Character, Set<Node>> rowMap = new HashMap<Character, Set<Node>>();
    // Map<Character, Set<Node>> colMap = new HashMap<Character, Set<Node>>();
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
    } catch (Throwable t) {
      throw new Error(getName(), t);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.Node#remove(hwm.Cell)
   */
  public void remove(Cell cell) {
    if (cmap.remove(cell.getC()) != null) {
      for (Set<CellImpl> set : cmap.values()) {
        set.remove(cell);
      }
      checkRowColRec(cells);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.Node#getPossibilities()
   */
  public Set<Character> getPossibilities() {
    return cmap.keySet();
  }
}
