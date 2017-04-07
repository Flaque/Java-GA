import java.util.*;
import java.lang.*;

public class Mate
{
 private    Chromosome MT_father, MT_mother, MT_child1, MT_child2;
 private    int MT_posChild1, MT_posChild2, MT_posLastChild,MT_posFather, MT_posMother,
             MT_numGenes, MT_numChromes;

 public Mate(ArrayList<Chromosome> population, int numGenes, int numChromes)
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
            
    
            int[] fatherGenes = new int[MT_father.GetNumGenes()];
            int[] motherGenes = new int[MT_mother.GetNumGenes()];

            for (int i =0; i < MT_father.GetNumGenes(); i++)
            {
                fatherGenes[i] = MT_father.GetGene(i);
            }
            for (int i =0; i < MT_mother.GetNumGenes(); i++)
            {
                motherGenes[i] = MT_mother.GetGene(i);
            }

            int[] childGenes =  new int[MT_father.GetNumGenes()];
            //Randomly choose 1 gene from the dad
            Random r = new Random();
            int low = 0;
            int high = MT_father.GetNumGenes() - 1;
            int rGene = r.nextInt(high-low) + low;


            //first character in the cycle
            char startPair1 = fatherGenes[rGene];
            char startPair2 = motherGenes[rGene];

            childGenes.add(startPair1);


            char curGene = startPair1
            i = 0;
            do{ 
                if (motherGenes[rGene] == fatherGenes[i]){
                    rGene = i
                }
                childGenes.add(curGene);
                i = (i + 1)%MT_father.GetNumGenes(); //this is to stay in the index
            }while(curGene != startPair1)

            MT_child1       = new Chromosome(MT_numGenes);
            for (int i =0; i < MT_father.GetNumGenes(); i++)
            {
                MT_child1.SetGene(i, childGenes[i]);
            }
            population.add(MT_posChild1,MT_child1);

        }




    }
    return population;

 }
 //Simple Top-Down Pairing
 public ArrayList<Chromosome> Crossover(ArrayList<Chromosome> population, int numPairs)
    {
        for (int j = 0; j < numPairs; j++)
        {
            MT_father       =  population.get(MT_posFather);
            MT_mother       =  population.get(MT_posMother);
            MT_child1       = new Chromosome(MT_numGenes);
            MT_child2       = new Chromosome(MT_numGenes);
            Random rnum     = new Random();
            int crossPoint  = rnum.nextInt(MT_numGenes);

            //left side
            for (int i = 0; i < crossPoint; i++)
                {
                    MT_child1.SetGene(i,MT_father.GetGene(i));
                    MT_child2.SetGene(i,MT_mother.GetGene(i));
                }
    
            //right side 
            for (int i = crossPoint; i < MT_numGenes; i++)
                {
                    MT_child1.SetGene(i, MT_mother.GetGene(i));
                    MT_child2.SetGene(i, MT_father.GetGene(i));
                }
                
            population.add(MT_posChild1,MT_child1);
            population.add(MT_posChild2,MT_child2);
            
            MT_posChild1    = MT_posChild1 + 2;
            MT_posChild2    = MT_posChild2 + 2;
            MT_posFather    = MT_posFather + 2;
            MT_posMother    = MT_posMother + 2;
        }
        return population;
    }
 }
