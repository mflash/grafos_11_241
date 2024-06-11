#include <iostream>

#include "edgeweightedgraph.h"

using namespace std;

int main() {
  EdgeWeightedGraph g("tinyEWG.txt");

  for (auto const& v : g.getVerts()) {
    cout << v << ": ";
    for (auto const& w : g.getAdj(v)) cout << w << " ";
    cout << endl;
  }

  cout << endl;
  cout << g.toDot();
}
