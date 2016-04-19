import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		args = new String[2];
		args[0]="in0";
		args[1]="out0";
		
		if(args.length == 2){
			
			String dir = System.getProperty("user.dir");
	
			Scanner s = new Scanner(System.in);
	
			String pathIn = dir+"\\"+args[0];
	
			Graph graph = new Graph(); 
			
			try {
				graph.readGraphIn(pathIn);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
//			String pathOut = dir+"\\"+args[1];
//			
//			try {
//				graph.saveGraphOut(pathOut);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
	
			s.close();
			
			
		}
		else{
			System.out.println("Parâmetros Incorretos!");
		}
	}

}
