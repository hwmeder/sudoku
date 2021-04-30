package hwm.sudoku;

public interface PuzzleDesc {

  /**
   * @return char used for blank cells
   */
  char getNullCharacter();

  /**
   * @return # of rectangles in a column of the puzzle
   */
  int getNRecsInCol();

  /**
   * @return # of rectangles in a row of the puzzle
   */
  int getNRecsInRow();

  /**
   * @return # of cells in a column of a rectangle
   */
  int getNCellsInRecCol();

  /**
   * @return # of cells in a row of a rectangle
   */
  int getNCellsInRecRow();

  /**
   * @return String containing the characters to be used in filling in the cells
   */
  String getPossibleCharacterStr();

  /**
   * @return array of String describing the contents of the rows of the puzzle
   */
  String[] getPuzzleStrArray();

  /**
   * Initialize the puzzle after setting the description of the puzzle 
   */
  void init();

  /**
   * @param dimension = number of cells on a side of the sqaure puzzle
   */
  void setDimension(int dimension);

  /**
   * @param puzzleStrArray = array of String describing the initial contents of the rows of the puzzle
   */
  void setPuzzleStrArray(String[] puzzleStrArray);

  /**
   * @param nRecsInCol = # of rectangles in a column of the puzzle
   */
  void setNRecsInCol(int nRecsInCol);

  /**
   * @param nRecsInRow = # of rectangles in a row of the puzzle
   */
  void setNRecsInRow(int nRecsInRow);

  /**
   * @param possibleCharacterStr = String containing the characters to be used in filling in the cells
   */
  void setPossibleCharacterStr(String possibleCharacterStr);

  /**
   * @param proposedSolutionStr expected solution
   * @return true if proposedSolution matches expected solution
   */
  boolean checkSolution(String[] proposedSolutionStr);

  /**
   * @return Cell Array corresponding to puzzle
   */
  Character[][] getCharGrid();

  /**
   * @param recRow row of rectangle
   * @param recCol column of rectangle
   * @param cellRow row within rectangle of the cell
   * @param cellCol column within rectangle of the cell
   * @return true if specified cell is part of original puzzle
   */
  boolean isOriginal(int recRow, int recCol, int cellRow, int cellCol) ;
}