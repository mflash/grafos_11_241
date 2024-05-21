import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFS {

	private Set<String> marked;
	private Map<String, String> edgeTo;
	private String start;
	
	public DFS(Graph g, String start) {
		this.start = start;
		marked = new HashSet<>();
		edgeTo = new HashMap<>();
		dfs(g, start);
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

	public void dfs(Graph g, String s)
	{
		// System.out.println("Visitando: "+s);
		marked.add(s); // marca como visitado
		for(String w: g.getAdj(s)) {
			// Se não foi visitado ainda...
			if(!marked.contains(w)) {
				edgeTo.put(w, s);
				dfs(g, w);
			}
		}
	}

}
