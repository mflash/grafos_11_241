#include <vector>
#include <map>
#include <string>
#include "digraph.h"

#ifndef DCYCLE_H
#define DCYCLE_H

enum
{
	WHITE,
	GRAY,
	BLACK
};

class DirectedCycle
{

public:
	DirectedCycle(Digraph &g);
	bool containsCycle();

private:
	std::string s;
	std::map<std::string, int> marked;
	bool hasCycle;
	bool containsCycle(Digraph &g, std::string v);
};

#endif
