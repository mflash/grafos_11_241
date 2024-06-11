#include <map>
#include <string>

#ifndef UF_H
#define UF_H

class UnionFind
{
public:
    std::string find(std::string v);
    void uni(std::string v, std::string w);

private:
    std::map<std::string, std::string> uf;
};

#endif
