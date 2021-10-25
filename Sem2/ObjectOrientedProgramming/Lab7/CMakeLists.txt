cmake_minimum_required(VERSION 3.16)
project(Lab7)

set(CMAKE_CXX_STANDARD 14)

add_executable(Lab7 main.cpp domain/guardian_statue.cpp domain/guardian_statue.h tests/tests_domain.cpp tests/tests_domain.h tests/tests.h tests/tests.cpp domain/Exception.cpp domain/Exception.h repository/file_repository.cpp repository/file_repository.h tests/tests_file_repository.cpp tests/tests_file_repository.h service/service.cpp service/service.h tests/tests_service.cpp tests/tests_service.h ui/ui.cpp ui/ui.h)

set(CMAKE_CXX_FLAGS --coverage)

