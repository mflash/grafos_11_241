#include "bfs.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <queue>

using namespace std;

BreadthFirstSearch::BreadthFirstSearch(Graph &g, string s)
{
	this->s = s;
	bfs(g, s);
}

void BreadthFirstSearch::bfs(Graph &g, string v)
{
	queue<string> fila;
	fila.push(v);
	marked.insert(v);
	_distTo[v] = 0;
	while (!fila.empty())
	{
		string x = fila.front();
		fila.pop();
		int dist = _distTo[x];
		// System.out.println("Visitando " + x);
		for (auto const &w : g.getAdj(x))
		{
			if (marked.find(w) == marked.end())
			{
				edgeTo[w] = x;
				_distTo[w] = dist + 1;
				// System.out.println(" >> adicionando " + w);
				fila.push(w);
				marked.insert(w);
			}
		}
	}
}

bool BreadthFirstSearch::hasPathTo(string s)
{
	return marked.find(s) != marked.end();
}

int BreadthFirstSearch::distTo(string s)
{
	if (hasPathTo(s))
		return _distTo[s];
	return -1;
}

vector<string> BreadthFirstSearch::pathTo(string v)
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
