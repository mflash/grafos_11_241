#include <map>
#include <set>
#include <string>
#include <vector>

#ifndef GRAPH_H
#define GRAPH_H

class Graph {
 public:
  Graph();
  Graph(std::string filename);

  std::vector<std::string> getAdj(std::string v);
  std::set<std::string> getVerts();

  void addEdge(std::string v, std::string w);

  std::string toDot();

 protected:
  void addToList(std::string v, std::string w);
  std::set<std::string> vertices;

 private:
  std::map<std::string, std::vector<std::string>> graph;
};

#endif
