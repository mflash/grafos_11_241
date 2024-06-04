from graph import Graph

class UndirectedCycle:
    def __init__(self, g):
        self.g = g
        self.marked = {}
        self.edges = set()
        self.hasCycle = False
        for v in g.getVerts():
            if v not in self.marked:
                print("Testando",v)
                self.hasCycle = self.__containsCycle(v)
                if self.hasCycle:
                    break

    def __containsCycle(self, v):
        print("estou em", v)
        self.marked[v] = True
        for w in self.g.getAdj(v):
            e = v + "-" + w
            print("e:",e)
            if w not in self.marked:
                self.edges.add(e)
                res = self.__containsCycle(w)
                if res:
                    return res
            elif w + "-" + v not in self.edges:
                return True
        return False

if __name__ == "__main__":

    g = Graph("tinyG.txt")

    uc = UndirectedCycle(g)

    print("Tem ciclo?", uc.hasCycle)
    print()
