//
// Created by Dan on 4/12/2020.
//
#include "directed_graph.h"

DirectedGraph::DirectedGraph() {
    vertices = {};
    edges = {};
}

size_t DirectedGraph::get_number_of_vertices() {
    return vertices.size();
}

size_t DirectedGraph::get_number_of_edges() {
    return edges.size();
}

bool DirectedGraph::is_edge(int v1, int v2) {
    for (auto edge : edges) {
        if (edge->getV1()->getIndex() ==v1 && edge->getV2()->getIndex() == v2) {
            return true;
        }
    }
    return false;
}

void DirectedGraph::add_vertex() {
    vertices.push_back(new Vertex(vertices.size()));
}

void DirectedGraph::delete_vertex(int v) {
    vertices.erase(vertices.begin() + v);
    for (auto & edge : edges) {
        if (edge->getV1()->getIndex() == v) {
            edge->getV2()->delete_inbound(v);
        } else if (edge->getV1()->getIndex() == v) {
            edge->getV2()->delete_outbound(v);
        }
    }
    delete vertices.at(v);
    vertices.at(v) = nullptr;
}

void DirectedGraph::add_edge(int v1, int v2, int cost) {
    for (auto edge : edges) {
        if (edge->getV1()->getIndex() == v1 && edge->getV2()->getIndex() == v2) {
            if (edge->getCost() > cost) {
                edge->setCost(cost);
                return;
            }
            else {
                throw std::exception("Edge exists");
            }
        }
    }
    Edge *edge = new Edge(vertices.at(v1), vertices.at(v2), cost);
    vertices.at(v2)->add_inbound(vertices.at(v1));
    vertices.at(v1)->add_outbound(vertices.at(v2));
    edges.push_back(edge);
}

void DirectedGraph::delete_edge(int v1, int v2) {
        for (auto i = 0; i < edges.size(); i++) {
            if (edges.at(i)->getV1()->getIndex() == v1 && edges.at(i)->getV2()->getIndex() == v2) {
                edges.erase(edges.begin() + i);
                vertices.at(v1)->delete_outbound(v2);
                vertices.at(v2)->delete_inbound(v1);
                delete edges.at(i);
                return;
            }
        }
    throw std::exception("Edge does not exist");
}

size_t DirectedGraph::get_in_degree(int v) {
    return vertices.at(v)->get_inbound_grade();
}

size_t DirectedGraph::get_out_degree(int v) {
    return vertices.at(v)->get_outbound_grade();
}

DirectedGraph::DirectedGraph(const DirectedGraph &graph) {
    *this = graph;
}

void DirectedGraph::read_from_file(const std::string& file_name) {
    std::ifstream in(file_name);
    int v, e;
    in >> v >> e;
//    if (v * (v - 1) > e) {
//        throw std::exception("Too many edges");
//    }
    for (auto i = 0; i < v; i++) {
        add_vertex();
    }
    int v1, v2, cost;
    for (int i = 0; i < e; i++) {
        in >> v1 >> v2 >> cost;
        add_edge(v1, v2, cost);
    }
    in.close();
}

void DirectedGraph::write_to_file(const std::string& file_name) {
    std::ofstream out;
    out.open(file_name, std::ofstream::out | std::ofstream::trunc);
    out << vertices.size() << ' ' << edges.size() << '\n';
    for (auto edge : edges) {
        out << edge->getV1()->getIndex() << ' ' << edge->getV2()->getIndex() << ' ' << edge->getCost() << '\n';
    }
    out.close();
}

DirectedGraph::~DirectedGraph() {
    for (auto vertex : vertices) {
        delete vertex;
    }
    for (auto edge : edges) {
        delete edge;
    }
}

int DirectedGraph::get_inbound(int v1, int v2) {
    for (auto edge : edges) {
        if (edge->getV1()->getIndex() == v1 && edge->getV2()->getIndex() == v2) {
            return edge->getV1()->getIndex();
        }
    }
    throw std::exception("Edge does not exist");
}

int DirectedGraph::get_outbound(int v1, int v2) {
    for (auto edge : edges) {
        if (edge->getV1()->getIndex() == v1 && edge->getV2()->getIndex() == v2) {
            return edge->getV1()->getIndex();
        }
    }
    throw std::exception("Edge does not exist");
}

int DirectedGraph::get_cost(int v1, int v2) {
    for (auto edge : edges) {
        if (edge->getV1()->getIndex() == v1 && edge->getV2()->getIndex() == v2) {
            return edge->getCost();
        }
    }
    throw std::exception("Edge does not exist");
}

void DirectedGraph::set_cost(int v1, int v2, int cost) {
    for (auto edge : edges) {
        if (edge->getV1()->getIndex() == v1 && edge->getV2()->getIndex() == v2) {
            edge->setCost(cost);
        }
    }
    throw std::exception("Edge does not exist");
}

std::vector<int> DirectedGraph::get_all_vertices() {
    std::vector<int> v;
    v.reserve(vertices.size());
for (auto i = 0; i < vertices.size(); i++) {
        v.push_back(i);
    }
    return v;
}

std::vector<int> DirectedGraph::get_all_inbound_neighbours(int v) {
   return vertices.at(v)->get_inbound();
}

std::vector<int> DirectedGraph::get_all_outbound_neighbours(int v) {
    return vertices.at(v)->get_outbound();
}

DirectedGraph *generate_random_graph(int v, int e) {
    auto *graph = new DirectedGraph();
    for (auto i = 0; i < v; i++) {
        graph->add_vertex();
    }
    int v1, v2, cost;
    while (e > 0) {
        v1 = rand() % v;
        v2 = rand() % v;
        cost = rand()% 50;
        if (!graph->is_edge(v1, v2)) {
            graph->add_edge(v1, v2, cost);
            e--;
        }
    }
    return graph;
}
