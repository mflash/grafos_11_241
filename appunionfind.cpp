#include <iostream>
#include "unionfind.h"

using namespace std;

int main()
{
    UnionFind uf;
    cout << "find 1: " << uf.find("1") << endl;
    cout << "find 3: " << uf.find("3") << endl;
    cout << "find 2: " << uf.find("2") << endl;
    cout << endl;
    uf.uni("1", "3");

    cout << "find 1: " << uf.find("1") << endl;
    cout << "find 2: " << uf.find("2") << endl;
    cout << "find 3: " << uf.find("3") << endl;
    cout << endl;
    uf.uni("1", "2");

    cout << "find 1: " << uf.find("1") << endl;
    cout << "find 2: " << uf.find("2") << endl;
    cout << "find 3: " << uf.find("3") << endl;
    cout << endl;
}