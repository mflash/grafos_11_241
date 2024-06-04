#include "directedcycle.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <set>

using namespace std;

DirectedCycle::DirectedCycle(Digraph &g)
{
	hasCycle = false;
	for (auto const &w : g.getVerts())
		marked[w] = WHITE;

	for (auto const &w : g.getVerts())
	{
		if (marked[w] == WHITE)
		{
			hasCycle = containsCycle(g, w);
			if (hasCycle)
				break;
		}
	}
}

bool DirectedCycle::containsCycle()
{
	return hasCycle;
}

bool DirectedCycle::containsCycle(Digraph &g, string v)
{
	marked[v] = GRAY;
	for (auto const &w : g.getAdj(v))
	{
		string e = v + "-" + w;
		if (marked[w] == GRAY)
			return true;
		else if (marked[w] == WHITE)
		{
			bool res = containsCycle(g, w);
			if (res)
				return res;
		}
	}
	marked[v] = BLACK;
	return false;
}
