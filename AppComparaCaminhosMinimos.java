import java.util.Random;

public class AppComparaCaminhosMinimos {
    public static void main(String[] args) {

        Random r = new Random();
        // EdgeWeightedDigraph g = new EdgeWeightedDigraph("tinyEWD.txt");
        // Digraph g = DigraphGenerator.dag(200, 300);
        Digraph g = DigraphGenerator.complete(100);
        EdgeWeightedDigraph dg = new EdgeWeightedDigraph();
        for (String v : g.getVerts()) {
            for (String w : g.getAdj(v)) {
                double weight = r.nextInt(5, 30);
                Edge e = new Edge(v, w, weight);
                dg.addEdge(v, w, weight);
            }
        }

        FloydWarshall fw = new FloydWarshall(dg);
        long tempoFW = fw.tempoTotal();

        long tempoD = 0;
        for (String s : g.getVerts()) {
            long ti = System.currentTimeMillis();
            Dijkstra d = new Dijkstra(dg, s);
            long tf = System.currentTimeMillis();
            tempoD += (tf - ti);
        }

        System.out.println("Tempo Dijkstra      : " + tempoD);
        System.out.println("Tempo Floyd-Warshall: " + tempoFW);

    }
}