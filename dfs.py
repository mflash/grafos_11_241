from graph import Graph

class DepthFirstSearch:
    def __init__(self, g, s):
        self.s = s
        self.marked = {}
        self.edgeTo = {}
        self.__dfs(g, s)

    def hasPathTo(self, v):
        return v in self.marked

    def pathTo(self, v):
        path = []
        while v != self.s:
            path.insert(0, v)
            v = self.edgeTo[v]
        path.insert(0, self.s)
        return path

    def __dfs(self, g, s):
        self.marked[s] = True
        for w in g.getAdj(s):
            if w not in self.marked:
                self.edgeTo[w] = s
                self.__dfs(g, w)

if __name__ == "__main__":

    g = Graph("tinyG.txt")

    dfs = DepthFirstSearch(g, "0")

    for v in g.getVerts():
        print(f"{v}: ", end="")
        if dfs.hasPathTo(v):
            for w in dfs.pathTo(v):
                print(f"{w} ", end="")
        print()
    print()
