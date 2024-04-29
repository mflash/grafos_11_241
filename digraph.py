from graph import Graph


class Digraph(Graph):

    def addEdge(self, v, w):
        super().addToList(v, w)

    def toDot(self):
        NEWLINE = '\n'
        sb = "digraph {" + NEWLINE
        sb += "rankdir = LR;" + NEWLINE
        sb += "node [shape = circle];" + NEWLINE
        for v in sorted(self.getVerts()):
            for w in self.getAdj(v):
                sb += v + " -> " + w + NEWLINE
        sb += "}" + NEWLINE
        return sb


if __name__ == "__main__":

    g = Digraph()

    g.addEdge("0", "1")
    g.addEdge("0", "2")
    g.addEdge("2", "1")

    g = Digraph("tinyG.txt")

    for v in g.getVerts():
        print(f"{v}: ", end="")
        for w in g.getAdj(v):
            print(f"{w} ", end="")
        print()
    print()
    print(g.toDot())
