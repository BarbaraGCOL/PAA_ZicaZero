import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class to perform operations on a undirected and connected Graph (from a input file)
 * @author barbara.lopes
 *
 */
public class Graph {

	private int[][] accessMatrix, adjacencyMatrix;
	private int vertexCount, accessCount, edgesCount;

	/**
	 * Constructor
	 */
	public Graph(){

	}

	/**
	 * Add a edge on the AdjacencyMatrix
	 * @param i - vertex 1
	 * @param j - vertex 2
	 */
	public void addEdge(int i, int j) {
		
		if (i > 0 && i <= vertexCount && j > 0 && j <= vertexCount) {
			// Add Adjacency (add edge on the matrix)
			adjacencyMatrix[i-1][j-1] = 1;
			
			//Mirroring
			adjacencyMatrix[j-1][i-1] = 1;
		}
	}

	/**
	 * Add access points of the vertex on the matrix 
	 * @param i - vertex
	 * @param acessPoints - vector of access points
	 */
	public void addAccess(int i, int[] acessPoints) {
		if (i >= 0 && i < vertexCount) {
			// Add all access points of the vertex
			for(int j = 0; j < acessPoints.length; j++){
				accessMatrix[i][acessPoints[j] - 1] = 1;
			}
		}
	}

	/**
	 * Format Adjacency Matrix 
	 * @return matrix of adjacencies on string format
	 */
	public String[] stringAdjMatrix(){
		String[] matrix = new String[vertexCount];
		String linha = "";
		
		for(int i=0; i<vertexCount; i++)
		{
			for(Integer valor: adjacencyMatrix[i]){
				linha += valor+" ";
			}
			matrix[i] = linha;
			linha = "";
		}
		return matrix;
	}

	/**
	 * Format Access Matrix 
	 * @return matrix of access points on string format
	 */
	public String[] stringAccessMatrix(){

		String[] matrix = new String[vertexCount];
		String linha = "";

		for(int i=0; i<vertexCount; i++)
		{
			for(Integer valor: accessMatrix[i]){
				linha += valor+" ";
			}
			matrix[i] = linha;
			linha = "";
		}
		return matrix;
	}

	/**
	 * Read Graph structure of file 
	 * @param path - file path
	 * @return Graph Class instance
	 * @throws IOException
	 */
	public void readGraphIn(String path) throws IOException{

		int indice = -1;
		String[]valores;
		boolean edgesFineshed = false;
		int v1, v2;
		int[]accessPoints = null;

		try { 

			FileReader arq = new FileReader(path); 
			BufferedReader lerArq = new BufferedReader(arq); 
			String linha = lerArq.readLine(); 

			while (linha != null) { 
				valores = linha.split(" ");

				if(indice == -1){
					// the first line of the file - vertices and edges size (m and n values)
					if(!edgesFineshed){
						vertexCount = Integer.parseInt(valores[0]);
						adjacencyMatrix = new int[vertexCount][vertexCount];
						vertexCount = Integer.parseInt(valores[0]);
						edgesCount = Integer.parseInt(valores[1]);
					}
					else{
						// First line of access points -access points size (r value)
						accessCount = Integer.parseInt(valores[0]);
						accessMatrix = new int[vertexCount][accessCount];
					}
				}
				else{
					// If still are edges to read
					if(!edgesFineshed){
						v1 = Integer.parseInt(valores[0]);
						v2 = Integer.parseInt(valores[1]);

						addEdge(v1, v2);
					}
					// If is reading Acess Points
					else{
						accessPoints = new int[valores.length];
						for(int i = 0; i < valores.length; i++){
							accessPoints[i] = Integer.parseInt(valores[i]);
						}
						addAccess(indice, accessPoints);
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
	 * @param graph - Graph Class instance
	 * @throws IOException
	 */
	public void saveGraphOut(String path) throws IOException{

		FileWriter arq = new FileWriter(path); 
		PrintWriter gravarArq = new PrintWriter(arq); 

		String[] stringAdjMatriz = stringAdjMatrix();
		String[] stringAccessMatriz = stringAccessMatrix();

		// Save adjacencyMatrix
		for (int i=0; i<stringAdjMatriz.length; i++) { 
			gravarArq.println(stringAdjMatriz[i]); 
		} 

		// Save accessMatrix
		for (int i=0; i<stringAccessMatriz.length; i++) { 
			gravarArq.println(stringAccessMatriz[i]); 
		}

		arq.close(); 
		System.out.println("Arquivo "+path+" salvo com sucesso!!!");
	}
}