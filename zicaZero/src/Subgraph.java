import java.util.HashSet;
import java.util.Set;

/**
 * Class to Represent a Subgraph
 * @author barbara.lopes
 *
 */
public class Subgraph {
	
	private Graph graph;
	private GraphSearch graphSearch;
	
	public Subgraph(Graph graph){
		this.graph = graph;
		graphSearch = new GraphSearch(graph);
	}
	
	/**
	 * See if the subgraph is connected (optimal solution)
	 * @param subgraph
	 * @return true if the subgraph is connected, false if isn't
	 */
	public boolean isConnected(Set<Integer>subgraph){
		
		int visitedVertices = graphSearch.bfs(new HashSet<Integer>(subgraph));
		
		return visitedVertices == subgraph.size();
	}
	
	/**
	 * See if the nodes of the subgraph cover all existent focus of the original graph
	 * @param subgraph
	 * @return true if the subgraph cover all focus, false if it doesn't
	 */
	public boolean coverAllFocus(Set<Integer>subgraph){
		
		Set<Integer> coverFocus = new HashSet<Integer>();
		
		for(int node: subgraph){
			coverFocus.addAll(graph.getFocus(node));
		}
		if(coverFocus.size() == graph.getFocusCount())
			return true;
		return false;
	}
}
