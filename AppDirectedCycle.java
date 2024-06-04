public class AppDirectedCycle {

    public static void main(String[] args) {
        Digraph g = new Digraph("tinyG.txt");

        DirectedCycle dc = new DirectedCycle(g);
        System.out.println(dc.containsCycle());
    }
}
