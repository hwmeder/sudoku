package hwm.sudoku.impl;

import hwm.sudoku.Node;

public abstract class NodeBaseImpl implements Node {

  /**
   * A label describing the identify of the node
   */
  private final String label;

  /**
   * A name describing the type of this node
   */
  private String nodeType;

  /**
   * @param nodeType a name that describes the type of this node
   * @param label    that identifies this node
   */
  public NodeBaseImpl(String nodeType, String label) {
    this.label = label;
    this.nodeType = nodeType;
  }

  public String getLabel() {
    return label;
  }

  public String getNodeType() {
    return nodeType;
  }

}