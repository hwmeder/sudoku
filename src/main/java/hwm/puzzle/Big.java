package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Big extends PuzzleDescImpl {
  public Big() {
    setNRecsInRow(4);
    setNRecsInCol(4);

    String[] puzzleStr = {
        "1...8...B.G2E...",
        ".2...9...C.13F..",
        "..3...A...D.24G.",
        "...4...B...E.351",
        "2...5...C...F.46",
        "73...6...D...G.5",
        "684...7...E...1.",
        ".795...8...F...2",
        "3.8A6...9...G...",
        ".4.9B7...A...1..",
        "..5.AC8...B...2.",
        "...6.BD9...C...3",
        "4...7.CEA...D...",
        ".5...8.DFB...E..",
        "..6...9.EGC...F.",
        "...7...A.F1D...G",
    };
    setPuzzleStrArray(puzzleStr);
    String[] solutionStrArray = {
        "5229F3479D76.8E1",
        "GFD45E1A8B3C2697",
        "318A62.G.2E45CFD",
        "E1679..8257F.B43",
        "8.1E295..F.37AG.",
        "7623G.6FEA1.4958",
        "654GEA.178923FC.",
        "9AF.38.7645GED12",
        "..584G93AGD.C27E",
        "DE7FA1283965B4.G",
        "2GA.DB.5F.471369",
        "43917G.6218.D5AF",
        "A836C7F24EG.91B5",
        "C9B586GE132AF7D4",
        "F7G2ADA456B98E3C",
        "14ED8539C7F86G2A"
    };
    setSolutionStrArray(solutionStrArray);
  }
}
