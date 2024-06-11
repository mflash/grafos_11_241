import java.util.HashMap;
import java.util.Map;

public class UnionFind {

	private Map<String, String> parent;
	private Map<String, Integer> size;
	private int totalSets;

	public UnionFind(EdgeWeightedGraph g) {
		parent = new HashMap<>();
		size = new HashMap<>();
		totalSets = g.totalVertices;
		for(String v: g.vertices) {
			parent.put(v, v);
			size.put(v, 1);
		}
	}

	public String find(String p) {
		while (!p.equals(parent.get(p))) {
			parent.put(p, parent.get(parent.get(p))); // path compression
			p = parent.get(p);
		}
		return p;
	}

	public void union(String p, String q) {
		String rootP = find(p);
		String rootQ = find(q);
		if (size.get(rootP) < size.get(rootQ)) {
			parent.put(rootP, rootQ);
			size.put(rootQ, size.get(rootQ) + size.get(rootP));
		} else {
			parent.put(rootQ, rootP);
			size.put(rootP, size.get(rootP) + size.get(rootQ));
		}
		totalSets--;
	}

	public int getTotalSets() {
		return totalSets;
	}

	public boolean connected(String p, String q) {
		return find(p).equals(find(q));
	}

	@Override
	public String toString() {
		StringBuilder aux = new StringBuilder();
		for(String v: parent.keySet()) {
			aux.append(v+": ");
			while(!v.equals(parent.get(v))) {
				v = parent.get(v);
				aux.append(" -> " + v);
			}
			aux.append("\n");
		}	
		return aux.toString();
	}
}
