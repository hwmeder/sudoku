package hwm.sudoku.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import hwm.Reporter;
import hwm.sudoku.Cell;
import hwm.sudoku.Container;
import hwm.sudoku.Node;

public class CellImpl extends NodeBaseImpl implements Cell {

  /**
   * reporter for outputting decisions
   */
  private final Reporter reporter;

  /**
   * The character for this Cell, if it has been determined
   */
  private Character c;

  /**
   * Set of containers containing this cell
   */
  private final Set<Container> containers = new HashSet<>();

  /**
   * Set of possible characters in this cell
   */
  private final Set<Character> possibilities = new HashSet<>();

  /**
   * @param c character contained by this cell, if it has been determined
   * @param possibilities set of possible characters in this sell, if it has not been determined yet
   * @param row the row that contains this cell
   * @param col column that contains this cell
   * @param rec rectangle that contains this cell
   * @param reporter for reporting decisions
   * @param nodeType name that describes the type of node
   * @param label describing this cell
   */
  CellImpl(Character c, Set<Character> possibilities,
      Container row, Container col, Container rec,
      Reporter reporter, String nodeType, String label) {
    super(nodeType, label);
    this.reporter = reporter;
    this.c = c;
    containers.add(row);
    containers.add(col);
    containers.add(rec);

    this.possibilities.addAll(possibilities);
  }

  public boolean setCIfPossible() {
    if (c != null) {
      return false; // the contents of this cell has already been determined and has not changed
    }
    Set<Character> possibilities = getPossibilities();
    if (possibilities.size() != 1) {
      return false; // the contents of this cell can not be determined yet
    }
    setC(possibilities.iterator().next());

    reporter.printWithPrefix(getLabel() + " = " + c + " :: checkRowColRec",
        c + " is the only value that works in " + getLabel() + ".");

    return true; // the contents of this cell has been updated
  }

  public void removeFromContainers() {
    possibilities.remove(c);
    for (Container container : containers) {
      container.remove(this);
    }
    // remove(this);
  }

  public void setC(Character c) {
    this.c = c;
    removeFromContainers();
  }

  @Override
  public Set<Character> getPossibilities() {
    if (c != null)
      return new HashSet<>(c);

    Set<Character> possibilities = new HashSet<>(this.possibilities);
    for (Container container : containers) {
      possibilities.retainAll(container.getPossibilities());
    }
    return possibilities;
  }

  public boolean retainAll(Set<Character> constrainingPossibilities, Node constrainingContainer) {
    Set<Character> possibilities = getPossibilities();
    Set<Character> removedPossibilities = new HashSet<>(possibilities);
    possibilities.retainAll(constrainingPossibilities);
    if (possibilities.size() > constrainingPossibilities.size()) {
      throw new RuntimeException(possibilities.size() + ">" + constrainingPossibilities.size());
    }
    removedPossibilities.removeAll(constrainingPossibilities);
    if (removedPossibilities.isEmpty()) {
      return false;
    }
    this.possibilities.retainAll(constrainingPossibilities);
    String englishReason = constrainingPossibilities.size() + " cells in " + constrainingContainer.getLabel()
        + " can only contain " + constrainingPossibilities + ".  Remove " + removedPossibilities
        + " from " + getLabel() + ".";
    if (constrainingPossibilities.size() == 1) {
      setC(constrainingPossibilities.iterator().next());
      reporter.printWithPrefix(getLabel() + " = " + c + " : " + constrainingContainer.getLabel()
              + " x " + removedPossibilities + " == " + constrainingPossibilities + " :: retainAll",
          englishReason);
    } else {
      reporter.printWithPrefix(getLabel() + " x " + removedPossibilities + " : "
          + constrainingContainer.getLabel() + " " + constrainingPossibilities + " :: retainAll", englishReason);
    }
    RowColRecAnalysisDriver.checkCell(this);
    return true;
  }

  public boolean remove(Character c2, Container node, Container crossNode) {
    if (c != null)
      return false;
    for (Container container : containers) {
      if (crossNode == container)
        return false;
    }
    Set<Character> possibilities = getPossibilities();
    if (possibilities.remove(c2)) {
      this.possibilities.retainAll(possibilities);
      String type = crossNode.getLabel() + " : " + node.getLabel();
      String string = c2 + " can only be in " + crossNode.getLabel() + " in "
          + node.getLabel() + ".  Therefore, remove " + c2 + " from all other "
          + crossNode.getNodeType() + "s in " + node.getLabel() + ".";
      if (possibilities.size() == 1) {
        reporter.printWithPrefix(getLabel() + " = " + c + " : " + type + " x "
            + c2 + " :: remove", string);
        setC(possibilities.iterator().next());
      }
      else {
        reporter.printWithPrefix(getLabel() + " x " + c2 + " : " + type + " "
            + getPossibilities() + " :: remove", string);
        RowColRecAnalysisDriver.checkCell(this);
      }
      return true;
    }
    return false;
  }

  public Character getC() {
    return c;
  }

  public Set<Container> getContainers() {
    return containers;
  }

  public String getPossibilitiesStr() {
    if (c != null)
      return c.toString();
    StringBuilder str = new StringBuilder();
    Set<Character> possibilities = getPossibilities();
    Character[] array = possibilities.toArray(new Character[0]);
    Arrays.sort(array);
    for (Character c : array) {
      str.append(c.toString());
    }
    return str.toString();
  }
}
