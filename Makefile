CPPFLAGS = -Wall -g -std=c++11   # Opções do compilador: todos warnings e debug info

# classe Graph
appgraph = appgraph
appgraph_src = appgraph.cpp graph.cpp
appgraph_obj = $(appgraph_src:.cpp=.o)

# classe Digraph
appdgraph = appdigraph
appdgraph_src = appdigraph.cpp digraph.cpp graph.cpp
appdgraph_obj = $(appdgraph_src:.cpp=.o)

# DFS
appdfs = appdfs
appdfs_src = appdfs.cpp graph.cpp dfs.cpp
appdfs_obj = $(appdfs_src:.cpp=.o)

# BFS
appbfs = appbfs
appbfs_src = appbfs.cpp graph.cpp bfs.cpp
appbfs_obj = $(appbfs_src:.cpp=.o)

# Ciclos nao dirigidos
appucycle = appucycle
appucycle_src = appundirectedcycle.cpp undirectedcycle.cpp graph.cpp
appucycle_obj = $(appucycle_src:.cpp=.o)

# Ciclos dirigidos
appdcycle = appdcycle
appdcycle_src = appdirectedcycle.cpp directedcycle.cpp digraph.cpp graph.cpp
appdcycle_obj = $(appdcycle_src:.cpp=.o)

# Ord. Topológica
apptopo = apptopo
apptopo_src = apptopo.cpp graph.cpp digraph.cpp topological.cpp
apptopo_obj = $(apptopo_src:.cpp=.o)

# classe UnionFind
appufind = appufind
appufind_src = appunionfind.cpp unionfind.cpp 
appufind_obj = $(appufind_src:.cpp=.o)

# classe EdgeWeightedGraph
appewgraph = appewgraph
appewgraph_src = appewgraph.cpp edgeweightedgraph.cpp
appewgraph_obj = $(appewgraph_src:.cpp=.o)

# classe EdgeWeightedDigraph
appewdigraph = appewdigraph
appewdigraph_src = appewdigraph.cpp edgeweighteddigraph.cpp edgeweightedgraph.cpp
appewdigraph_obj = $(appewdigraph_src:.cpp=.o)

all: appgraph appdigraph appdfs appbfs appucycle appdcycle apptopo appewgraph appufind appewdigraph

$(appgraph): $(appgraph_obj)
	g++ $(CPPFLAGS) $(appgraph_obj) -o $@

$(appdgraph): $(appdgraph_obj)
	g++ $(CPPFLAGS) $(appdgraph_obj) -o $@

$(appdfs): $(appdfs_obj)
	g++ $(CPPFLAGS) $(appdfs_obj) -o $@

$(appbfs): $(appbfs_obj)
	g++ $(CPPFLAGS) $(appbfs_obj) -o $@

$(appucycle): $(appucycle_obj)
	g++ $(CPPFLAGS) $(appucycle_obj) -o $@

$(appdcycle): $(appdcycle_obj)
	g++ $(CPPFLAGS) $(appdcycle_obj) -o $@

$(apptopo): $(apptopo_obj)
	g++ $(CPPFLAGS) $(apptopo_obj) -o $@

$(appewgraph): $(appewgraph_obj)
	g++ $(CPPFLAGS) $(appewgraph_obj) -o $@

$(appufind): $(appufind_obj)
	g++ $(CPPFLAGS) $(appufind_obj) -o $@

$(appewdigraph): $(appewdigraph_obj)
	g++ $(CPPFLAGS) $(appewdigraph_obj) -o $@

clean:
	-@ rm -f $(appgraph_obj) $(appgraph) $(appdgraph_obj) $(appdgraph) $(appdfs_obj) $(appbfs_obj) $(appdfs) $(appbfs) \
	   	$(appucycle) $(appucycle_obj) $(appdcycle) $(appdcycle_obj) $(apptopo) $(apptopo_obj) $(appewgraph_obj) $(appewgraph) \
		$(appufind) $(appufind_obj) $(appewdigraph) $(appewdigraph_obj)
