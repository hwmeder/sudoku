package hwm.sudoku.impl;

import hwm.Reporter;
import hwm.sudoku.Cell;
import hwm.sudoku.Container;
import hwm.sudoku.Node;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CellImpl extends NodeBaseImpl implements Cell {

  private final Reporter reporter;

  private Character c;

  /**
   * Set of Node containing this Cell.
   */
  private final Set<Container> containers = new HashSet<>();

  private final Set<Character> possibilities = new HashSet<>();

  CellImpl(Character c, Set<Character> possibilities, Container row,
      Container col, Container rec, Reporter reporter, Object nodeType,
      String label) {
    super(nodeType, label);
    this.reporter = reporter;
    this.c = c;
    containers.add(row);
    containers.add(col);
    containers.add(rec);

    this.possibilities.addAll(possibilities);
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.soduku.impl.Cell#checkRowColRec()
   */
  public boolean checkRowColRec() {
    if (c != null) {
      return false;
    }
    Set<Character> possibilities = getPossibilities();
    if (possibilities.size() != 1)
      return false;
    Character target = possibilities.iterator().next();
    if (target == null)
      throw new Error();
    setC(target);
    reporter.printWithPrefix(getName() + " = " + c + " :: checkRowColRec", c
        + " is the only value that works in " + getName() + ".");
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.soduku.impl.Cell#remove()
   */
  public void remove() {
    possibilities.remove(c);
    for (Container container : containers) {
      container.remove(this);
    }
    // remove(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.soduku.impl.Cell#setC(java.lang.Character)
   */
  public void setC(Character c) {
    this.c = c;
    remove();
  }

  @Override
  public Set<Character> getPossibilities() {
    Set<Character> possibilities = new HashSet<>();
    if (c != null)
      return possibilities;

    possibilities.addAll(this.possibilities);
    for (Container container : containers) {
      possibilities.retainAll(container.getPossibilities());
    }
    return possibilities;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.soduku.impl.Cell#retainAll(java.util.Set, java.lang.String)
   */
  public boolean retainAll(Set<Character> newSet, Node node) {
    Set<Character> possibilities = getPossibilities();
    Set<Character> removedPossibilities = new HashSet<>(possibilities);
    if (possibilities.size() <= newSet.size())
      return false;
    possibilities.retainAll(newSet);
    if (possibilities.size() > newSet.size())
      throw new Error(possibilities.size() + ">" + newSet.size());
    removedPossibilities.removeAll(newSet);
    this.possibilities.retainAll(newSet);
    String englishReason = newSet.size() + " cells in " + node.getName()
        + " can only contain " + newSet + ".  Remove " + removedPossibilities
        + " from " + getName() + ".";
    if (newSet.size() == 1) {
      setC(newSet.iterator().next());
      reporter.printWithPrefix(getName() + " = " + c + " : " + node.getName()
          + " x " + removedPossibilities + " == " + newSet + " :: retainAll",
          englishReason);
    } else {
      reporter.printWithPrefix(getName() + " x " + removedPossibilities + " : "
          + node.getName() + " " + newSet + " :: retainAll", englishReason);
    }
    checkRowColRec(this);
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.soduku.impl.Cell#remove(java.lang.Character, hwm.soduku.Container,
   *      hwm.soduku.Container)
   */
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
      String type = crossNode.getName() + " : " + node.getName();
      String string = c2 + " can only be in " + crossNode.getName() + " in "
          + node.getName() + ".  Therefore, remove " + c2 + " from all other "
          + crossNode.getNodeType() + "s in " + node.getName() + ".";
      if (possibilities.size() == 1) {
        reporter.printWithPrefix(getName() + " = " + c + " : " + type + " x "
            + c2 + " :: remove", string);
        setC(possibilities.iterator().next());
      } else {
        reporter.printWithPrefix(getName() + " x " + c2 + " : " + type + " "
            + getPossibilities() + " :: remove", string);
        checkRowColRec(this);
      }
      return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.soduku.impl.Cell#getC()
   */
  public Character getC() {
    return c;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.soduku.impl.Cell#getContainers()
   */
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
