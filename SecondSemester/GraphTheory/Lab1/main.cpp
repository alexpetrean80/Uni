#include <iostream>
#include "directed_graph.h"

void menu() {
    auto *graph = new DirectedGraph();

    int command;
    while (true) {
        std::cout << "Menu\n "
                                "0. Exit\n"
                                "1. Generate random graph\n"
                                "2. Read graph from file\n"
                                "3. Write graph to file\n"
                                "4. Add vertex\n"
                                "5. Delete vertex\n"
                                "6. Add edge\n"
                                "7. Delete edge\n"
                                "8. Get number of vertices\n"
                                "9. Get number of edges\n"
                                "10. Print all vertices\n"
                                "11. Get inbound neighbours of vertex\n"
                                "12. Get outbound neighbours of vertex\n"
                                "13. Get in degree of vertex\n"
                                "14. Get out degree of vertex\n"
                                "Enter command: ";
    std::cin >> command;
    try {
    switch(command) {
        case 1: {
            int v, e;
            std::cin >> v >> e;
            graph = generate_random_graph(v, e);
        }
        case 2: {
            std::string file;
            std::cin >> file;
            graph->read_from_file(file);
        }
        case 3: {
            std::string file;
            std::cin >> file;
            graph->write_to_file(file);
        }
        case 4: {
            graph->add_vertex();
        }
        case 5: {
            int v;
            std::cin >> v;
            graph->delete_vertex(v);
        }
        case 6: {
            int v1, v2, cost;
            std::cin >> v1 >> v2 >> cost;
            graph->add_edge(v1, v2, cost);
        }
        case 7: {
            int v1, v2;
            std::cin >> v1 >> v2;
            graph->delete_edge(v1, v2);
        }
        case 8: {
            int nr = graph->get_number_of_vertices();
            std::cout << nr;
        }
        case 9: {
            int nr = graph->get_number_of_edges();
            std::cout << nr;
        }
        case 10: {
            std::vector<int> vertices = graph->get_all_vertices();
            for (auto &&vertex_id : vertices) {
                std::cout << vertex_id << " ";
            }
            std::cout << '\n';
        }
        case 11: {
            int v;
            std::cin >> v;
            std::vector<int> n = graph->get_all_inbound_neighbours(v);
            for (auto ne : n) {
                std::cout << ne << ' ';
            }
        }
        case 12: {
            int v;
            std::cin >> v;
            std::vector<int> n = graph->get_all_outbound_neighbours(v);
            for (auto ne : n) {
                std::cout << ne << ' ';
            }
        }
        case 13: {
            int v;
            std::cin >> v;
            std::cout << graph->get_in_degree(v);
        }
        case 14: {
            int v;
            std::cin >> v;
            std::cout << graph->get_out_degree(v);
        }
        default: {
            delete graph;
            return;
        }
    }}
    catch (std::exception &e) {
        std:: cout << e.what();
    }
    }
}
int main() {
    auto *graph1 = new DirectedGraph();
    graph1->read_from_file("input.txt");
    graph1->write_to_file("output.txt");
    std::cout << graph1->get_out_degree(4)
    delete graph1;
    auto *graph2 = new DirectedGraph();
    graph2->read_from_file("input1k.txt");
    graph2->write_to_file("output1k.txt");
    delete graph2;
    auto *graph = generate_random_graph(150, 200);;
    graph->write_to_file("output_random.txt");
    delete graph;
  //  menu();
    return 0;
}
