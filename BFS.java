import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BFS {

	private Graph g;
	private Set<String> marked;
	private Map<String, String> edgeTo;
	private Map<String, Integer> distTo;
	private String start;
	private int totalVisitados;
	
	public BFS(Graph g, String start) {
		this.start = start;
		this.g = g;
		marked = new HashSet<>();
		edgeTo = new HashMap<>();
		distTo = new HashMap<>();
		totalVisitados = 0;
		bfs(g, start);
	}

	boolean hasPathTo(String w) {
		// Se o conj. contém w, é porque eu já passei por ele!
		return marked.contains(w);
	}

	Iterable<String> pathTo(String w) {
		List<String> path = new LinkedList<>();
		while(!w.equals(start)) {
			// Insere no INÍCIO da lista
			path.add(0, w);
			w = edgeTo.get(w);
		}
		path.add(0, start);
		return path;
	}

	public int distTo(String v) {
		if(!hasPathTo(v))
			return -1; // representa vértice não visitado
		return distTo.get(v);
	}

	// Verifica se o grafo é fortemente conexo, ou seja,
	// se é possível chegar a todos os vértices a partir de um qualquer
	public boolean isStronglyConnected() {
		System.out.println("Total visitados: "+totalVisitados);
		System.out.println("Total vértices: "+g.totalVertices);
		return totalVisitados == g.totalVertices;
	}

	public void bfs(Graph g, String s)
	{
		List<String> fila = new LinkedList<>();
		marked.add(s); // marca como visitado
		fila.add(s); // adiciona o inicial à fila
		distTo.put(s, 0); // inicial está em distância 0
		while(!fila.isEmpty()) {
			s = fila.removeFirst();
			totalVisitados++;
			// System.out.println("Visitando: "+s);
			for(String w: g.getAdj(s)) {
				// Se não foi visitado ainda...
				if(!marked.contains(w)) {
					fila.add(w);
					marked.add(w);
					edgeTo.put(w, s);
					int dist = distTo.get(s);
					distTo.put(w, dist+1);
				}
			}
		}
	}

}
