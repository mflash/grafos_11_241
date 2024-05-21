#include "dfs.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <set>

using namespace std;

DepthFirstSearch::DepthFirstSearch(Graph &g, string s)
{
	this->s = s;
	dfs(g, s);
}

void DepthFirstSearch::dfs(Graph &g, string v)
{
	// cout << "em " << v << endl;
	marked.insert(v); // marca v como visitado
	for (auto const &w : g.getAdj(v))
	{
		if (marked.find(w) == marked.end())
		{
			edgeTo[w] = v;
			dfs(g, w);
		}
	}
}

bool DepthFirstSearch::hasPathTo(string s)
{
	return marked.find(s) != marked.end();
}

vector<string> DepthFirstSearch::pathTo(string v)
{
	vector<string> path;
	while (v != s)
	{
		path.insert(path.begin(), v);
		v = edgeTo[v];
	}
	path.insert(path.begin(), s);
	return path;
}
