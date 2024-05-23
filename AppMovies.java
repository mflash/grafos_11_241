import java.util.Set;

public class AppMovies {
	public static void main(String[] args) {
		Graph g = new Graph();
		In arq = new In("movies.txt");
		int cont = 0;
		while(arq.hasNextLine()) {
			String linha = arq.readLine();
			// System.out.println(linha);
			String dados[] = linha.split("/");
			String nomeFilme = dados[0];
			// System.out.println(nomeFilme);
			for(int i=1; i<dados.length; i++) {
				String nomePessoa = dados[i];
				g.addEdge(nomeFilme, nomePessoa);
			}
			// cont++;
			// if(cont > 5)
				// break;
		}
		System.out.println("Vértices: "+g.totalVertices);
		System.out.println("Arestas: "+g.totalEdges);
		// System.out.println(g.toDot());
		Set<String> nomes = g.getVerts();
		// usar o conj. nomes para verificar se os nomes
		// digitados realmente existem!	
	}
}
