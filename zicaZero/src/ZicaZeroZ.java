import java.io.IOException;
import java.util.Set;


public class ZicaZeroZ {

	public static Set<Integer> searchVoluntiers(Graph graph){
		Permute permute = new Permute();
		Set<Integer> solution = permute.combine(graph);
		return solution;
	}
	
	public static void main(String[] args) {
		
		Graph graph = new Graph();
		
		args = new String[2];
		args[0] = "in0";
		args[1] = "out0";
		
		if(args.length == 2){
			
			try {
				graph.readGraphIn(args[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Set<Integer> path = searchVoluntiers(graph);
			
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
