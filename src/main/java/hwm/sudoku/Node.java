package hwm.sudoku;

import java.util.Set;

public interface Node {

  String getName();

  Set<Character> getPossibilities();

}