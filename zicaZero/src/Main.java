import java.io.IOException;
import java.util.Set;


public class Main {

	public static Set<Integer> buscaVoluntarios(Graph graph){
		Permute permute = new Permute();
		Set<Integer> solution = permute.combine(graph);
		return solution;
	}
	
	public static void main(String[] args) {
		
		Graph graph = new Graph();
				
		if(args.length == 2){
			
			try {
				graph.readGraphIn(args[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Set<Integer> caminho = buscaVoluntarios(graph);
			
			try {
				graph.saveOut(args[1], caminho);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Parâmetros Incorretos!");
		}
	}
}
