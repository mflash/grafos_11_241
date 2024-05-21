public class AppDFS {
	
	public static void main(String[] args) {
		Graph g = new Graph("tinyG.txt");
		// g.addEdge("6", "9");
		DFS dfs = new DFS(g, "0");

		for(String v: g.getVerts())
		{
			System.out.print(v+": ");
			if(dfs.hasPathTo(v))
			{
				// Exibe o caminho
				for(String w: dfs.pathTo(v)) {
					System.out.print(w+ " ");
				}
				System.out.println();
			}
			else {
				System.out.println(" Sem caminho");
			}

		}
	}
}
