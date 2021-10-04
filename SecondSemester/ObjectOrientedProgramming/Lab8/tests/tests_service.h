#ifndef LAB7_TESTS_SERVICE_H
#define LAB7_TESTS_SERVICE_H

#include "../service/service.h"
#include <cassert>

void test__set_mode__valid_mode__mode_is_set();
void test__set_repo_file_name__valid_file_name__file_name_is_set();
void test__set_my_list_file_name__valid_file_name__file_name_is_set();
void test__add_guardian_statue__mode_A__guardian_statue_added();
void test__add_guardian_statue__mode_B__exception_is_thrown();
void test__delete_guardian_statue__mode_A__guardian_statue_deleted();
void test__delete_guardian_statue__mode_B__exception_is_thrown();
void test__update_guardian_statue__mode_A__guardian_statue_updated();
void test__update_guardian_statue__mode_B__exception_is_thrown();
void test__next__mode_B__guardian_statue_returned();
void test__next__mode_A__exception_is_thrown();
void test__save__mode_B__guardian_statue_saved();
void test__save__mode_A__exception_is_thrown();

#endif//LAB7_TESTS_SERVICE_H
