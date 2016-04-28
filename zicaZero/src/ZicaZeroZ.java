import java.io.IOException;
import java.util.Set;


public class ZicaZeroZ {

	/**
	 * Calculates the smallest number of volunteers so that all 
	 * focus are accessed by, at least, one volunteer and all the 
	 * volunteers have Friendship bonds between each other.	 
	 * @param graph
	 * @return set of volunteers that satisfies the preconditions
	 */
	public static Set<Integer> searchVolunteers(Graph graph){
		Permutation permute = new Permutation();
		Set<Integer> volunteers = permute.combine(graph);
		return volunteers;
	}
	
	public static void main(String[] args) {
		
		Graph graph = new Graph();
				
		if(args.length == 2){
			
			try {
				graph.readGraphIn(args[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Set<Integer> path = searchVolunteers(graph);
			
			try {
				graph.saveOut(args[1], path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Incorrect Parameters!");
		}
	}
}
