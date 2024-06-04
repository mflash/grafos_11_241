#include <iostream>
#include "digraph.h"
#include "topological.h"

using namespace std;

int main()
{
  Digraph g("tinydag.txt");
  Topological topo(g);

  for (auto const &v : topo.getTopological())
    cout << v << " ";
  cout << endl;
}
