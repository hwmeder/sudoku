package hwm;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Reporter {

  private final PrintStream ps;

  private String prefix;

  private static final NumberFormat format = new DecimalFormat("00");

  public Reporter(PrintStream ps) {
    this.ps = ps;
  }

  public void println() {
    ps.println();
  }

  public void println(String string) {
    ps.println(string);
  }

  public void print(Object o) {
    ps.print(o);
  }

  private void printWithPrefix(String string) {
    if (prefix == null) {
      ps.println();
      ps.print("   ");
    } else {
      ps.print(prefix);
    }
    ps.print(string);
    prefix = null;
  }

  public void printDone() {
    ps.println();
  }

  public void setCount(int count) {
    prefix = format.format(count) + ":";
  }

  public void clear() {
    prefix = "";
  }

  public void printWithPrefix(String encodedReason, String englishReason) {
    printWithPrefix(encodedReason+"\n   - "+englishReason);
  }
}
