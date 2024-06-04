#include "undirectedcycle.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <set>

using namespace std;

UndirectedCycle::UndirectedCycle(Graph &g)
{
	hasCycle = false;
	for (auto const &w : g.getVerts())
	{
		if (marked.find(w) == marked.end())
		{
			hasCycle = containsCycle(g, w);
			if (hasCycle)
				break;
		}
	}
}

bool UndirectedCycle::containsCycle()
{
	return hasCycle;
}

bool UndirectedCycle::containsCycle(Graph &g, string v)
{
	cout << "Estou em " << v << endl;
	marked.insert(v);
	for (auto const &w : g.getAdj(v))
	{
		string e = v + "-" + w;
		if (marked.find(w) == marked.end())
		{
			edges.insert(e);
			bool res = containsCycle(g, w);
			if (res)
				return res;
		}
		else if (edges.find(w + "-" + v) == edges.end())
			return true;
	}
	return false;
}
