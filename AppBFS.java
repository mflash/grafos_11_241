public class AppBFS {
	
	public static void main(String[] args) {
		Graph g = new Graph("tinyG.txt");
		// g.addEdge("6", "9");
		BFS bfs = new BFS(g, "0");

		for(String v: g.getVerts())
		{
			System.out.print(v+": ");
			if(bfs.hasPathTo(v))
			{
				System.out.print("dist: " + bfs.distTo(v)+" - ");
				// Exibe o caminho
				for(String w: bfs.pathTo(v)) {
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
