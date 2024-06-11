class UnionFind():

    def __init__(self):
        self.uf = {}

    def addSets(self, sets):
        self.uf = {key: None for key in sets}

    def find(self, s):
        x = self.uf[s]
        while x is not None:
            s = x
            x = self.uf[x]
        return s

    def union(self, s, t):
        s = self.find(s)
        t = self.find(t)
        self.uf[t] = s


if __name__ == "__main__":

    uf = UnionFind()
    uf.addSets([1, 2, 3, 4, 5, 6])

    print(uf.uf)
    print("conj. 5:", uf.find(5))

    print()
    print("Unindo 1-3, 1-5, e 5-6")
    uf.union(1, 3)
    uf.union(1, 5)
    uf.union(5, 6)
    print(uf.uf)
    print("conj. 3:", uf.find(3))
    print("conj. 5:", uf.find(5))
    print("conj. 6:", uf.find(6))
