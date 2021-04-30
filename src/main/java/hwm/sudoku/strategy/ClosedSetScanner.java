package hwm.sudoku.strategy;

import hwm.sudoku.Cell;
import hwm.sudoku.Node;
import hwm.sudoku.Strategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClosedSetScanner implements Strategy {

  private final Map<Character, Set<Cell>> map;

  private final Node node;

  public ClosedSetScanner(Map<Character, Set<Cell>> map, Node node) {
    this.map = map;
    this.node = node;
  }

  public boolean execute() {
    Set<Cell> setOfPossibilities = new HashSet<>();
    Map<Character, Set<Cell>> mapOfPossibilities = Collections
        .unmodifiableMap(map);
    Set<Character> chars = new HashSet<>();
    return check(chars, setOfPossibilities, mapOfPossibilities);
  }

  public boolean check(Set<Character> chars, Set<Cell> set,
      Map<Character, Set<Cell>> possibilitiesMap) {
    Map<Character, Set<Cell>> map = new HashMap<>(
        possibilitiesMap);
    while (!map.keySet().isEmpty()) {
      Character c = map.keySet().iterator().next();
      Set<Cell> addedCells = new HashSet<>(map.remove(c));
      addedCells.removeAll(set);
      set.addAll(addedCells);
      chars.add(c);
      assert set.size() >= chars.size();
      if (set.size() <= chars.size()) {
        boolean change = false;
        for (Cell cell : set) {
          if (cell.retainAll(chars, node)) {
            change = true;
          }
        }
        if (change)
          return true;
      } else {
        if (map.size() > 0)
          if (check(chars, set, Collections.unmodifiableMap(map)))
            return true;
      }
      set.removeAll(addedCells);
      chars.remove(c);
    }
    return false;
  }
}
