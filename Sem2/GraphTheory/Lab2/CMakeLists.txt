cmake_minimum_required(VERSION 3.16)
project(Lab2)

set(CMAKE_CXX_STANDARD 14)

add_executable(Lab2 main.cpp directed_graph.h directed_graph.cpp edge.h edge.cpp vertex.h vertex.cpp)