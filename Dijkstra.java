import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Dijkstra {
    private EdgeWeightedDigraph g;
    private String start;

    private Map<String, Edge> edgeTo;
    private Map<String, Double> distTo;
    private IndexMinHeap<String, Double> pq;

    public Dijkstra(EdgeWeightedDigraph g, String s) {
        this.g = g;
        this.start = s;

        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        pq = new IndexMinHeap<>();

        for (String v : g.getVerts())
            distTo.put(v, Double.POSITIVE_INFINITY);
        distTo.put(s, 0.0);

        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            String v = pq.delMin();
            for (Edge e : g.getAdj(v)) {
                relax(e);
            }
        }
    }

    public Iterable<String> pathTo(String v) {
        LinkedList<String> lista = new LinkedList<>();
        while (!v.equals(start)) {
            lista.add(0, v);
            // System.out.println("Edgeto: " + v + ": " + edgeTo.get(v));
            Edge e = edgeTo.get(v);
            v = e.getV();
        }
        lista.add(0, v);
        return lista;
    }

    public double distTo(String v) {
        if (!distTo.containsKey(v))
            return -1;
        return distTo.get(v);
    }

    private void relax(Edge e) {
        // System.out.println("Relax: " + e.toString());
        String v = e.getV();
        String w = e.getW();
        if (distTo.get(w) > distTo.get(v) + e.getWeight()) {
            distTo.put(w, distTo.get(v) + e.getWeight());
            edgeTo.put(w, e);
            if (pq.contains(w))
                pq.decreaseValue(w, distTo.get(w));
            else
                pq.insert(w, distTo.get(w));
        }
    }
}