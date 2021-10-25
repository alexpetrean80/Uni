/* 6. Write a program that, given a graph with costs and two vertices,
 * finds a lowest cost walk between the given vertices,
 * or prints a message if there are negative cost cycles in the graph.
 * The program shall use the matrix multiplication algorithm.*/


#include <iostream>
#include "directed_graph.h"

void matrix_multiplication(DirectedGraph &graph, int v1, int v2) {

}

int main() {
    auto graph = std::make_unique<DirectedGraph>();
    graph->read_from_file("graph.txt");
    int v1, v2;
    std::cin >> v1 >> v2;
    matrix_multiplication(*graph, v1, v2);
    return 0;
}
