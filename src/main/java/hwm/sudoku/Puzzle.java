package hwm.sudoku;

import hwm.Reporter;

import java.util.Set;

public interface Puzzle {

  Character getCharacter(int irr, int irc, int icr, int icc);

  Character getNullCharacter();

  Set<Character> getPossibleCharacters();

  int numCellsInRecCol();

  int numCellsInRecRow();

  int numRecCols();

  int numRecRows();

  String getName();

  void build(Reporter reporter, Set<Container> nodeSet,
      Set<Cell> cellSet);

  void displayCells();

  Cell getCell(int irr, int irc, int icr, int icc);

  String[] getSolutionStr();

}