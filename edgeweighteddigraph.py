from edgeweightedgraph import EdgeWeightedGraph

class EdgeWeightedDigraph(EdgeWeightedGraph):

    def addEdge(self, v, w, weight):
        e = [v, w, weight]
        super().addToList(v, e)

    def toDot(self):
        NEWLINE = '\n'
        sb = "digraph {" + NEWLINE
        sb += "rankdir = LR;" + NEWLINE
        sb += "node [shape = circle];" + NEWLINE
        for v in sorted(self.getVerts()):
            for e in self.getAdj(v):
                sb += f'{e[0]} -- {e[1]} [label="{e[2]}"]' + NEWLINE
        sb += "}" + NEWLINE
        return sb

if __name__ == "__main__":

    #    g = EdgeWeightedDigraph()

    #    g.addEdge("0", "1")
    #    g.addEdge("0", "2")
    #    g.addEdge("2", "1")

    g = EdgeWeightedDigraph("tinyEWG.txt")

    for v in g.getVerts():
        print(f"{v}: ", end="")
        for w in g.getAdj(v):
            print(f"{w} ", end="")
        print()
    print()
    print(g.toDot())
