import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class OrdTopologica {

	private Set<String> marked;
	private List<String> ordemTopo;
	
	public OrdTopologica(Graph g) {
		marked = new HashSet<>();
		ordemTopo = new LinkedList<>();
		for(String v: g.getVerts()) {
			if(!marked.contains(v))
				dfs(g, v);
		}
	}

	public Iterable<String> getOrdemTopo() {
		return ordemTopo;
	}

	public void dfs(Graph g, String s)
	{
		// System.out.println("Visitando: "+s);
		marked.add(s); // marca como visitado
		for(String w: g.getAdj(s)) {
			// Se não foi visitado ainda...
			if(!marked.contains(w)) {
				dfs(g, w);
			}
		}
		// Adiciona no início da lista
		ordemTopo.add(0, s);
	}

}
