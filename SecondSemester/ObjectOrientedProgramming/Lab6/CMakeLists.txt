cmake_minimum_required(VERSION 3.16)
project(Lab6)

set(CMAKE_CXX_STANDARD 14)

add_executable(Lab6 main.cpp guardian_statue.cpp guardian_statue.h dynamic_vector.h repository.cpp repository.h service.cpp service.h ui.cpp ui.h tests.cpp tests.h)

set(CMAKE_CXX_FLAGS --coverage)
set(CMAKE_EXE_LINKER_FLAGS "")