//
// Created by Dan on 4/12/2020.
//
#pragma once
#ifndef LAB1CLION_DIRECTED_GRAPH_H
#define LAB1CLION_DIRECTED_GRAPH_H
#include <vector>
#include <string>
#include <random>
#include <fstream>
#include "vertex.h"
#include "edge.h"

typedef int vertex_id ;

class DirectedGraph {
private:
    std::vector<Vertex *> vertices;
    std::vector<Edge *> edges;
public:
    DirectedGraph();
    ~DirectedGraph();
    DirectedGraph(DirectedGraph const &);
    size_t get_number_of_vertices();
    size_t get_number_of_edges();
    bool is_edge(int, int);
    void add_vertex();
    void delete_vertex(int);
    void add_edge(int, int, int cost = 0);
    void delete_edge(int, int);
    int get_inbound(int, int);
    int get_outbound(int, int);
    int get_cost(int, int);
    void set_cost(int, int, int);
    size_t get_in_degree(int);
    size_t get_out_degree(int);
    void read_from_file(const std::string&);
    void write_to_file(const std::string&);
    std::vector<int> get_all_vertices();
    std::vector<int> get_all_inbound_neighbours(int);
    std::vector<int> get_all_outbound_neighbours(int);
};

DirectedGraph *generate_random_graph(int v, int e);
#endif //LAB1CLION_DIRECTED_GRAPH_H
