#include "edgeweighteddigraph.h"

#include <fstream>
#include <iostream>
#include <set>
#include <sstream>

using namespace std;

EdgeWeightedDigraph::EdgeWeightedDigraph(string filename) {
  ifstream file;
  file.open(filename);
  string line, v, w;
  float weight;
  if (file.is_open()) {
    while (getline(file, line)) {
      stringstream ss(line);
      getline(ss, v, ' ');
      getline(ss, w, ' ');
      ss >> weight;
      addEdge(v, w, weight);
    }
    file.close();
  }
}

void EdgeWeightedDigraph::addEdge(string v, string w, float weight) {
  Edge e(v, w, weight);
  addToList(v, e);
  vertices.insert(v);
  vertices.insert(w);
}

string EdgeWeightedDigraph::toDot() {
  const string NEWLINE = "\n";
  ostringstream ss;
  ss << "digraph {" << NEWLINE;
  ss << "rankdir = LR;" << NEWLINE;
  ss << "node [shape = circle];" << NEWLINE;
  for (auto const& v : getVerts()) {
    for (auto const& e : getAdj(v)) {
      ss << e.v << " -- " << e.w << " [label=\"" << e.weight << "\"]"
        << NEWLINE;
    }
  }
  ss << "}" << NEWLINE;
  return ss.str();
}
