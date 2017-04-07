import java.io.IOException;
import java.util.Random;

public class TSP extends GA {
	
	int[][] costs;
	int iterations = 50;

  public TSP(String paramFileName, String gridFileName) {
    super(paramFileName);
    System.out.println(getGridIndex('a'));
    
    try {
    	this.InitPop();
		this.costs = TableReader.Read(gridFileName);
    	ComputeCost();
        SortPop();
        TidyUp();
	} catch (IOException e) {
		e.printStackTrace();
	}
    
  }
  
  private int getGridIndex(char letter) {
	  return ((int)letter) - 96 - 1;
  }
 
  
protected void InitPop() {
    char letter;
    
    for (int index = 0; index < GA_numChromesInit; index++)
    {
        Chromosome Chrom = new Chromosome(GA_numGenes);
        
        for (int j = 0; j < GA_numGenes; j++)
            { 
                letter = (char) (j + 97); //97 is the value 'a'  
                Chrom.SetGene(j,letter);
            }
        Chrom.SetCost(0);
        GA_pop.add(Chrom);
    }
}
  
private Chromosome swap(Chromosome chrome) {
    Random rnum     = new Random();
    int leftIndex = rnum.nextInt(GA_numGenes);
	int rightIndex = rnum.nextInt(GA_numGenes);
	
	char left = chrome.GetGene(leftIndex);
	char right = chrome.GetGene(rightIndex);
//	
//	System.out.print(leftIndex + " " + left + " ");
//	System.out.print(rightIndex + " " + right + " \n");
	
	chrome.SetGene(rightIndex, left);
	chrome.SetGene(leftIndex, right);
	return chrome;
}
  
protected void Mutate() 
 {
     int totalGenes  = (GA_numGenes * GA_numChromes);
     int numMutate   = (int) (totalGenes * GA_mutFact);
     Random rnum     = new Random();

     for (int i = 0; i < numMutate; i++) 
     {
         //position of chromosome to mutate--but not the first one
         //the number generated is in the range: [1..GA_numChromes)
         
         int chromMut = 1 + (rnum.nextInt(GA_numChromes - 1));
         
         int geneMut = rnum.nextInt(GA_numGenes); //pos of mutated gene
         
         Chromosome newChromosome = GA_pop.remove(chromMut); //get chromosome
         
         swap(newChromosome);
         
         GA_pop.add(newChromosome); //add mutated chromosome at the end
     }
     
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
        chrom.SetCost(cost);
        GA_pop.add(i,chrom);
    }
}

@Override
protected boolean IsCloseEnough(Chromosome chrome) {
	if (this.iterations > 0) {
		this.iterations--;
		return false;
	}
	else return true;
}

public static void main(String args[]) {
	new TSP("params.dat", "table.txt");
}
}
