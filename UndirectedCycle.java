import java.util.HashSet;
import java.util.Set;

public class UndirectedCycle {
    private Graph g;
    private Set<String> marked;
    private Set<String> edges;
    private boolean hasCycle;

    public UndirectedCycle(Graph g) {
        this.g = g;
        marked = new HashSet<>();
        edges = new HashSet<>();
        hasCycle = false;
        for (String v : g.getVerts()) {
            if (!marked.contains(v)) {
                System.out.println("Testando " + v);
                hasCycle = containsCycle(v);
                if (hasCycle)
                    break;
            }
        }
    }

    public boolean containsCycle() {
        return hasCycle;
    }

    private boolean containsCycle(String v) {
        System.out.println("estou em " + v);
        marked.add(v);
        for (String w : g.getAdj(v)) {
            String e = v + "-" + w;
            System.out.println("e: " + e);
            if (!marked.contains(w)) {
                edges.add(e);
                boolean res = containsCycle(w);
                if (res)
                    return res;
            } else if (!edges.contains(w + "-" + v))
                return true;
        }
        return false;
    }
}