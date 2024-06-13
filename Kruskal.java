import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Kruskal {
    List<Edge> arvore;
    EdgeWeightedGraph g;
    double custoTotal;

    public Kruskal(EdgeWeightedGraph g) {
        this.g = g;
        arvore = new LinkedList<>();
        UnionFind uf = new UnionFind(g);

        List<Edge> fila = new LinkedList<>();
        for (Edge e : g.getEdges())
            fila.add(e);
        Collections.sort(fila);

        custoTotal = 0;

        // Executa Kruskal
        while (!fila.isEmpty()) {
            Edge e = fila.removeFirst();
            String v = e.getV();
            String w = e.getW();
            String r1 = uf.find(v);
            String r2 = uf.find(w);
            // Há ciclo se r1 == r2
            if (!r1.equals(r2)) {
                // Não forma ciclo
                uf.union(v, w);
                arvore.add(e);
                custoTotal += e.getWeight();
            }
        }

    }

    Iterable<Edge> getArvore() {
        return arvore;
    }

    double getCustoTotal() {
        return custoTotal;
    }

}