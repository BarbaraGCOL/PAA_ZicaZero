import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Subgraph {
	
	private Graph graph;
	private BFS bfs;
	
	public Subgraph(Graph graph){
		this.graph = graph;
		bfs = new BFS(graph);
	}
	
	public boolean isConnected(Set<Integer>subgraph){
		
		int visitedVertices = bfs.bfs(new ArrayList<Integer>(subgraph));
		
		return visitedVertices == subgraph.size();
	}
	
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
