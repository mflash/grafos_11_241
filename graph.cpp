#include "graph.h"

#include <fstream>
#include <iostream>
#include <set>
#include <sstream>

using namespace std;

Graph::Graph() {}

Graph::Graph(string filename) {
  ifstream file;
  file.open(filename);
  string line, v, w;
  if (file.is_open()) {
    while (getline(file, line)) {
      stringstream ss(line);
      getline(ss, v, ' ');
      getline(ss, w, ' ');
      addEdge(v, w);
    }
    file.close();
  }
}

vector<string> Graph::getAdj(string v) { return graph[v]; }

set<string> Graph::getVerts() { return vertices; }

void Graph::addEdge(string v, string w) {
  addToList(v, w);
  addToList(w, v);
  vertices.insert(v);
  vertices.insert(w);
}

string Graph::toDot() {
  // Usa um conjunto de arestas para evitar duplicatas
  set<string> edges;
  const string NEWLINE = "\n";
  ostringstream sb;
  sb << "graph {" << NEWLINE;
  sb << "rankdir = LR;" << NEWLINE;
  sb << "node [shape = circle];" << NEWLINE;
  for (auto const& v : getVerts()) {
    for (auto const& w : getAdj(v)) {
      string edge = v < w ? v + w : w + v;
      if (edges.find(edge) == edges.end()) {
        sb << v << " -- " << w << NEWLINE;
        edges.insert(edge);
      }
    }
  }
  sb << "}" << NEWLINE;
  return sb.str();
}

void Graph::addToList(string v, string w) {
  vector<string>& list = graph[v];
  list.push_back(w);
}
