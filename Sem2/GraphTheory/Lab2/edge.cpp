//
// Created by Dan on 4/14/2020.
//

#include "edge.h"

Vertex *Edge::getV1() const {
    return v1;
}

void Edge::setV1(Vertex *v_1) {
    v1 = v_1;
}

Vertex *Edge::getV2() const {
    return v2;
}

void Edge::setV2(Vertex *v_2) {
    v2 = v_2;
}

int Edge::getCost() const {
    return cost;
}

void Edge::setCost(int new_cost) {
    Edge::cost = new_cost;
}

bool Edge::operator==(const Edge &rhs) const {
    return v1 == rhs.v1 &&
           v2 == rhs.v2 &&
           cost == rhs.cost;
}

bool Edge::operator!=(const Edge &rhs) const {
    return !(rhs == *this);
}

Edge::Edge(Vertex *p_vertex, Vertex *p_vertex_1, int i) {
    v1 = p_vertex;
    v2 = p_vertex_1;
    cost = i;
}
