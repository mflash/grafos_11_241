from graph import Graph
from queue import Queue

class BreadthFirstSearch:
    def __init__(self, g, s):
        self.s = s
        self.marked = {}
        self.edgeTo = {}
        self.distanceTo = {}
        self.__bfs(g, s)

    def hasPathTo(self, v):
        if v == self.s:
            return False
        return v in self.marked

    def pathTo(self, v):
        path = []
        while v != self.s:
            path.insert(0, v)
            v = self.edgeTo[v]
        path.insert(0, self.s)
        return path

    def distTo(self, v):
        if not self.hasPathTo(v):
            return -1
        return self.distanceTo[v]

    def __bfs(self, g, v):
        fila = Queue()
        fila.put(v)
        self.distanceTo[v] = 0
        self.marked[v] = True
        while not fila.empty():
            v = fila.get()
            dist = self.distanceTo[v]
            for w in g.getAdj(v):
                if w not in self.marked:
                    self.marked[w] = True
                    self.edgeTo[w] = v
                    self.distanceTo[w] = dist + 1
                    fila.put(w)


if __name__ == "__main__":

    g = Graph("tinyG.txt")

    bfs = BreadthFirstSearch(g, "0")

    for v in g.getVerts():
        print(f"{v}: ", end="")
        if bfs.hasPathTo(v):
            for w in bfs.pathTo(v):
                print(f"{w} ", end="")
            print("dist:", bfs.distTo(v))
        else:
            print("Sem caminho para", v)
    print()
