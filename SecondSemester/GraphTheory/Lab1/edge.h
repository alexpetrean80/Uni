//
// Created by Dan on 4/14/2020.
//

#ifndef LAB1CLION_EDGE_H
#define LAB1CLION_EDGE_H


#include "vertex.h"

class Edge {
private:
    Vertex *v1, *v2;
    int cost;
public:
    int getCost() const;
    void setCost(int new_cost);
    Edge() = default;
    Vertex *getV2() const;
    void setV2(Vertex *v_2);
    Vertex *getV1() const;
    void setV1(Vertex *v_1);
    bool operator==(const Edge &rhs) const;
    bool operator!=(const Edge &rhs) const;

    Edge(Vertex *p_vertex, Vertex *p_vertex_1, int i);
};


#endif //LAB1CLION_EDGE_H
