import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Permute {

	public Set<Integer> combine(Graph graph)
	{
		List<Integer>vertices = graph.getVertices();
		Subgraph subgraph = new Subgraph(graph);
		int counVertices = graph.getVertexCount();

		Set<Set<Integer>> combinations = new LinkedHashSet<Set<Integer>>();
		Set<Integer> combination;
		
		for(int i = 0; i < vertices.size(); i++)
		{
			combination = new LinkedHashSet<Integer>();
			combination.add(vertices.get(i));
			if(subgraph.coverAllFocus(combination)){
				return combination;
			}
			combinations.add(combination);
		}
		
		for(int combNum = 2 ; combNum <= counVertices; combNum++)
		{
			Set<Set<Integer>> combinationsAux = new LinkedHashSet<Set<Integer>>();
			
			for(Set<Integer> comb: combinations)
			{
				if(comb.size() == combNum -1){
					for(int i = 0; i < vertices.size(); i++)
					{
						Set<Integer> newComb = new LinkedHashSet<Integer>(comb);
						newComb.add(vertices.get(i));
						if(subgraph.coverAllFocus(newComb) && subgraph.isConnected(newComb)){
							return newComb;
						}
						combinationsAux.add(newComb);
					}
				}
			}
			combinations.addAll(combinationsAux);
		}
		return graph.getVerticesSet();
	}
}
