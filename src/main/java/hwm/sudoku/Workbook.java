package hwm.sudoku;

import java.util.Set;

public interface Workbook {

  void displayCells();

  void displayPuzzleName();

  void execute(Strategy strategy);

  boolean executeOnce(Strategy strategy);

  Set<Container> getNodes();

  Cell getCell(int irr, int irc, int icr, int icc);

  void setCellChar(String ch, int irr, int irc, int icr, int icc);

  String[] getSolutionStr();

}