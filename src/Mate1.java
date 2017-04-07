import java.util.*;
import java.lang.*;

public class Mate1
{
 private    Chromosome MT_father, MT_mother, MT_child1, MT_child2;
 private    int MT_posChild1, MT_posChild2, MT_posLastChild,MT_posFather, MT_posMother,
             MT_numGenes, MT_numChromes;

 public Mate1(ArrayList<Chromosome> population, int numGenes, int numChromes)
    {
        MT_numGenes     = numGenes;
        MT_numChromes   = numChromes;
        
        MT_posChild1    = population.size()/2;
        MT_posChild2    = MT_posChild1 + 1;
        MT_posLastChild= population.size() - 1;
        
        for (int i = MT_posLastChild; i >= MT_posChild1; i--)
            population.remove(i);
        
        MT_posFather = 0;
        MT_posMother = 1;
    }


 public ArrayList<Chromosome> CycleCrossover(ArrayList<Chromosome> population, int numPairs)
 {
    for (int j = 0; j < numPairs; j++)
    {
            MT_father       =  population.get(MT_posFather);
            MT_mother       =  population.get(MT_posMother);
            MT_child1       = new Chromosome(MT_numGenes);
            MT_child2       = new Chromosome(MT_numGenes);
            
    
            char[] fatherGenes = new char[MT_father.GetNumGenes()];
            char[] motherGenes = new char[MT_mother.GetNumGenes()];

            for (int i =0; i < MT_father.GetNumGenes(); i++)
            {
                fatherGenes[i] = MT_father.GetGene(i);
            }
            for (int i =0; i < MT_mother.GetNumGenes(); i++)
            {
                motherGenes[i] = MT_mother.GetGene(i);
            }

            char[] childGenes =  new char[MT_father.GetNumGenes()];
            //Randomly choose 1 gene from the dad
            Random r = new Random();
            int low = 0;
            int high = MT_father.GetNumGenes() - 1;
            int rGene = r.nextInt(high-low) + low;


            //first character in the cycle
            char startPair1 = fatherGenes[rGene];
            char startPair2 = motherGenes[rGene];

            childGenes[0] = startPair1;


            char curGene = startPair1;
            int a = 0;
            int b = 0;
            do{ 
                if (motherGenes[rGene] == fatherGenes[a]){
                    rGene = a;
                    childGenes[b] = curGene;
                    j++;
                }
                
                a = (a + 1)%MT_father.GetNumGenes(); //this is to stay in the index
            }while(curGene != startPair1);

            MT_child1       = new Chromosome(MT_numGenes);
            for (int i =0; i < MT_father.GetNumGenes(); i++)
            {
                MT_child1.SetGene(i, childGenes[i]);
            }
            population.add(MT_posChild1,MT_child1);

        }
        return population;
    }
    

 }
 