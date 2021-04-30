package hwm.sudoku.impl;

import hwm.sudoku.PuzzleDesc;

import java.util.Arrays;

/**
 * @author hwm
 * 
 */
public class PuzzleDescImpl implements PuzzleDesc {

  private int nRecsInRow = 3;

  private int nRecsInCol = 3;

  private final char nullCharacter = '.';

  private String possibleCharacterStr = "123456789";

  private String[] puzzleStrArray;

  private String[] solutionStrArray;

  private Character[][] charGrid;

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.PuzzleDesc#setDimension(int)
   */
  public void setDimension(int dimension) {
    if (dimension < 1)
      dimension = 1;

    nRecsInRow = (int) Math.round(Math.sqrt(dimension));
    for (; dimension % nRecsInRow > 0; nRecsInRow--) { }
    nRecsInCol = dimension / nRecsInRow;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.PuzzleDesc#init()
   */
  public void init() {
    int dimension = nRecsInRow * nRecsInCol;
    possibleCharacterStr = sortAndRemoveDuplicates(possibleCharacterStr);
    // Append additional characters in case they are needed.
    possibleCharacterStr = possibleCharacterStr.replaceAll("\\.", "")
        + "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()-=+{}[]:\"<>?/\\|"
            .replaceAll(regexEscape(possibleCharacterStr), "");

    if (dimension > possibleCharacterStr.length()) {
      dimension = possibleCharacterStr.length();
      setDimension(dimension);
    }
    StringBuilder usedCharStr = new StringBuilder("" + nullCharacter);

    String[] puzzleStrArray = new String[dimension];
    char[] puzzleCharArray = new char[dimension];
    Arrays.fill(puzzleCharArray, nullCharacter);
    String nullPuzzleStr = new String(puzzleCharArray);
    int i = 0;
    int max = 0;
    if (this.puzzleStrArray != null) {
      max = Math.min(this.puzzleStrArray.length, dimension);
    }
    for (; i < max; i++) {
      String puzzleStr = this.puzzleStrArray[i];
      if (puzzleStr == null)
        puzzleStr = "";
      puzzleStr = (puzzleStr + nullPuzzleStr).substring(0, dimension);
      puzzleStrArray[i] = puzzleStr;
      usedCharStr.append(puzzleStr);
    }
    for (; i < dimension; i++) {
      puzzleStrArray[i] = nullPuzzleStr;
    }
    this.puzzleStrArray = puzzleStrArray;

    usedCharStr = new StringBuilder(sortAndRemoveDuplicates(usedCharStr.toString()));
    possibleCharacterStr = usedCharStr.toString().replaceAll(regexEscape(nullCharacter
        + ""), "")
        + possibleCharacterStr.replaceAll(regexEscape(usedCharStr.toString()), "");
    possibleCharacterStr = possibleCharacterStr.substring(0, dimension);

    charGrid = getCharGrid();
  }

  private String sortAndRemoveDuplicates(String string) {
    char[] possibleCharacterArray = string.toCharArray();
    Arrays.sort(possibleCharacterArray);
    for (int i = 1; i < possibleCharacterArray.length; i++) {
      if (possibleCharacterArray[i] == possibleCharacterArray[i - 1])
        possibleCharacterArray[i - 1] = nullCharacter;
    }
    return new String(possibleCharacterArray);
  }

  private String regexEscape(String string) {
    return '[' + string.replaceAll("[\\\\]", "\\\\\\\\").replaceAll("[\\[]",
        "\\\\[").replaceAll("[]]", "\\\\]") + ']';
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.PuzzleDesc#getNullCharacter()
   */
  public char getNullCharacter() {
    return nullCharacter;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.PuzzleDesc#getNRecsInCol()
   */
  public int getNRecsInCol() {
    return nRecsInCol;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.PuzzleDesc#getNRecsInRow()
   */
  public int getNRecsInRow() {
    return nRecsInRow;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.PuzzleDesc#getNCellsInRecCol()
   */
  public int getNCellsInRecCol() {
    return nRecsInRow;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.PuzzleDesc#getNCellsInRecRow()
   */
  public int getNCellsInRecRow() {
    return nRecsInCol;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.PuzzleDesc#getPossibleCharacterStr()
   */
  public String getPossibleCharacterStr() {
    return possibleCharacterStr;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.impl.PuzzleDesc#getPuzzleStr()
   */
  public String[] getPuzzleStrArray() {
    if (puzzleStrArray == null) {
      init();
    }
    return puzzleStrArray;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.PuzzleDesc#setPuzzleStrArray(java.lang.String[])
   */
  public void setPuzzleStrArray(String[] puzzleStrArray) {
    if (puzzleStrArray != null)
      this.puzzleStrArray = puzzleStrArray;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.PuzzleDesc#setNRecsInCol(int)
   */
  public void setNRecsInCol(int nRecsInCol) {
    if (nRecsInCol > 0)
      this.nRecsInCol = nRecsInCol;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.PuzzleDesc#setNRecsInRow(int)
   */
  public void setNRecsInRow(int nRecsInRow) {
    if (nRecsInRow > 0)
      this.nRecsInRow = nRecsInRow;
  }

  /*
   * (non-Javadoc)
   * 
   * @see hwm.sudoku.PuzzleDesc#setPossibleCharacterStr(java.lang.String)
   */
  public void setPossibleCharacterStr(String possibleCharacterStr) {
    if (possibleCharacterStr != null)
      this.possibleCharacterStr = possibleCharacterStr;
  }

  protected void setSolutionStrArray(String[] solutionStrArray) {
    this.solutionStrArray = solutionStrArray;
  }

  public boolean checkSolution(String[] proposedSolutionStrArray) {
    if (solutionStrArray.length != proposedSolutionStrArray.length)
      return false;
    for (int i = 0; i < solutionStrArray.length; i++) {
      if (!solutionStrArray[i].equals(proposedSolutionStrArray[i])) {
        System.out.println(solutionStrArray[i] + " != "
            + proposedSolutionStrArray[i]);
        return false;
      }
    }
    return true;
  }

  public Character[][] getCharGrid() {
    int ncols = nRecsInCol * nRecsInRow;
    Character[][] chargrid = new Character[ncols][ncols];
    for (int ir = 0; ir < ncols; ir++) {
      char[] chars = getPuzzleStrArray()[ir].toCharArray();
      for (int ic = 0; ic < chars.length; ic++) {
        char c = chars[ic];
        if (c != getNullCharacter()) {
          chargrid[ir][ic] = c;
        }
      }
    }
    return chargrid;
  }

  public boolean isOriginal(int recRow, int recCol, int cellRow, int cellCol) {
    return charGrid[recRow * nRecsInRow + cellRow][recCol * nRecsInCol
        + cellCol] != null;
  }
}
