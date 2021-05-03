import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import hwm.Reporter;
import hwm.sudoku.Container;
import hwm.sudoku.PuzzleDesc;
import hwm.sudoku.Strategy;
import hwm.sudoku.Workbook;
import hwm.sudoku.impl.PuzzleDescImpl;
import hwm.sudoku.impl.WorkbookImpl;
import hwm.sudoku.strategy.NodePossibilityEliminator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SudokuTest {
  @Test
  public void testAllSamplePuzzles() throws IllegalAccessException, InstantiationException {
    final List<Class<?>> classesInPackage = getClassesInPackage("hwm.puzzle");
    for (Class<?> puzzleClass : classesInPackage) {
      if (hwm.puzzle.Big.class.equals(puzzleClass)) {
        continue; // BiG has multiple solutions
      }
      System.out.println(puzzleClass);
      final PuzzleDescImpl puzzle = (PuzzleDescImpl) puzzleClass.newInstance();
      final Reporter reporter = new MyReporter();
      Workbook workbook = build(puzzle, reporter);
      final String[] problemStr = workbook.getSolutionStr();
      solve(workbook);
      final String[] solutionStr = workbook.getSolutionStr();
      reporter.println(Arrays.toString(problemStr));
      reporter.println(Arrays.toString(solutionStr));
      assertTrue(puzzleClass.getSimpleName(), puzzle.checkSolution(solutionStr));
    }
  }

  private static Workbook build(PuzzleDesc puzzleDesc, Reporter reporter) {
    return new WorkbookImpl(puzzleDesc, reporter);
  }

  private static void solve(Workbook workbook) {
    Set<Container> containers = workbook.getNodes();
    solve(workbook, new NodePossibilityEliminator(containers));
  }

  private static void solve(Workbook workbook, Strategy strategy) {
    workbook.displayCells();

    try {
      workbook.execute(strategy);
    }
    finally {
      workbook.displayCells();
      workbook.displayPuzzleName();
    }
  }

  private static class MyReporter extends Reporter {

    public MyReporter() {
      super(null);
    }

    @Override
    public void println() {
    }

    @Override
    public void println(final String string) {
    }

    @Override
    public void print(final Object o) {
    }

    @Override
    public void printDone() {
    }

    @Override
    public void setCount(final int count) {
    }

    @Override
    public void clear() {
    }

    @Override
    public void printWithPrefix(final String encodedReason, final String englishReason) {
    }
  }

  private static List<Class<?>> getClassesInPackage(@SuppressWarnings("SameParameterValue") String packageName) {
    String path = packageName.replaceAll("\\.", File.separator);
    List<Class<?>> classes = new ArrayList<>();
    String[] classPathEntries = System.getProperty("java.class.path").split(
        System.getProperty("path.separator")
    );

    String name;
    for (String classpathEntry : classPathEntries) {
      if (classpathEntry.endsWith(".jar")) {
        File jar = new File(classpathEntry);
        try {
          JarInputStream is = new JarInputStream(new FileInputStream(jar));
          JarEntry entry;
          while ((entry = is.getNextJarEntry()) != null) {
            name = entry.getName();
            if (name.endsWith(".class")) {
              if (name.contains(path) && name.endsWith(".class")) {
                String classPath = name.substring(0, entry.getName().length() - 6);
                classPath = classPath.replaceAll("[|/]", ".");
                classes.add(Class.forName(classPath));
              }
            }
          }
        }
        catch (Exception ex) {
          // Silence is gold
        }
      }
      else {
        try {
          File base = new File(classpathEntry + File.separatorChar + path);
          for (File file : Objects.requireNonNull(base.listFiles())) {
            name = file.getName();
            if (name.endsWith(".class")) {
              name = name.substring(0, name.length() - 6);
              classes.add(Class.forName(packageName + "." + name));
            }
          }
        }
        catch (Exception ex) {
          // Silence is gold
        }
      }
    }

    return classes;
  }
}
