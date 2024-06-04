from digraph import Digraph

class OrdTopologica:
    def __init__(self, g):
        self.marked = {}
        self.ordemTopo = []
        for v in g.getVerts():
            if v not in self.marked:
                self.__dfs(g, v)

    def ordem(self):
        return self.ordemTopo

    def __dfs(self, g, s):
        self.marked[s] = True
        for w in g.getAdj(s):
            if w not in self.marked:
                self.__dfs(g, w)
        self.ordemTopo.insert(0, s)

if __name__ == "__main__":

    g = Digraph("tinyDAG.txt")

    ot = OrdTopologica(g)

    print("Ordem topológica:")
    for v in ot.ordem():
        print("->", v)
