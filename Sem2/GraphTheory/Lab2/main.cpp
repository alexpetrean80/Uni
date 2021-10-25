#include "directed_graph.h"
#include <iostream>
#include <queue>
#include <vector>

void BFS(int first, int last, DirectedGraph &graph) {
    std::queue<std::pair<int, std::vector<int>>> nodes;
    std::vector<bool> visited;
    std::vector<int> inbound;
    visited.reserve(graph.get_number_of_vertices());
    for (auto i = 0; i < graph.get_number_of_vertices(); i++) {
        visited.push_back(false);
    }
    auto node = std::make_pair(last, std::vector<int>{});
    visited[node.first] = true;
    nodes.push(node);
    while (!nodes.empty()) {
            for (auto n : nodes.front().second) {
                std::cout << n << ' ';
            }
            std::cout << nodes.front().first;
            return;
        }
        inbound = graph.get_all_inbound_neighbours(nodes.front().first);
        for (auto neighbour : inbound) {
            if (!visited[neighbour]) {
                node = std::make_pair(neighbour, nodes.front().second);
                node.second.push_back(nodes.front().first);
                nodes.push(node);
            }
        }
        nodes.pop();
    }
}

int main() {
    int first, last;
    auto graph = new DirectedGraph();
    graph->read_from_file("graph10k.txt");
    std::cin >> first >> last;
    BFS(first, last, *graph);
    return 0;
}
