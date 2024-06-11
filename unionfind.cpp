#include "unionfind.h"
#include <iostream>

using namespace std;

string UnionFind::find(string v)
{
    map<string, string>::iterator it = uf.find(v);
    while (it != uf.end())
    {
        v = it->second;
        it = uf.find(v);
    }
    return v;
}

void UnionFind::uni(string v, string w)
{
    string s = find(v);
    string t = find(w);
    uf[t] = s;
}
