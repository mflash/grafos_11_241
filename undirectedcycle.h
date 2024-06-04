#include <vector>
#include <map>
#include <set>
#include <string>
#include "graph.h"

#ifndef UCYCLE_H
#define UCYCLE_H

class UndirectedCycle
{

public:
	UndirectedCycle(Graph &g);
	bool containsCycle();

private:
	std::string s;
	std::set<std::string> marked;
	std::set<std::string> edges;
	bool hasCycle;
	bool containsCycle(Graph &g, std::string v);
};

#endif
