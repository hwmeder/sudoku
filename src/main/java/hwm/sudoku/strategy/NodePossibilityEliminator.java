package hwm.sudoku.strategy;

import hwm.sudoku.Container;
import hwm.sudoku.Strategy;

import java.util.Set;

public class NodePossibilityEliminator implements Strategy {

  private final Set<Container> nodes;

  public NodePossibilityEliminator(Set<Container> nodes) {
    this.nodes = nodes;
  }

  public boolean execute() {
    for (Container node : nodes) {
      if (node.checkShares()) {
        return true;
      }
    }
    return false;
  }
}