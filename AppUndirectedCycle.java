public class AppUndirectedCycle {

    public static void main(String[] args) {
        Graph g = new Graph("tinyG.txt");

        UndirectedCycle uc = new UndirectedCycle(g);
        System.out.println(uc.containsCycle());
    }
}
