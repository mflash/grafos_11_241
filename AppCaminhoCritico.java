public class AppCaminhoCritico {

    public static void main(String[] args) {
        In arq = new In("jobs.txt");

        EdgeWeightedDigraph dg = new EdgeWeightedDigraph();

        while (arq.hasNextLine()) {
            String line = arq.readLine();
            String[] dados = line.split(" ");
            String jobNum = dados[0];
            int dur = Integer.parseInt(dados[1]);
            String jobFim = jobNum + "f";
            dg.addEdge(jobNum, jobFim, dur);
            for (int i = 2; i < dados.length; i++) {
                String dep = dados[i];
                dg.addEdge(jobFim, dep, 0);
            }
            dg.addEdge("START", jobNum, 0);
            dg.addEdge(jobFim, "END", 0);
        }
        System.out.println(dg.toDot());
    }
}
