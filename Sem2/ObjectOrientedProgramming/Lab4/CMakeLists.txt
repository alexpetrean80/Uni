cmake_minimum_required(VERSION 3.16)
project(Lab4 C)

set(CMAKE_C_STANDARD 99)
add_executable(Lab4 main.c item.h item.c item_list.h item_list.c repo.h repo.c service.h service.c ui.h ui.c tests.h tests.c)