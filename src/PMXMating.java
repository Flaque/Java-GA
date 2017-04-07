import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PMXMating extends Mate {

	public PMXMating(ArrayList<Chromosome> population, int numGenes, int numChromes) {
		super(population, numGenes, numChromes);
	}
	
	private int[] bounds() {
		Random rnum = new Random();
		int left = rnum.nextInt(MT_numGenes);
		int right = rnum.nextInt(MT_numGenes);
		int[] values = new int[2];
		
		if (left == right) return bounds();
		if (left < right) {
			values[0] = left; values[1] = right;
			return values;
		} else {
			values[1] = left; values[0] = right;
			return values;
		}
	}
	
	private void swap(Chromosome chrome_one, Chromosome chrome_two, int index_1, int index_2) {
		char one_gene = chrome_one.GetGene(index_1);
		char two_gene = chrome_two.GetGene(index_2);
		
		chrome_one.SetGene(index_1, two_gene);
		chrome_two.SetGene(index_2, one_gene);
	}
	
	private Map<Character, Character> getBoundsMap(Chromosome chrome_one, Chromosome chrome_two, int[] bounds) {
		int left = bounds[0];
        int right = bounds[1];
        
        Map<Character, Character> mapity_maps = new HashMap<Character, Character>();
        
        for (int i = left; i <= right; i++) {
        	char g_one = chrome_one.GetGene(i);
        	char g_two = chrome_two.GetGene(i);
        	mapity_maps.put(g_one, g_two); // Like a g_six
        }
        
        return mapity_maps;
	}
	
	private void swap_bounds(Chromosome chrome_one, Chromosome chrome_two, int[] bounds) {
		int left = bounds[0];
        int right = bounds[1];
        
        for (int i = left; i <= right; i++) {
            this.swap(chrome_one, chrome_two, i, i);
        }
	}
	
	private ArrayList<Integer> leftOvers(int[] bounds) {
		int size = this.MT_numGenes - (bounds[1] - bounds[0]) - 2;
		ArrayList<Integer> elements = new ArrayList<Integer>();
		for (int i = 0; i < bounds[0]; i++) {
			elements.add(i);
		}
		for (int i = bounds[1]+1; i < this.MT_numGenes; i++) {
			elements.add(i);
		}
		
		
		return elements;
	}
	
	private ArrayList<Character> getChars(ArrayList<Integer> indices, Chromosome chrome) {
		
		ArrayList<Character> chars = new ArrayList<Character>();
		for (int i = 0; i < indices.size(); i++) {
			chars.add(chrome.GetGene(indices.get(i)));
		}
		return chars;
	}
	
	private char getMatchingGene(char gene, Map<Character, Character> map) {
		
		char matched_gene = map.get(gene);
		
		while (map.get(matched_gene) != null) {
		   matched_gene = map.get(gene);
		}
		
		return map.get(matched_gene);
	}
	
	private void swapBasedOnMap(Chromosome chrome_one, Chromosome chrome_two,  Map<Character, Character> map, int[] bounds) {
		
		
		// Left side
		for (int i = 0; i < bounds[0]; i++) {
			char one_gene = chrome_one.GetGene(i);
			char two_gene = getMatchingGene(one_gene, map);
			
			chrome_one.SetGene(i, two_gene);
			chrome_two.SetGene(chrome_two.GetGeneIndex(two_gene), one_gene);
		}
		
		// Right side
		for (int i = bounds[1]+1; i < MT_numGenes; i++) {
			char one_gene = chrome_one.GetGene(i);
			char two_gene = getMatchingGene(one_gene, map);
			
			chrome_one.SetGene(i, two_gene);
			chrome_two.SetGene(chrome_two.GetGeneIndex(two_gene), one_gene);
		}
	}

	public ArrayList<Chromosome> Crossover(ArrayList<Chromosome> population, int numPairs) {
		Random rnum = new Random();
		
		for (int i = 0; i < numPairs; i++) {
			MT_father  = population.get(MT_posFather);
            MT_mother  = population.get(MT_posMother);
            
            MT_child1  = new Chromosome(MT_numGenes);
            MT_child2  = new Chromosome(MT_numGenes);
            
            int crossPoint = rnum.nextInt(MT_numGenes);
            int[] bounds = this.bounds();
            
            // Swap with PMX mating
            swap_bounds(MT_child1, MT_child2, bounds);
            Map<Character, Character> map = getBoundsMap(MT_child1, MT_child2, bounds);
            swapBasedOnMap(MT_father, MT_mother, map, bounds);
		}
		
		return population;
	}
}
