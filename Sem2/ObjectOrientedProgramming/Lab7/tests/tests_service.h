#ifndef LAB7_TESTS_SERVICE_H
#define LAB7_TESTS_SERVICE_H

#include "../service/service.h"
#include <cassert>

void test__add_guardian_statue__mode_A__guardian_statue_is_added();
void test__add_guardian_statue__mode_B__exception_is_thrown();
void test__delete_guardian_statue__mode_A__guardian_statue_is_deleted();
void test__delete_guardian_statue__mode_B__exception_is_thrown();
void test__update_guardian_statue__mode_A__guardian_statue_is_updated();
void test__update_guardian_statue__mode_B__exception_is_thrown();
void test__get_mode__valid_mode__mode_is_returned();
void test__get_file__valid_file__file_is_returned();
void test__set_file__valid_file__file_is_set();
void test__save__mode_B__guardian_statue_is_saved();
void test__save__mode_A__exception_is_thrown();
void test__next__mode_B__guardian_statue_is_returned();
void test__next__mode_A__exception_is_thrown();
void test__get_my_list__mode_A__exception_is_thrown();

#endif//LAB7_TESTS_SERVICE_H
