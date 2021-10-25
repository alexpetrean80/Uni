cmake_minimum_required(VERSION 3.16)
project(Lab8)

set(CMAKE_CXX_STANDARD 14)

add_executable(Lab8 main.cpp
        domain/guardian_statue_validator.cpp
        domain/guardian_statue_validator.h
        domain/exceptions.cpp
        domain/exceptions.h
        domain/guardian_statue.h
        domain/guardian_statue.cpp
        repository/repository.cpp
        repository/repository.h
        tests/tests.cpp
        tests/tests.h
        tests/tests_domain.cpp
        tests/tests_domain.h
        tests/tests_repository.cpp
        tests/tests_repository.h
        tests/tests_service.cpp
        tests/tests_service.h
        service/service.cpp
        service/service.h
        ui/ui.cpp
        ui/ui.h)

