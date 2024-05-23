#include <vector>
#include <map>
#include <set>
#include <string>
#include "graph.h"

#ifndef BFS_H
#define BFS_H

class BreadthFirstSearch
{

public:
	BreadthFirstSearch(Graph &g, std::string s);

	bool hasPathTo(std::string v);
	std::vector<std::string> pathTo(std::string v);
	int distTo(std::string v);

private:
	std::string s;
	std::set<std::string> marked;
	std::map<std::string, std::string> edgeTo;
	std::map<std::string, int> _distTo;
	void bfs(Graph &g, std::string v);
};

#endif
