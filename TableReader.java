import java.util.*;
import java.lang.*;

public class TableReader {

  int fileName;

  public TableReader(String fileName) {
    this.fileName = fileName;
  }

  public static int[][] Read(String fileName) {
    Collection<String> lines = Files.lines(Paths.get("table.txt"))
      .collect(Collectors.toList());
    System.out.println(lines);
  }

  public static void main(String[] args) {
    System.out.println("hey there");
  }
}
