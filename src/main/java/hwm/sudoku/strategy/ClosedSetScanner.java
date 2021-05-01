package hwm.sudoku.strategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import hwm.sudoku.Cell;
import hwm.sudoku.Node;
import hwm.sudoku.Strategy;

public class ClosedSetScanner implements Strategy {

  private final Map<Character, Set<Cell>> possibilitiesMap;
  private final Node container;

  /**
   * Looks for a subset of Cells in the specified container to which a set of characters are restricted.
   * If found, then all other characters in this subset of Cells can be excluded from their possible characters.
   *
   * @param possibilitiesMap map of character to the set of possible cells that could contain the character
   * @param container to be analysed
   */
  public ClosedSetScanner(Map<Character, Set<Cell>> possibilitiesMap, Node container) {
    this.possibilitiesMap = Collections.unmodifiableMap(possibilitiesMap);
    this.container = container;
  }

  public boolean execute() {
    Set<Cell> setOfPossibilities = new HashSet<>();
    Set<Character> chars = new HashSet<>();
    return check(chars, setOfPossibilities, this.possibilitiesMap);
  }

  private boolean check(Set<Character> chars, Set<Cell> set, Map<Character, Set<Cell>> possibilitiesMap) {
    Map<Character, Set<Cell>> map = new HashMap<>(possibilitiesMap);
    while (! map.keySet().isEmpty()) {
      Character c = map.keySet().iterator().next();
      Set<Cell> addedCells = new HashSet<>(map.remove(c));
      addedCells.removeAll(set);
      set.addAll(addedCells);
      chars.add(c);
      assert set.size() >= chars.size();
      if (set.size() <= chars.size()) {
        boolean change = false;
        for (Cell cell : set) {
          if (cell.retainAll(chars, container)) {
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
