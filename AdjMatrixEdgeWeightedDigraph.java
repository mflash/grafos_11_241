
/******************************************************************************
 *  Compilation:  javac AdjMatrixEdgeWeightedDigraph.java
 *  Execution:    java AdjMatrixEdgeWeightedDigraph V E
 *  Dependencies: StdOut.java
 *
 *  An edge-weighted digraph, implemented using an adjacency matrix.
 *  Parallel edges are disallowed; self-loops are allowed.
 *  
 ******************************************************************************/

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * The {@code AdjMatrixEdgeWeightedDigraph} class represents a edge-weighted
 * digraph of vertices named 0 through <em>V</em> - 1, where each
 * directed edge is of type {@link DirectedEdge} and has a real-valued weight.
 * It supports the following two primary operations: add a directed edge
 * to the digraph and iterate over all of edges incident from a given vertex.
 * It also provides
 * methods for returning the number of vertices <em>V</em> and the number
 * of edges <em>E</em>. Parallel edges are disallowed; self-loops are permitted.
 * <p>
 * This implementation uses an adjacency-matrix representation.
 * All operations take constant time (in the worst case) except
 * iterating over the edges incident from a given vertex, which takes
 * time proportional to <em>V</em>.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Marcelo Cohen
 */
public class AdjMatrixEdgeWeightedDigraph {
  private static final String NEWLINE = System.getProperty("line.separator");

  private final int totalVertices;
  private int totalEdges;
  private Edge[][] adj;
  private Map<String, Integer> dic;
  private Map<Integer, String> dic2;
  private EdgeWeightedDigraph g;

  /**
   * Initializes an empty edge-weighted digraph with {@code V} vertices and 0
   * edges.
   * 
   * @param V the number of vertices
   * @throws IllegalArgumentException if {@code V < 0}
   */
  public AdjMatrixEdgeWeightedDigraph(EdgeWeightedDigraph g) {
    this.g = g;
    this.totalVertices = g.totalVertices;
    if (totalVertices < 0)
      throw new IllegalArgumentException("number of vertices must be nonnegative");
    this.dic = new HashMap<>();
    this.dic2 = new HashMap<>();
    int cont = 0;
    for (String v : g.getVerts()) {
      dic.put(v, cont);
      dic2.put(cont, v);
      cont++;
    }
    this.totalEdges = 0;
    this.adj = new Edge[totalVertices][totalVertices];
    for (Edge e : g.getEdges())
      addEdge(e);
  }

  public EdgeWeightedDigraph getDigraph() {
    return g;
  }

  public int mapToArray(String v) {
    return dic.get(v);
  }

  public String mapToString(int v) {
    return dic2.get(v);
  }

  /**
   * Returns the number of vertices in the edge-weighted digraph.
   * 
   * @return the number of vertices in the edge-weighted digraph
   */
  public int getTotalVertices() {
    return totalVertices;
  }

  /**
   * Returns the number of edges in the edge-weighted digraph.
   * 
   * @return the number of edges in the edge-weighted digraph
   */
  public int getTotalEdges() {
    return totalEdges;
  }

  /**
   * Adds the directed edge {@code e} to the edge-weighted digraph (if there
   * is not already an edge with the same endpoints).
   * 
   * @param e the edge
   */
  public void addEdge(Edge e) {
    String v = e.getV();
    String w = e.getW();
    int v1 = dic.get(v);
    int w1 = dic.get(w);
    if (adj[v1][w1] == null) {
      totalEdges++;
      adj[v1][w1] = e;
    }
  }

  /**
   * Returns the directed edges incident from vertex {@code v}.
   * 
   * @param v the vertex
   * @return the directed edges incident from vertex {@code v} as an Iterable
   * @throws IllegalArgumentException unless {@code 0 <= v < V}
   */
  public Iterable<Edge> getAdj(String v) {
    return g.getAdj(v);
  }

  public String toDot() {
    return g.toDot();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("  ");
    for (int i = 0; i < adj.length; i++) {
      String v = mapToString(i);
      sb.append(String.format("%-5s ", v));
    }
    sb.append(NEWLINE);
    for (int i = 0; i < adj.length; i++) {
      String v = mapToString(i);
      sb.append(v + " ");
      for (int j = 0; j < adj[i].length; j++) {
        if (adj[i][j] != null)
          sb.append(String.format("%5.2f ", adj[i][j].getWeight()));
        else
          sb.append("----- ");
      }
      sb.append(NEWLINE);
    }
    return sb.toString();
  }

  /**
   * Unit tests the {@code AdjMatrixEdgeWeightedDigraph} data type.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    EdgeWeightedDigraph g = new EdgeWeightedDigraph("tinyEWD.txt");
    AdjMatrixEdgeWeightedDigraph ag = new AdjMatrixEdgeWeightedDigraph(g);
    System.out.println(ag.toDot());
    System.out.println(ag);
  }

}
