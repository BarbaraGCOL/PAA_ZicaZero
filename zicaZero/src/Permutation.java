import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to perform a permutation on a graph's nodes
 * @author barbara.lopes
 *
 */
public class Permutation {

	/**
	 * Combine the vertices of the graph and verifies if the 
	 * combination covers all the existent focus of the original graph
	 * and is connected in the graph
	 * @param graph
	 * @return the combinations that satisfies the preconditions
	 */
	public Set<Integer> combine(Graph graph)
	{
		Set<Integer>vertices = graph.getVertices();
		Subgraph subgraph = new Subgraph(graph);
		int countVertices = graph.getVertexCount();

		Set<Set<Integer>> combinations = new HashSet<Set<Integer>>();
		Set<Integer> combination;
		
		Runtime runtime = Runtime.getRuntime();

		NumberFormat format = NumberFormat.getInstance();

		System.gc();
		
		for(int vertex: vertices)
		{
			combination = new HashSet<Integer>();
			combination.add(vertex);
			if(subgraph.coverAllFocus(combination)){
				return combination;
			}
			combinations.add(combination);
		}

		for(int combinationSize = 2 ; combinationSize <= countVertices; combinationSize++)
		{
			Set<Set<Integer>> combinationsAux = new HashSet<Set<Integer>>();
			
			for(Set<Integer> comb: combinations)
			{
				if(comb.size() == combinationSize -1){
					for(int vertex: vertices)
					{
						Set<Integer> newComb = new HashSet<Integer>(comb);
						newComb.add(vertex);
						if(subgraph.coverAllFocus(newComb) && subgraph.isConnected(newComb)){

							StringBuilder sb = new StringBuilder();
							long maxMemory = runtime.maxMemory();
							long allocatedMemory = runtime.totalMemory();
							long freeMemory = runtime.freeMemory();
							
							sb.append("free memory: " + format.format(freeMemory / 1024) + "<br/>");
							sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + "<br/>");
							sb.append("max memory: " + format.format(maxMemory / 1024) + "<br/>");
							sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory))));
							
							System.out.println(sb);
							
							return newComb;
						}
						combinationsAux.add(newComb);
					}
				}
			}
			combinations.addAll(combinationsAux);
		}
		
		
		
		System.gc();
		
		return new HashSet<Integer>();
	}
}
