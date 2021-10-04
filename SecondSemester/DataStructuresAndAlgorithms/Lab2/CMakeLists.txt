cmake_minimum_required(VERSION 3.16)
project(Lab2)

set(CMAKE_CXX_STANDARD 14)

add_executable(Lab2 App.cpp ExtendedTest.h ExtendedTest.cpp ShortTest.h ShortTest.cpp SMMIterator.h SMMIterator.cpp
        SortedMultiMap.h SortedMultiMap.cpp)