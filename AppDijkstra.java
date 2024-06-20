public class AppDijkstra {
    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph("tinyEWD.txt");
        Dijkstra d = new Dijkstra(g, "0");
        System.out.println(g.toDot());

        for (String v : g.getVerts()) {
            System.out.print(v + " : ");
            System.out.print(d.distTo(v) + " -> ");
            for (String w : d.pathTo(v))
                System.out.print(w + " ");
            System.out.println();
        }
    }
}