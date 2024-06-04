from digraph import Digraph
from enum import Enum


class Mark(Enum):
    WHITE = 0
    GRAY = 1
    BLACK = 2


class DirectedCycle:
    def __init__(self, g):
        self.g = g
        self.marked = {}
        self.edges = set()
        self.hasCycle = False
        for v in g.getVerts():
            self.marked[v] = Mark.WHITE
        for v in g.getVerts():
            if self.marked[v] == Mark.WHITE:
                print("Testando", v)
                self.hasCycle = self.__containsCycle(v)
                if self.hasCycle:
                    break

    def __containsCycle(self, v):
        print("estou em", v)
        self.marked[v] = Mark.GRAY
        for w in self.g.getAdj(v):
            if self.marked[w] == Mark.GRAY:
                return True
            elif self.marked[w] == Mark.WHITE:
                res = self.__containsCycle(w)
                if res:
                    return res
        self.marked[v] = Mark.BLACK
        return False


if __name__ == "__main__":

    g = Digraph("tinyDAG.txt")

    dc = DirectedCycle(g)

    print("Tem ciclo?", dc.hasCycle)
    print()
