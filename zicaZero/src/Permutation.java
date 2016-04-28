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
		int counVertices = graph.getVertexCount();

		Set<Set<Integer>> combinations = new HashSet<Set<Integer>>();
		Set<Integer> combination;
		
		for(int vertex: vertices)
		{
			combination = new HashSet<Integer>();
			combination.add(vertex);
			if(subgraph.coverAllFocus(combination)){
				return combination;
			}
			combinations.add(combination);
		}

		System.gc();
		
		for(int combNum = 2 ; combNum <= counVertices; combNum++)
		{
			Set<Set<Integer>> combinationsAux = new HashSet<Set<Integer>>();
			
			for(Set<Integer> comb: combinations)
			{
				if(comb.size() == combNum -1){
					for(int vertex: vertices)
					{
						Set<Integer> newComb = new HashSet<Integer>(comb);
						newComb.add(vertex);
						if(subgraph.coverAllFocus(newComb) && subgraph.isConnected(newComb)){
							return newComb;
						}
						combinationsAux.add(newComb);
					}
				}
			}
			combinations.addAll(combinationsAux);
		}
		
		System.gc();
		
		return vertices;
	}
}
