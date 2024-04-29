#include "digraph.h"

#include <fstream>
#include <iostream>
#include <set>
#include <sstream>

using namespace std;

Digraph::Digraph(string filename) {
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

void Digraph::addEdge(string v, string w) {
  addToList(v, w);
  vertices.insert(v);
  vertices.insert(w);
}

string Digraph::toDot() {
  const string NEWLINE = "\n";
  ostringstream sb;
  sb << "digraph {" << NEWLINE;
  sb << "rankdir = LR;" << NEWLINE;
  sb << "node [shape = circle];" << NEWLINE;
  for (auto const &v : getVerts()) {
    for (auto const &w : getAdj(v)) sb << v << " -> " << w << NEWLINE;
  }
  sb << "}" << NEWLINE;
  return sb.str();
}
