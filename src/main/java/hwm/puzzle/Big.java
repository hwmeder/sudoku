package hwm.puzzle;

import hwm.sudoku.impl.PuzzleDescImpl;

public final class Big extends PuzzleDescImpl {
  public Big() {
    setNRecsInRow(4);
    setNRecsInCol(4);

    String[] puzzleStr = {
        "5...F3...D.6..E1",
        "GFD45E1A8B3C2697",
        "3..A6..G..E.5CFD",
        "E.6.9....5.F.B.3",
        "8.1E295..F.37AG.",
        "7..3G..FEA..4958",
        ".54GEA..78923FC.",
        "9AF.38..6.5GED12",
        "..584G93AGD.C27E",
        "DE7FA.2.3965B..G",
        "2GA.DB.5F...1369",
        "43..7G.6....D5AF",
        "A836C7F2.EG.91B5",
        "C9B586GE132AF7D4",
        "F7G2ADA456B98E3C",
        "14ED8539C7F86G2A",
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
