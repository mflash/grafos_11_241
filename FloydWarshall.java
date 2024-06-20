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
  private double[][] dist; // dist[v][w] = distancia do caminho mais curto de v->w
  private int[][] prev; // prev[v][w] = ultima aresta no caminho mais curto de v->w
  private Map<String, Integer> dicNomeIndice;
  private Map<Integer, String> dicIndiceNome;

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

    // dicionários para mapear nomes<->indices
    this.dicIndiceNome = new HashMap<>();
    this.dicNomeIndice = new HashMap<>();

    // Inicializa os dicionários de mapeamento
    int cont = 0;
    for (String v : g.getVerts()) {
      dicNomeIndice.put(v, cont);
      dicIndiceNome.put(cont, v);
      cont++;
    }

    dist = new double[V][V]; // inicialize todos com Double.POSITIVE_INFINITY
    prev = new int[V][V]; // inicialize todos com -1

    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        dist[i][j] = Double.POSITIVE_INFINITY;
        if (i == j) {
          dist[i][i] = 0;
          prev[i][i] = i;
        } else {
          prev[i][j] = -1;
        }
      }
    }

    for (Edge e : g.getEdges()) {
      int u = nomeParaIndice(e.getV());
      int v = nomeParaIndice(e.getW());
      double weight = e.getWeight();
      dist[u][v] = weight;
      prev[u][v] = u;
    }

    // Comeco do algoritmo...
    startTime = System.currentTimeMillis();

    // Loop de Floyd-Warshall
    for (int k = 0; k < V; k++) {
      for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++) {
          if (dist[i][j] > dist[i][k] + dist[k][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
            prev[i][j] = prev[k][j];
          }
        }
        if (dist[i][i] < 0) {
          temCicloNegativo = true;
          return;
        }
      }
    }

    // Fim do algoritmo
    endTime = System.currentTimeMillis();
  }

  public int nomeParaIndice(String nome) {
    return dicNomeIndice.get(nome);
  }

  public String indiceParaNome(int indice) {
    return dicIndiceNome.get(indice);
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
    return dist(s, t) != Double.POSITIVE_INFINITY;
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
    int u = nomeParaIndice(s);
    int v = nomeParaIndice(t);
    return dist[u][v];
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
    int u1 = nomeParaIndice(u);
    int v1 = nomeParaIndice(v);

    lista.add(v);
    while (u1 != v1) {
      // System.out.println("Em " + v);
      v1 = prev[u1][v1];
      v = indiceParaNome(v1);
      lista.add(0, v);
    }
    return lista;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Distâncias:" + NEWLINE);
    sb.append("  ");
    for (int i = 0; i < dist.length; i++) {
      String v = indiceParaNome(i);
      sb.append(String.format("%-5s ", v));
    }
    sb.append(NEWLINE);
    for (int i = 0; i < dist.length; i++) {
      String v = indiceParaNome(i);
      sb.append(v + " ");
      for (int j = 0; j < dist[i].length; j++) {
        if (prev[i][j] != -1)
          sb.append(String.format("%5.2f ", dist[i][j]));
        else
          sb.append("----- ");
      }
      sb.append(NEWLINE);
    }
    // Ligações
    sb.append(NEWLINE + "Ligações:" + NEWLINE);
    sb.append("  ");
    for (int i = 0; i < prev.length; i++) {
      String v = indiceParaNome(i);
      sb.append(String.format("%-5s ", v));
    }
    sb.append(NEWLINE);
    for (int i = 0; i < prev.length; i++) {
      String v = indiceParaNome(i);
      sb.append(v + " ");
      for (int j = 0; j < prev[i].length; j++) {
        if (prev[i][j] != -1) {
          String w = indiceParaNome(prev[i][j]);
          sb.append(String.format("%-5s ", w));
        } else
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
    System.out.println(fw);

    // Mostra todas as distâncias dos caminhos mais curtos
    // ...

    Set<String> verts = g.getVerts();
    // Exibe mensagem se houver ciclo negativo
    if (true) {
      if (fw.temCicloNegativo()) {
        System.out.println("Existe um ciclo negativo!");
      } else {
        // Exibe todos os caminhos
        for (String u : verts) {
          for (String v : verts) {
            if (u != v && fw.temCaminho(u, v)) {
              System.out.print(u + "->" + v + ": ");
              for (String x : fw.caminho(u, v)) {
                System.out.print(x + " ");
              }
              System.out.println();
            }
          }
        }
      }
    }
    System.out.println();
    System.out.println("Tempo de Floyd-Warshall: " + fw.tempoTotal());
  }

}
