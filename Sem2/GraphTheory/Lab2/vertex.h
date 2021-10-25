//
// Created by Dan on 4/12/2020.
//
#pragma once
#ifndef LAB1CLION_VERTEX_H
#define LAB1CLION_VERTEX_H
#include <vector>
#include <exception>

typedef int vertex_id;
class Vertex {
private:
    int index;
public:
    int getIndex() const;

private:
    std::vector<Vertex *> inbound_neighbours;
    std::vector<Vertex *> outbound_neighbours;
public:
    explicit Vertex(int);
    void add_inbound(Vertex *);
    void delete_inbound(int);
    void add_outbound(Vertex *);
    void delete_outbound(int);
    size_t get_inbound_grade();
    size_t get_outbound_grade();
    std::vector<int> get_inbound();
    std::vector<int> get_outbound();
    bool operator==(const Vertex &rhs) const;
    bool operator!=(const Vertex &rhs) const;
};


#endif //LAB1CLION_VERTEX_H
