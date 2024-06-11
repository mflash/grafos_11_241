#include <string>
#include <sstream>

#ifndef EWGRAPH_EDGE_H
#define EWGRAPH_EDGE_H

struct Edge
{
  std::string v;
  std::string w;
  float weight;
  Edge(std::string v, std::string w, float weight) {
    this->v = v;
    this->w = w;
    this->weight = weight;
  }

  friend std::ostream& operator<<(std::ostream& os, const Edge& obj) {
    return os << obj.v << "-" << obj.w << " (" << obj.weight << ")";
  }
};

#endif

