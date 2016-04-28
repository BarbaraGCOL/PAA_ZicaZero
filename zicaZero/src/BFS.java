import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class BFS {
	
	private Set<Integer> visitedNodes = new HashSet<Integer>();
	private Graph graph;
	
	public BFS(Graph graph){
		this.graph = graph;
	}
	
	/**
	 * Performe Breath First Search on the subgraph
	 * @param subgraph
	 * @return how many reachable nodes from the first node of the subgraph 
	 */
	public int bfs(HashSet<Integer>subgraph)
	{
		visitedNodes = new HashSet<Integer>();
		
		int node = subgraph.iterator().next();
		Queue<Integer> q= new PriorityQueue<Integer>();//LinkedList<Integer>();
		q.add(node);
		visitedNodes.add(node);
		
		while(!q.isEmpty())
		{
			node = q.remove();
			Integer child = null;
			while((child = getUnvisitedChildNode(node, subgraph)) != null)
			{
				visitedNodes.add(child);
				q.add(child);
			}
		}
		
		return visitedNodes.size();
	}
	
	/**
	 * Get next unvisited child node from the source node
	 * @param n - Source node
	 * @param subgraph
	 * @return next unvisited child node from the source node
	 */
	private Integer getUnvisitedChildNode(int node, HashSet<Integer>subgraph)
	{
		for(int childNode: graph.getAdjacency(node)){
			if(!visitedNodes.contains(childNode) && subgraph.contains(childNode))
				return childNode;
		}
		return null;
	}
}
