public class AppDigraph
{
  public static void main(String[] args) {
    /* 
    Graph g = new Graph();
    g.addEdge("0", "1");
    g.addEdge("0", "2");
    g.addEdge("2", "1");
    */
    Digraph g = new Digraph("tinyG.txt");

    for (String v : g.getVerts()) {
      System.out.print(v + ": ");
      for (String w : g.getAdj(v))
        System.out.print(w + " ");
      System.out.println();
    }

    System.out.println();
    System.out.println(g.toDot());
  }
}
