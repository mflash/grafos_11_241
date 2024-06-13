public class AppKruskal {

    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph("tinyEWG.txt");
        Kruskal k = new Kruskal(g);
        System.out.println("Custo total: " + k.getCustoTotal());
        for (Edge e : k.getArvore()) {
            System.out.println(e);
        }
    }
}
