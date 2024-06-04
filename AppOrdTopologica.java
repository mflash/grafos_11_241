public class AppOrdTopologica {

	public static void main(String[] args) {
		Digraph g = new Digraph("tinyDAG.txt");

		OrdTopologica ot = new OrdTopologica(g);
		System.out.println("Ordem topológica:");
		for (String v : ot.getOrdemTopo())
			System.out.println("-> "+v);
	}
}
