package hwm.sudoku;


import java.util.Set;

public interface Cell extends Node {

  boolean checkRowColRec();

  void remove();

  void setC(Character c);

  boolean retainAll(Set<Character> newSet, Node node);

  boolean remove(Character c2, Container node,
      Container crossNode);

  Character getC();

  Set<Container> getContainers();

  String getPossibilitiesStr();
}