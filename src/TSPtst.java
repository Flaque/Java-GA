
public class TSPtst {

	 public static void main(String args[])
	    {

	        TSP GA = new TSP(args[0],args[1]);

	        System.out.println();
	        //WG1.DisplayParams(); Uncomment to display the contents of the parameter file
	        //WG1.DisplayPop(); Uncomment to display the population before evolution
	        GA.Evolve();
	        //WG1.DisplayPop(); Uncomment to display the population after evolution
	        System.out.println();
	    }
}
