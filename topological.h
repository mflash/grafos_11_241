#include <deque>
#include <set>
#include <string>
#include "graph.h"

#ifndef TOPO_H
#define TOPO_H

class Topological
{

public:
	Topological(Graph &g);

	const std::deque<std::string>& getTopological();

private:
	std::set<std::string> marked;
	std::deque<std::string> topo;
	void dfs(Graph &g, std::string v);
};

#endif
