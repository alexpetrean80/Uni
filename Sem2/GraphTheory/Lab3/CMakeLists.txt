cmake_minimum_required(VERSION 3.16)
project(Lab3)

set(CMAKE_CXX_STANDARD 14)

add_executable(Lab3 main.cpp directed_graph.cpp directed_graph.h edge.h edge.cpp vertex.cpp vertex.h)