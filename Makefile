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

all: appgraph appdigraph appdfs appbfs

$(appgraph): $(appgraph_obj)
	g++ $(CPPFLAGS) $(appgraph_obj) -o $@

$(appdgraph): $(appdgraph_obj)
	g++ $(CPPFLAGS) $(appdgraph_obj) -o $@

$(appdfs): $(appdfs_obj)
	g++ $(CPPFLAGS) $(appdfs_obj) -o $@

$(appbfs): $(appbfs_obj)
	g++ $(CPPFLAGS) $(appbfs_obj) -o $@

clean:
	-@ rm -f $(appgraph_obj) $(appgraph) $(appdgraph_obj) $(appdgraph) ${appdfs_obj} ${appdfs} ${appbfs_obj} ${appbfs}

