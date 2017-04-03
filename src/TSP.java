import java.io.IOException;

public class TSP extends GA {
	
	int[][] costs;

  public TSP(String paramFileName, String gridFileName) {
    super(paramFileName);
    System.out.println(getGridIndex('a'));
    
    try {
		this.costs = TableReader.Read(gridFileName);
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  
  private int getGridIndex(char letter) {
	  return ((int)letter) - 96 - 1;
  }
 
@Override
protected void ComputeCost() {
	for (int i = 0; i < GA_pop.size(); i++)
    {
        int cost = 0;
        Chromosome chrom = GA_pop.remove(i);
        for (int j = 0; j < GA_numGenes-1; j++) {
        	int col = getGridIndex(chrom.GetGene(j));
        	int row = getGridIndex(chrom.GetGene(j + 1));
        	cost += this.costs[col][row];
        }
        cost++;
        chrom.SetCost(cost);
        GA_pop.add(i,chrom);
    }
}

@Override
protected boolean IsCloseEnough(Chromosome chrome) {
	return true;
}

public static void main(String args[]) {
	new TSP("params.dat", "table.txt");
}
}
