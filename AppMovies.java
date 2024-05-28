import java.util.Scanner;
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
		Scanner sc = new Scanner(System.in);
		String nome1, nome2;
		do {
			System.out.print("Inicial: ");
			nome1 = sc.nextLine();
			if(!nomes.contains(nome1)) {
				System.out.println("Nome não encontrado!");
			}
		} while(!nomes.contains(nome1));
		do {
			System.out.print("Final: ");
			nome2 = sc.nextLine();
			if(!nomes.contains(nome2)) {
				System.out.println("Nome não encontrado!");
			}
		} while(!nomes.contains(nome2));
		// Agora basta fazer um caminhamento por largura a partir do nome1!
		System.out.println();
		BFS bfs = new BFS(g, nome1);
		if(bfs.hasPathTo(nome2)) {
			for(String nome: bfs.pathTo(nome2)) {
				System.out.println(nome);
			}
		}
		System.out.println("O grafo é fortemente conexo? "+bfs.isStronglyConnected());
	}
}
