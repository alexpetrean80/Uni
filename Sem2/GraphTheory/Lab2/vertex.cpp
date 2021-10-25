#include "vertex.h"

void Vertex::add_inbound(Vertex *v) {
    inbound_neighbours.push_back(v);
}

void Vertex::delete_inbound(int v) {
    for (auto i = 0; i < inbound_neighbours.size(); i++) {
        if (inbound_neighbours.at(i)->getIndex() == v) {
            inbound_neighbours.erase(inbound_neighbours.begin() + i);
            return;
        }
    }
    throw std::exception();
}

void Vertex::add_outbound(Vertex *v) {
    outbound_neighbours.push_back(v);
}

void Vertex::delete_outbound(int v) {
    for(auto i = 0; i < outbound_neighbours.size(); i++) {
        if (inbound_neighbours.at(i)->getIndex() == v) {
            inbound_neighbours.erase(inbound_neighbours.begin() + i);
            return;
        }
    }
    throw std::exception();
}

bool Vertex::operator==(const Vertex &rhs) const {
    return inbound_neighbours == rhs.inbound_neighbours &&
           outbound_neighbours == rhs.outbound_neighbours;
}

bool Vertex::operator!=(const Vertex &rhs) const {
    return !(rhs == *this);
}

size_t Vertex::get_inbound_grade() {
    return inbound_neighbours.size();
}

size_t Vertex::get_outbound_grade() {
    return outbound_neighbours.size();
}

Vertex::Vertex(int v) {
    index = v;
}

int Vertex::getIndex() const {
    return index;
}

std::vector<int> Vertex::get_inbound() {
    std::vector<int> v;
    for (auto ver : inbound_neighbours) {
        v.push_back(ver->getIndex());
    }
    return v;
}

std::vector<int> Vertex::get_outbound() {
    std::vector<int> v;
    for (auto ver : outbound_neighbours) {
        v.push_back(ver->getIndex());
    }
    return v;
}



