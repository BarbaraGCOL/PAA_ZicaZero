import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFS {
	
	private Set<Integer> visitados = new HashSet<Integer>();
	private Graph graph;
	
	public BFS(Graph graph){
		this.graph = graph;
	}
	
	public int bfs(List<Integer>subgrafo)
	{
		visitados = new HashSet<Integer>();
		
		int n = subgrafo.get(0);
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(n);
		visitados.add(n);
		
		while(!q.isEmpty())
		{
			n = q.remove();
			Integer child = null;
			while((child=getUnvisitedChildNode(n, subgrafo))!=null)
			{
				visitados.add(child);
				q.add(child);
			}
		}
		
		return visitados.size();
	}
	
	private Integer getUnvisitedChildNode(int n, List<Integer>subgrafo)
	{
		for(int node: graph.getAdjacency(n)){
			if(!visitados.contains(node) && subgrafo.contains(node))
				return node;
		}
		return null;
	}
}
