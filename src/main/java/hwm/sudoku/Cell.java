package hwm.sudoku;

import java.util.Set;

public interface Cell extends Node {

  /**
   * If the character for this cell has already been determined, do nothing and return false.
   * <p>
   * Check to see if the set of possible characters has been reduced to a single character.
   * If so, update the character for this cell to to character that remains.
   *
   * @return true if the character for this cell has been updated
   */
  boolean setCIfPossible();

  /**
   * Remove this Cell from all containers of undetermined Cells
   */
  void removeFromContainers();

  /**
   * Set the character for this Cell and
   * remove this Cell from all containers of undetermined Cells
   *
   * @param c character that this Cell should be set to
   */
  void setC(Character c);

  /**
   * Remove any characters from the set of possible characters that are not in the constraining possibilities
   *
   * @param constrainingPossibilities the only characters that are allowed as per the constraining container
   * @param constrainingContainer     the container that is constraining the possible characters
   * @return true if any characters were removed from the possible characters for this cell
   */
  boolean retainAll(Set<Character> constrainingPossibilities, Node constrainingContainer);

  boolean remove(Character c, Container node, Container crossNode);

  /**
   * @return the character that has been determined for this Cell ... null if it has not been determined
   */
  Character getC();

  /**
   * @return the set of containers that contain this Cell
   */
  Set<Container> getContainers();

  /**
   Used by GUI clients
   return a String containing the possible characters for this Cell
   */
  @SuppressWarnings("unused")
  String getPossibilitiesStr();
}