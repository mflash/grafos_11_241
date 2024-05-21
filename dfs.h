#include <vector>
#include <map>
#include <set>
#include <string>
#include "graph.h"

#ifndef DFS_H
#define DFS_H

class DepthFirstSearch
{

public:
	DepthFirstSearch(Graph &g, std::string s);

	bool hasPathTo(std::string v);
	std::vector<std::string> pathTo(std::string v);

private:
	std::string s;
	std::set<std::string> marked;
	std::map<std::string, std::string> edgeTo;
	void dfs(Graph &g, std::string v);
};

#endif
