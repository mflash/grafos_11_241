#include <string>

#include "edgeweightedgraph.h"

#ifndef EWDGRAPH_H
#define EWDGRAPH_H

class EdgeWeightedDigraph: public EdgeWeightedGraph {
 public:
  EdgeWeightedDigraph();
  EdgeWeightedDigraph(std::string filename);

  void addEdge(std::string v, std::string w, float weight);
  std::string toDot();
};

#endif
