package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public class Puzzle16x16 extends PuzzleDescImpl {
  public Puzzle16x16() {
    setNRecsInRow(4);
    setNRecsInCol(4);

    String[] puzzleStr = {//
    "..3E..9..08....4", //
    "0.5.C..8.1.3....", //
    "F..C...4....1.9.", //
    ".D.B0........5.E", //
    "4..6..........0.", //
    "8..D.A..E..1..B9", //
    "C........7.63..5", //
    ".1.26.7..9.D..8.", //
    ".4.3901...5.2...", //
    ".7D...F.B.....A.", //
    "...0.CAB..78....", //
    "9..142..3A...E.7", //
    "....7.....C...EF", //
    ".EF7B......A..5D", //
    "3.............20", //
    ".A...F05.D2B.9C.", //
    };
    setPuzzleStrArray(puzzleStr);
    init();
    String[] solution = {//
    "723EDB91F085CA64", //
    "095ACE286143DF7B", //
    "F68CA3547BDE1092", //
    "1D4B076F9CA2853E", //
    "45962DB3A8FCE701", //
    "837DFA4CE50162B9", //
    "C0AF81E927B63D45", //
    "B1E26570493DFC8A", //
    "A4639017CE5F2BD8", //
    "57D836FEB21904AC", //
    "EF205CABD4789316", //
    "9CB1428D3A605EF7", //
    "DB05793216C4A8EF", //
    "2EF7B8C6039A415D", //
    "38C914DA5FE7B620", //
    "6A14EF058D2B79C3", //
    };
    setSolutionStrArray(solution);
  }
}
