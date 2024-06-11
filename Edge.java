public class Edge implements Comparable<Edge> {
  private String v;
  private String w;
  private double weight;

  public Edge(String v, String w, double weight) {
    this.v = v;
    this.w = w;
    this.weight = weight;
  }

  public String getV() {
    return v;
  }

  public String getW() {
    return w;
  }

  public double getWeight() {
    return weight;
  }

  @Override
  public int compareTo(Edge other) {
    return Double.compare(this.weight, other.weight);
  }

  @Override
  public String toString() {
    return v + "-" + w + " (" + weight + ")";
  }
}
