import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class to perform operations on a undirected and connected Graph (from a input file)
 * @author barbara.lopes
 *
 */
public class Graph {

	private HashMap<Integer, List<Integer>> adjacencyList;
	private Set<Integer>[] focusList;
	private int vertexCount, focusCount, edgesCount;

	/**
	 * Constructor
	 */
	public Graph(){
		adjacencyList = new HashMap<Integer, List<Integer>>();
	}

	/**
	 * Add a edge on the AdjacencyList
	 * @param i - vertex 1
	 * @param j - vertex 2
	 */
	public void addEdge(int i, int j) {
		
		if (i > 0 && i <= vertexCount && j > 0 && j <= vertexCount) {
			
			List<Integer> set;
			
			if(adjacencyList.get(i) == null){
				set = new ArrayList<Integer>();
				adjacencyList.put(i, set);
			}
			
			if(adjacencyList.get(j) == null){
				set = new ArrayList<Integer>();
				adjacencyList.put(j, set);
			}
			
			// Add Adjacency (add edge on the matrix)
			adjacencyList.get(i).add(j);
			
			//Mirroring
			adjacencyList.get(j).add(i);
		}
	}

	/**
	 * Add focus of the vertex on the list 
	 * @param i - vertex
	 * @param focus - vector of focus
	 */
	public void addFocus(int i, Set<Integer> focus) {
		if (i >= 0 && i < vertexCount) {
			// Add all focus of the vertex
			focusList[i] = focus;
		}
	}

	/**
	 * Read Graph structure of file 
	 * @param path - file path
	 * @return Graph Class instance
	 * @throws IOException
	 */
	public void readGraphIn(String nomArq) throws IOException{

		int indice = -1;
		String[]valores;
		boolean edgesFineshed = false;
		int v1, v2;
		Set<Integer> focus = null;

		String dir = System.getProperty("user.dir");
		
		String pathIn = dir+"\\"+nomArq;

		try { 

			FileReader arq = new FileReader(pathIn); 
			BufferedReader lerArq = new BufferedReader(arq); 
			String linha = lerArq.readLine(); 

			while (linha != null) { 
				valores = linha.split(" ");

				if(indice == -1){
					// the first line of the file - vertices and edges size (m and n values)
					if(!edgesFineshed){
						vertexCount = Integer.parseInt(valores[0]);
						edgesCount = Integer.parseInt(valores[1]);
					}
					else{
						// First line of access points -access points size (r value)
						focusCount = Integer.parseInt(valores[0]);
						focusList = new TreeSet[vertexCount];
					}
				}
				else{
					// If still are edges to read
					if(!edgesFineshed){
						v1 = Integer.parseInt(valores[0]);
						v2 = Integer.parseInt(valores[1]);

						addEdge(v1, v2);
					}
					// If is reading Access Points
					else{
						focus = new TreeSet<Integer>();
						for(int i = 0; i < valores.length; i++){
							focus.add(Integer.parseInt(valores[i]));
						}
						addFocus(indice, focus);
					}
				}

				linha = lerArq.readLine(); 
				indice ++;

				// If all the edges were read (it means that will start reading the access points)
				if(!edgesFineshed && indice > edgesCount - 1){
					indice = -1;
					edgesFineshed = true;
				}
			} 
			arq.close(); 
		} catch (IOException e) { 
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); 
		} 
	}

	/**
	 * Save Graph output on file
	 * @param path - output file path
	 * @param out - output to be save
	 * @throws IOException
	 */
	public void saveOut(String path, Set<Integer> out) throws IOException{

		FileWriter arq = new FileWriter(path); 
		PrintWriter gravarArq = new PrintWriter(arq); 

		// Save out
		for(int value: out){
			gravarArq.print(value+" "); 
		}

		arq.close(); 
		System.out.println("Arquivo "+path+" salvo com sucesso!!!");
	}

	
	public int getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

	public int getFocusCount() {
		return focusCount;
	}

	public void setAccessCount(int accessCount) {
		this.focusCount = accessCount;
	}
	
	public List<Integer> getVertices(){
		return new ArrayList<Integer>(adjacencyList.keySet());
	}
	
	public Set<Integer> getVerticesSet(){
		return adjacencyList.keySet();
	}
	
	public List<Integer> getAdjacency(int vertex){
		return adjacencyList.get(vertex);
	}
	
	public Set<Integer> getFocus(int vertex){
		return focusList[vertex - 1];
	}
}