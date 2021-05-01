package hwm.sudoku;

import java.util.Set;

public interface Node {
  /**
   * @return the label that describes the position of the node
   */
  String getLabel();

  /**
   * @return the set of possible characters that this node might contain
   */
  Set<Character> getPossibilities();
}