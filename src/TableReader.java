import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;

public class TableReader {

  String fileName;

  public TableReader(String fileName) {
    this.fileName = fileName;
  }
  
  private static int[] getInts(String line) {
	  String[] splits = line.split(" ");
	  int[] nums = new int[splits.length];
	  for (int i = 0; i < splits.length; i++) {
		  nums[i] = Integer.parseInt(splits[i]);
	  }
	  return nums;
  }

  public static int[][] Read(String fileName) throws IOException {
    Collection<String> lines = Files.lines(Paths.get(fileName))
      .collect(Collectors.toList());
    String[] linesArray = lines.toArray(new String[lines.size()]);
    
    int[][] nums = new int[lines.size()][];
    
    for (int i = 0; i < lines.size(); i++) {
    	nums[i] = TableReader.getInts(linesArray[i]);
    }
    
	return nums;
  }
 
  
}
