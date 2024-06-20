import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* 
 * Implementação do algoritmo de Floyd-Warshall usando matriz de adjacência
 */
public class FloydWarshall {
  private static final String NEWLINE = System.getProperty("line.separator");
  private boolean temCicloNegativo; // tem ciclo negativo?
  private Map<String, Double> dist; // dist[v][w] = distancia do caminho mais curto de v->w
  private Map<String, String> prev; // prev[v][w] = ultima aresta no caminho mais curto de v->w
  private EdgeWeightedDigraph g;

  private long startTime, endTime;

  /**
   * Calcula uma arvore de caminhos mais curtos de todos os vertices para todos os
   * vertices
   * em um digrafo valorado {@code G}. Se houver um ciclo negativo, seta a flag e
   * retorna.
   * 
   * @param G o digrafo valorado
   */
  public FloydWarshall(EdgeWeightedDigraph g) {
    int V = g.getTotalVerts();
    this.g = g;
    // dist = new double[V][V]; // inicialize todos com Double.POSITIVE_INFINITY
    dist = new HashMap<>();
    // prev = new int[V][V]; // inicialize todos com -1
    prev = new HashMap<>();

    Set<String> verts = g.getVerts();

    // Inicialização "matrizes"
    for (String v : verts) {
      for (String w : verts) {
        dist.put(v + "-" + w, Double.POSITIVE_INFINITY);
        prev.put(v + "-" + w, null);
        if (v.equals(w)) {
          dist.put(v + "-" + w, 0.0);
        }
      }
    }

    // Monta "matrizes" iniciais
    for (Edge e : g.getEdges()) {
      String v = e.getV();
      String w = e.getW();
      double weight = e.getWeight();
      dist.put(v + "-" + w, weight);
      prev.put(v + "-" + w, v);
    }

    // Comeco do algoritmo...
    startTime = System.currentTimeMillis();

    // Loop de Floyd-Warshall
    for (String k : verts) {
      for (String i : verts) {
        for (String j : verts) {
          double dist_ikj = dist.get(i + "-" + k) + dist.get(k + "-" + j);
          if (dist.get(i + "-" + j) > dist_ikj) {
            dist.put(i + "-" + j, dist_ikj);
            prev.put(i + "-" + j, prev.get(k + "-" + j));
          }
        }
        if (dist.get(i + "-" + i) < 0) {
          temCicloNegativo = true;
          return;
        }
      }
    }

    // Fim do algoritmo
    endTime = System.currentTimeMillis();
  }

  public long tempoTotal() {
    return endTime - startTime;
  }

  /**
   * Tem ciclo negativo?
   * 
   * @return {@code true} se existe um ciclo negativo, e {@code false} caso não
   *         exista
   */
  public boolean temCicloNegativo() {
    return temCicloNegativo;
  }

  /**
   * Existe caminho entre o vertice {@code s} e o vertice {@code t}?
   * 
   * @param s o vertice de origem
   * @param t o vertice de destino
   * @return {@code true} se existe um caminho
   */
  public boolean temCaminho(String s, String t) {
    return dist.get(s + "-" + t) != Double.POSITIVE_INFINITY;
  }

  /**
   * Retorna o comprimento do caminho mais curto do vertice {@code s} ao vertice
   * {@code t}.
   * 
   * @param s o vertice de origem
   * @param t o vertice de destino
   * @return o comprimento do caminho mais curto de {@code s} a {@code t};
   *         {@code Double.POSITIVE_INFINITY} se não houver caminho
   * @throws UnsupportedOperationException se existir um ciclo negativo
   */
  public double dist(String s, String t) {
    return dist.get(s + "-" + t);
  }

  /**
   * Retorna o caminho mais curto de {@code s} a {@code t}.
   * 
   * @param u o vertice de origem
   * @param v o vertice de destino
   * @return o caminho mais curto de {@code u} a {@code v}
   *         como um ArrayList de String, e {@code null} se não houver caminho
   * @throws UnsupportedOperationException se existir um ciclo negativo
   */
  public List<String> caminho(String u, String v) {
    if (temCicloNegativo())
      throw new UnsupportedOperationException("Existe um ciclo negativo!");
    if (!temCaminho(u, v))
      return null;

    List<String> lista = new ArrayList<>();

    // Monte e retorne o caminho

    lista.add(v);
    while (!u.equals(v)) {
      v = prev.get(u + "-" + v);
      lista.add(0, v);
    }
    return lista;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    List<String> verts = new ArrayList<String>(g.getVerts());
    sb.append("Distâncias:" + NEWLINE);
    sb.append("  ");
    for (String v : verts) {
      sb.append(String.format("%-5s ", v));
    }
    sb.append(NEWLINE);
    for (String v : verts) {
      sb.append(v + " ");
      for (String w : verts) {
        if (prev.get(v + "-" + w) != null)
          sb.append(String.format("%5.2f ", dist.get(v + "-" + w)));
        else
          sb.append("----- ");
      }
      sb.append(NEWLINE);
    }

    sb.append(NEWLINE);
    sb.append("Caminhos:" + NEWLINE);
    sb.append("  ");
    for (String v : verts) {
      sb.append(String.format("%-5s ", v));
    }
    sb.append(NEWLINE);
    for (String v : verts) {
      sb.append(v + " ");
      for (String w : verts) {
        if (prev.get(v + "-" + w) != null)
          sb.append(String.format("%-5s ", prev.get(v + "-" + w)));
        else
          sb.append("----- ");
      }
      sb.append(NEWLINE);
    }
    return sb.toString();
  }

  /**
   * Testa a classe {@code FloydWarshall}
   * 
   */
  public static void main(String[] args) {

    // Carrega um grafo de um arquivo texto
    EdgeWeightedDigraph g = new EdgeWeightedDigraph("tinyEWD.txt");

    // Executa Floyd-Warshall
    FloydWarshall fw = new FloydWarshall(g);

    System.out.println();

    // Mostra todas as distâncias dos caminhos mais curtos
    // ...

    Set<String> verts = g.getVerts();
    // Exibe mensagem se houver ciclo negativo
    if (fw.temCicloNegativo()) {
      System.out.println("Existe um ciclo negativo!");
    } else {
      // Exibe todos os caminhos
      for (String u : verts) {
        for (String v : verts) {
          System.out.print(u + "->" + v + ": ");
          if (u != v && fw.temCaminho(u, v)) {
            for (String x : fw.caminho(u, v)) {
              System.out.print(x + " ");
            }
            System.out.println(" (" + fw.dist(u, v) + ")");
          } else
            System.out.println(" *** Sem caminho");
        }
      }
    }
    System.out.println();
    System.out.println("Tempo de Floyd-Warshall: " + fw.tempoTotal());

    System.out.println();
    System.out.println(fw);

    // System.out.println(g.toDot());
  }

}
