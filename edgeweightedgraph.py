class EdgeWeightedGraph:
    def __init__(self, *args):
        self.graph = {}
        self.vertices = set()
        if len(args) == 1:
            self.__readFromFile(args[0])

    def addEdge(self, v, w, weight):
        e = [v, w, weight]
        self.addToList(v, e)
        self.addToList(w, e)

    def getAdj(self, v):
        return self.graph[v] if v in self.graph else []

    def getVerts(self):
        return self.vertices

    def toDot(self):
        edges = set()
        NEWLINE = '\n'
        sb = "graph {" + NEWLINE
        sb += "rankdir = LR;" + NEWLINE
        sb += "node [shape = circle];" + NEWLINE
        for v in sorted(self.getVerts()):
            for e in self.getAdj(v):
                edge = e[0] + "-" + e[1]
                if edge not in edges:
                    sb += f'{e[0]} -- {e[1]} [label="{e[2]}"]' + NEWLINE
                    edges.add(edge)
        sb += "}" + NEWLINE
        return sb

    def addToList(self, v, e):
        list = self.graph[v] if v in self.graph else []
        list.append(e)
        self.graph[v] = list
        self.vertices.add(e[0])
        self.vertices.add(e[1])

    def __readFromFile(self, filename):
        with open(filename) as arq:
            for line in arq:
                verts = line[:-1].split()
                self.addEdge(verts[0], verts[1], float(verts[2]))


if __name__ == "__main__":

    #    g = EdgeWeightedGraph()

    #    g.addEdge("0", "1")
    #    g.addEdge("0", "2")
    #    g.addEdge("2", "1")

    g = EdgeWeightedGraph("tinyEWG.txt")

    for v in g.getVerts():
        print(f"{v}: ", end="")
        for w in g.getAdj(v):
            print(f"{w} ", end="")
        print()
    print()
    print(g.toDot())
