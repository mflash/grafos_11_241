#include "topological.h"
#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

Topological::Topological(Graph &g)
{
	for (auto const &v : g.getVerts()) {
    if (marked.find(v) == marked.end())
      dfs(g, v);
  }
}

void Topological::dfs(Graph &g, string v)
{
	marked.insert(v); // marca v como visitado
	for (auto const &w : g.getAdj(v))
	{
		if (marked.find(w) == marked.end())
			dfs(g, w);
	}
  topo.push_front(v);
}

const deque<string>& Topological::getTopological()
{
  return topo;
}
