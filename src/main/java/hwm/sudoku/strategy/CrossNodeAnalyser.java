package hwm.sudoku.strategy;

import hwm.sudoku.Cell;
import hwm.sudoku.Container;
import hwm.sudoku.Strategy;

import java.util.Map;
import java.util.Set;

public class CrossNodeAnalyser implements Strategy {

  private final Map<Character, Set<Container>> map;

  private final Container crossNode;

  public CrossNodeAnalyser(Map<Character, Set<Container>> map,
      Container crossNode) {
    this.map = map;
    this.crossNode = crossNode;
  }

  public boolean execute() {
    for (Character c : map.keySet()) {
      Set<Container> set = map.get(c);
      if (set.size() == 1) {
        Container node = set.iterator().next();
        boolean changed = false;
        for (Cell cell : node.getCells()) {
          if (cell.remove(c, node, crossNode)) {
            changed = true;
          }
        }
        if (changed)
          return true;
      }
    }
    return false;
  }
}
