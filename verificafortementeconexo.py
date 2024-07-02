def make_graph():
    # matriz de adjacência
    return [
            [0, 10, 0, 10, 0, 0],
            [0, 0, 4, 2, 8, 0],
            [0, 0, 0, 0, 0, 10],
            [0, 0, 0, 0, 9, 0],
            [0, 0, 6, 0, 0, 10],
            [1, 0, 0, 0, 0, 0],
        ]

def toDot(G):
    NEWLINE = '\n'
    sb = "digraph {" + NEWLINE
    sb += "rankdir = LR;" + NEWLINE
    sb += "node [shape = circle];" + NEWLINE
    tot = len(G)
    for v in range(tot):
        for w in range(tot):
            if G[v][w] > 0:
                sb += f'{v} -> {w} [label="{G[v][w]}"]' + NEWLINE
    sb += "}" + NEWLINE
    return sb

def dfsRecursivo(G, source):
    global visited

    visited.add(source)
 
    for i in range(len(G[source])):
        # se vértice não foi visitado e ainda há caminho para i (peso > 0)
        if i not in visited and G[source][i] > 0:
            # marca como visitado (para não incluir duas vezes)
            visited.add(i)
            dfsRecursivo(G, i)

def dfs(G, source):
    dfsRecursivo(G, source)

def fortementeConexo(G):
    global visited
    for v in range(len(G)):
        visited = set()
        print("DFS: ", v)
        dfs(G, v)
        # Se não chegar em TODOS os outros vértices menos o inicial,
        # não é fortemente conexo
        if len(visited) != len(G):
            return False
    return True

if __name__ == "__main__":
    visited = set()
    G = make_graph()
    print(toDot(G))
    source = 0
    if fortementeConexo(G):
        print("Grafo é fortemente conexo!")
    else:
        print("Grafo não é fortemente conexo...")
