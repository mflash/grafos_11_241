# Original: https://github.com/msambol/dsa/tree/master/maximum_flow
# Por Takashi Idobe

from collections import deque

infinity = float("inf")

def make_graph():
    # Grafo idêntico ao vídeo no YouTube: https://youtu.be/Tl90tNtKvxs
    # (matriz de adjacência)
    return [
            [0, 10, 0, 10, 0, 0],
            [0, 0, 4, 2, 8, 0],
            [0, 0, 0, 0, 0, 10],
            [0, 0, 0, 0, 9, 0],
            [0, 0, 6, 0, 0, 10],
            [0, 0, 0, 0, 0, 0],
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

# Encontra caminhos de source a sink usando BFS (forma mais simples de implementar)
def bfs(G, source, sink, parent):
    visited = set()

    queue = deque() # double-ended queue (fila de duas pontas)
    queue.append(source)

    visited.add(source)
 
    while queue:
        node = queue.popleft() # remove first

        for i in range(len(G[node])):
            # se vértice não foi visitado e ainda há caminho para i (peso > 0)
            if i not in visited and G[node][i] > 0:
                # inclui na fila a visitar
                queue.append(i)
                # marca como visitado (para não incluir duas vezes)
                visited.add(i)
                # parent => edgeTo (de onde venho para chegar em i)
                parent[i] = node

    # retorna True se chegou em sink
    return sink in visited


def ford_fulkerson(G, source, sink):
    # Array preenchido pelo BFS (armazena o caminho percorrido)
    parent = [-1] * (len(G)) # [ -1, -1, -1, ... ]
    max_flow = 0

    # Enquanto ainda há caminho de source -> sink...
    while bfs(G, source, sink, parent):

        path_flow = infinity
 
        # Percorre o caminho obtido pelo BFS
        s = sink
        while s != source:
            # Encontra o MENOR valor no caminho percorrido
            path_flow = min(path_flow, G[parent[s]][s])
            s = parent[s]

        # Adiciona ao fluxo máximo do grafo
        max_flow += path_flow

        v = sink
        # Adiciona ou subtrai fluxo baseado no caminho
        while v != source:
            u = parent[v]
            G[u][v] -= path_flow # aresta normal
            G[v][u] += path_flow # "aresta residual"
            v = parent[v]

    return max_flow


def main():
    G = make_graph()
    print(toDot(G))
    source = 0
    sink = 5
    max_flow = ford_fulkerson(G, source, sink)
    print(f'Fluxo máximo: {max_flow}')

main()